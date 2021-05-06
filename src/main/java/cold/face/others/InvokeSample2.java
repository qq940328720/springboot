package cold.face.others;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class InvokeSample2 {
    private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=pntest";
    static String pwd = "fc457216-a9a7-4172-9d10-4c4f5fa9a27b";
//    private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=testto";
//    static String pwd = "6e323963fbc04500918e8d1174610dd8";
//    private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=kxedt";
//    private static String pwd = "01e5113d19b24df8a42c4d60fd8e7cd2";

    public static void main(String arg[]) throws Exception {

//        //用户同步接口，将贵公司的用户同步成为金蝶友商网的用户
//        String userName_inner = "yljusertest121302";//"yljtest2020011401";//"yljusertest121302";
//
//        String corpAccount = "yanlijingtest1127";
//
//        String cipherCode1 = "RkKkaAyYW7ngRvO8H5pWUuJBk4fFGSjPzUQYH48e9cqWih38O4xqffMiQDuuhOBozOUSgejhOQ8IcVAptFu4QtwL3m5cuupM0OBwCfllPWHFhmcNa4CYwbegODumtdhg2U7upyKOvcCSDpH3djjN1T2LEDK2aPzU4VDZd6p6mgILLcVIfqfF0XTJoKagsxm9%2BJcsJ6ryYN%2Bgw4%2FcG1RYvBphc3jSE7vx%2FLmHr8kI078bfQIOTFAwOZckCMl6QV2vgGgCJJysXgNTDg38iGsv5CYNFY7Nq%2BDBbu1LMyc5QI6AE3IgnRA1HCv7G%2FTlSwbG8xYAW7oO%2FYxLALMMMEc%2FAlaHFliYhehA1rsEpgKvn9c1M25iWj1TaRm3P8Gr0TNNdUvFbdBYlIqt9i9vN32TT7WAHxcGAMCNpkNxBabQvZ17sfn1Oeq2xqHytrt3fiCL%2FctugjiHHQOY5lfLfK2lWbvOmSsjRR94wOrdeHguNQgZrXRTYEapey5N%2Fr8LEljlGYYI6hLn6aXfemwB2Q0YgKM3AXyiHyKPYifvUfg7jR0X7OFg2RvwV4ThovaRj82n%2BaZ4i9Gqkjuaj1Ckib0C8yuBFH1ci8c01pCk%2F2mhHvE6JBjiREZudUfBH1SrFLQ6mfrZY%2FQwuGGRnqKowNS4aV1wOfwhH84tMTviB6RrRAoLftmqNmHZo7SXGm5Pi%2BGO0YfXPiLQ6%2BReIAJeE70ZuYldlcQxX%2FIYaQj0BAm8ppreb6ffwJDvlhRnRIrlTlOuEWbV6rTd18NmvZo0VH0ZukC%2BdTLEywbv19Fo%2FVykToSulmgi8az7z7AB4kRtdG02%2BYjCfCHn85Xl5o9iu6DRLA%3D%3D";
//        String iv1 = "34d9d547-fcd1-46";
//        String secret1 = "fc457216-a9a7-4172-9d10-4c4f5fa9a27b";
//        String result = CryptographUtil.AESDecrypt(URLDecoder.decode(cipherCode1, "UTF-8"), iv1, secret1);
////        String result = CryptographUtil.AESDecrypt(cipherCode1, iv1, secret1);
//        System.out.println(result);

        corpSync("corp11xxx", pwd);
//        userSync(pwd, "10009", "yljtest2020091014240", "18629669242");
//        updateUserInfo(pwd, "10009", "18611100111");
//        userBinding("12345678901", "12345678901", pwd);
//        applyCards(pwd);
//        activeProductCard(pwd, userName_inner);
//        addService("yanlijingtest1127", "1", "101142", "123456", pwd);
//        renewService(userName_inner, "2", "PRODUCT_TYPE_ACCMULTI_PLUS", "24", pwd);
//        getUserTrajectory(pwd, "3");
//        queryPrice(pwd);
//        getOrderService(pwd_pntest);

//        userLogin("12345678901", "", 0L, pwd, "");//yljusertest121302  12345678901

//        checkMobile("18629669242", pwd);

//        getServiceCode("20190625_gy_1", "c20190625_gy_1", pwd);
    }

    private static void updateUserInfo(String pwd, String userAccount, String mobile) {
        String strTest = getupdateUserInfoXml(userAccount, mobile);
        encrypt(strTest, pwd);
    }

    private static String getupdateUserInfoXml(String userAccount, String mobile) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>updateUserInfo</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"password\">" + "jdy888888" + "</attr>");
        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
        sbXml.append("<attr name=\"mobile\">" + mobile + "</attr>");
        sbXml.append("<attr name=\"phone\">" + "18629669242" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static void getServiceCode(String userAccount, String corpAccount, String pwd) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>getServiceCode</type>");
        sbXml.append("<data>");
//        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
        sbXml.append("<attr name=\"corpAccount\">" + corpAccount + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        encrypt(sbXml.toString(), pwd);
    }

    private static String generateIv() {
        String uuid = UUID.randomUUID().toString();
        try {
            System.out.println("uuid:" + uuid);
            uuid = URLEncoder.encode(uuid, "utf-8");
            System.out.println("uuid:" + uuid);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String iv = uuid.substring(0, 16);
        return iv;
    }

    private static void userSync(String userName, String pwd) throws UnsupportedEncodingException {
        String strTest = getXml(userName);
//		String strTest = "<?xml version=\"1.0\" encoding = \"utf-8\"?><request><type>userSync</type><data><attr name=\"userAccount\">101157543091</attr><attr name=\"realName\">101157543</attr><attr name=\"password\">101157543</attr><attr name=\"bindTo\">huanqiu</attr><attr name=\"bindAsAdmin\">False</attr><attr name=\"gender\">1</attr></data></request>";
        execute(encrypt(strTest, pwd));
    }

    private static void userSync1(String pwd) throws UnsupportedEncodingException {
        String strTest = getXml1();
        execute(encrypt(strTest, pwd));
    }

    private static void corpSync(String corpAccount, String pwd) throws UnsupportedEncodingException {
        String strTest = getCorporationXml(corpAccount);
        execute(encrypt(strTest, pwd));
    }

    private static void corpSyncTest(String corpAccount, String pwd) throws UnsupportedEncodingException {
        String strTest = "<?xml version=\"1.0\" encoding = \"utf-8\"?><request><type>corporationBinding</type><data><attr name=\"corpAccount\">kzxt0001</attr><attr name=\"corpName\">深圳快学教育有限公司00</attr><attr name=\"corpNickName\"></attr><attr name=\"corpPhone\">0755-17666147000</attr><attr name=\"corpMobile\">17727600000</attr><attr name=\"corpLinkman\"></attr><attr name=\"corpFax\"></attr><attr name=\"corpEmail\"></attr><attr name=\"corpAddress\"></attr></data></request>";
        execute(encrypt(strTest, pwd));
    }

    private static void userBinding(String corpAccount, String userAccount, String pwd) throws UnsupportedEncodingException {
        String strTest = getUserBindingXml(corpAccount, userAccount);
        encrypt(strTest, pwd);
    }

    /**
     * @param userName      用户名
     * @param productTypeId 产品类型,
     * @param serviceId     要单点进去的服务id，可为空
     * @param pwd           伙伴申请的密钥
     * @param from
     * @throws UnsupportedEncodingException
     */
    private static void userLogin(String userName, String productType, long serviceId, String pwd, String from) throws UnsupportedEncodingException {
        String strTest = getUserLoginXml(userName, productType, serviceId, from);
        System.out.println(encrypt(strTest, pwd));
    }

    private static void addService(String userName, String userType, String productType, String IISPOrderId, String pwd) throws UnsupportedEncodingException {
        String strTest = getAddServiceXml(userName, userType, productType, IISPOrderId, pwd);
//		System.out.println("addService:" + strTest);
        String url = encrypt(strTest, pwd);
        System.out.println(url);
//        execute(url);
    }

    private static void addService(String userName, String userType, String productType, String IISPOrderId, String pwd, String buyerType) throws UnsupportedEncodingException {
        String strTest = getAddServiceXml2(userName, userType, productType, IISPOrderId, pwd, buyerType);
//		System.out.println("addService:" + strTest);
        String url = encrypt(strTest, pwd);
        System.out.println(url);
        execute(url);
    }

    private static void addModelService(String userName, String userType, String productType, String IISPOrderId, String pwd) throws UnsupportedEncodingException {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>modifyService</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + userName + "</attr>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + IISPOrderId + "</attr>");
        sbXml.append("<attr name=\"buyYear\">" + "1" + "</attr>");

        sbXml.append("<attr name=\"serviceId\">" + "79137960895" + "</attr>");


        JSONObject obj = new JSONObject();
        obj.put("subProductType", "PRODUCT_TYPE_V5YJXC");
        obj.put("subUserNum", 5);
        JSONObject obj2 = new JSONObject();
        obj2.put("subProductType", "PRODUCT_TYPE_V5YKJ");
        obj2.put("subUserNum", 4);
        JSONObject obj3 = new JSONObject();
        obj3.put("subProductType", "PRODUCT_TYPE_V5POS");
        obj3.put("subUserNum", 6);
        JSONObject obj4 = new JSONObject();
        obj4.put("subProductType", "PRODUCT_TYPE_V5LS");
        obj4.put("subUserNum", 3);
        JSONArray arr = new JSONArray();
        arr.add(obj);
        arr.add(obj2);
        arr.add(obj3);
        arr.add(obj4);
        System.out.println(arr.toJSONString());
        sbXml.append("<attr name=\"subProducts\">" + arr.toJSONString() + "</attr>");
//		sbXml.append("<attr name=\"maxUser\">" + "1" + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + "1" + "</attr>");
//		sbXml.append("<attr name=\"createAccountSet\">" + "true" + "</attr>");
//		sbXml.append("<attr name=\"accountSetCreator\">" + "17254320006" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
//		System.out.println("addService:" + strTest);
        String url = encrypt(sbXml.toString(), pwd);
        System.out.println(url);
        execute(url);
    }

    private static void enableService(String accountSetCreator, String userType, String productType, String serviceId, String pwd) throws UnsupportedEncodingException {
        String strTest = getEnableServiceXml(accountSetCreator, userType, productType, serviceId);
        String url = encrypt(strTest, pwd);
        System.out.println(url);
        execute(url);
    }

    private static void modifyService(String userName, String userType, String productType, String pwd) throws UnsupportedEncodingException {
        String strTest = getModifyServiceXml(userName, userType, productType, pwd);
        System.out.println("modifyService:" + strTest);
        System.out.print(encrypt(strTest, pwd));
    }

    private static void stopService(String userName, String userType, String productType, String IISPOrderId, String pwd) throws UnsupportedEncodingException {
        String strTest = getStopServiceXml(userName, userType, productType, IISPOrderId, pwd);
//		System.out.println("stopService:" + strTest);
        System.out.print(encrypt(strTest, pwd));
        execute(encrypt(strTest, pwd));
    }

    private static void reopenService(String userName, String userType, String productType, String IISPOrderId, String pwd) throws UnsupportedEncodingException {
        String strTest = getReopenServiceXml(userName, userType, productType, IISPOrderId, pwd);
//		System.out.println("reopenService:" + strTest);
        System.out.print(encrypt(strTest, pwd));
        execute(encrypt(strTest, pwd));
    }

    public static String encrypt(String strTest, String pwd) {
        try {
            String iv = generateIv();
            String strEnXml = "";
            try {
                strEnXml = CryptographUtil.AESEncrypt(strTest, iv, pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //UrlEncode
            strEnXml = URLEncoder.encode(strEnXml, "UTF-8");
            String URL = YOUSHANG_API_URL + "&iv=" + iv + "&cipherCode=" + strEnXml;
            System.out.println(URL);
            return URL;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getXml(String username) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>userSync</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + username + "</attr>");
        sbXml.append("<attr name=\"email\">test@kingdee.com</attr>");
        sbXml.append("<attr name=\"password\">888888</attr>");
        sbXml.append("<attr name=\"mobile\">" + username + "</attr>");
//		sbXml.append("<attr name=\"phone\">0755-86073803</attr>");
//		sbXml.append("<attr name=\"gender\">1</attr>");//性别，男1，女0
        sbXml.append("<attr name=\"realName\">rui</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static String getXml1() {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>userSync</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">yljusertest</attr>");
        sbXml.append("<attr name=\"email\"></attr>");
        sbXml.append("<attr name=\"mobile\">18629679247</attr>");
        sbXml.append("<attr name=\"phone\"></attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static String getCorporationXml(String corpAccount) {
//        StringBuffer sbXml = new StringBuffer(100);
//        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
//        sbXml.append("<request>");
//        sbXml.append("<type>corporationBinding</type>");
//        sbXml.append("<data>");
//        sbXml.append("<attr name=\"corpAccount\">" + corpAccount + "</attr>");
//        sbXml.append("<attr name=\"corpName\">大公司1" + "</attr>");
//        sbXml.append("<attr name=\"corpNickName\">公司1</attr>");
//        sbXml.append("<attr name=\"corpPhone\">0755-12345678</attr>");
//        sbXml.append("<attr name=\"corpMobile\">13511234567</attr>");
//        sbXml.append("<attr name=\"corpLinkman\">张先生</attr>");
//        sbXml.append("<attr name=\"corpFax\">0755-12345678</attr>");
//        sbXml.append("<attr name=\"corpEmail\">someone@somecorp.com</attr>");
//        sbXml.append("<attr name=\"corpAddress\">深圳市深南大道xx号</attr>");
//        sbXml.append("</data>");
//        sbXml.append("</request>");
//        return sbXml.toString();
        String xml="<?xml version=\"1.0\" encoding = \"utf-8\"?><request><type>corporationBinding</type><data><attr name=\"corpAccount\">plus000014</attr><attr name=\"corpName\">plus000014公司</attr><attr name=\"corpNickName\">plus000014NickName</attr><attr name=\"corpPhone\">0755-12345678</attr><attr name=\"corpMobile\">13311234564</attr><attr name=\"corpLinkman\">张先生</attr></data></request>";
        return xml;

    }

    private static String getUserBindingXml(String corpAccount, String userAccount) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>userBinding</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"corpAccount\">" + corpAccount + "</attr>");
        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
        sbXml.append("<attr name=\"bindAsAdmin\">true</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static String getUserLoginXml(String username, String productType, long serviceId, String from) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>userLogin</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + username + "</attr>");
        sbXml.append("<attr name=\"time\">" + System.currentTimeMillis() + "</attr>");
        sbXml.append("<attr name=\"productType\"></attr>");
//        sbXml.append("<attr name=\"userType\">" + 2 + "</attr>");
//        if (serviceId != 0) {
//            sbXml.append("<attr name=\"serviceId\">" + serviceId + "</attr>");
//        sbXml.append("<attr name=\"relayState\">" + "https://service.jdy.com/myservice_new/index.jsp" + "</attr>");
//        }
//        sbXml.append("<attr name=\"from\">" + from + "</attr>");
//        if (from.equals("mobile")) {
//            sbXml.append("<attr name=\"page\">mobile</attr>");
//        }
        sbXml.append("<attr name=\"page\"></attr>");
        sbXml.append("<attr name=\"clientIp\"></attr>");
//        sbXml.append("<attr name=\"userType4\">" + 2 + "</attr>");
//        sbXml.append("<attr name=\"userType5\">" + 2 + "</attr>");
//        sbXml.append("<attr name=\"userType6\">" + 2 + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static String getModifyServiceXml(String username, String userType, String productType, String pwd) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>modifyService</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + username + "</attr>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + "201212270000" + "</attr>");
        sbXml.append("<attr name=\"maxUser\">" + "1" + "</attr>");
//        sbXml.append("<attr name=\"serviceId\">" + "798695119392" + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + "10" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
//        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
//        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
//        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
//        sbXml.append("<attr name=\"IISPOrderId\">" + IISPOrderId + "</attr>");
//        sbXml.append("<attr name=\"language\">" + 1 + "</attr>");
//        sbXml.append("<attr name=\"accountNum\">" + 1 + "</attr>");
//        sbXml.append("<attr name=\"maxUser\">" + "1" + "</attr>");
//        sbXml.append("<attr name=\"buyYear\">" + "1" + "</attr>");
//        sbXml.append("<attr name=\"timeUnit\">" + 2 + "</attr>");
//        sbXml.append("<attr name=\"currency\">" + 1 + "</attr>");
////		sbXml.append("<attr name=\"createAccountSet\">" + "true" + "</attr>");
////		sbXml.append("<attr name=\"accountSetCreator\">" + "17254320006" + "</attr>");
////		sbXml.append("<attr name=\"requestId\">requestId09c2</attr>");
////		sbXml.append("<attr name=\"siNo\">siNo09c2</attr>");
    }

    private static String getStopServiceXml(String username, String userType, String productType, String IISPOrderId, String pwd) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>stopService</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + username + "</attr>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + IISPOrderId + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static String getReopenServiceXml(String username, String userType, String productType, String IISPOrderId, String pwd) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>reopenService</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + username + "</attr>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + IISPOrderId + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static String getAddServiceXml(String userAccount, String userType, String productType, String IISPOrderId, String pwd) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>addService</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + IISPOrderId + "</attr>");
        sbXml.append("<attr name=\"language\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + 3 + "</attr>");
        sbXml.append("<attr name=\"maxUser\">" + "5" + "</attr>");
        sbXml.append("<attr name=\"buyYear\">" + "3" + "</attr>");
        sbXml.append("<attr name=\"timeUnit\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"currency\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"subProducts\">" + "[{\"subProductType\":\"101144\",\"subUserNum\":1000}]" + "</attr>");
//		sbXml.append("<attr name=\"accountSetCreator\">" + "17254320006" + "</attr>");
//		sbXml.append("<attr name=\"requestId\">requestId09c2</attr>");
//		sbXml.append("<attr name=\"siNo\">siNo09c2</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static String getAddServiceXml2(String userAccount, String userType, String productType, String IISPOrderId, String pwd, String buyerType) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>addService</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + IISPOrderId + "</attr>");
        sbXml.append("<attr name=\"language\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + 3 + "</attr>");
        sbXml.append("<attr name=\"maxUser\">" + "3" + "</attr>");
        sbXml.append("<attr name=\"buyYear\">" + "2" + "</attr>");
        sbXml.append("<attr name=\"currency\">" + 1 + "</attr>");
//		sbXml.append("<attr name=\"siNo\">"+ "sino20190904" +"</attr>");
//		sbXml.append("<attr name=\"serviceId\">"+ "791379625947" +"</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static String getEnableServiceXml(String accountSetCreator, String userType, String productType, String serviceId) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>createAccountSet</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"serviceId\">" + serviceId + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"accountSetCreator\">" + accountSetCreator + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    public static void execute(String URL) {
        HttpClient client = new HttpClient();
        //http post
        PostMethod post = new PostMethod(URL);
        post.setRequestHeader("Accept-Charset", "utf-8");
        System.out.println(URL);
        try {
            int status = client.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String ret = new String(post.getResponseBody(), "UTF8");
                //获取xml格式返回，status=0是成功
                //<?xml version="1.0" encoding="UTF-8"?><response><type>userSync</type><status>0</status></response>
                System.out.println(ret + "success!");
            } else {
                System.out.println("error!");
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryProduct(String userName, String productType, String productName, String pwd) throws UnsupportedEncodingException {
        String strTest = getTryProductXml(userName, productType, productName);
        execute(encrypt(strTest, pwd));
    }

    private static String getTryProductXml(String username, String freeTryProduct, String productName) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>tryProductNew</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + username + "</attr>");

        //在线进销存3.0（标准版-试用版）对应freeTryProduct=16
        //在线会计3.0企业版Plus 对应freeTryProduct=17
        sbXml.append("<attr name=\"productName\">" + productName + "</attr>");
        sbXml.append("<attr name=\"freeTryProduct\">" + freeTryProduct + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static void checkMobile(String mobile, String pwd) throws UnsupportedEncodingException {
        String strTest = getUserCheckMobileXml(mobile);
        String url = encrypt(strTest, pwd);
    }

    private static String getUserCheckMobileXml(String mobile) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>checkMobile</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"mobile\">" + mobile + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static void getUserTrajectory(String pwd_inner, String type) throws UnsupportedEncodingException {
        String strTest = getUserTrajectoryXml(type);
        String url = encrypt(strTest, pwd_inner);
    }

    private static String getUserTrajectoryXml(String type) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>getUserTrajectory</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"startTime\">" + "1597823245000" + "</attr>");
        sbXml.append("<attr name=\"endTime\">" + "1597996807000" + "</attr>");
        sbXml.append("<attr name=\"type\">" + type + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static void renewService(String userName, String userType, String productType, String IISPOrderId, String pwd) throws UnsupportedEncodingException {
        String strTest = renewServiceXml(userName, userType, productType, IISPOrderId);
        String url = encrypt(strTest, pwd);
    }

    private static String renewServiceXml(String userName, String userType, String productType, String IISPOrderId) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>renewService</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + userName + "</attr>");
        sbXml.append("<attr name=\"userType\">" + userType + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productType + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + IISPOrderId + "</attr>");
        sbXml.append("<attr name=\"language\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + 3 + "</attr>");
        sbXml.append("<attr name=\"maxUser\">" + "2" + "</attr>");
        sbXml.append("<attr name=\"buyYear\">" + "9" + "</attr>");
        sbXml.append("<attr name=\"timeUnit\">" + 2 + "</attr>");
        sbXml.append("<attr name=\"currency\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"serviceId\">" + 79214916927L + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }

    private static void activeProductCard(String pwd_inner, String userAccount) throws UnsupportedEncodingException {
        String strTest = activeProductCardXml(userAccount);
        encrypt(strTest, pwd_inner);
    }

    private static String activeProductCardXml(String userAccount) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>activeProductCard</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
        sbXml.append("<attr name=\"productType\">" + "PRODUCT_TYPE_YKJ_EDU" + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + "1" + "</attr>");
        sbXml.append("<attr name=\"maxUser\">" + "2" + "</attr>");
        sbXml.append("<attr name=\"language\">" + "1" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static void applyCards(String pwd_inner) throws UnsupportedEncodingException {
        String strTest = applyCardsXml();
        encrypt(strTest, pwd_inner);
    }

    private static String applyCardsXml() {//PRODUCT_TYPE_YKJ_EDU,PRODUCT_TYPE_YKJ_EDU
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>applyCards</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"productType\">" + "PRODUCT_TYPE_YKJ_EDU" + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + "1" + "</attr>");
        sbXml.append("<attr name=\"maxUser\">" + "2" + "</attr>");
        sbXml.append("<attr name=\"language\">" + "1" + "</attr>");
        sbXml.append("<attr name=\"buyYear\">" + "3" + "</attr>");
        sbXml.append("<attr name=\"timeUnit\">" + "2" + "</attr>");
        sbXml.append("<attr name=\"buyCount\">" + "100" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static void getOrderService(String pwd) throws UnsupportedEncodingException {
        String strTest = getOrderServiceXml();
        encrypt(strTest, pwd);
    }

    private static String getOrderServiceXml() {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>getOrderService</type>");
        sbXml.append("<data>");
//        sbXml.append("<attr name=\"IISPOrderId\">" + "5bd9f4f8a55e4d42ad1b557e368c16d8" + "</attr>");
        sbXml.append("<attr name=\"IISPOrderId\">" + "2019042511" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static void queryPrice(String pwd) throws UnsupportedEncodingException {
        String strTest = getQueryPriceXml();
        encrypt(strTest, pwd);
//        execute(encrypt(strTest, pwd));
    }

    private static String getQueryPriceXml() {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>queryPrice</type>");
        sbXml.append("<data>");
//        sbXml.append("<attr name=\"maxUser\">" + 2 + "</attr>");
//        sbXml.append("<attr name=\"buyYear\">" + 1 + "</attr>");
//        sbXml.append("<attr name=\"accountNum\">" + 1 + "</attr>");
//        sbXml.append("<attr name=\"language\">" + 1 + "</attr>");
//        sbXml.append("<attr name=\"currency\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"productType\">" + "PRODUCT_TYPE_JDYV5" + "</attr>");
        sbXml.append("<attr name=\"subProducts\">" + "[{\"subProductType\":\"PRODUCT_TYPE_V5YKJ\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5YJXC\",\"subUserNum\":2},{\"subProductType\":\"PRODUCT_TYPE_V5LS\",\"subUserNum\":3},{\"subProductType\":\"PRODUCT_TYPE_V5POS\",\"subUserNum\":4}]" + "</attr>");
//        sbXml.append("<attr name=\"subProducts\">" + "[{\"subProductType\":\"PRODUCT_TYPE_V5YJXC\",\"subUserNum\":1}]" + "</attr>");
//        sbXml.append("<attr name=\"subProducts\">" + "[{\"subProductType\":\"PRODUCT_TYPE_V5YKJ\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5YJXC\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5LS\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5POS\",\"subUserNum\":1}]" + "</attr>");
//        sbXml.append("<attr name=\"subProducts\">" + "[{\"subProductType\":\"PRODUCT_TYPE_V5YKJ\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5YJXC\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5LS\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5POS\",\"subUserNum\":1}]" + "</attr>");
//        sbXml.append("<attr name=\"subProducts\">" + "[{\"subProductType\":\"PRODUCT_TYPE_V5YKJ\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5YJXC\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5LS\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5POS\",\"subUserNum\":1}]" + "</attr>");
//        sbXml.append("<attr name=\"subProducts\">" + "[{\"subProductType\":\"PRODUCT_TYPE_V5YKJ\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5YJXC\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5LS\",\"subUserNum\":1},{\"subProductType\":\"PRODUCT_TYPE_V5POS\",\"subUserNum\":1}]" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static void userSync(String password, String corpAccount, String userAccount, String mobile) throws UnsupportedEncodingException {
        String strTest = getXml(corpAccount, userAccount, mobile);
        encrypt(strTest, password);
    }

    private static String getXml(String corpAccount, String userAccount, String mobile) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>userSync</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"bindTo\">" + corpAccount + "</attr>");
        sbXml.append("<attr name=\"userAccount\">" + userAccount + "</attr>");
        sbXml.append("<attr name=\"email\"></attr>");
        sbXml.append("<attr name=\"mobile\">" + mobile + "</attr>");
        sbXml.append("<attr name=\"phone\"></attr>");
        sbXml.append("<attr name=\"bindAsAdmin\">true</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static void getCorpServiceList(String corpAccount, String pwd) throws UnsupportedEncodingException {
        String strTest = getCorpServiceListXml(corpAccount);
        System.out.println("getCorpServiceList:" + strTest);
        String url = encrypt(strTest, pwd);
        System.out.print(url);
//        execute(url);
    }

    private static String getCorpServiceListXml(String corpAccount) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>getCorpServiceList</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"corpAccount\">" + corpAccount + "</attr>");
        sbXml.append("<attr name=\"isValid\">" + 0 + "</attr>");
        sbXml.append("<attr name=\"page\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"size\">" + 1000 + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
    }


}
