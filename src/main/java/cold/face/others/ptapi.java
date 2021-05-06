package cold.face.others;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

public class ptapi {

    public static void main(String[] args) {

        JSONObject param = new JSONObject();
        param.put("federationPartnerId", 103303);
        param.put("pageSize", 100);
        param.put("pageNum", 1);
        param.put("time", new Date());


        try {
            String iv = generateIv();
            String strEnXml = "";
            try {
                strEnXml = CryptographUtil.AESEncrypt(param.toString(), iv, "01e5113d19b24df8a42c4d60fd8e7cd2");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //UrlEncode
            strEnXml = URLEncoder.encode(strEnXml, "UTF-8");
            String URL = "https://ptapi.jdy.com/service/getFederationPartnerServiceList?" + "iv=" + iv + "&cipherCode=" + strEnXml + "&appKey=kxedt";
            System.out.println(URL);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
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

}
