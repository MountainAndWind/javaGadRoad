package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 17:41
 */
public class DateRange {
    private String type;
    private String new_begin;
    private String new_end;
    private String new_duration;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNew_begin() {
        return new_begin;
    }

    public void setNew_begin(String new_begin) {
        this.new_begin = new_begin;
    }

    public String getNew_end() {
        return new_end;
    }

    public void setNew_end(String new_end) {
        this.new_end = new_end;
    }

    public String getNew_duration() {
        return new_duration;
    }

    public void setNew_duration(String new_duration) {
        this.new_duration = new_duration;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "type='" + type + '\'' +
                ", new_begin='" + new_begin + '\'' +
                ", new_end='" + new_end + '\'' +
                ", new_duration='" + new_duration + '\'' +
                '}';
    }
}
