/**
 * Represents the information of a wallet in the response.
 */
export interface WalletDtoResponse {
    id: number;
    currency: string;
    balance: number; 
    createdDate: string; 
}