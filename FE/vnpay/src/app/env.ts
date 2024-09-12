/* eslint-disable */
interface ENV {
  [key: string]: any;
}

const env: ENV = {
  baseGatewayUrl: process.env.REACT_APP_BASE_GATEWAY_URL,
  paymentReturn: process.env.REACT_APP_PAYMENT_RETURN_URL,
  paymentCurr: process.env.REACT_APP_PAYMENT_CURR,
  paymentLocale: process.env.REACT_APP_PAYMENT_LOCALE,
  paymentIp: process.env.REACT_APP_PAYMENT_IP, 
  paymentOrderType: process.env.REACT_APP_PAYMENT_ORDER_TYPE,
};

export default env;