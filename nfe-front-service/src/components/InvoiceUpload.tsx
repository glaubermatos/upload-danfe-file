import { ChangeEvent, FormEvent, useState } from "react";

interface InvoiceUploadProps {
    onUpload: (file: File) => Promise<void>
}

export function InvoiceUpload({onUpload}: InvoiceUploadProps) {
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

        onUpload(xmlInvoice)
        setXmlInvoice({} as File)
    }

    return(
        <form onSubmit={handleSubmit}>
            <input type={"file"} onChange={handleSelectInvoice} id="xmlNotaFiscal" />
            <button type="submit">Enviar</button>
        </form>
    )
}