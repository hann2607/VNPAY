import React, { useEffect } from "react";
import { Row, Col } from "antd";
import { useNavigate } from "react-router-dom";
import checkoutApi from "../../api/checkout";
import url from "../../app/url";

const PaymentForm: React.FunctionComponent = () => {
  const navigate = useNavigate();

  /**
   * Fetch the wallet when the component mounts
   */
  useEffect(() => {
    const fetchData = async () => {
      // Retrieve query parameters from the URL
      const searchParams = new URLSearchParams(window.location.search);

      // Extract and assign parameters to an object with default empty strings
      const params = {
        vnpAmount: searchParams.get("vnp_Amount") || "",
        vnpBankCode: searchParams.get("vnp_BankCode") || "",
        vnpBankTranNo: searchParams.get("vnp_BankTranNo") || "",
        vnpCardType: searchParams.get("vnp_CardType") || "",
        vnpOrderInfo: searchParams.get("vnp_OrderInfo") || "",
        vnpPayDate: searchParams.get("vnp_PayDate") || "",
        vnpResponseCode: searchParams.get("vnp_ResponseCode") || "",
        vnpTmnCode: searchParams.get("vnp_TmnCode") || "",
        vnpTransactionNo: searchParams.get("vnp_TransactionNo") || "",
        vnpTransactionStatus: searchParams.get("vnp_TransactionStatus") || "",
        vnpTxnRef: searchParams.get("vnp_TxnRef") || "",
        vnpSecureHash: searchParams.get("vnp_SecureHash") || "",
      };

      // Send the parameters to the checkout API
      await checkoutApi.pay(params);

      // Redirect to the success or error page based on transaction status
      const redirectTo =
        params.vnpTransactionStatus === "00" ? url.success : url.error;
      navigate(redirectTo);
    };

    // Invoke the fetchData function
    fetchData();
  }, [navigate]);

  return (
    <Row justify="center" align="middle" className="page success-page">
      <Col span={24} className="page-container success-container">
        <Row justify="space-between" className="content success-content"></Row>
      </Col>
    </Row>
  );
};

export default PaymentForm;
