import { WalletDtoResponse } from '../model/Wallet';
import { OrderDtoResponse } from '../model/Product';
import { HttpResponse } from '../model/http';
import axiosClient, { handleRequest } from './axiosClient';

const walletApi = {
  /**
   * Retrieves information about a specific wallet.
   * 
   * @param id - The ID of the wallet to retrieve information for.
   * @returns A promise resolving to the HTTP response containing the wallet information.
   */
  getWalletInfo: (id: number): Promise<HttpResponse<WalletDtoResponse>> => {
    const url = `/wallet/` + id;
    return handleRequest(axiosClient.get(url));
  },

  /**
   * Retrieves a list of orders associated with the wallet.
   * 
   * @returns A promise resolving to the HTTP response containing a list of order responses.
   */
  getOrders: (): Promise<HttpResponse<OrderDtoResponse[]>> => {
    const url = `/wallet/orders`;
    return handleRequest(axiosClient.get(url));
  },
};

export default walletApi;
