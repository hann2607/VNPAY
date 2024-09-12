/**
 * Represents the information required for a payment transaction.
 */
export interface PayInfomation {
    vnpAmount: string;
    vnpBankCode: string;
    vnpBankTranNo: string;
    vnpCardType: string;
    vnpOrderInfo: string;
    vnpPayDate: string;
    vnpResponseCode: string;
    vnpTmnCode: string;
    vnpTransactionNo: string;
    vnpTransactionStatus: string;
    vnpTxnRef: string;
    vnpSecureHash: string;
}