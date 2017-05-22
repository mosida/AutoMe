package com.mosida.autome;

import android.content.Context;

import java.util.Locale;

/**
 * Created by mosida on 5/13/17.
 */

public class Utils {

    public static Locale[] getSystemLanguageList(){
        //获取Android系统上的语言列表
        Locale mLanguagelist[] = Locale.getAvailableLocales();
        return mLanguagelist;
    }

    public static String getCurrentLauguage(){
        //获取系统当前使用的语言
        String mCurrentLanguage = Locale.getDefault().getLanguage();
        //设置成简体中文的时候，getLanguage()返回的是zh
        return mCurrentLanguage;
    }

    public static String getCurrentLauguageUseResources(Context context){
        /**
         * 获得当前系统语言
         */
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage(); // 获得语言码
        return language;
    }
}
