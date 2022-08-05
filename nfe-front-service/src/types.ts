export interface Invoice {
    id: number;
    nomeArquivo: string;
    numero: number;
    dhRegistro: string;
    nomeDestinatario: string;
    nomeEmitente: string;
    valorNota: number;
    status: string;
}

export interface Duplicate {
    id: number;
	parcela: number;
	valor: number;
	dataVencimento: string;
}
