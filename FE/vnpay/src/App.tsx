import React, { Fragment } from "react";
import { Routes, Route } from "react-router-dom";
import MainPageLayout from "./components/Layout/page-layout/MainPageLayout";
import CartPage from "./feature/cart/index";
import PaymentPage from "./feature/payment/index";
import SuccessPage from "./feature/success/index";
import ErrorPage from "./feature/error/index";
import WalletPage from "./feature/wallet/index";

const App: React.FC = () => {
  return (
    <Fragment>
      <Routes>
        <Route path="/" element={<MainPageLayout />}>
          <Route path="" element={<CartPage />} />
          <Route path="pay" element={<PaymentPage />} />
          <Route path="success" element={<SuccessPage />} />
          <Route path="error" element={<ErrorPage />} />
          <Route path="wallet" element={<WalletPage />} />
        </Route>
      </Routes>
    </Fragment>
  );
};

export default App;
