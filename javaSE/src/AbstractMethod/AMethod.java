package AbstractMethod;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/10 17:58
 */
public class AMethod  extends FatherMethod{
    @Override
    protected void operateTime() {
        String str = "";
        for(int i = 0 ; i < 1000 ; i++) {
            str += 1;
        }
    }
}
