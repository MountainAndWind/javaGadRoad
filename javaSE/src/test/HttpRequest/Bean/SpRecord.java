package test.HttpRequest.Bean;

import java.util.List;

/**
 * @description: 审批流程信息，可能有多个审批节点。
 * @author: slfang
 * @time: 2020/4/10 15:04
 */
public class SpRecord {
    /**
     * 审批节点状态：1-审批中；2-已同意；3-已驳回；4-已转审
     */
    private String sp_status;
    /**
     * 节点审批方式：1-或签；2-会签
     */
    private Integer approverattr;
    /**
     * 审批节点详情,一个审批节点有多个审批人
     */
    private List<RecordDetails> details;

    public String getSp_status() {
        return sp_status;
    }

    public void setSp_status(String sp_status) {
        this.sp_status = sp_status;
    }

    public Integer getApproverattr() {
        return approverattr;
    }

    public void setApproverattr(Integer approverattr) {
        this.approverattr = approverattr;
    }

    public List<RecordDetails> getDetails() {
        return details;
    }

    public void setDetails(List<RecordDetails> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "SpRecord{" +
                "sp_status='" + sp_status + '\'' +
                ", approverattr=" + approverattr +
                ", details=" + details +
                '}';
    }
}
