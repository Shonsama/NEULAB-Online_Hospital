package com.neuedu.lab;

import com.neuedu.lab.Utils.ConstantUtils;
import org.junit.Test;

import java.text.ParseException;

public class ConstantUtilsTest {
    @Test
    public void TestGetSystemTime() throws ParseException {
        System.out.println(ConstantUtils.getSystemInitializeTime());
    }
}
