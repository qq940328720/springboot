package cold.face.others;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class InvokeSample2 {
    //	private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=aexjy";
    private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=testto";
//	private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=testone";
//	private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=pntest";
//	private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=csgs";
//	private static String YOUSHANG_API_URL = "http://agent.youshang.com/federation/commonservice/servicePortal.do?shortName=hr";

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
        execute(encrypt(strTest, pwd));
    }

    /**
     * @param userName      用户名
     * @param productTypeId 产品类型,
     * @param serviceId     要单点进去的服务id，可为空
     * @param pwd           伙伴申请的密钥
     * @param from
     * @throws UnsupportedEncodingException
     */
    private static void userLogin(String userName, long productTypeId, long serviceId, String pwd, String from) throws UnsupportedEncodingException {
        String strTest = getUserLoginXml(userName, productTypeId, serviceId, from);
        System.out.println("userLogin:" + strTest);
        System.out.println(encrypt(strTest, pwd));
    }

    private static void addService(String userName, String userType, String productType, String IISPOrderId, String pwd) throws UnsupportedEncodingException {
        String strTest = getAddServiceXml(userName, userType, productType, IISPOrderId, pwd);
//		System.out.println("addService:" + strTest);
        String url = encrypt(strTest, pwd);
        System.out.println(url);
        execute(url);
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

    public static String encrypt(String strTest, String pwd) throws UnsupportedEncodingException {
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
        return URL;

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
        sbXml.append("<attr name=\"userAccount\">18692641842</attr>");
        sbXml.append("<attr name=\"email\"></attr>");
        sbXml.append("<attr name=\"mobile\">15823202203</attr>");
        sbXml.append("<attr name=\"phone\"></attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
    }

    private static String getCorporationXml(String corpAccount) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>corporationBinding</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"corpAccount\">" + corpAccount + "</attr>");
        sbXml.append("<attr name=\"corpName\">测试公司" + corpAccount + "</attr>");
        sbXml.append("<attr name=\"corpNickName\">测试公司</attr>");
        sbXml.append("<attr name=\"corpPhone\">hedong1012</attr>");
        sbXml.append("<attr name=\"corpMobile\">18325296018</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        return sbXml.toString();
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

    private static String getUserLoginXml(String username, long productTypeId, long serviceId, String from) {
        StringBuffer sbXml = new StringBuffer(100);
        sbXml.append("<?xml version=\"1.0\" encoding = \"utf-8\"?>");
        sbXml.append("<request>");
        sbXml.append("<type>userLogin</type>");
        sbXml.append("<data>");
        sbXml.append("<attr name=\"userAccount\">" + username + "</attr>");
        sbXml.append("<attr name=\"time\">" + System.currentTimeMillis() + "</attr>");
        sbXml.append("<attr name=\"productType\">" + productTypeId + "</attr>");
        sbXml.append("<attr name=\"userType\">" + 2 + "</attr>");
        if (serviceId != 0) {
            sbXml.append("<attr name=\"serviceId\">" + serviceId + "</attr>");
        }
        sbXml.append("<attr name=\"from\">" + from + "</attr>");
        if (from.equals("mobile")) {
            sbXml.append("<attr name=\"page\">mobile</attr>");
        }
        sbXml.append("<attr name=\"userType2\">" + 2 + "</attr>");
        sbXml.append("<attr name=\"userType3\">" + 2 + "</attr>");
        sbXml.append("<attr name=\"userType4\">" + 2 + "</attr>");
        sbXml.append("<attr name=\"userType5\">" + 2 + "</attr>");
        sbXml.append("<attr name=\"userType6\">" + 2 + "</attr>");
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
        sbXml.append("<attr name=\"maxUser\">" + "2" + "</attr>");
        sbXml.append("<attr name=\"serviceId\">" + "798695119392" + "</attr>");
        sbXml.append("<attr name=\"accountNum\">" + "10" + "</attr>");
        sbXml.append("</data>");
        sbXml.append("</request>");
        System.out.println(sbXml.toString());
        return sbXml.toString();
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
        sbXml.append("<attr name=\"accountNum\">" + 1 + "</attr>");
        sbXml.append("<attr name=\"maxUser\">" + "1" + "</attr>");
        sbXml.append("<attr name=\"buyYear\">" + "1" + "</attr>");
        sbXml.append("<attr name=\"currency\">" + 1 + "</attr>");
//		sbXml.append("<attr name=\"createAccountSet\">" + "true" + "</attr>");
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

    public static void main(String arg[]) throws Exception {
        //用户同步接口，将贵公司的用户同步成为金蝶友商网的用户
        String userName = "17254321011";//u09061548@testto  u09041412@testto
//		String userName = "kingdeetestloginrui";//u09061548@testto  u09041412@testto
//		String userName = "pntestcrs2";
//		String userName = "10154795";
//		String userName = "20190823_pntest_2";
        String pwd = "6e323963fbc04500918e8d1174610dd8";//testto 内网

//		String pwd = "2506ed69087643b3aa23b9bdd629347b";//海尔内网


//		String pwd = "5e992511-8861-4535-a690-6e6ccf0bc34d";//云南爱尔信 外网
//		userSync1(pwd);
//		String userName = "18923136545";
//		String userName = "test1101";
//		String pwd = "bc6ab032-5037-43d2-9042-c29a68a22e3b";//1568609a-0889-4d3d-972a-25fb0e2abe04
//		String pwd1 = "a7cd4a20-d855-45c3-8604-7e696d8a600e";

//		String pwd = "d4fb922a-6542-4c6e-b138-726735816966";//

//		String pwd = "fc457216-a9a7-4172-9d10-4c4f5fa9a27b";//pntest 外网 伙伴测试公司
//		String pwd = "a2b9bdcd-85cb-46af-bdcb-36594ef78585";//testone 模拟 测试111
//		String pwd = "bc6ab032-5037-43d2-9042-c29a68a22e3b";//csgs 内网 测试公司1

//		String pwd = "bc6ab032-5037-43d2-9042-c29a68a22e3b";//shmckj 外网 上海美橙科技

//		String pwd = "5e992511-8861-4535-a690-6e6ccf0bc34d";//csgs 模拟  kingdeetest1181
        //1、同步用户接口
//		userSync(userName, pwd);

		String corpAccount = "c17254320006";
//        String corpAccount = "c20190903_testto_1";
//		String corpAccount = "c20190823_pntest_1";
//		corpSync(corpAccount, pwd);
////////		
//		userBinding(corpAccount,userName, pwd);
//		addService(userName, "SERVICE_TYPE_AGENT_PERSONAL ", pwd);
//		addService(userName, "1", "100876", "e784c8c6-6695-44f5-a333-4b45990d32f8", pwd, 1+"");
//		String busNo = "201812041357";
//		addService(corpAccount, "1", "PRODUCT_TYPE_ACCMULTI_PLUS","23", pwd);
//		addService(corpAccount, "1", "PRODUCT_TYPE_YJXC","23", pwd);
//		addService(corpAccount, "1", "PRODUCT_TYPE_ACCMULTI_PLUS","23", pwd);
//		addService(corpAccount, "1", "PRODUCT_TYPE_YCM","23", pwd);

//		addService(corpAccount, "1", "PRODUCT_TYPE_YJXC","201909031829", pwd, 1+"");
//		
//		enableService(userName,"1","PRODUCT_TYPE_YKJ","79102911695", pwd);
//		addModelService(corpAccount, "1", "PRODUCT_TYPE_JDYV5","23", pwd);
//		addService(userName, "100876", pwd);
//		addService(userName, "100871", pwd);

//		modifyService(userName, "2", "PRODUCT_TYPE_ACCMULTI_PLUS", pwd);

//        getCorpServiceList(corpAccount, pwd);

//        FederationServicePageParam fs = new FederationServicePageParam();
//        fs.setFederationPartnerId(14570);
//        fs.setIsValid(1);
//        fs.setPageNum(1);
//        fs.setPageSize(1000);
//        fs.setServiceType("");
//        fs.setTime(new Date());
//        fs.setIsEnable(1);
//
//        String content = encrypt(JSONObject.toJSONString(fs), pwd);
//
//        System.out.println("content:" + content);
        
//		stopService(corpAccount, "1", "100852", busNo, pwd);
//		reopenService(corpAccount, "1", "100942", busNo, pwd);

//		addService(userName, "SERVICE_TYPE_SCM", pwd);
//		addService(userName, "8", pwd);
//		tryProduct(userName, "SERVICE_TYPY_SCMB" , "在线进销存3.0（基础版-试用版）",pwd);
//		tryProduct(userName, "16" , "在线进销存3.0（标准版-试用版）",pwd);//试用云进销存
//		tryProduct(userName, "17" , "在线会计3.0企业版Plus",pwd);//试用云会计
//		tryProduct(userName, "17,16" , "在线会计3.0企业版Plus,在线进销存3.0（标准版-试用版）",pwd);//试用云财贸
//		
//		tryProduct(userName, "SERVICE_TYPY_SCMS" , "在线进销存3.0（标准版-试用版）",pwd);

//		
//		String cipherCode = "R5pFIsgpVPU%2FDuFoYeYFSy2FalgMC4A79EV%2FXOLeyvxEmpFzFOc1qPT1mkrMUl8LTssFn%2FhA88FN68ENuXUmywqNf0FfFH6W6EN8AYzw9W%2FkSRv3oYPHuVv8Srz2PC2uYQ8SafzZgUhNAv2Xpo8xwZ9hOQ6bH%2FonPtbjOW0gAjRllAd3N500AQqS0E4FQgc8TdNml4r3m0yyHLI49HEY3N9glXPFq1ZNh%2BJ%2F%2B4WWhUi6TjVCpJH2ElDLIHFCKSzZ30coW8QJEcPDTqlIyF5Ve2suz86kkzMQUemNo82kB8D0d20HKDKBbKJBMM3nFkfq5ihFDerF1SJlVa0a4lZer3jrneTo3m7T%2F30%2FCx6JJ4We%2BOejb%2FQgHePCzTZrMf7Pgkv0itHEIoF%2BgvPhwWlRrA%3D%3D";
//		String iv = "3f60c2e1-efc9-41";
//		
//		String plainText = CryptographUtil.AESDecrypt(cipherCode, iv, pwd);
//		System.out.println(plainText);

        //单点登录接口
//		userLogin(userName, 8 , 7989157792717904L, pwd, "172.20.11.206");
//		userLogin(userName, 17 , 0L, pwd, "mobile");
//		userLogin(userName, 17 , 79869263934L, pwd, "mobile");
//		
//		userLogin(userName, 0 , 0L, pwd, "mobile");
//		userLogin(userName, 0 , 7963921149121L, pwd, "mobile1");
//		userLogin(userName, 1 , 791819159204L, pwd, "120.85.77.214");
//		userLogin(userName, 17 , 79869286936L, pwd, "120.85.77.214");//17254320006
//		userLogin(userName, 17 , 79869316910L, pwd, "120.85.77.214");//17254320008

//		userLogin(userName, 16 , 79102911691L, pwd, "172.20.11.206");
//		userLogin("17254320008", 0 , 0L, pwd, "172.20.11.206");
//		userLogin("17254320010", 0 , 0L, pwd, "172.20.11.206");
//		userLogin(userName, 0 , 79869316914L, pwd, "172.20.11.206");
//		userLogin(userName, 0 , 79869505916L, pwd, "mobile1");

//        String cipherCode="YpoDM29Hr/PJEcEwsvz1vHs5T4Av1F6Q2T7J7NJd2F2OCqqS0fyKeP+CryMlHuy0R9atFUAg7o/dKx5N5uOCucSY1eY/rcD+Rde8zOEIrKrMch/WrENp7gLp3ZVnWFlS7vrJsylbnhOUoceMzntWajeUCVgC1Av8wSCNjytYi/Fh46A09hAOWztYPGFU1tPMIQNt9C/Gv37QzUf9HNa09Dfh2V2RdoRPEmsftKEER4qg39CW4GpwNSHTXpsRhbhWkdLQOpSaOPfrzcqx9Ddl59vcnJ5x25/kb6839hFFYt01P5FIVeB56puCpY5a0WKZrDGXXEzsSOi9OK9boZ9kOhU0b4wDiVFdV0a6C0s0TvQTDb3aIpfXsPXSpD3HE/75XKcoMOEVarffPgIY/oEIQ7RZSbFH9psZZXjvT5rNNWD0Q/mGwdibtuGlNitWux8DUbFY6S3mKYQP2f+VyQpmiVYn0ZWFfMxCIeUiJ+D4atVIirmgLVCbkGT0V1ioOlKprgFj6PKVOIrJ99kDAI+QjMXVKUz9YhTnOjhw8DfqBMiYQkTYf+wAwJP2NxgSkt4mq+UAFGqjvp6gBc40TjM5Hw==";
//        String iv="nahwq6jlsuy07kvr";
//        String secret="fc457216-a9a7-4172-9d10-4c4f5fa9a27b";
//
//        String cipherCode1="nahwq6jlsuy07kvr&cipherCode=YpoDM29Hr%2FPJEcEwsvz1vHs5T4Av1F6Q2T7J7NJd2F2OCqqS0fyKeP%2BCryMlHuy0R9atFUAg7o%2FdKx5N5uOCucSY1eY%2FrcD%2BRde8zOEIrKrMch%2FWrENp7gLp3ZVnWFlS7vrJsylbnhOUoceMzntWajeUCVgC1Av8wSCNjytYi%2FFh46A09hAOWztYPGFU1tPMIQNt9C%2FGv37QzUf9HNa09Dfh2V2RdoRPEmsftKEER4qg39CW4GpwNSHTXpsRhbhWkdLQOpSaOPfrzcqx9Ddl59vcnJ5x25%2Fkb6839hFFYt01P5FIVeB56puCpY5a0WKZrDGXXEzsSOi9OK9boZ9kOhU0b4wDiVFdV0a6C0s0TvQTDb3aIpfXsPXSpD3HE%2F75XKcoMOEVarffPgIY%2FoEIQ7RZSbFH9psZZXjvT5rNNWD0Q%2FmGwdibtuGlNitWux8DUbFY6S3mKYQP2f%2BVyQpmiVYn0ZWFfMxCIeUiJ%2BD4atVIirmgLVCbkGT0V1ioOlKprgFj6PKVOIrJ99kDAI%2BQjMXVKUz9YhTnOjhw8DfqBMiYQkTYf%2BwAwJP2NxgSkt4mq%2BUAFGqjvp6gBc40TjM5Hw%3D%3D";
//        String iv1="3a1f72be-28d9-4e";
//        String secret1="6e323963fbc04500918e8d1174610dd8";
//        
//      String result=  CryptographUtil.AESDecrypt(cipherCode, iv, secret);
////      String result=  CryptographUtil.AESDecrypt(cipherCode1, iv1, secret1);
//        
//        
//        
//        String xml="<?xml version=\"1.0\" encoding = \"utf-8\"?><request><type>corporationBinding</type><data><attr name=\"corpAccount\">kzxt0001</attr><attr name=\"corpName\">深圳快学教育有限公司</attr><attr name=\"corpNickName\"></attr><attr name=\"corpPhone\">0755-17666147893</attr><attr name=\"corpMobile\">17727604007</attr><attr name=\"corpLinkman\"></attr><attr name=\"corpFax\"></attr><attr name=\"corpEmail\"></attr><attr name=\"corpAddress\"></attr></data>";
//        String ssiv="w3r6ah5xkscidv89";
        String password="fc457216-a9a7-4172-9d10-4c4f5fa9a27b";

//        String result = CryptographUtil.AESEncrypt(xml, ssiv, password);

        corpSync("yanlijingtest", password);
//        corpSyncTest("yanlijingtest1", pwd);
//        System.out.println(result);
        
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
