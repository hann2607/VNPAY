/**
 * Represents an error associated with a specific field in a form or request.
 */
interface FieldErrorData {
    field: number;
    messageCode: string;
  }
  
  /**
   * Represents general error data returned by an API or validation system.
   */
  interface ErrorData {
    messageCode: string;
    [key: string]: string;
  }
  
  /**
   * Represents the structure of a response from an API call that includes error information.
   */
  interface AxiosResponseData {
    fieldErrors: FieldErrorData[];
    messageCode: string;
    errors: object;
    data: ErrorData;
    detail: string;
  }
  
  export default AxiosResponseData;
  