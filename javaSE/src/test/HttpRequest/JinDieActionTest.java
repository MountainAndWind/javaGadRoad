package test.HttpRequest;

/**
 * @description:
 * @author: slfang
 * @time: 2020/10/26 15:22
 */
public class JinDieActionTest {


    public static void main(String[] args) throws Exception {
        InvokeHelper.POST_K3CloudURL = "http://116.236.28.211:81/k3cloud";
        String dbId = "5f86a2f4286f36";
        String uid = "Demo";
        String pwd = "666666";
        int lang = 2052;

        if (InvokeHelper.Login(dbId, uid, pwd, lang)) {
// ���۶����������
// ҵ�����Id
            String sFormId = "SAL_SaleOrder";
//��Ҫ���������
// �����ֶο�����Ҫ�����Լ�ʵ��ֵ���޸�
// FCustId FSalerId FMaterialId FUnitID
            String sContent = "{\"Creator\":\"String\",\"NeedUpDateFields\":[\"FBillTypeID\",\"FDate\",\"FBusinessType\",\"FSaleOrgId\"," +
                    "\"FCustId\",\"FSettleCurrId\",\"FSalerId\",\"SAL_SaleOrder__FSaleOrderEntry\",\"FMaterialId\",\"FSettleOrgIds\"," +
                    "\"FUnitID\",\"FQty\",\"SAL_SaleOrder__FSaleOrderFinance\",\"FSettleCurrId\",\"FLocalCurrId\",\"FIsIncludedTax\"," +
                    "\"FBillTaxAmount\",\"FBillAmount\",\"FBillAllAmount\",\"FExchangeTypeId\",\"FExchangeRate\"],\"Model\":{\"FID\":0," +
                    "\"FBillTypeID\":{\"FNumber\":\"XSDD01_SYS\"},\"FBusinessType\":\"NORMAL\",\"FSaleOrgId\":{\"FNUMBER\":\"100\"}," +
                    "\"FCustId\":{\"FNUMBER\":\"CUST0001\"},\"FSettleCurrId\":{\"FNUMBER\":\"PRE001\"},\"FSalerId\":{\"FNUMBER\":\"0002\"}," +
                    "\"SAL_SaleOrder__FSaleOrderFinance\":{\"FExchangeRate\":6.8123},\"SAL_SaleOrder__FSaleOrderEntry\":[{\"FMaterialId\":" +
                    "{\"FNUMBER\":\"001\"},\"FSettleOrgIds\":{\"FNUMBER\":\"100\"},\"FUnitID\":{\"FNumber\":\"��\"},\"FQty\":10}]}}";
            InvokeHelper.Save(sFormId, sContent);
            System.out.println("hola success");
        }
    }
}
