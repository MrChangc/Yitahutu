package com.yitahutu.cn.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017\10\17 0017.
 */
public class MachUtil {
    public static int long2int(Long id) {
        return Integer.valueOf(String.valueOf(id));
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^[1][3-8]+\\d{9}");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
    public static boolean isNumber(String number) {
        Pattern p = Pattern.compile("^[0-9]*$ ");
        Matcher m = p.matcher(number);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
    public static boolean isPassword(String mobiles) {
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
}
