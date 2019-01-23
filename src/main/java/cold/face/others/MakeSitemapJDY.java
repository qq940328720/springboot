package cold.face.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeSitemapJDY {
    
    private static Logger log = LoggerFactory.getLogger(MakeSitemapJDY.class);

    public static void main(String[] args) {
        String website = "https://www.jdy.com/sitemap.xml";
        try {
            makeRobots();
            List<SiteMapIndex> siteMapIndexArr = getSiteMapIndex(website, "utf-8");
            makeSiteMapIndex(siteMapIndexArr.size());
            int i = 1;
            for (SiteMapIndex siteMapIndex : siteMapIndexArr) {
                List<SiteMap> siteMapArr = getSiteMap(siteMapIndex.getLoc(), "utf-8");
                makeSiteMap(siteMapArr, i);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //写robots.txt文件
    private static void makeRobots() throws Exception {
        String dir = "E:\\eclipse-workspace\\jdy\\robots.txt";
        String content = "User-agent: *\n" +
                "Disallow: /wp-admin/\n" +
                "Allow: /wp-admin/admin-ajax.php\n" +
                "\n" +
                "Sitemap: https://www.jdy.com/sitemap_index.xml";
        writeToFile2(dir, content);
    }

    //写sitemap_index.xml文件
    private static void makeSiteMapIndex(int count) throws Exception {
        String dir = "E:\\eclipse-workspace\\jdy\\sitemap_index.xml";
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"https://www.jdy.com/wp-content/plugins/all-in-one-seo-pack/sitemap.xsl\"?>\n" +
                "<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
        for (int i = 1; i <= count; i++) {
            content += "\n   <sitemap>\n" +
                    "      <loc>https://www.jdy.com/sitemap/sitemap" + String.valueOf(i) + ".xml</loc>\n" +
                    "   </sitemap>";
        }
        content += "\n" +
                "</sitemapindex>";
        writeToFile2(dir, content);
    }

    //写sitemap.xml文件
    private static void makeSiteMap(List<SiteMap> siteMapArr, int index) throws Exception {
        String dir = "E:\\eclipse-workspace\\jdy\\sitemap\\sitemap" + index + ".xml";
        String content = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"https://www.jdy.com/wp-content/plugins/all-in-one-seo-pack/sitemap.xsl\"?>\n" +
                "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9\n" +
                " http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">";
        for (SiteMap siteMap : siteMapArr) {
            String loc = siteMap.getLoc();
            if (loc.equals("https://www.jdy.com") || loc.equals("https://www.jdy.com/")) {
                siteMap.setPriority("1.0");
            } else if (loc.contains("https://www.jdy.com/news/") && loc.contains(".html")) {
                loc = loc.replace("https://www.jdy.com/news/", "");
                loc = loc.replace(".html", "");
                try {
                    Long id = Long.valueOf(loc);
                    if (id > 1110000000L && id <= 1110131187L) {
                        siteMap.setPriority("0.1");
                    } else {
                        siteMap.setPriority("0.3");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                siteMap.setPriority("0.7");
            }
            content += "\n\t<url>\n" +
                    "\t\t<loc>" + siteMap.getLoc() + "</loc>\n" +
                    "\t\t<priority>" + siteMap.getPriority() + "</priority>\n" +
                    "\t\t<changefreq>" + siteMap.getChangefreq() + "</changefreq>\n" +
                    "\t</url>";
        }
        content += "\n" +
                "</urlset>";
        writeToFile2(dir, content);
    }

    //爬取sitemap_index.xml页面数据
    public static List<SiteMapIndex> getSiteMapIndex(String urlInfo, String charset) throws Exception {
        //读取目的网页URL地址，获取网页源码
        URL url = new URL(urlInfo);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        InputStream is = httpUrl.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        StringBuilder sb = new StringBuilder();
        String content = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            //这里是对链接进行处理
//            line = line.replaceAll("</?a[^>]*>", "");
            //这里是对样式进行处理
//            line = line.replaceAll("<(\\w+)[^>]*>", "<$1>");
            sb.append(line);
        }
        is.close();
        br.close();
        //获得网页源码
        content = sb.toString();
        String rgex = "<sitemap>(.*?)</sitemap>";
        List<String> siteMapArr = getSubUtil(content, rgex);
        List<SiteMapIndex> ret = new ArrayList<>();
        if (siteMapArr.size() > 0)
            for (String sitemap : siteMapArr) {
                String loc = getSubUtilSimple(sitemap, "<loc>(.*?)</loc>");
//                String lastmod=getSubUtilSimple(sitemap,"<lastmod>(.*?)</lastmod>");
                SiteMapIndex siteMapIndex = new SiteMapIndex();
                siteMapIndex.setLoc(loc);
//                siteMapIndex.setLastMod(lastmod);
                ret.add(siteMapIndex);
            }
        return ret;
    }

    //爬取sitemap.xml页面数据
    public static List<SiteMap> getSiteMap(String urlInfo, String charset) throws Exception {
        //读取目的网页URL地址，获取网页源码
        URL url = new URL(urlInfo);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        InputStream is = httpUrl.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        StringBuilder sb = new StringBuilder();
        String content = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        br.close();
        //获得网页源码
        content = sb.toString();
        String rgex = "<url>(.*?)</url>";
        List<String> siteMapArr = getSubUtil(content, rgex);
        List<SiteMap> ret = new ArrayList<>();
        if (siteMapArr.size() > 0)
            for (String sitemap : siteMapArr) {
                String loc = getSubUtilSimple(sitemap, "<loc>(.*?)</loc>");
                String priority = getSubUtilSimple(sitemap, "<priority>(.*?)</priority>");
                String changefreq = getSubUtilSimple(sitemap, "<changefreq>(.*?)</changefreq>");
                SiteMap siteMapIndex = new SiteMap();
                siteMapIndex.setLoc(loc);
                siteMapIndex.setPriority(priority);
                siteMapIndex.setChangefreq(changefreq);
                ret.add(siteMapIndex);
            }
        return ret;
    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     *
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap, String rgex) {
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     *
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * 写入文件
     *
     * @throws IOException
     */
    public static void writeToFile2(String filePath, String content) throws Exception {
        try {
            File file = new File(filePath);
            //文件不存在时候，主动创建文件。
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            fw.close();
            log.info(filePath + "---successful!");
        } catch (Exception e) {
            log.info(e.getLocalizedMessage());
        }
    }

    private static class SiteMapIndex {
        private String loc;
        private String lastMod;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getLastMod() {
            return lastMod;
        }

        public void setLastMod(String lastMod) {
            this.lastMod = lastMod;
        }
    }

    private static class SiteMap {
        private String loc;
        private String priority;
        private String changefreq;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getChangefreq() {
            return changefreq;
        }

        public void setChangefreq(String changefreq) {
            this.changefreq = changefreq;
        }
    }
}