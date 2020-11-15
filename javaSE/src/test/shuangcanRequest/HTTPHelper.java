package test.shuangcanRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPHelper {
    // slf4j��־��¼��
    private static final Logger LOG = LoggerFactory.getLogger(HTTPHelper.class);

    /***
     * ��ָ��URL����GET����������
     *
     * @param apiUrl
     * @param data
     * @param projectId
     * @param signature
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String sendGet(String apiUrl, LinkedHashMap<String, String> headers,
                                 String encoding) throws Exception {
        // �����Ӧ����
        String http_RespContent = null;
        HttpURLConnection httpURLConnection = null;
        int http_StatusCode = 0;
        String http_RespMessage = null;
        try {
            LOG.info(">>>> ʵ������Url: " + apiUrl);

            // ��������
            URL url = new URL(apiUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // ��Ҫ���
            httpURLConnection.setDoOutput(true);
            // ��Ҫ����
            httpURLConnection.setDoInput(true);
            // ��������
            httpURLConnection.setUseCaches(false);
            // HTTP����ʽ
            httpURLConnection.setRequestMethod("GET");
            // ����Headers
            if (null != headers) {
                for (String key : headers.keySet()) {
                    httpURLConnection.setRequestProperty(key, headers.get(key));
                }
            }
            // ���ӻỰ
            httpURLConnection.connect();
            // �����Ӧ״̬(HTTP״̬��)
            http_StatusCode = httpURLConnection.getResponseCode();
            // �����Ӧ��Ϣ(HTTP״̬������)
            http_RespMessage = httpURLConnection.getResponseMessage();
            // �����Ӧ����
            if (HttpURLConnection.HTTP_OK == http_StatusCode) {
                // ������Ӧ���
                http_RespContent = getResponseContent(httpURLConnection);
            } else {
                // ���ط�200״̬ʱ��Ӧ���
                http_RespContent = getErrorResponseContent(httpURLConnection);
                String msg =
                        MessageFormat.format("����ʧ��: Http״̬�� = {0} , {1}", http_StatusCode, http_RespMessage);
                LOG.info(msg);
            }
        } catch (UnknownHostException e) {
            String message = MessageFormat.format("��������ʱ�����쳣: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } catch (MalformedURLException e) {
            String message = MessageFormat.format("��ʽ�����URL: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } catch (IOException e) {
            String message = MessageFormat.format("��������ʱ�����쳣: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } catch (Exception e) {
            String message = MessageFormat.format("��������ʱ�����쳣: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } finally {
            if (null != httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }
        return http_RespContent;
    }


    /***
     * ��ָ��URL����POST����������
     *
     * @param apiUrl
     * @param data
     * @param projectId
     * @param signature
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String sendPOST(String apiUrl, String data, LinkedHashMap<String, String> headers,
                                  String encoding) throws Exception {
        // �����Ӧ����
        String http_RespContent = null;
        HttpURLConnection httpURLConnection = null;
        int http_StatusCode = 0;
        String http_RespMessage = null;
        try {
            // ��������
            URL url = new URL(apiUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // ��Ҫ���
            httpURLConnection.setDoOutput(true);
            // ��Ҫ����
            httpURLConnection.setDoInput(true);
            // ��������
            httpURLConnection.setUseCaches(false);
            // HTTP����ʽ
            httpURLConnection.setRequestMethod("POST");
            // ����Headers
            if (null != headers) {
                for (String key : headers.keySet()) {
                    httpURLConnection.setRequestProperty(key, headers.get(key));
                }
            }
            // ���ӻỰ
            httpURLConnection.connect();
            // ��������������ָ���URL�������
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            // �����������
            dos.write(data.getBytes(encoding));
            dos.flush();
            dos.close();
            // �����Ӧ״̬(HTTP״̬��)
            http_StatusCode = httpURLConnection.getResponseCode();
            // �����Ӧ��Ϣ(HTTP״̬������)
            http_RespMessage = httpURLConnection.getResponseMessage();
            // �����Ӧ����
            if (HttpURLConnection.HTTP_OK == http_StatusCode) {
                // ������Ӧ���
                http_RespContent = getResponseContent(httpURLConnection);
            } else {
                // ���ط�200״̬ʱ��Ӧ���
                http_RespContent = getErrorResponseContent(httpURLConnection);
                String msg =
                        MessageFormat.format("����ʧ��: Http״̬�� = {0} , {1}", http_StatusCode, http_RespMessage);
                LOG.info(msg);
            }
        } catch (UnknownHostException e) {
            String message = MessageFormat.format("��������ʱ�����쳣: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } catch (MalformedURLException e) {
            String message = MessageFormat.format("��ʽ�����URL: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } catch (IOException e) {
            String message = MessageFormat.format("��������ʱ�����쳣: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } catch (Exception e) {
            String message = MessageFormat.format("��������ʱ�����쳣: {0}", e.getMessage());
            Exception ex = new Exception(message);
            ex.initCause(e);
            throw ex;
        } finally {
            if (null != httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }
        return http_RespContent;
    }

    /***
     * ��ȡHttpResponse��Ӧ����
     *
     * @param httpURLConnection
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private static String getResponseContent(HttpURLConnection httpURLConnection)
            throws UnsupportedEncodingException, IOException {
        StringBuffer contentBuffer = null;
        BufferedReader responseReader = null;
        try {
            contentBuffer = new StringBuffer();
            String readLine = null;
            responseReader =
                    new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while ((readLine = responseReader.readLine()) != null) {
                contentBuffer.append(readLine);
            }
        } finally {
            if (null != responseReader) {
                responseReader.close();
            }
        }
        return contentBuffer.toString();
    }

    /***
     * ��ȡHttpResponse��Ӧ����
     *
     * @param httpURLConnection
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private static String getErrorResponseContent(HttpURLConnection httpURLConnection)
            throws UnsupportedEncodingException, IOException {
        StringBuffer contentBuffer = null;
        BufferedReader responseReader = null;
        try {
            contentBuffer = new StringBuffer();
            String readLine = null;
            responseReader =
                    new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream(), "UTF-8"));
            while ((readLine = responseReader.readLine()) != null) {
                contentBuffer.append(readLine);
            }
        } finally {
            if (null != responseReader) {
                responseReader.close();
            }
        }
        return contentBuffer.toString();
    }
}