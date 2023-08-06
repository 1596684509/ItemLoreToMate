package xiao_student.itemloretomate.Util;

import java.util.Formatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {

    /**
     * 用于获取一个事件是否触发
     * @param probability 触发几率
     * @return default false
     */
    public static boolean getEventBoolean(double probability) {

        Random random = new Random();
        double probabilityNum = random.nextInt(100)+1;

        if(probability >= probabilityNum) {

            return true;

        }

        return false;

    }

    /**
     * String　にほうしい　String　があるかどうかを探す
     * @param s1　検索先
     * @param target　探したい String
     * @return true=ある
     */
    public static boolean getFinded(String s1, String target) {

        Pattern pattern = Pattern.compile(target);
        Matcher matcher = pattern.matcher(s1);
        return matcher.find();

    }

}
