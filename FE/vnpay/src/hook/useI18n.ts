import {useTranslation} from 'react-i18next';

const useI18n = () => {
  const {i18n} = useTranslation();

  const handleChangeLanguages = async () => {
    const switchedLang = i18n.language === 'en' ? 'vi' : 'en';
    i18n.changeLanguage(switchedLang).catch(() => {});
  };

  return {handleChangeLanguages, i18n};
};

export default useI18n;
