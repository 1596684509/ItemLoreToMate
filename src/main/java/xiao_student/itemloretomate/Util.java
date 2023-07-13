package xiao_student.itemloretomate;

import java.util.Random;

public class Util {

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

}
