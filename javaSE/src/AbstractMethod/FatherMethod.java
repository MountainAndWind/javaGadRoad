package AbstractMethod;

/**
 * @description:  抽象方法主类
 * @author: slfang
 * @time: 2020/2/10 17:56
 */
public abstract class FatherMethod {

    public long  getTotalTime(){
        long startTime = System.currentTimeMillis();//开始时间模板方法

        //具体操作（留给子类完成）
        operateTime();

        long endTime = System.currentTimeMillis();//结束时间

        long time = endTime - startTime;//操作  计算时间差
        return time;
    }

    protected abstract void operateTime();


}
