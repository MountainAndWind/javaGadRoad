package test.EXCEL.git;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/17 20:44
 */
public class TestInfo {
    private String id;
    private String sqr;
    private String date;
    private String je;
    private String total;

    public TestInfo() {
    }

    public TestInfo(String id, String sqr, String date, String je, String total) {
        this.id = id;
        this.sqr = sqr;
        this.date = date;
        this.je = je;
        this.total = total;
    }



    public TestInfo(String id, String sqr, String date, String je) {
        this.id = id;
        this.sqr = sqr;
        this.date = date;
        this.je = je;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
