import React, { useEffect, useState } from "react";
import { Row, Col, Avatar, Button } from "antd";
import { OrderDtoResponse } from "../../model/Product";
import { WalletDtoResponse } from "../../model/Wallet";
import walletApi from "../../api/wallet";
import { formatDate } from "../../util/DateTime";
import { Status } from "../../enum/status";
import checkoutApi from "../../api/checkout";
import { useNavigate } from "react-router-dom";
import { useErrTranslation } from "../../hook/common";
import { handleError } from "../../components/notification/ErrorNotification";
import { useAppTranslation } from "../../hook/common";
import { useTranslation } from "react-i18next";

const WalletForm: React.FunctionComponent = () => {
  const [orders, setOrders] = useState<OrderDtoResponse[]>();
  const [walletInfo, setWalletInfo] = useState<WalletDtoResponse>();
  const [hoveredOrderIndex, setHoveredOrderIndex] = useState<number | null>(
    null
  );
  const navigate = useNavigate();
  const [errorShown, setErrorShown] = useState(true);
  const et = useErrTranslation();
  const t = useAppTranslation();
  const { i18n } = useTranslation();

  /**
   * Formats a number into a string with thousand separators.
   *
   * @param price The number to format.
   * @returns A string representation of the number with thousand separators.
   */
  function formatPrice(price: number): string {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
  }

  /**
   * useEffect hook to fetch wallet and order data on component mount.
   */
  useEffect(() => {
    const fetchData = async () => {
      // Fetch wallet information
      const { ok, body, error } = await walletApi.getWalletInfo(1);
      if (ok) {
        setWalletInfo(body);

        // Fetch orders if wallet info retrieval was successful
        const { ok: ordersOk, body: orders } = await walletApi.getOrders();
        if (ordersOk) {
          setOrders(orders);
        }
      } else {
        handleError(error, {
          translate: et,
          showNotification: errorShown,
          setErrorShown,
        });
      }
    };

    fetchData();
  }, [errorShown, et]);

  /**
   * Handles mouse movement over an order item, setting the index of the hovered order.
   *
   * @param index The index of the order being hovered.
   */
  const handleMouseMoveOnOrder = (index: number) => {
    setHoveredOrderIndex(index);
  };

  /**
   * Handles mouse leaving an order item, resetting the hovered order index.
   */
  const handleMouseLeaveOnOrder = () => {
    setHoveredOrderIndex(null);
  };

  /**
   * Initiates payment process for a given order.
   *
   * @param order The order to process payment for.
   */
  const payment = async (order: OrderDtoResponse) => {
    const vnpayId = generateUniqueId();
    const data = {
      vnpAmount: (order.totalAmount * 100).toString(),
      vnpCurrCode: "VND",
      vnpTxnRef: vnpayId,
      vnpOrderInfo: order.orderId,
      vnpLocale: "vn",
      vnpReturnUrl: "http://localhost:3001/pay",
      vnpIpAddr: "192.168.1.1",
      vnpOrderType: "250000",
      status: "PENDING",
      orderId: order.orderId,
      productIds: [order.id],
    };

    // Send payment request and handle redirection based on response
    const { ok, body } = await checkoutApi.reCheckout(data);
    if (ok) {
      window.location.href = body.url;
    }
  };

  /**
   * Generates a unique identifier for transactions or orders.
   *
   * @returns A unique identifier string.
   */
  function generateUniqueId(): string {
    return Math.random().toString(36).substr(2, 9);
  }

  /**
   * Handles navigation to the homepage.
   */
  const handleOrderNow = () => {
    navigate("/");
  };

  return (
    <Row justify="center" align="middle" className="page wallet-page">
      <Col span={24} className="page-container wallet-container">
        <Row justify="space-between" className="content wallet-content">
          <Col span={7} className="card">
            <Row className="header">
              <Col span={24} className="col-image">
                <Avatar
                  size={130}
                  src="https://phongreviews.com/wp-content/uploads/2022/11/anh-avatar-dep-cho-con-trai-11.jpg"
                  className="image"
                />
              </Col>
            </Row>
            <Row className="card-content">
              <Col span={24}>
                <Row>
                  <Col span={24} className="title-container">
                    <span className="title">{t("wallet.title")}</span>
                  </Col>
                </Row>
                <Row>
                  <Col span={24} className="money-container">
                    <span className="money">
                      {formatPrice(walletInfo?.balance || 0)}{" "}
                      {walletInfo?.currency}
                    </span>
                  </Col>
                </Row>
                <Row>
                  <Col span={11} className="btn-container">
                    <Button className="btn" onClick={handleOrderNow}>
                      {t("button.order")}
                      <span className="icon">&#8594;</span>
                    </Button>
                  </Col>
                </Row>
              </Col>
            </Row>
            <Row className="footer">
              <Col span={24}>
                <Row>
                  <Col span={24} className="title-container">
                    <span className="title">{t("wallet.lastOrder")}</span>
                  </Col>
                </Row>
                {orders && orders?.length > 0 ? (
                  orders.map((order, index) => (
                    <Row
                      className="order"
                      key={index}
                      onMouseMove={() => handleMouseMoveOnOrder(index)}
                      onMouseLeave={handleMouseLeaveOnOrder}
                    >
                      <Col
                        span={15}
                        className={
                          hoveredOrderIndex === index &&
                          order.status === Status.PENDING
                            ? "d-none"
                            : "d-block"
                        }
                      >
                        <Row>
                          <Col span={24} className="order-id-container">
                            <span className="order-id">#{order.orderId}</span>
                          </Col>
                        </Row>
                        <Row>
                          <Col span={24} className="time-container">
                            <span className="time">
                              {formatDate(order.createdDate)}
                            </span>
                          </Col>
                        </Row>
                      </Col>
                      <Col
                        span={9}
                        className={
                          hoveredOrderIndex === index &&
                          order.status === Status.PENDING
                            ? "d-none"
                            : "d-block"
                        }
                      >
                        <Row>
                          <Col span={24} className="price-container">
                            <span className="price">
                              {formatPrice(order.totalAmount)} VND
                            </span>
                          </Col>
                          <Col span={24} className="status-container">
                            <span className="status">{order.status}</span>
                          </Col>
                        </Row>
                      </Col>
                      <Col
                        span={24}
                        className={
                          hoveredOrderIndex === index &&
                          order.status === Status.PENDING
                            ? "btn-container d-block"
                            : "btn-container d-none"
                        }
                      >
                        <Button
                          className="checkout"
                          onClick={() => {
                            payment(order);
                          }}
                        >
                          {t("button.payment")}
                        </Button>
                      </Col>
                    </Row>
                  ))
                ) : (
                  <img
                    className="image-no-data"
                    src="https://cdni.iconscout.com/illustration/premium/thumb/file-not-found-4064359-3363920.png?f=webp"
                    alt="No data"
                    width="300"
                    height="300"
                  ></img>
                )}
              </Col>
            </Row>
          </Col>
          <Col span={24}>
            <Row justify="center" className="language">
              <Col>
                <span className="flag-left">{t("language")}:</span>
              </Col>
              <Col>
                <span
                  className="flag flag-left"
                  onClick={() => i18n.changeLanguage("vi")}
                >
                  VN
                </span>
              </Col>
              <Col>
                <span className="flag flag-left">|</span>
              </Col>
              <Col>
                <span
                  className="flag flag-right"
                  onClick={() => i18n.changeLanguage("en")}
                >
                  ENG
                </span>
              </Col>
            </Row>
          </Col>
        </Row>
      </Col>
    </Row>
  );
};

export default WalletForm;
