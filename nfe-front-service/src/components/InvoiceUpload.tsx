import React from "react";
import { Toolbar } from 'primereact/toolbar';
import { FileUpload, FileUploadHandlerParam } from "primereact/fileupload";

interface InvoiceUploadProps {
    onUpload: (file: File) => Promise<void>
}

export function InvoiceUpload({onUpload}: InvoiceUploadProps) {

    async function handleUploadFile(event: FileUploadHandlerParam) {
        onUpload(event.files[0])
        event.options.clear();
    }

    const rightContents = (
        <React.Fragment>
            <FileUpload
            name="xmlNotaFiscal"
            chooseLabel="Upload"
            url="/notas-fiscais" 
            mode="basic"
            // accept="text/xml"
            // auto
            customUpload uploadHandler={((e) => handleUploadFile(e))} />
        </React.Fragment>
    );

    return(
        <Toolbar right={rightContents} />
    )
}