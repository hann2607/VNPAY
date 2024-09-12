import React, { useEffect } from "react";
import { Row, Col, Avatar, Button } from "antd";
import { useNavigate } from "react-router-dom";
import url from "../../app/url";
import { useAppTranslation } from "../../hook/common";
import { useTranslation } from "react-i18next";

const ErrorForm: React.FunctionComponent = () => {
  const navigate = useNavigate();
  const t = useAppTranslation();
  const { i18n } = useTranslation();

  /**
   * Redirects the user to the wallet page
   */
  const redirectPage = () => {
    navigate(url.wallet);
  };

  /* eslint-disable react-hooks/exhaustive-deps */
  useEffect(() => {
    const timeoutId = setTimeout(() => {
      redirectPage();
    }, 5000);

    return () => clearTimeout(timeoutId);
  }, []);
  /* eslint-enable react-hooks/exhaustive-deps */

  return (
    <Row justify="center" align="middle" className="page success-page">
      <Col span={24} className="page-container success-container">
        <Row justify="space-between" className="content success-content">
          <Col span={7} className="card">
            <Row className="header">
              <Col span={24} className="col-image">
                <Avatar
                  size={200}
                  src="https://media.istockphoto.com/id/1477492069/vector/payment-error-smartphone-app-with-cross-checkmark-cashless-nfc-payment-failed-online.jpg?s=170667a&w=0&k=20&c=2y_8qYn2ZekXNee66CJV54m83YmfowJdSVl4TICCvHI="
                  className="image"
                />
              </Col>
            </Row>
            <Row className="card-content">
              <Col span={24}>
                <Row>
                  <Col span={24} className="title-container">
                    <span className="title">{t("errorPage.title")}</span>
                  </Col>
                </Row>
                <Row>
                  <Col span={24} className="subtitle-container">
                    <span className="subtitle">{t("errorPage.subTitle")}</span>
                  </Col>
                </Row>
                <Row>
                  <Col span={24} className="content-success-container">
                    <span className="content-success">
                      {t("errorPage.content")}
                    </span>
                  </Col>
                </Row>
              </Col>
            </Row>
            <Row className="footer">
              <Col span={24} className="btn-container">
                <Button className="btn" onClick={redirectPage}>
                  {t("button.wallet")} <span className="icon">&#8594;</span>
                </Button>
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

export default ErrorForm;
