package xiao_student.itemloretomate.Util;

import xiao_student.itemloretomate.MyEvent.TimerEventAble;

import java.util.Timer;
import java.util.TimerTask;

/**
 * スキル時間計算
 * もし listener は null だったら　CD計算
 * じゃなかったら　buff　の存在時間を計算
 */
public class EventTimer {

    private int timer;
    private boolean timerIsOver = true;
    private int firstTimer;
    private TimerEventAble listener;

    public EventTimer(TimerEventAble listener) {
        this.listener = listener;
    }

    public void setTimer(int timer) {
        this.timer = timer;
        this.firstTimer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void reduceCD() {

        timer--;

    }

    public void setTimerIsOver(boolean timerIsOver) {
        this.timerIsOver = timerIsOver;
    }

    public boolean isTimerIsOver() {
        return timerIsOver;
    }

    public int getFirstCD() {
        return firstTimer;
    }

    public void start() {

        setTimerIsOver(false);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                reduceCD();

                if(listener != null && !isTimerIsOver()) {

                    listener.run();

                }

                //计时结束
                if(getTimer() == 0) {

                    setTimerIsOver(true);
                    setTimer(getFirstCD());
                    this.cancel();

                }

            }
        }, 0, 1000l);

    }

}
