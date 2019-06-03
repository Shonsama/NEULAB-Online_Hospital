package com.neuedu.lab.Utils;


import com.alibaba.fastjson.JSONObject;


import java.math.BigDecimal;

public class ConstantUtils {
    public static BigDecimal convertToNegtive(BigDecimal source){
        return source.multiply(new BigDecimal(-1));
    }


    /**
     * 返回统一格式化
     *
     */
    public static JSONObject responseSuccess(Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.SUCCESS_CODE);
        result.put("data", data);
        result.put("msg", ConstantDefinition.SUCCESS_MESSAGE);
        return result;
    }
    public static JSONObject responseFail(Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.FAIL_CODE);
        result.put("data", data);
        result.put("msg", ConstantDefinition.FAIL_MESSAGE);
        return result;
    }
    public static JSONObject response(String code, String msg, Object data) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }
}
