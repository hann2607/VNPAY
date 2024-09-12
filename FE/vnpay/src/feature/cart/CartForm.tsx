import React, { useEffect, useState } from "react";
import { Row, Col, Button } from "antd";
import { CartInfomation } from "../../model/CartInfo";
import { CloseOutlined } from "@ant-design/icons";
import { Avatar } from "antd";
import productApi from "../../api/product";
import { ProductResponse } from "../../model/Product";
import { Status } from "../../enum/status";
import env from "../../app/env";
import { useAppTranslation } from "../../hook/common";
import { useTranslation } from "react-i18next";

type CartFormProps = {
  onSubmit: (values: CartInfomation) => void;
};

const CartForm: React.FunctionComponent<CartFormProps> = ({ onSubmit }) => {
  const [items, setItems] = useState<ProductResponse[]>();
  const t = useAppTranslation();
  const { i18n } = useTranslation();

  /**
   * Fetch the products when the component mounts
   */
  useEffect(() => {
    const fetchProductsCart = async () => {
      // List of product IDs to fetch
      const ids = [1, 2, 3];

      const { ok, body } = await productApi.getProductsCart(ids);
      if (ok) {
        setItems(body);
      }
    };
    fetchProductsCart();
  }, []);

  /**
   * Handle form submission
   */
  const handleSubmit = () => {
    // Generate a unique order ID
    const orderId = generateUniqueId();

    // Generate order data
    const data = {
      vnpAmount: (calculateTotalPriceWithDiscount(items) * 100).toString(),
      vnpCurrCode: env.paymentCurr,
      vnpTxnRef: generateUniqueId(),
      vnpOrderInfo: orderId,
      vnpLocale: env.paymentLocale,
      vnpReturnUrl: env.paymentReturn,
      vnpIpAddr: env.paymentIp,
      vnpOrderType: env.paymentOrderType,
      status: Status.PENDING,
      orderId: orderId,
      productIds: items?.map((_, index) => index + 1) ?? [], // List of product IDs
    };

    // Submit the order data
    onSubmit(data);
  };

  /**
   * Generate a unique identifier
   *
   * @returns A unique alphanumeric string
   */
  function generateUniqueId() {
    return Math.random().toString(36).substr(2, 9);
  }

  /**
   * Format price with thousand separators
   *
   * @param price The price to format
   * @returns The formatted price string
   */
  function formatPrice(price: number) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
  }

  /**
   * Calculate total price with discounts applied
   *
   * @param items The list of products
   * @returns The total price with discounts
   */
  const calculateTotalPriceWithDiscount = (items?: ProductResponse[]) => {
    if (!items) return 0;

    return items.reduce((total, item) => {
      const discountPrice = item.price - (item.price * item.discount) / 100;
      return total + discountPrice;
    }, 0);
  };

  /**
   * Calculate the total original price of the products
   *
   * @param items The list of products
   * @returns The total original price
   */
  const calculateOldPrice = (items?: ProductResponse[]) => {
    if (!items) return 0;

    return items.reduce((total, item) => {
      return total + item.price;
    }, 0);
  };

  return (
    <Row justify="center" align="middle" className="page cart-page">
      <Col span={24} className="page-container cart-container">
        <Row justify="space-between" className="content cart-content">
          <Col span={7} className="card">
            <Row className="header">
              <Col span={13} className="title">
                <span>{t("cart.title")}</span>
              </Col>
              <Col span={11} className="quantity">
                <span>
                  {items ? items.length : 0} {t("cart.items")}
                </span>
              </Col>
            </Row>
            <Row className="border">
              <Col span={24}></Col>
            </Row>
            <Row className="card-content">
              {items
                ? items.map((item, index) => (
                    <Col span={24} className="item" key={index}>
                      <Row>
                        <Col span={4} className="col-image">
                          <Avatar
                            size={60}
                            src={item.image}
                            className="image"
                          />
                        </Col>
                        <Col span={12}>
                          <Row>
                            <Col span={24} className="name">
                              <span>{item.name}</span>
                            </Col>
                          </Row>
                          <Row>
                            <Col span={24} className="quantity">
                              <span>{t("cart.quantity")}: 1</span>
                            </Col>
                          </Row>
                          <Row>
                            <Col span={24} className="remove">
                              <CloseOutlined />{" "}
                              <span>{t("button.remove")}</span>
                            </Col>
                          </Row>
                        </Col>
                        <Col span={8}>
                          <Row>
                            <Col span={24} className="price">
                              <span>{formatPrice(item.price)} VND</span>
                            </Col>
                          </Row>
                          <Row>
                            <Col span={24} className="col-discount">
                              <Row>
                                <Col span={24} className="discount">
                                  <span>-{item.discount}%</span>
                                </Col>
                              </Row>
                            </Col>
                          </Row>
                        </Col>
                      </Row>
                      <Row
                        className={
                          index === items.length - 1 ? "border-end" : "border"
                        }
                      >
                        <Col span={24}></Col>
                      </Row>
                    </Col>
                  ))
                : ""}
            </Row>
            <Row className="border">
              <Col span={24}></Col>
            </Row>
            <Row className="footer">
              <Col span={24}>
                <Row>
                  <Col span={24} className="col-total-price">
                    <del className="old-price">
                      {formatPrice(calculateOldPrice(items))} VND
                    </del>{" "}
                    &nbsp; &nbsp;
                    <span className="new-price">
                      {formatPrice(calculateTotalPriceWithDiscount(items))} VND
                    </span>
                  </Col>
                </Row>
              </Col>
              <Col span={24}>
                <Row>
                  <Col span={24} className="note">
                    <span>{t("cart.fees")}</span>
                  </Col>
                </Row>
              </Col>
              <Col span={24}>
                <Row>
                  <Col span={24} className="btn-container">
                    <Button className="checkout" onClick={handleSubmit}>
                      {t("button.checkout")}
                    </Button>
                  </Col>
                </Row>
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

export default CartForm;
