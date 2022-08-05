import { createContext, ReactNode, useContext, useEffect, useState } from "react";
import { api } from "../http/api";
import { Duplicate, Invoice } from "../types";

interface InvoiceContextData {
    invoices: Invoice[]; 
    uploadInvoice: (xmlInvoice: File) => Promise<string>;
    deleteInvoice: (numberInvoice: number) => Promise<void>;
    getDuplicates: (numberInvoice: number) => Promise<Duplicate[]>;
}

interface InvoiceProviderProps {
    children: ReactNode;
}

const InvoicesContext = createContext<InvoiceContextData>({} as InvoiceContextData);

export function InvoiceProvider({children}: InvoiceProviderProps) {
    const [invoices, setInvoices] = useState<Invoice[]>([])

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

    async function getDuplicates(numberInvoice: number) {
        const response = await api.get<Duplicate[]>(`/notas-fiscais/${numberInvoice}/duplicatas`)

        return response.data
    }

    return(
        <InvoicesContext.Provider value={{
            invoices,
            uploadInvoice,
            deleteInvoice,
            getDuplicates
        }}>
            {children}
        </InvoicesContext.Provider>
    )
}

export function useInvoices() {
    const context = useContext(InvoicesContext)

    return context
}