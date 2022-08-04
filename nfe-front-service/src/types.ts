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

export interface ApiError {
    status: string;
    title: string;
    detail: string;
    timestamp: string;
    userMessage: string;
    fields: Field[]
}

interface Field {
    name: string;
    userMessage: string;
}