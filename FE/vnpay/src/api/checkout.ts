import { HttpResponse } from '../model/http';
import { CartInfomation } from '../model/CartInfo';
import axiosClient, { handleRequest } from './axiosClient';
import { PayInfomation } from '../model/PayInfo';

const checkoutApi = {
  /**
   * Initiates a checkout process with the given cart information.
   * 
   * @param body - The cart information to be processed.
   * @returns A promise resolving to the HTTP response containing the result of the checkout operation.
   */
  checkout: (body: CartInfomation): Promise<HttpResponse<any>> => {
    const url = `/checkout`;
    return handleRequest(axiosClient.post(url, body));
  },

  /**
   * Re-initiates a checkout process with the given cart information.
   * 
   * @param body - The cart information to be processed for re-checkout.
   * @returns A promise resolving to the HTTP response containing the result of the re-checkout operation.
   */
  reCheckout: (body: CartInfomation): Promise<HttpResponse<any>> => {
    const url = `/recheckout`;
    return handleRequest(axiosClient.post(url, body));
  },

  /**
   * Processes a payment with the provided payment information.
   * 
   * @param body - The payment information to be processed.
   * @returns A promise resolving to the HTTP response containing the result of the payment operation.
   */
  pay: (body: PayInfomation): Promise<HttpResponse<any>> => {
    const url = `/checkout/pay`;
    return handleRequest(axiosClient.post(url, body));
  },
};

export default checkoutApi;
