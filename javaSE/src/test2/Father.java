package test2;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/9/5 16:11
 * @Version 1.0
 **/
public class Father<T> {

    private String name;

    private String age;

    private T  t;
    public Father(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public void getInfo(){
        System.out.println(this.getClass().getSimpleName()+""+this.age+"----"+this.name+"���ͣ�"+t);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
