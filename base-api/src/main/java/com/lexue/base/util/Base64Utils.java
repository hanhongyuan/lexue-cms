package com.lexue.base.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * Created by admin on 2017/4/15.
 */
public class Base64Utils {

    /**
     * 读取输入流,转换为Base64字符串
     * @param input
     * @return
     */
    public static String GetImageStrByInPut(InputStream input)throws Exception {
        byte[] data = null;
        // 读取图片字节数组
        try {
            data = new byte[input.available()];
            input.read(data);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return Base64.getEncoder().encodeToString(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 图片转化成base64字符串 将图片文 件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @return
     */
    public static String GetImageStr(File file) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
    /**
     * base64字符串转化成图片 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr
     *            数据内容(字符串)
     * @param path
     *            输出路径
     * @return
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);// Base64解码
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     *
     *
     */
    public static byte[] base64ToImage(String imgStr) {
        if (imgStr == null) // 图像数据为空
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);// Base64解码
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {

        }
        return null;
    }
    /**
     * 从URL中读取图片,转换成流形式.
     * @param destUrl
     * @return
     */
    public static InputStream saveToFile(String destUrl){
        HttpURLConnection httpUrl=null;
        URL url = null;
        InputStream in = null;
        try{
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            in = httpUrl.getInputStream();
            return in;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void byte2File(byte[] buf, String filePath, String fileName)
    {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory())
            {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /*public  static void main(String [] args)throws Exception{
            StringBuilder strb=new StringBuilder();
            strb.append("name").append("lilong").append("sex").append("12");
            File fil=new File("C:\\Users\\admin\\Pictures\\1.jpg");
            FileOutputStream fos = new FileOutputStream(fil);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(strb);
    }*/
}
