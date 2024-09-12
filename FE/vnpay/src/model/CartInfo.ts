/**
 * Represents the information required to process a cart checkout.
 */
export interface CartInfomation {
    vnpAmount: string;
    vnpCurrCode: string;
    vnpTxnRef: string;
    vnpOrderInfo: string;
    vnpLocale: string;
    vnpReturnUrl: string;
    vnpIpAddr: string;
    vnpOrderType: string;
    status: string;
    orderId: string;
    productIds: number[];
}