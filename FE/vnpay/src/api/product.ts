import { ProductResponse } from '../model/Product';
import { HttpResponse } from '../model/http';
import axiosClient, { handleRequest } from './axiosClient';

const productApi = {
  /**
   * Retrieves products for the cart based on the provided product IDs.
   * 
   * @param body - An array of product IDs to retrieve from the cart.
   * @returns A promise resolving to the HTTP response containing the list of product responses.
   */
  getProductsCart: (body: number[]): Promise<HttpResponse<ProductResponse[]>> => {
    const url = `/cart`;
    return handleRequest(axiosClient.post(url, body));
  },
};

export default productApi;
