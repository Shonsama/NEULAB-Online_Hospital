package com.neuedu.lab;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PureTest {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(c.getTime()).replaceAll("[[\\s-:punct:]]",""));
    }
}
