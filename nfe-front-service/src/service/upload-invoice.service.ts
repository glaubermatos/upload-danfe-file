import { api } from "../http/api"
import { Invoice } from "../types"

export const getAllInvoices = () => {
    api.get<Invoice[]>('/notas-fiscais')
}