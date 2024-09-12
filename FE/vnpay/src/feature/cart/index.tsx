import React, { useState } from "react";
import CartForm from "./CartForm";
import checkoutApi from "../../api/checkout";
import { CartInfomation } from "../../model/CartInfo";
import { handleError } from "../../components/notification/ErrorNotification";
import { useErrTranslation } from "../../hook/common";

const CartPage: React.FC = () => {
  const [errorShown, setErrorShown] = useState(true);
  const et = useErrTranslation();

  /**
   * Handles the submission of the cart form
   *
   * @param values The cart information to be submitted
   */
  const onSubmitCartForm = async (values: CartInfomation) => {
    // Send cart information to the checkout API
    const { ok, body, error } = await checkoutApi.checkout(values);

    // If the API call was successful
    if (ok) {
      // Redirect to the payment URL provided in the response
      window.location.href = body.url;
    } else {
      handleError(error, {
        translate: et,
        showNotification: errorShown,
        setErrorShown,
      });
    }
  };

  return (
    <>
      <CartForm onSubmit={onSubmitCartForm} />
    </>
  );
};

export default CartPage;
