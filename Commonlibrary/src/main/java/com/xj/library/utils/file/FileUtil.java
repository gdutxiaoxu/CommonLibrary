package com.xj.library.utils.file;

import android.database.Cursor;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 *
 * @author xujun
 * 2016/12/27 23:50.
 * <p>
 * 文件工具类
 */
public class FileUtil {

    public static final String UTF_8 = "UTF-8";
    public static final String GBK = "GBK";

    public static boolean isImage(String fileName) {
        return fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("" +
                ".jpeg") ||
                fileName.endsWith(".gif") || fileName.endsWith(".bmp")
                || fileName.endsWith(".webp");
    }

    public static void exportToCSV(Cursor c, File saveFile) {
        int rowCount = 0;
        int colCount = 0;
        BufferedWriter bfw;
        try {
            rowCount = c.getCount();
            colCount = c.getColumnCount();
            bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile), GBK));
            if (rowCount > 0) {
                c.moveToFirst();
                // 写入表头
                for (int i = 0; i < colCount; i++) {
                    if (i != colCount - 1)
                        bfw.write(c.getColumnName(i) + ',');
                    else
                        bfw.write(c.getColumnName(i));
                }
                // 写好表头后换行
                bfw.newLine();
                // 写入数据
                for (int i = 0; i < rowCount; i++) {
                    c.moveToPosition(i);
                    // Toast.makeText(mContext, "正在导出第"+(i+1)+"条",
                    // Toast.LENGTH_SHORT).show();
//                    Log.v("导出数据", "正在导出第" + (i + 1) + "条");
                    for (int j = 0; j < colCount; j++) {
                        if (j != colCount - 1)
                            bfw.write(c.getString(j) + ',');
                        else
                            bfw.write(c.getString(j));
                    }
                    // 写好每条记录后换行
                    bfw.newLine();
                }
            }
            // 将缓存数据写入文件
            bfw.flush();
            // 释放缓存
            bfw.close();
            // Toast.makeText(mContext, "导出完毕！", Toast.LENGTH_SHORT).show();
            Log.v("导出数据", "导出完毕！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            c.close();
        }
    }

    /**
     * if exit, rename file
     *
     * @param file
     * @return
     */
    public static File ifExitRenameFile(File file) {
        int i = 1;
        String path = file.getPath();
        path = path + "(" + i + ")";
        while (new File(path).exists()) {
            ++i;
            path = path + "(" + i + ")";
        }
        return new File(path);
    }

    /**
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
     *
     * @param bytes
     * @return
     */
    public static String bytes2kb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1)
            return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (returnValue + "KB");
    }

    public static String dateFormat(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return sdf.format(date * 1000);
    }

    public static void saveToFile(List<String> list, String path) {
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : list) {
            stringBuilder.append(line).append("\n");
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new
                    FileOutputStream(file), UTF_8));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bufferedWriter);
        }
    /*    FileWriter filerWriter = null;
        BufferedWriter bufWriter = null;
        try {
            filerWriter = new FileWriter(file, true);
            bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(stringBuilder.toString());
//            bufWriter.newLine();
            bufWriter.flush();

            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bufWriter);
            IOUtils.close(filerWriter);
        }*/
    }

    public static String readFile(Set<String> list, String path) {
        File file = new File(path);
        BufferedReader bufferedReader = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(new
                    FileInputStream(file), UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(line);
                line = bufferedReader.readLine();
                stringBuilder.append(line);
            }
            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bufferedReader);
        }
        return "";
    }

    public static String readFile(List<String> list, String path) {
        File file = new File(path);
        BufferedReader bufferedReader = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(new
                    FileInputStream(file), UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(line);
                line = bufferedReader.readLine();
                stringBuilder.append(line);
            }
            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bufferedReader);
        }
        return "";
    }

    public static long fileSize(String pathAndFile) {
        File file = new File(pathAndFile);
        return file.length();
    }

    public static boolean fileExist(String pathAndFile) {
        File file = new File(pathAndFile);
        if (file.exists()) return true;
        else return false;
    }

    public static void fileRename(String fName, String nName) {
        File file = new File(fName);
        file.renameTo(new File(nName));
        file.delete();
    }

    public static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        return value;
    }

}
