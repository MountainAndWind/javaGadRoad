package AbstractMethod;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/10 17:58
 */
public class BMethod extends FatherMethod {
    @Override
    protected void operateTime() {
        int num = 0;
        for(int i = 0 ; i < 100000 ; i++) {
            num += 1;
        }
    }
}
