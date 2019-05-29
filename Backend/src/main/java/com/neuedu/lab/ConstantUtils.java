package com.neuedu.lab;

import java.math.BigDecimal;

public class ConstantUtils {
    public static BigDecimal convertToNegtive(BigDecimal source){
        return source.multiply(new BigDecimal(-1));
    }
}
