import { useInvoices } from "../hooks/useInvoices"

export function InvoiceTable() {
    const {invoices, deleteInvoice, showDuplicates} = useInvoices()

    async function handleDeleteInvoice(numero: number) {
        await deleteInvoice(numero)
        alert(`Nota Fiscal ${numero} removida com sucesso!`)
    }

    async function handleShowDuplicates(numero: number) {
        const response = await showDuplicates(numero)

        console.log(response)
    }

    return(
        <div>
            {invoices.map(invoice => {
                return(
                    <div key={invoice.id}>
                        <strong>Nome arquivo</strong>
                        <span>{invoice.nomeArquivo}</span>
                        <button type="button" onClick={() => handleShowDuplicates(invoice.numero)}>Duplicatas</button>
                        <button type="button" onClick={() => handleDeleteInvoice(invoice.numero)}>Excluir</button>
                    </div>
                )
            })}
        </div>
    )
}