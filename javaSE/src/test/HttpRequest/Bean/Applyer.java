package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 15:02
 */
public class Applyer {
    /**
     * 申请人userid
     */
    private String userid;
    /**
     * 申请人所在部门id
     */
    private String partyid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPartyid() {
        return partyid;
    }

    public void setPartyid(String partyid) {
        this.partyid = partyid;
    }

    @Override
    public String toString() {
        return "Applyer{" +
                "userid='" + userid + '\'' +
                ", partyid='" + partyid + '\'' +
                '}';
    }
}
