package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/12 17:05
 */
public class SpNo {
    private String spNo;

    public String getSpNo() {
        return spNo;
    }

    public void setSpNo(String spNo) {
        this.spNo = spNo;
    }

    @Override
    public String toString() {
        return "SpNo{" +
                "spNo='" + spNo + '\'' +
                '}';
    }
}
