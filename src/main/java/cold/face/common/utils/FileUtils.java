package cold.face.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by ylj on 18-5-15.
 */
public class FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 读取txt
     *
     * @param filePath 文件路径
     * @param charset  编码
     * @return
     * @throws IOException
     */
    public static String readFromFile(String filePath, String charset) throws IOException {
        String ret = "";
        //设置默认编码
        File file = new File(filePath);
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        if (file.isFile() && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                inputStreamReader = new InputStreamReader(fileInputStream, charset);
                bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }

                fileInputStream.close();
                inputStreamReader.close();
                bufferedReader.close();
                ret = sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            }
        }
        return ret;
    }

    /**
     * 写入txt
     *
     * @param filePath 文件路径
     * @param content  内容
     * @param append   是否扩充(true:在原文件后面增加写入的内容;false:覆盖源文件内容)
     * @throws IOException
     */
    public static void writeToFile(String filePath, String content, boolean append) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            File file = new File(filePath);
            //文件不存在时候，主动创建文件。
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            fw.close();
            log.info("test2 done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null)
                fw.close();
            if (bw != null)
                bw.close();
        }
    }

    /**
     * 复制文件
     *
     * @param fromPath 源文件路径
     * @param toPath   目标文件路径
     * @param append   是否扩充(true:在原文件后面增加写入的内容;false:覆盖源文件内容)
     * @throws IOException
     */
    public static void copyFile(String fromPath, String toPath, boolean append) throws IOException {
        File fromFile = new File(fromPath);
        if (!fromFile.exists()) {
            throw new IOException("源文件不存在！");
        }
        File toFile = new File(toPath);
        if (!toFile.exists()) {
            toFile.createNewFile();
        }

        FileInputStream ins = null;
        FileOutputStream out = null;
        try {
            ins = new FileInputStream(fromFile);
            out = new FileOutputStream(toFile, append);
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, n);
            }
            ins.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ins != null)
                ins.close();
            if (out != null)
                out.close();
        }
    }
}
