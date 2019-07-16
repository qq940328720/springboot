package cold.face.common.utils;

import javax.net.ssl.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    public static final String CONNFALL = "HTTPFALL";

    /**
     * 忽视证书HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
        public boolean verify(String s, SSLSession sslsession) {
            return true;
        }
    };

    /**
     * Ignore Certification
     */
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {
        private X509Certificate[] certificates;

        @Override
        public void checkClientTrusted(X509Certificate certificates[], String authType) {
            if (this.certificates == null) {
                this.certificates = certificates;
            }
        }

        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate, String s) {
            if (this.certificates == null) {
                this.certificates = ax509certificate;
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    };

    /**
     * GET
     *
     * @param urlString
     * @param request
     * @param response
     * @param charCode
     * @param sessionId
     * @return
     */
    public static String http_get(String urlString, HttpServletRequest request, HttpServletResponse response, String charCode, String sessionId) {
        HttpsURLConnection httpConnection = null;
        String contentStr = "";
        try {
            URL url = new URL(urlString);
            // use ignore host name verifier
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            httpConnection = (HttpsURLConnection) url.openConnection();
            // Prepare SSL Context
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            httpConnection.setSSLSocketFactory(ssf);

            httpConnection.setRequestMethod("GET");
            httpConnection.setConnectTimeout(30 * 1000);
            httpConnection.setReadTimeout(60 * 1000);
            if (!"".equals(sessionId)) {
                httpConnection.setRequestProperty("Cookie", sessionId);
            }
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream urlStream = httpConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream, charCode));
                String sCurrentLine = null;
                StringBuffer sTotalString = new StringBuffer();
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    sTotalString.append(sCurrentLine);
                }
                contentStr = sTotalString.toString();
                urlStream.close();
                bufferedReader.close();
            } else {
                contentStr = CONNFALL;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return contentStr;
    }

    /**
     * POST
     *
     * @param urlString
     * @param params
     * @param request
     * @param response
     * @param charCode
     * @param sessionId
     * @return
     */
    public static String http_post(String urlString, Map<String, String> params, HttpServletRequest request, HttpServletResponse response, String charCode, String sessionId) {
        HttpsURLConnection httpConnection = null;
        String contentStr = "";
        try {
            StringBuffer paramsBuffer = new StringBuffer();
            Iterator<Map.Entry<String, String>> paramsIter = params.entrySet().iterator();
            while (paramsIter.hasNext()) {
                Map.Entry<String, String> element = paramsIter.next();
                paramsBuffer.append(element.getKey());
                paramsBuffer.append("=");
                paramsBuffer.append(URLEncoder.encode(element.getValue(), charCode));
                paramsBuffer.append("&");
            }
            int iLen = paramsBuffer.length();
            if (iLen > 0) {
                paramsBuffer = paramsBuffer.deleteCharAt(iLen - 1);
            }
            URL url = new URL(urlString);
            // use ignore host name verifier
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            httpConnection = (HttpsURLConnection) url.openConnection();
            // Prepare SSL Context
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            httpConnection.setSSLSocketFactory(ssf);

            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConnection.setConnectTimeout(30 * 1000);
            httpConnection.setReadTimeout(60 * 1000);
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);
            httpConnection.setUseCaches(false);
            httpConnection.setInstanceFollowRedirects(true);
            if (!"".equals(sessionId)) {
                httpConnection.setRequestProperty("Cookie", sessionId);
            }
            DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
            out.writeBytes(paramsBuffer.toString());
            out.flush();
            out.close();

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream urlStream = httpConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream, charCode));
                String sCurrentLine = null;
                StringBuffer sTotalString = new StringBuffer();
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    sTotalString.append(sCurrentLine);
                }
                contentStr = sTotalString.toString();
                urlStream.close();
                bufferedReader.close();
            } else {
                contentStr = CONNFALL;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return contentStr;
    }

    /**
     * POST new
     *
     * @param request
     * @param response
     * @param resource
     * @param charCode
     * @param sessionId
     * @return
     */
    public static String http_post_new(HttpServletRequest request, HttpServletResponse response, Resource resource, String charCode, String sessionId) {
        HttpsURLConnection httpConnection = null;
        String contentStr = "";
        try {
            String inputFields = resource.getInputFields().trim();//必输入字段
            String extraFields = resource.getExtraFields();//可选输入字段
            String targetUrl = resource.getTargetUrl().trim();//目标地址
            String targetRefererUrl = resource.getTargetRefererUrl();//目标地址的Referer
            String proxyUrl = resource.getProxyUrl();//代理服务器地址
            String proxyPort = resource.getProxyPort();//代理服务器端口
            Map<String, String> parameters = new HashMap<String, String>();
            String[] ifields = inputFields.split(";");
            for (String ifield : ifields) {
                String[] ifieldItems = ifield.split("#");
                if (ifieldItems.length > 1) {
                    parameters.put(ifieldItems[1], URLDecoder.decode(request.getParameter(ifieldItems[1]), "UTF-8"));
                }
            }
            if (extraFields != null && !"".equals(extraFields.trim())) {
                String[] efields = extraFields.split(";");
                for (String efield : efields) {
                    String[] efieldItems = efield.split("=");
                    int eLen = efieldItems.length;
                    if (eLen > 1) {
                        parameters.put(efieldItems[0], efieldItems[1]);
                    } else {
                        if (eLen == 1) {
                            parameters.put(efieldItems[0], "");
                        }
                    }
                }
            }
            StringBuffer paramsBuffer = new StringBuffer();
            Iterator<Map.Entry<String, String>> paramsIter = parameters.entrySet().iterator();
            while (paramsIter.hasNext()) {
                Map.Entry<String, String> element = paramsIter.next();
                paramsBuffer.append(element.getKey());
                paramsBuffer.append("=");
                paramsBuffer.append(URLEncoder.encode(element.getValue(), charCode));
                paramsBuffer.append("&");
            }
            int iLen = paramsBuffer.length();
            if (iLen > 0) {
                paramsBuffer = paramsBuffer.deleteCharAt(iLen - 1);
            }
            URL url = new URL(targetUrl);
            // use ignore host name verifier
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            if ((proxyUrl != null && !"".equals(proxyUrl.trim())) && (proxyPort != null && !"".equals(proxyPort.trim()))) {//设置了代理
                SocketAddress addr = new InetSocketAddress(proxyUrl.trim(), Integer.parseInt(proxyPort.trim()));//代理服务器地址
                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
                httpConnection = (HttpsURLConnection) url.openConnection(proxy);
            } else {//没有设置代理
                httpConnection = (HttpsURLConnection) url.openConnection();
            }
            // Prepare SSL Context
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            httpConnection.setSSLSocketFactory(ssf);

            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConnection.setConnectTimeout(30 * 1000);
            httpConnection.setReadTimeout(60 * 1000);
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);
            httpConnection.setUseCaches(false);
            httpConnection.setInstanceFollowRedirects(true);
            if (targetRefererUrl != null && !"".equals(targetRefererUrl.trim())) {
                httpConnection.setRequestProperty("Referer", targetRefererUrl.trim());
            }
            if (sessionId != null && !"".equals(sessionId.trim())) {
                httpConnection.setRequestProperty("Cookie", sessionId);
            }
            DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
            out.writeBytes(paramsBuffer.toString());
            out.flush();
            out.close();

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream urlStream = httpConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream, charCode));
                String sCurrentLine = null;
                StringBuffer sTotalString = new StringBuffer();
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    sTotalString.append(sCurrentLine);
                }
                contentStr = sTotalString.toString();
                urlStream.close();
                bufferedReader.close();
            } else {
                contentStr = CONNFALL;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return contentStr;
    }

    /**
     * 获取网址Cookie的值  new
     *
     * @param urlString
     * @param proxyUrl
     * @param proxyPort
     * @param cookieKey
     * @return
     */
    public static String getUrlCookieNew(String urlString, String proxyUrl, String proxyPort, String cookieKey) {
        HttpsURLConnection httpConnection = null;
        String JSESSIONID = "";
        try {
            URL url = new URL(urlString.trim());
            // use ignore host name verifier
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            if ((proxyUrl != null && !"".equals(proxyUrl.trim())) && (proxyPort != null && !"".equals(proxyPort.trim()))) {//设置了代理
                SocketAddress addr = new InetSocketAddress(proxyUrl.trim(), Integer.parseInt(proxyPort.trim()));//代理服务器地址
                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
                httpConnection = (HttpsURLConnection) url.openConnection(proxy);
            } else {//没有设置代理
                httpConnection = (HttpsURLConnection) url.openConnection();
            }
            // Prepare SSL Context
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            httpConnection.setSSLSocketFactory(ssf);

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String session_value = httpConnection.getHeaderField("Set-Cookie");
//					System.out.println("获取网址Cookie---Set-Cookie："+session_value);
                if (session_value != null && !"".equals(session_value)) {
                    String[] sessionId = session_value.split(";");
                    if (sessionId != null) {
                        for (int i = 0; i < sessionId.length; i++) {
                            if (sessionId[i].indexOf(cookieKey) >= 0) {
                                JSESSIONID = sessionId[i];
                                break;
                            }
                        }
                    }
                }
                if ("".equals(JSESSIONID)) {
                    Map<String, List<String>> headerMap = httpConnection.getHeaderFields();
                    if (headerMap != null) {
                        //1.获取所有的cookie值        
                        List<String> listsessionValue = headerMap.get("Set-Cookie");
                        //System.out.println(listsessionValue);
                        if (listsessionValue != null) {
                            StringBuffer StrBuf = new StringBuffer();
                            for (String strValue : listsessionValue) {
                                if (strValue.endsWith(";")) {
                                    StrBuf.append(strValue + " ");
                                } else {
                                    StrBuf.append(strValue + "; ");
                                }
                            }
                            JSESSIONID = StrBuf.toString().trim();
                        }
                        //2.获取单一cookie值             List<String> listsessionValue=headerMap.get("Set-Cookie");
//								System.out.println(listsessionValue);
//								for(String str :listsessionValue){
//									if(str.indexOf(keyName)>=0){
//										JSESSIONID=str;
//										break;
//									}
//								}

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        //System.out.println("Cookie的值："+JSESSIONID);
        return JSESSIONID;
    }

    /**
     * 获取网址Cookie的值
     *
     * @param urlString
     * @param cookieKey
     * @return
     */
    public static String getUrlCookie(String urlString, String cookieKey) {
        HttpsURLConnection httpConnection = null;
        String JSESSIONID = "";
        try {
            URL url = new URL(urlString);
            // use ignore host name verifier
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            httpConnection = (HttpsURLConnection) url.openConnection();
            // Prepare SSL Context
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            httpConnection.setSSLSocketFactory(ssf);

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String session_value = httpConnection.getHeaderField("Set-Cookie");
//				System.out.println("获取网址Cookie---Set-Cookie："+session_value);
                if (session_value != null && !"".equals(session_value)) {
                    String[] sessionId = session_value.split(";");
                    if (sessionId != null) {
                        for (int i = 0; i < sessionId.length; i++) {
                            if (sessionId[i].indexOf(cookieKey) >= 0) {
                                JSESSIONID = sessionId[i];
                                break;
                            }
                        }
                    }
//					Map<String, List<String>> headerMap1 = urlConnection.getHeaderFields();
//					List<String> listsessionValue1=headerMap1.get("Set-Cookie");
//					System.out.println(listsessionValue1);
                    if ("".equals(JSESSIONID)) {
                        Map<String, List<String>> headerMap = httpConnection.getHeaderFields();
                        if (headerMap != null) {
//                          1.获取所有的cookie值        
                            List<String> listsessionValue = headerMap.get("Set-Cookie");
                            //System.out.println(listsessionValue);
                            if (listsessionValue != null) {
                                StringBuffer StrBuf = new StringBuffer();
                                for (String strValue : listsessionValue) {
                                    if (strValue.endsWith(";")) {
                                        StrBuf.append(strValue + " ");
                                    } else {
                                        StrBuf.append(strValue + "; ");
                                    }
                                }
                                JSESSIONID = StrBuf.toString().trim();
                            }
//                          2.获取单一cookie值             
//                          List<String> listsessionValue=headerMap.get("Set-Cookie");
//							System.out.println(listsessionValue);
//							for(String str :listsessionValue){
//								if(str.indexOf(keyName)>=0){
//									JSESSIONID=str;
//									break;
//								}
//							}

                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        //System.out.println("Cookie的值："+JSESSIONID);
        return JSESSIONID;
    }

	/*public static void main(String[] args) throws Exception {
		String urlString1 = "https://www.xm-l-tax.gov.cn/fp/fpyz/fp_yz.do";
		String jsessionId=HttpsUtil.getUrlCookie(urlString1, "JSESSIONID");
		int index=jsessionId.indexOf("!!");
		String tempCookie=jsessionId.substring(index+2);
//		String urlString2 = "https://www.xm-l-tax.gov.cn/fp/fpyz/fp_yz_01.do;jsessionid="+tempCookie;
		String urlString2 = "https://www.xm-l-tax.gov.cn/fp/fpyz/fp_yz_01.do";
		Map<String,String> params=new HashMap<String, String>();
		params.put("fpyzList[0].string(FP_ID)", "235021071008");
		params.put("fpyzList[0].string(FPHM)", "02945712");
		params.put("b_submit", "提 交");
		String getStr=HttpsUtil.getUrlPost(urlString2, params, null, null, "GBK", "");
		String urlString ="https://sso.youshang.com/sso/loginSPProvideLoginInfoServlet?ReqID=&act=&signedInfo=&needAuth=&kickout=N&loginResult=&entityId=assistant&assertHashType=3&name=kingdee_fapiao&password=fapiao20130301&authCode=&ydname=&ydpassword=";
		String getStr=HttpsUtil.getUrlGet(urlString, null, null, "UTF-8", "");
		System.out.println(getStr);
	}*/

    /**
     * http方式请求RestFull接口
     *
     * @param method
     * @param url
     * @param param
     * @return
     */
    public static String http_restFull(final String method, final String url, final String param) {
        String contentStr = "";
        try {
            System.out.println("-------------param===" + param);
            URL restServiceURL = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            // 设置请求方式  GET POST DELETE PUT
            httpConnection.setRequestMethod(method);
            // 打开输入/出开关
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);

            //设置接收格式
            httpConnection.setRequestProperty("Accept", "application/json");
            //设置请求头（后台自定义的）
            httpConnection.setRequestProperty("X-X-X", "xxx");
            //设置传参类型
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setConnectTimeout(100000);
            httpConnection.setReadTimeout(100000);

            // 传递参数
            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(param.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream urlStream = httpConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream, "UTF-8"));
                String sCurrentLine = "";
                StringBuffer sTotalString = new StringBuffer("");
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    sTotalString.append(sCurrentLine);
                }
                contentStr = sTotalString.toString();
                urlStream.close();
                bufferedReader.close();
            } else {
                contentStr = "HTTPFAIL";
            }
            httpConnection.disconnect();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentStr;
    }

    /**
     * 获取cookieMap
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 获取cookieValue
     *
     * @param request
     * @return
     */
    private static String getCookieValue(HttpServletRequest request, String cookieName) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(cookieName)) {//cookieName 为cookie名称
            Cookie cookie = cookieMap.get(cookieName);
            return cookie.getValue();
        } else {
            return null;
        }
    }


}
