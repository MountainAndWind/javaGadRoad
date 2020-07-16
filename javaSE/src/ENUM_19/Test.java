package ENUM_19;
import static ENUM_19.DOME1.*;
/**
 * @description
 * @Author slfang
 * @Time 2019/1/24 20:09
 * @Version 1.0
 **/
public class Test {

    DOME1 dome1;

    public Test(DOME1 dome1) {
        this.dome1 = dome1;
    }
    @Override
    public String toString() {
        return "Test{" +
                "ONE=" + ONE +
                '}';
    }

    public static void main(String[] args){
        System.out.println(ONE.ordinal());
        System.out.println();
    }
}
