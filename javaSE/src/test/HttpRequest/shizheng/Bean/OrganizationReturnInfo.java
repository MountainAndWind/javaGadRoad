package test.HttpRequest.shizheng.Bean;

import java.util.List;

/**
 * @description:组织部门调用返回参数
 * @author: slfang
 * @time: 2020/4/17 13:15
 */
public class OrganizationReturnInfo {
    private String Status;
    private String StatusMessage;
    private List<OrganizationInfo> Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatusMessage() {
        return StatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        StatusMessage = statusMessage;
    }

    public List<OrganizationInfo> getData() {
        return Data;
    }

    public void setData(List<OrganizationInfo> data) {
        Data = data;
    }
}
