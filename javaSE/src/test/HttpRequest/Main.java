package test.HttpRequest;


import java.util.HashMap;

/**
 * @date 2015年1月14日 下午1:49:50
 * @author Zheng Haibo
 * @Description: 测试
 */
public class Main {

    private static final String url="http://106.14.237.123:9182/seal/apply/images/download?businessId=2705719326632300976";
    public static void main(String[] args) {

        HttpClientUtils instance = HttpClientUtils.getInstance();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("x-qys-accesstoken", "Jn4ubvpsKm");
        hashMap.put("x-qys-signature", "907327dc1726180d3439e19996c0db5e");
        hashMap.put("x-qys-timestamp", "0");
        hashMap.put("Host", "106.14.237.123");
        instance.httpDownloadFile(url, "C:\\Users\\19504\\Desktop", null, hashMap);
    }

}