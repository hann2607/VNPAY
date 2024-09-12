import { notification } from "antd";
import { HttpError } from "../../model/http";

interface ErrorHandlingOptions {
  showNotification?: boolean;
  customHandler?: (error: HttpError) => void;
  setErrorShown?: React.Dispatch<React.SetStateAction<boolean>>;
  translate?: (key: string) => string;
}

/**
 * Handles errors by showing notifications or calling a custom handler.
 *
 * @param error - The error object containing error details.
 * @param options - Optional settings for error handling.
 */
export function handleError(
  error: HttpError | undefined,
  options?: ErrorHandlingOptions
) {
  if (!error) return;

  const {
    showNotification = true,
    customHandler,
    setErrorShown,
    translate = (key: string) => key,
  } = options || {};

  // If a custom handler is provided, call it and return early
  if (customHandler) {
    customHandler(error);
    return;
  }

  // Show notification if required
  if (showNotification) {
    notification.error({
      message: "Error",
      description: getErrorDescription(error, translate),
      onClose: () => setErrorShown?.(true),
    });
    setErrorShown?.(false);
  }
}

/**
 * Retrieves a user-friendly error description based on the error object.
 *
 * @param error - The error object containing error details.
 * @param translate - The translation function.
 * @returns - A string describing the error.
 */
function getErrorDescription(
  error: HttpError,
  translate: (key: string) => string
): string {
  // If the error is a bad request, get the first field error if available
  if (error.badRequest) {
    return translate(
      getFirstFieldError(error.fieldErrors) || "error.badRequest"
    );
  }

  // Return the message code or a default unknown error message
  return translate(error.messageCode || "error.unknown");
}

/**
 * Retrieves the first error message from the field errors object.
 *
 * @param fieldErrors - An optional object containing field-specific errors.
 * @returns - The first error message or undefined if no errors are found.
 */
function getFirstFieldError(
  fieldErrors?: Record<string, string[]>
): string | undefined {
  // Check if fieldErrors is defined before attempting to access its properties
  if (fieldErrors) {
    const firstKey = Object.keys(fieldErrors)[0];
    if (firstKey) {
      return fieldErrors[firstKey][0];
    }
  }
  return undefined;
}
