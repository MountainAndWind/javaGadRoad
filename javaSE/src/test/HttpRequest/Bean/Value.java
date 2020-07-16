package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 17:34
 */
public class Value {
    private String text;
    private Attendance attendance;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Value{" +
                "text='" + text + '\'' +
                ", attendance=" + attendance +
                '}';
    }
}
