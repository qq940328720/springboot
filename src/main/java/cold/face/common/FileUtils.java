package cold.face.common;

import java.io.*;

/**
 * Created by ylj on 18-5-15.
 */
public class FileUtils {
    /**
     * 读取txt
     *
     * @throws IOException
     */
    public static String readFile(String filePath) throws Exception {
        //设置默认编码
        String charset = "UTF-8";
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                return null;
            }
        } else
            return "";
    }

    /**
     * 写入txt
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
            System.out.println("test2 done!");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 复制文件
     *
     * @throws IOException
     */
    public static void copyFile(File fromFile, File toFile) throws IOException {
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n = 0;
        while ((n = ins.read(b)) != -1) {
            out.write(b, 0, n);
        }

        ins.close();
        out.close();
    }
}
