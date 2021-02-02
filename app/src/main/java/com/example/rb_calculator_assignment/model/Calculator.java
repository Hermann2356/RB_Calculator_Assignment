package com.example.rb_calculator_assignment.model;

public class Calculator {

    public Calculator() { }
    
    public double add (double num1, double num2) {
        return num1 + num2;
    }
    
    public double subtract(double num1, double o2) {
        return num1 - o2;
    }
    
    public double multiply(double num1, double o2) {
        return num1 * o2;
    }
    
    public double divide(double num1, double o2) {
        return num1 / o2;
    }
    
    public double percentage(double num) {
        return num * .01;
    }
    
    public double plusNegate(double num) {
        return -(num);
    }

    public static double getDouble(CharSequence text) {
        return Double.parseDouble(String.valueOf(text));
    }
    
}
