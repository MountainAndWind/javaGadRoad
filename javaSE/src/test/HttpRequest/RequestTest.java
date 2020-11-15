package test.HttpRequest;


import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;

public class RequestTest {

    public static void main(String[] args) {
        // Ӧ��ID
        String appId = "7438817329";
        // Ӧ����Կ
        String appKey = "9695c95c9bff4b4b93a18789779ceac6";
        // �ӿڵ�������  ��ʽhttps://openapi.esign.cn   ���� https://smlopenapi.esign.cn
        String host = "https://smlopenapi.esign.cn";
        /*// �����˻�ID
        String accountId = "4c66c987eXXXc2eb474b5abc54";*/
        // ����ǩ����Ȩ-POST����
        testPost(appId, appKey, host);
        // ����ǩ����Ȩ-GET����
        //testGet(appId, appKey, host, accountId);
    }


    /***
     * ����ǩ����Ȩ-POST����
     *
     * @param appId
     * @param appKey
     * @param host
     */
    public static void testPost(String appId, String appKey, String host) {
        // ���˴����˺Žӿڵ�ַ
        //String accountsApi = "/v1/accounts/createByThirdPartyUserId";
        String accountsApi = "/v2/identity/verify/individual/base";
        // ���˴����˺Žӿ������ַ
        String accountsApiUrl = host + accountsApi;

        try {
            // ��������Body��
           /* JSONObject reqBodyObj = new JSONObject();
            reqBodyObj.put("idNo", "342523199707227616");
            reqBodyObj.put("name", "������");

            // ����Body������
            String reqBodyData = reqBodyObj.toString();*/
            String reqBodyData = "{'name':'������','idNo':'342523199707227616'}";
            // ������Body���ڵ����ݼ���ContentMD5
            String contentMD5 = doContentMD5(reqBodyData);

            // ������ǩ���ַ���
            String method = "POST";
            String accept = "*/*";
            String contentType = "application/json; charset=UTF-8";
            String url = accountsApi;
            String headers = "" ;
            String date = "";

            StringBuffer sb = new StringBuffer();
            sb.append(method).append("\n").append(accept).append("\n").append(contentMD5).append("\n")
                    .append(contentType).append("\n").append(date).append("\n");
            if ("".equals(headers)) {
                sb.append(headers).append(url);
            } else {
                sb.append(headers).append("\n").append(url);
            }

            // ������������ǩ�����������
            String plaintext = sb.toString();
            // ��������ǩ��ֵ
            String reqSignature = doSignatureBase64(plaintext, appKey);

            // ��ȡʱ���(��ȷ������)
            long timeStamp = timeStamp();

            // ��������ͷ
            LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
            header.put("X-Tsign-Open-App-Id", appId);
            header.put("X-Tsign-Open-Auth-Mode", "Signature");
            header.put("X-Tsign-Open-Ca-Timestamp", String.valueOf(timeStamp));
            header.put("Accept", accept);
            header.put("Content-Type", contentType);
            header.put("X-Tsign-Open-Ca-Signature", reqSignature);
            header.put("Content-MD5", contentMD5);

            // ����POST����
            String result = HTTPHelper.sendPOST(accountsApiUrl, reqBodyData, header, "UTF-8");
            JSONObject resultObj = JSONObject.fromObject(result);
            System.out.println("���󷵻���Ϣ�� " + resultObj.toString());
            /*{"code":0,"message":"�ɹ�","data":{"verifyId":"acb21422-fc31-4c18-b57b-f0284a01b64a"}}*/
        } catch (Exception e) {
            e.printStackTrace();
            String msg = MessageFormat.format("����ǩ����Ȩ��ʽ���ýӿڳ����쳣: {0}", e.getMessage());
            System.out.println(msg);
        }
    }

