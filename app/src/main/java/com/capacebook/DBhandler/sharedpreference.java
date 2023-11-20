package com.capacebook.DBhandler;

import android.content.Context;
import android.content.SharedPreferences;

    public class sharedpreference {

        private static Context mcontext;
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        int PRIVATE_MODE =0;
        public static final String PREFERENCE = "User";

        private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

        public void SharedPref(Context mcontext){
            this.mcontext = mcontext;
            pref = mcontext.getSharedPreferences(PREFERENCE, PRIVATE_MODE);
            editor = pref.edit();
        }

        public void setIsFirstTimeLaunch(boolean isFirstTimeLaunch){
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch);
            editor.commit();
        }

        public boolean isFirstTimeLaunch(){
            return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
        }

        public static void setSharedPreference(Context context, String name, String value){
            mcontext = context;
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString(name, value);
            editor.apply();
        }

        public static String getSharedPreferences(Context context, String name){
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
            return settings.getString(name,"");
        }
        public static String getSharedPreferences(String name){
            Context context = null;
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
            return settings.getString(name,"");
        }

        public static void removepreference(Context context, String name){
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
            settings.edit().remove(name).apply();
        }


        public static void clearPreference(Context context){
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
            settings.edit().clear().apply();
        }
    }

