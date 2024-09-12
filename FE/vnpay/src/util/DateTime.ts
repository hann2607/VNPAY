import { format, parseISO } from 'date-fns';

/**
 * Formats a date string from ISO format to 'dd-MM-yyyy'.
 * 
 * @param dateString - The date string in ISO 8601 format.
 * @returns The formatted date string in 'dd-MM-yyyy' format.
 */
export const formatDate = (dateString: string): string => {
  const date = parseISO(dateString); 
  return format(date, 'dd-MM-yyyy');  
};