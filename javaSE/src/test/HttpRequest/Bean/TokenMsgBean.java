package test.HttpRequest.Bean;

/**
 * @description: 回去token接口放回bean
 * @author: slfang
 * @time: 2020/4/12 16:52
 */
public class TokenMsgBean {
    private String errcode;
    private String errmsg;
    private String access_token;
    private String expires_in;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "TokenMsgBean{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
