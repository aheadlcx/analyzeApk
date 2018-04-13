package qsbk.app.core.utils;

import com.tencent.connect.common.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class IDCardValidate {
    public static String validate(String str) throws ParseException {
        int i = 0;
        String str2 = "";
        String[] strArr = new String[]{"1", "0", "x", "9", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO, "7", Constants.VIA_SHARE_TYPE_INFO, "5", "4", "3", "2"};
        String[] strArr2 = new String[]{"7", "9", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, "5", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO, "4", "2", "1", Constants.VIA_SHARE_TYPE_INFO, "3", "7", "9", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, "5", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO, "4", "2"};
        str2 = "";
        if (str.length() != 15 && str.length() != 18) {
            return "身份证号码长度应该为15位或18位。";
        }
        if (str.length() == 18) {
            str2 = str.substring(0, 17);
        } else if (str.length() == 15) {
            str2 = str.substring(0, 6) + Constants.VIA_ACT_TYPE_NINETEEN + str.substring(6, 15);
        }
        if (!a(str2)) {
            return "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
        }
        String substring = str2.substring(6, 10);
        String substring2 = str2.substring(10, 12);
        String substring3 = str2.substring(12, 14);
        if (!isDataFormat(substring + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + substring2 + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + substring3)) {
            return "身份证生日无效。";
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (gregorianCalendar.get(1) - Integer.parseInt(substring) > 150 || gregorianCalendar.getTime().getTime() - simpleDateFormat.parse(substring + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + substring2 + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + substring3).getTime() < 0) {
            return "身份证生日不在有效范围。";
        }
        if (Integer.parseInt(substring2) > 12 || Integer.parseInt(substring2) == 0) {
            return "身份证月份无效";
        }
        if (Integer.parseInt(substring3) > 31 || Integer.parseInt(substring3) == 0) {
            return "身份证日期无效";
        }
        if (a().get(str2.substring(0, 2)) == null) {
            return "身份证地区编码错误。";
        }
        int i2 = 0;
        while (i < 17) {
            i2 += Integer.parseInt(String.valueOf(str2.charAt(i))) * Integer.parseInt(strArr2[i]);
            i++;
        }
        str2 = str2 + strArr[i2 % 11];
        if (str.length() != 18) {
            return "";
        }
        if (str2.equals(str.toLowerCase())) {
            return "";
        }
        return "身份证无效，不是合法的身份证号码";
    }

    private static boolean a(String str) {
        if (Pattern.compile("[0-9]*").matcher(str).matches()) {
            return true;
        }
        return false;
    }

    private static Hashtable a() {
        Hashtable hashtable = new Hashtable();
        hashtable.put(Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "北京");
        hashtable.put(Constants.VIA_REPORT_TYPE_SET_AVATAR, "天津");
        hashtable.put(Constants.VIA_REPORT_TYPE_JOININ_GROUP, "河北");
        hashtable.put(Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "山西");
        hashtable.put(Constants.VIA_REPORT_TYPE_WPA_STATE, "内蒙古");
        hashtable.put(Constants.VIA_REPORT_TYPE_QQFAVORITES, "辽宁");
        hashtable.put(Constants.VIA_REPORT_TYPE_DATALINE, "吉林");
        hashtable.put(Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    public static boolean isDataFormat(String str) {
        if (Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$").matcher(str).matches()) {
            return true;
        }
        return false;
    }
}
