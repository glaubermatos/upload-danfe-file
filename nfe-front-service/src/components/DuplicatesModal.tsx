import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import { Duplicate, Invoice } from '../types';
import { DuplicatesTable } from './DuplicatesTable';
import { useInvoices } from '../hooks/useInvoices';
import { useEffect, useState } from 'react';

interface DuplicatesModelProps {
    visible: boolean;
    onClose: () => void;
    invoice: Invoice;
}

export function DuplicatesModal({visible, onClose, invoice}: DuplicatesModelProps) {
    const { getDuplicates } = useInvoices()
    const [duplicates, setDuplicates] = useState<Duplicate[]>([])

    useEffect(() => {
        loadDuplicates(invoice.numero)

    }, [invoice.numero]) // eslint-disable-line react-hooks/exhaustive-deps

    async function loadDuplicates(invoiceNumber: number) {
        if (invoiceNumber) {
            const response = await getDuplicates(invoiceNumber)
            setDuplicates(response)
        }
    }

    const renderFooter = (
        <div>
            <Button label="Fechar" className='p-button-outlined p-button-secondary p-button-sm' onClick={onClose} />
        </div>
    );

    return(
        <Dialog header="Nota Fiscal - Duplicatas" visible={visible} style={{ width: '50vw' }} footer={renderFooter} onHide={onClose}>
            <div>
                <strong>Nota fiscal Nº</strong>
                <p>{invoice.numero}</p>
            </div>
            <DuplicatesTable duplicates={duplicates} />
        </Dialog>
    )
}