import React, { useState } from "react";
import { useInvoices } from "../hooks/useInvoices"
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel'
import { Button } from 'primereact/button'
import { ConfirmDialog } from 'primereact/confirmdialog'; // To use <ConfirmDialog> tag
import { confirmDialog } from 'primereact/confirmdialog'; // To use confirmDialog method
import { Badge } from 'primereact/badge';

import { Invoice } from "../types";
import { DuplicatesModal } from "./DuplicatesModal";

interface InvoiceTableProps {
    onRemoveInvoice: (invoiceNumber: number) => void
}

export function InvoiceTable({ onRemoveInvoice }: InvoiceTableProps) {
    const { invoices } = useInvoices()
    const [invoice, setInvoice] = useState<Invoice>({} as Invoice)
    const [isDupĺicatesDialogVisible, setsDupĺicatesDialogVisible] = useState(false)

    function confirmDeletion(invoice: Invoice) {
        confirmDialog({
            message: `Remover Nota Fiscal nº ${invoice.numero}`,
            header: 'Confirmar exclusão',
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: 'Remover',
            acceptClassName: 'p-button-danger',
            rejectLabel: 'Cancelar',
            rejectClassName: 'p-button-outlined p-button-secondary',
            accept: () => onRemoveInvoice(invoice.numero),
            reject: () => {}
        });
    }

    const actionBodyTemplate = (invoice: Invoice) => {
        return (
            <React.Fragment>
                <Button className="p-button-outlined p-button-secondary p-button-sm" disabled={"PROCESSADA_COM_ERRO" === invoice.status ? true : false} onClick={() => openDuplicateDialog(invoice)}>Duplicatas</Button>
                <Button className="p-button-outlined p-button-danger p-button-sm" onClick={() => confirmDeletion(invoice)}>Remover</Button>
            </React.Fragment>
        );
    }

    function openDuplicateDialog(invoice: Invoice) {
        setsDupĺicatesDialogVisible(true)
        setInvoice(invoice)
    }

    function closeDuplicateDialog() {
        setsDupĺicatesDialogVisible(false)
    }

    const statusTemplate = ({status}: Invoice) => {
        switch (status) {
            case 'AGUARDANDO_PROCESSAMENTO':
            
                return <Badge value="Aguardando processamento" severity="info"></Badge>
        
            case 'EM_PROCESSAMENTO':
            
                return <Badge value="Em processamento" severity="warning"></Badge>
        
            case 'PROCESSADA':
            
                return <Badge value="Processada" severity="success"></Badge>
        
            case 'PROCESSADA_COM_ERRO':
            
                return <Badge value="Processada com erro" severity="danger"></Badge>
        
            default:
                return null;
        }
    }

    return(
        <>
            <ConfirmDialog /> 
            <DuplicatesModal invoice={invoice} visible={isDupĺicatesDialogVisible} onClose={closeDuplicateDialog} />
            <Panel>
                <DataTable header="Notas Fiscais" value={invoices} stripedRows size="small"  responsiveLayout="stack" breakpoint="960px">
                    <Column field="numero" header="Numero da nota" />
                    <Column field="nomeArquivo" header="Nome arquivo" />
                    <Column field="nomeEmitente" header="Emitente" />
                    <Column field="nomeDestinatario" header="Destinatario" />
                    <Column field="valorNota" header="Valor da Nota" />
                    <Column field="status" header="Status" body={statusTemplate} exportable={false}  />

                    <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '8rem' }}></Column>
                </DataTable>
            </Panel>
        
        </>
    )
}