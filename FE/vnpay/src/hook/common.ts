import {useTranslation} from 'react-i18next';

export const useAppTranslation = () => useTranslation().t;
export const useErrTranslation = () => useTranslation(['errors']).t;
