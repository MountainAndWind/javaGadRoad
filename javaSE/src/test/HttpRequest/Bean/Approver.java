package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 16:22
 */
public class Approver {
    /**
     * 分支审批人userid
     */
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Approver{" +
                "userid='" + userid + '\'' +
                '}';
    }
}
