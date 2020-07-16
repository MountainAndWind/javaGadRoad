package test.HttpRequest.Bean;

import java.util.List;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 17:31
 */
public class Contents {
    private String control;
    private String id;
    private List<Title> title;
    private Value value;

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Title> getTitle() {
        return title;
    }

    public void setTitle(List<Title> title) {
        this.title = title;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Contents{" +
                "control='" + control + '\'' +
                ", id='" + id + '\'' +
                ", title=" + title +
                ", value=" + value +
                '}';
    }
}
