package test.HttpRequest.Bean;

import java.util.List;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 14:57
 */
public class ApproveInfo {

    /**
     * 审批编号
     */
    private String sp_no;
    /**
     * 审批模板名称
     */
    private String sp_name;
    /**
     * 申请单状态
     */
    private String sp_status;

    /**
     * 审批申请提交时间,Unix时间戳
     */
    private String apply_time;

    /**
     * 	申请人信息
     */
    private Applyer applyer;

    /**
     * 审批流程信息，可能有多个审批节点。
     */
    private List<SpRecord> sp_record;

    private ApplyDate apply_data;

    public Applyer getApplyer() {
        return applyer;
    }

    public void setApplyer(Applyer applyer) {
        this.applyer = applyer;
    }

    public List<SpRecord> getSp_record() {
        return sp_record;
    }

    public void setSp_record(List<SpRecord> sp_record) {
        this.sp_record = sp_record;
    }

    public String getSp_no() {
        return sp_no;
    }

    public void setSp_no(String sp_no) {
        this.sp_no = sp_no;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }

    public String getSp_status() {
        return sp_status;
    }

    public void setSp_status(String sp_status) {
        this.sp_status = sp_status;
    }


    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public ApplyDate getApply_data() {
        return apply_data;
    }

    public void setApply_data(ApplyDate apply_data) {
        this.apply_data = apply_data;
    }

    @Override
    public String toString() {
        return "ApproveInfo{" +
                "sp_no='" + sp_no + '\'' +
                ", sp_name='" + sp_name + '\'' +
                ", sp_status='" + sp_status + '\'' +
                ", apply_time='" + apply_time + '\'' +
                ", applyer=" + applyer +
                ", sp_record=" + sp_record +
                ", apply_data=" + apply_data +
                '}';
    }
}
