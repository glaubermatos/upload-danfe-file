import { createContext, ReactNode, useContext, useEffect, useState } from "react";
import { api } from "../http/api";
import { ApiError, Duplicate, Invoice } from "../types";

interface InvoiceContextData {
    invoices: Invoice[]; 
    duplicates: Duplicate[];
    uploadInvoice: (xmlInvoice: File) => Promise<string>;
    deleteInvoice: (numberInvoice: number) => Promise<void>;
    showDuplicates: (numberInvoice: number) => Promise<void>;
}

interface InvoiceProviderProps {
    children: ReactNode;
}

const InvoicesContext = createContext<InvoiceContextData>({} as InvoiceContextData);

export function InvoiceProvider({children}: InvoiceProviderProps) {
    const [invoices, setInvoices] = useState<Invoice[]>([])
    const [duplicates, setDuplicates] = useState<Duplicate[]>([])

    useEffect(() => {
        async function fetchData() {
            await loadInvoices();
        }

        fetchData()
    }, [])

    async function loadInvoices() {
        const response = await api.get<Invoice[]>(`/notas-fiscais`)

        setInvoices(response.data)
    }

    async function uploadInvoice (xmlInvoice: File) {
        const data = new FormData()
        data.append('xmlNotaFiscal', xmlInvoice);

        const response = await api.post(`/notas-fiscais`, data, {headers: {
            "Content-Type": "multipart/form-data",
        }})

        console.log(response.data)

        await loadInvoices()
        return response.data
    }

    async function deleteInvoice(numberInvoice: number) {
        await api.delete(`/notas-fiscais/${numberInvoice}`)

        let invoicesUpdate = [...invoices]
        invoicesUpdate = invoicesUpdate.filter(invoice => invoice.numero !== numberInvoice)

        setInvoices(invoicesUpdate)
    }

    async function showDuplicates(numberInvoice: number) {
        const response = await api.get<Duplicate[]>(`/notas-fiscais/${numberInvoice}/duplicatas`)

        setDuplicates(response.data)
    }

    return(
        <InvoicesContext.Provider value={{
            invoices,
            duplicates,
            uploadInvoice,
            deleteInvoice,
            showDuplicates
        }}>
            {children}
        </InvoicesContext.Provider>
    )
}

export function useInvoices() {
    const context = useContext(InvoicesContext)

    return context
}