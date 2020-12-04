package XainCheng.base;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: slfang
 * @time: 2020/11/24 22:27
 */
public class CallableCreateWay implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "dasda";
    }
}
