package test.HttpRequest.Bean;

/**
 * @description: 审批节点详情,一个审批节点有多个审批人
 * @author: slfang
 * @time: 2020/4/10 16:17
 */
public class RecordDetails {
    /**
     * 分支审批人
     */
    private Approver approver;

    /**
     * 审批意见
     */
    private String speech;

    /**
     * 分支审批人审批状态：1-审批中；2-已同意；3-已驳回；4-已转审
     */
    private String sp_status;

    /**
     * 节点分支审批人审批操作时间戳，0表示未操作
     */
    private String sptime;

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getSp_status() {
        return sp_status;
    }

    public void setSp_status(String sp_status) {
        this.sp_status = sp_status;
    }

    public String getSptime() {
        return sptime;
    }

    public void setSptime(String sptime) {
        this.sptime = sptime;
    }

    @Override
    public String toString() {
        return "RecordDetails{" +
                "approver=" + approver +
                ", speech='" + speech + '\'' +
                ", sp_status='" + sp_status + '\'' +
                ", sptime='" + sptime + '\'' +
                '}';
    }
}
