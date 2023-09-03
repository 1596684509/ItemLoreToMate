package xiao_student.itemloretomate.MyListener;

public interface MyListener {

    void onStart();
    void onEnd();
    /*＊
    もしtrueを戻したら、元事件を続く
     */
    boolean onRun();

}
