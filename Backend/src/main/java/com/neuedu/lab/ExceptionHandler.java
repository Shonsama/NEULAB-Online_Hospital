package com.neuedu.lab;

public class ExceptionHandler {
    public static void soutError(String s){
        System.out.println("[ERROR]"+s);
    }

    public static void soutInfo(String s){
        if(ConstantDefinition.DEBUG_MODE){
            System.out.println("[INFO]"+s);
        }

    }
}
