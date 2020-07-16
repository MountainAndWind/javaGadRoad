package test.DataBaseCon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/12/16 15:57
 * @Version 1.0
 **/
public class MianTest {

    public static void main(String[] args) {
        OperateOracle operateOracle = new OperateOracle();
        Connection connection = operateOracle.getConnection();
        String sql="select * from XXGFAOB_JOURNAL_V a  where a.LEDGER_NAME='T073311_TCE_IFRS'" +
                " and CREATION_DATE='当前时间-1' and a.NATURAL_ACCOUNT in " +
                "(select CODENAME from FnaBudgetfeeType where ARCHIVE='0')";
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                String id = rs.getString("PERIOD_NAME");
                String name = rs.getString("PERIOD_YEAR");
                String gender = rs.getString("PERIOD_NUM");
                String age = rs.getString("D_KMK_CD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            operateOracle.ReleaseResource();
        }

    }
}
