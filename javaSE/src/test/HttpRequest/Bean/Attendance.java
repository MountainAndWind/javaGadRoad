package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 17:40
 */
public class Attendance {
    private DateRange date_range;

    public DateRange getDate_range() {
        return date_range;
    }

    public void setDate_range(DateRange date_range) {
        this.date_range = date_range;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "date_range=" + date_range +
                '}';
    }
}
