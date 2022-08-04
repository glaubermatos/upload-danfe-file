import { useState } from "react";
import { InvoiceTable } from "../components/InvoiceTable";
import { InvoiceUpload } from "../components/InvoiceUpload";
import { useInvoices } from "../hooks/useInvoices";
import { ApiError } from "../types";

export function Home() {
    const { uploadInvoice } = useInvoices()

    const [successMessage, setSuccessMessage] = useState("")
    const [error, setError] = useState<ApiError | null>()

    async function handleUpload(file: File) {
        try {
            const response = await uploadInvoice(file)
            console.log(response)
            setSuccessMessage(response)
            setError(null)
        } catch (err: any) {
            const error = err
            setError(error.response.data)
            setSuccessMessage("")
        }
    }        

    return(
        <div className="App">
            {successMessage && (
                <p>{successMessage}</p>
            )}

            {error && (
                <p>{error.userMessage}</p>
            )}

            <br />
            <br />

            <InvoiceUpload onUpload={handleUpload} />

            <InvoiceTable />
        </div>
    )
}