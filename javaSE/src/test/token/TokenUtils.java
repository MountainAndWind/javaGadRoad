package test.token;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * token���ɺ�У��
 * @author ouyangjuns
 */
public class TokenUtils {
    private static Map<String,String> MAP_TOKENS = new HashMap<String,String>();
    private static final int VALID_TIME = 60*60*2; // token��Ч��(��)
    //private static final int VALID_TIME = 60; // token��Ч��(��)
    public static final String TOKEN_ERROR = "F"; // �Ƿ�
    public static final String TOKEN_OVERDUE = "G"; // ����
    public static final String TOKEN_FAILURE = "S"; // ʧЧ

    /**
     * ����
     * @param args
     */
    public static void main(String[] args) {

        /*clientId:HLY
       clientCecret:HLY001*/
        /*String str = "username_and_password";

        // ��ȡtoken
        String token = TokenUtils.getToken(str);
        System.out.println("token Result: " + token);*/
        String str = "username_and_password";
        String token = "74667f7669787261767c7c3a36353d2b3122200f2e2b341035313c3627203734";

        // У��token
        String checkToken = TokenUtils.checkToken(token);
        System.out.println("checkToken Result: " + checkToken);
        if(str.equals(checkToken)) {
            System.out.println("==>token verification succeeded!");
        }

    }

    /**
     * ����token,��token���Ȳ�һ��,����һ��,������MD5����������ʽ����һ��
     * �÷�ʽ��tokenֻ���ڴ�����,�����Ŀ�Ƿֲ�ʽ,�����redis�洢
     * @param str: ���ַ������Զ���,��У��tokenʱҪ����һ��
     * @return
     */
    public static String getToken(String str) {
        String token = TokenEncryptUtils.encoded(getCurrentTime()+","+str);
        MAP_TOKENS.put(str, token);
        return token;
    }

    /**
     * У��token����Ч��
     * @param token
     * @return
     */
    public static String checkToken(String token) {
        if (token == null) {
            return TOKEN_ERROR;
        }
        try{
            String[] tArr = TokenEncryptUtils.decoded(token).split(",");
            if (tArr.length != 2) {
                return TOKEN_ERROR;
            }
            // token����ʱ���
            int tokenTime = Integer.parseInt(tArr[0]);
            // ��ǰʱ���
            int currentTime = getCurrentTime();
            if (currentTime-tokenTime < VALID_TIME) {
                String tokenStr = tArr[1];
                String mToken = MAP_TOKENS.get(tokenStr);
                if (mToken == null) {
                    return TOKEN_OVERDUE;
                } else if(!mToken.equals(token)) {
                    return TOKEN_FAILURE;
                }
                return tokenStr;
            } else {
                return TOKEN_OVERDUE;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return TOKEN_ERROR;
    }

    /**��ȡ��ǰʱ�����10λ������*/
    public static int getCurrentTime() {
        return (int)(System.currentTimeMillis()/1000);
    }

    /**
     * �Ƴ����ڵ�token
     */
    public static void removeInvalidToken() {
        int currentTime = getCurrentTime();
        for (Entry<String,String> entry : MAP_TOKENS.entrySet()) {
            String[] tArr = TokenEncryptUtils.decoded(entry.getValue()).split(",");
            int tokenTime = Integer.parseInt(tArr[0]);
            if(currentTime-tokenTime > VALID_TIME){
                MAP_TOKENS.remove(entry.getKey());
            }
        }
    }

}