package com.neuedu.lab.Utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.Token.Tokenizer;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public static JSONObject responseSuccess(String msg, Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.SUCCESS_CODE);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }
    public static JSONObject responseFail(Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.FAIL_CODE);
        result.put("data", data);
        result.put("msg", ConstantDefinition.FAIL_MESSAGE);
        return result;
    }
    public static JSONObject responseFail(String msg, Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.FAIL_CODE);
        result.put("data", data);
        result.put("msg",msg);
        return result;
    }

    public static JSONObject generateToken(Integer id,String user_type) {
        String userId = user_type+id;
        //生成 token
        Map<String, Object> payload = new HashMap<>();
        Date date = new Date();
        payload.put("userID", userId);// user ID 植入token
        payload.put("startTime", date.getTime());//生成时间
        payload.put("expiryTime", date.getTime() + ConstantDefinition.EXPIRY_TIME);//过期时间1小时

        JSONObject token = new JSONObject();
        token.put("token",Tokenizer.createToken(payload));
        return token;
    }

    public static void printInfo(String s){
        System.out.println("[INFO]"+s);
    }
}
