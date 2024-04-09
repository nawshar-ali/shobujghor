package com.shobujghor.app.utility.util;

public class MathUtil {
    public static final String addIntegerString(String num1, String num2) {
        return String.valueOf(Integer.parseInt(num1) + Integer.parseInt(num2));
    }

    public static final String addDoubleString(String num1, String num2) {
        return String.valueOf(Double.parseDouble(num1) + Double.parseDouble(num2));
    }

    public static final String multiplyDoubleString(String num1, String num2) {
        return String.valueOf(Double.parseDouble(num1) * Double.parseDouble(num2));
    }
}
