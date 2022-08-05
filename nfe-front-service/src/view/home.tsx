import { useRef } from "react";
import { InvoiceTable } from "../components/InvoiceTable";
import { InvoiceUpload } from "../components/InvoiceUpload";
import { useInvoices } from "../hooks/useInvoices";

import { Messages } from 'primereact/messages';

import "primereact/resources/themes/lara-light-blue/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";

export function Home() {
    const { uploadInvoice, deleteInvoice } = useInvoices()
    const messages = useRef<Messages>(null);

    async function handleUploadFile(file: File) {
        try {
            const response = await uploadInvoice(file)
            showSuccessMessage(response)
        } catch (err: any) {
            showError(err)
        }
    }

    async function handleRemoveInvoice(numberInvoice: number) {
        try {
            deleteInvoice(numberInvoice)
            showSuccessMessage(`Nota Fiscal Nº ${numberInvoice} removida.`)
        } catch (err: any) {
            showError(err)
        }
    }

    function showSuccessMessage(response: string) {
        if (null !== messages.current) {
            messages.current.replace({sticky: true, life: 10000, severity: 'success', summary: '', detail: response });
        }
    }

    function showError(err: any) {
        const fieldErro = err.response.data.fields?.map((field: {name: string, userMessage: string}) => field.userMessage)
        if (null !== messages.current) {
            messages.current.replace({sticky: true, life: 10000, severity: 'error', summary: err.response.data.userMessage, detail: fieldErro });
        }
    }

    return(
        <>
            <header>
                <div>
                    <h2>NFe upload service</h2>
                </div>
            </header>
            <main>                
                <Messages ref={messages}></Messages>
                <InvoiceUpload onUpload={handleUploadFile} />
                <InvoiceTable onRemoveInvoice={handleRemoveInvoice} />
            </main>
                
        </>

    )
}