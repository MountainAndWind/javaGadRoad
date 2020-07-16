package test.String;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/13 14:45
 */
public class StrCreate {
    public static void main(String[] args) {
        String a="fphm,bhsje,se,jshj,invoiceType,fpdm,sfyz,fkfmc,skfmc,zymc,nsrsbh,dq,czch,billingdate,fpfj";
        String b="id,priceWithoutTax,tax,taxincludedprice,invoiceType,invoiceCode,company_seal,purchaser,seller,invoiceServiceYype,salesTaxNo,city,license_number,billingDate,imageDocId";

        String[] splita = a.split(",");
        String[] splitb = b.split(",");

        String falg="_2_";

        int length = splita.length;
        String str="";
        for (int i = 0; i <length ; i++) {
            str+=splita[i];
            str+=falg;
            str+=splitb[i]+",";
        }
        System.out.println(str);


    }
}