    /***
     * ����ǩ����Ȩ-GET����
     *
     * @param appId
     * @param appKey
     * @param host
     */
    public static void testGet(String appId, String appKey, String host, String accountId) {
        // ��ѯ����ӡ�½ӿ�
        String getPersonSealsApi = "/v1/accounts/" + accountId + "/seals?offset=1&size=10";
        // ��ѯ����ӡ�½ӿ������ַ
        String getPersonSealsApi_Url = host + getPersonSealsApi;

        try {
            // GET����ʱContentMD5Ϊ""
            String contentMD5 = "";

            // ������ǩ���ַ���
            String method = "GET";
            String accept = "*/*";
            String contentType = "application/json; charset=UTF-8";
            String url = getPersonSealsApi;
            String date = "";
            String headers = "";

            StringBuffer sb = new StringBuffer();
            sb.append(method).append("\n").append(accept).append("\n").append(contentMD5).append("\n")
                    .append(contentType).append("\n").append(date).append("\n");
            if ("".equals(headers)) {
                sb.append(headers).append(url);
            } else {
                sb.append(headers).append("\n").append(url);
            }

            // ������������ǩ�����������
            String plaintext = sb.toString();
            // ��������ǩ��ֵ
            String reqSignature = doSignatureBase64(plaintext, appKey);

            // ��ȡʱ���(��ȷ������)
            long timeStamp = timeStamp();

            // ��������ͷ
            LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
            header.put("X-Tsign-Open-App-Id", appId);
            header.put("X-Tsign-Open-Auth-Mode", "Signature");
            header.put("X-Tsign-Open-Ca-Timestamp", String.valueOf(timeStamp));
            header.put("Accept", accept);
            header.put("Content-Type", contentType);
            header.put("X-Tsign-Open-Ca-Signature", reqSignature);
            header.put("Content-MD5", contentMD5);

            // ����GET����
            String result = HTTPHelper.sendGet(getPersonSealsApi_Url, header, "UTF-8");
            JSONObject resultObj = JSONObject.fromObject(result);
            System.out.println("���󷵻���Ϣ�� " + resultObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            String msg = MessageFormat.format("����ǩ����Ȩ��ʽ���ýӿڳ����쳣: {0}", e.getMessage());
            System.out.println(msg);
        }
    }

    /***
     *
     * @param str ���������Ϣ
     * @return MD5�����ժҪֵ��Base64����(ContentMD5)
     * @throws Exception ���ܹ����е��쳣��Ϣ
     */
    public static String doContentMD5(String str) throws Exception {
        byte[] md5Bytes = null;
        MessageDigest md5 = null;
        String contentMD5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            // ����md5����
            md5.update(str.getBytes("UTF-8"));
            // ��ȡ�ļ�MD5�Ķ��������飨128λ��
            md5Bytes = md5.digest();
            // ��MD5ժҪ
            //��Ķ���������md5Bytesʹ��Base64���б��루�����Ƕ�32λ��16�����ַ������б��룩
            contentMD5 = new String(Base64.encodeBase64(md5Bytes), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            String msg = MessageFormat.format("��֧�ִ��㷨: {0}", e.getMessage());
            Exception ex = new Exception(msg);
            ex.initCause(e);
            throw ex;
        } catch (UnsupportedEncodingException e) {
            String msg = MessageFormat.format("��֧�ֵ��ַ�����: {0}", e.getMessage());
            Exception ex = new Exception(msg);
            ex.initCause(e);
            throw ex;
        }
        return contentMD5;
    }

    /***
     * ��������ǩ��ֵ
     *
     * @param message ���������Ϣ
     * @param secret ��Կ
     * @return HmacSHA256�����ժҪֵ��Base64����
     * @throws Exception ���ܹ����е��쳣��Ϣ
     */
    public static String doSignatureBase64(String message, String secret) throws Exception {
        String algorithm = "HmacSHA256";
        Mac hmacSha256;
        String digestBase64 = null;
        try {
            hmacSha256 = Mac.getInstance(algorithm);
            byte[] keyBytes = secret.getBytes("UTF-8");
            byte[] messageBytes = message.getBytes("UTF-8");
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
            // ʹ��HmacSHA256�Զ�����������ϢBytes����ժҪ
            byte[] digestBytes = hmacSha256.doFinal(messageBytes);
            // ��ժҪ��Ľ��digestBytesת����ʮ�����Ƶ��ַ���
            // String digestBase64 = Hex.encodeHexString(digestBytes);
            // ��ժҪ��Ľ��digestBytesʹ��Base64���б���
            digestBase64 = new String(Base64.encodeBase64(digestBytes), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            String msg = MessageFormat.format("��֧�ִ��㷨: {0}", e.getMessage());
            Exception ex = new Exception(msg);
            ex.initCause(e);
            throw ex;
        } catch (UnsupportedEncodingException e) {
            String msg = MessageFormat.format("��֧�ֵ��ַ�����: {0}", e.getMessage());
            Exception ex = new Exception(msg);
            ex.initCause(e);
            throw ex;
        } catch (InvalidKeyException e) {
            String msg = MessageFormat.format("��Ч����Կ�淶: {0}", e.getMessage());
            Exception ex = new Exception(msg);
            ex.initCause(e);
            throw ex;
        }
        return digestBase64;
    }

    /***
     * ��ȡʱ���(���뼶)
     *
     * @return ���뼶ʱ���,�� 1578446909000
     */
    public static long timeStamp() {
        long timeStamp = System.currentTimeMillis();
        return timeStamp;
    }


}