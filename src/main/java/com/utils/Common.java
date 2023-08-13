package com.utils;

public class Common {
  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch(Exception e){
      return false;
    }
  }
}
