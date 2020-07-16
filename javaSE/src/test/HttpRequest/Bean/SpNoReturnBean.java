package test.HttpRequest.Bean;

import java.util.List;

/**
 * @description: 调用审批单号哦回写数据bean
 * @author: slfang
 * @time: 2020/4/12 17:03
 */
public class SpNoReturnBean {
    private Integer errcode;
    private String errmsg;
    private List<String> sp_no_list;
    private Integer next_cursor;


    public Integer getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public List<String> getSp_no_list() {
        return sp_no_list;
    }

    public void setSp_no_list(List<String> sp_no_list) {
        this.sp_no_list = sp_no_list;
    }

    public Integer getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(Integer next_cursor) {
        this.next_cursor = next_cursor;
    }

    @Override
    public String toString() {
        return "SpNoReturnBean{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", sp_no_list=" + sp_no_list +
                ", next_cursor=" + next_cursor +
                '}';
    }
}
