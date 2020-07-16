package test.EXCEL.update;

/**
 * @description:
 * @author: slfang
 * @time: 2020/3/30 14:51
 */
public class Bean {

    private String name;

    private String age;

    private String id;

    public Bean(String name, String age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
