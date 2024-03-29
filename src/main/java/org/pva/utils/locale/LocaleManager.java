package org.pva.utils.locale;

import java.util.Locale;

public class LocaleManager {

    public static final Locale RU_LOCALE = new Locale("ru");
    public static final Locale EN_LOCALE = new Locale("en");

    private static Lang currentLang = new Lang();

    public static Lang getCurrentLang() {
        return currentLang;
    }

    public static void setCurrentLang(Lang lang) {
        LocaleManager.currentLang = lang;
    }

}
