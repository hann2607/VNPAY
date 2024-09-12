import React, { useEffect } from "react";
import { Row, Col, Avatar, Button } from "antd";
import { useNavigate } from "react-router-dom";
import url from "../../app/url";
import { useAppTranslation } from "../../hook/common";
import { useTranslation } from "react-i18next";

const SuccessForm: React.FunctionComponent = () => {
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
                  src="https://img.freepik.com/premium-vector/sack-money-vector-icon-illustration-money-bag-cash-coin-finance-desgin-concept_374761-342.jpg"
                  className="image"
                />
              </Col>
            </Row>
            <Row className="card-content">
              <Col span={24}>
                <Row>
                  <Col span={24} className="title-container">
                    <span className="title">{t("successPage.title")}</span>
                  </Col>
                </Row>
                <Row>
                  <Col span={24} className="subtitle-container">
                    <span className="subtitle">
                      {t("successPage.subTitle")}
                    </span>
                  </Col>
                </Row>
                <Row>
                  <Col span={24} className="content-success-container">
                    <span className="content-success">
                      {t("successPage.content")}
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

export default SuccessForm;
