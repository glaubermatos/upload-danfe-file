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

export interface Duplicates {
    id: number;
	installment: number;
	value: number;
	expirationDate: string;
}