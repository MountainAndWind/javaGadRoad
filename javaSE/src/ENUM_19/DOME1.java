package ENUM_19;

/**
 * @description 入门dome
 * @Author slfang
 * @Time 2019/1/24 20:04
 * @Version 1.0
 **/
public enum  DOME1 {

    ONE("1"),TWO("2"),THREE("3");

    private String desc;

    DOME1(String desc){//一旦enum定义结束编译器就不准我们再使用其构造器来创建任何的实列了所以默认就是private类型
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    public static void main(String []agrs){
        for(DOME1 dome1:DOME1.values()){
            System.out.println(dome1.getDesc());//123
            System.out.println(dome1.ordinal());//012
        }
    }
}
