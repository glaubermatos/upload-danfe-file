import { createContext, ReactNode, useContext, useEffect, useState } from "react";
import { api } from "../http/api";
import { Duplicates, Invoice } from "../types";

interface InvoiceContextData {
    invoices: Invoice[]; 
    uploadInvoice: (xmlInvoice: File) => void;
    deleteInvoice: (numberInvoice: number) => Promise<void>;
    showDuplicates: (numberInvoice: number) => Promise<void>;
}

interface InvoiceProviderProps {
    children: ReactNode;
}

const InvoicesContext = createContext<InvoiceContextData>({} as InvoiceContextData);

export function InvoiceProvider({children}: InvoiceProviderProps) {
    const [invoices, setInvoices] = useState<Invoice[]>([])
    const [duplicates, setDuplicates] = useState<Duplicates[]>([])
    const [invoiceUploadedSuccessfully, setInvoiceUploadedSuccessfully] = useState<boolean>()

    useEffect(() => {
        api.get<Invoice[]>(`/notas-fiscais`)
            .then(response => setInvoices(response.data))
            
    }, [])

    function uploadInvoice (xmlInvoice: File) {
        const data = new FormData()
        data.append('xmlNotaFiscal', xmlInvoice);

        api.post(`/notas-fiscais`, data, {headers: {
            "Content-Type": "multipart/form-data",
            "Accept": "text/plain;charset=UTF-8"
        }}).then(response => {

            api.get<Invoice[]>(`/notas-fiscais`)
            .then(response => setInvoices(response.data))
        })

        
    }

    async function deleteInvoice(numberInvoice: number) {
        await api.delete(`/notas-fiscais/${numberInvoice}`)

        let invoicesUpdate = [...invoices]
        invoicesUpdate = invoicesUpdate.filter(invoice => invoice.numero !== numberInvoice)

        setInvoices(invoicesUpdate)
    }

    async function showDuplicates(numberInvoice: number) {
        const response = await api.get<Duplicates[]>(`/notas-fiscais/${numberInvoice}/duplicatas`)

        setDuplicates(response.data)
    }

    return(
        <InvoicesContext.Provider value={{
            invoices,
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