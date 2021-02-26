package cn.bdqn.easybuy.util;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class tset {
    public static void main(String[] args) {
        Date date = new Date(); // 从servlet里面来
        System.out.println(date.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 写到Dao里面去
        System.out.println(sdf.format(date));   // 输出string类型的字符串

    }
}
