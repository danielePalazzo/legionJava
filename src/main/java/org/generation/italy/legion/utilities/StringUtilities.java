package org.generation.italy.legion.utilities;

import java.time.LocalDate;

public class StringUtilities {
    public static boolean isNullOrEmpty(String s){
        return s == null || s.length()==0;
    }

    public static LocalDate fromJSONString(String s){
        if (isNullOrEmpty(s)){
            return null;
        }
        return LocalDate.parse(s);
    }
}
