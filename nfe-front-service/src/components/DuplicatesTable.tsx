import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Duplicate } from '../types';

interface DuplicatesTableProps {
    duplicates: Duplicate[]
}

export const DuplicatesTable = ({ duplicates }: DuplicatesTableProps) => {
    return (
        <div>
            <div className="card">
                <DataTable value={duplicates} responsiveLayout="scroll">
                    <Column field="parcela" header="Parcela"></Column>
                    <Column field="valor" header="Valor"></Column>
                    <Column field="dataVencimento" header="Data Vencimento"></Column>
                </DataTable>
            </div>
        </div>
    );
}