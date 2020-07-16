package AbstractMethod;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/10 18:01
 */
public class MainTest {

    public static void main(String[] args) {
        AMethod a = new AMethod();
        System.out.println(a.getTotalTime());

        BMethod b = new BMethod();
        System.out.println(b.getTotalTime());
    }
}
