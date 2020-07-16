package test.EXCEL.git;

/**
 * @author fsl
 * @create 20120/2/15 11:54 AM
 */
public enum ExcelFormat {

    FORMAT_INTEGER("INTEGER"),
    FORMAT_DOUBLE("DOUBLE"),
    FORMAT_PERCENT("PERCENT"),
    FORMAT_DATE("DATE");

    private String value;

    ExcelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
