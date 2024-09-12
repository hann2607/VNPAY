import { Status } from "../enum/status";

/**
 * Represents a product's information in the response.
 */
export interface ProductResponse {
    id: number;
    name: string;
    description: string;
    price: number;
    discount: number;
    image: string;
    available: boolean;
    createdDate: string;  
}

/**
 * Represents an order's information in the response.
 */
export interface OrderDtoResponse {
    id: number;
    orderId: string;
    totalAmount: number; 
    status: Status;
    createdDate: string; 
}