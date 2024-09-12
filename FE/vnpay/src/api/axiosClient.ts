import axios, { AxiosResponse } from 'axios';
import AxiosResponseData from '../model/axios';
import { BadRequestFieldError, HttpResponse } from '../model/http';
import env from '../app/env';

const axiosClient = axios.create({
  baseURL: env.baseGatewayUrl,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/vnd.vnpayapi.v1+json',
  },
  withCredentials: true,
});

axiosClient.interceptors.request.use(
  (config) => config,
  (error) => Promise.reject(error)
);

axiosClient.interceptors.response.use(
  // @ts-expect-error: we want to return the different data type
  (response: AxiosResponse<AxiosResponseData>) => {
    const { status, data: responseData, headers } = response;
    const data: HttpResponse<object> = {
      status,
      ok: true,
      body: responseData,
      data: function (): { payload: any; type: 'auth/loginSuccess'; } {
        throw new Error('Function not implemented.');
      }
    };

    if (headers.link) {
      data.pagination = {
        paging: 0,
        total: Number(headers['x-total-count']),
      };
    }

    return data;
  },
  ({ response }) => {
    const { status, data } = response as AxiosResponse<AxiosResponseData>;
    const fieldErrors: BadRequestFieldError = {};

    if (typeof data?.fieldErrors === 'object' || Array.isArray(data?.fieldErrors)) {
      if (data?.fieldErrors?.length) {
        data.fieldErrors.forEach(({ field, messageCode }) => {
          if (fieldErrors[field]) {
            fieldErrors[field].push(messageCode);
          } else {
            fieldErrors[field] = [messageCode];
          }
        });
      }
    }

    const error: HttpResponse = {
      status,
      ok: false,
      error: {
        unauthorized: status === 401,
        badRequest: status === 400,
        notFound: status === 404,
        clientError: status >= 400 && status <= 499,
        serverError: status >= 500 && status <= 599,
        messageCode: data?.messageCode || data?.data?.messageCode,
        title: `${data?.messageCode || ''}-title`,
        errors: data?.errors,
        detail: data?.detail,
        data: data?.data,
        fieldErrors: fieldErrors
      },
      data: function (): { payload: any; type: 'auth/loginSuccess'; } {
        throw new Error('Function not implemented.');
      }
    };


    return Promise.reject(error);
  }
);

const handleRequest = (promise: Promise<HttpResponse>) =>
  promise.then((res) => res).catch((err) => err as HttpResponse<any>);

export default axiosClient;

export { handleRequest };
