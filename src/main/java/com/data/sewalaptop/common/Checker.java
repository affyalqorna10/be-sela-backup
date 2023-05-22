package com.data.sewalaptop.common;

public class Checker {
    public static boolean validStrOnly(String str){
        return str != null && !str.isEmpty()
                && str.matches("^[a-zA-Z]*$");
    }

    public static boolean isNullStr(String str){
        return str != null && !str.isEmpty();
    }
}
