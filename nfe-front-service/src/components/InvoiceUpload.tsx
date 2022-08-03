import { ChangeEvent, FormEvent, useState } from "react";
import { useInvoices } from "../hooks/useInvoices"

export function InvoiceUpload() {
    const {uploadInvoice} = useInvoices();

    const [xmlInvoice, setXmlInvoice] = useState<File>({} as File);

    function handleSelectInvoice(event: ChangeEvent<HTMLInputElement>) {
        if (!event.target.files) {
            return
        }

        const selectedXmlInvoice = event.target.files[0]
        setXmlInvoice(selectedXmlInvoice)
    }

    async function handleSubmit(event: FormEvent) {
        event.preventDefault();

        await uploadInvoice(xmlInvoice)
    }

    return(
        <form onSubmit={handleSubmit}>
            <input type={"file"} onChange={handleSelectInvoice} id="xmlNotaFiscal" />
            <button type="submit">Enviar</button>
        </form>
    )
}