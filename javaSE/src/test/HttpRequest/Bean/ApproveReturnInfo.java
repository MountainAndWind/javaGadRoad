package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 14:58
 */
public class ApproveReturnInfo {
    private Integer errcode;
    private String errmsg;
    private ApproveInfo info;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public ApproveInfo getInfo() {
        return info;
    }

    public void setInfo(ApproveInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ApproveReturnInfo{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", info=" + info +
                '}';
    }
}
