package com.neuedu.lab.Utils;

public class ExceptionHandler extends Throwable {
    private Integer error_code;
    private String error_msg;
    public ExceptionHandler(int error_code, String error_msg){
        this.error_code = error_code;
        this.error_msg = error_msg;
    }
    public static void soutError(String s){
        System.out.println("[ERROR]"+s);
    }

    public static void soutInfo(String s){
        if(ConstantDefinition.DEBUG_MODE){
            System.out.println("[INFO]"+s);
        }

    }
}
