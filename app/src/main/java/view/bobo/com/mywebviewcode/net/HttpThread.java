package view.bobo.com.mywebviewcode.net;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BoBo on 2015/8/31.
 */
public class HttpThread extends  Thread{
    private String mUrl;
    private Context context;
    private InputStream in = null;
    private FileOutputStream out = null;

    public HttpThread(String url,Context context){
        this.mUrl = url;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            Toast.makeText(context,"开始下载...",Toast.LENGTH_LONG).show();
            URL httpUrl = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            //设置输入流
            conn.setDoInput(true);
            //设置输出流
            conn.setDoOutput(true);
            //输入流
            in = conn.getInputStream();
            //创建一个下载目录
            File downLoadFile;
            //文件所放置的目录与目录名称
            File sdFile;
            //判断SD卡是否挂载
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                downLoadFile = Environment.getExternalStorageDirectory();
                sdFile = new File(downLoadFile, "test.apk");
                //输出流
                out = new FileOutputStream(sdFile);
            }
            //创建一个6K的缓存字节数组
            byte[] bs = new byte[6 * 1024];
            int len;
            while((len = in.read(bs)) != -1){
                if (out != null){
                    out.write(bs,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,"下载失败",Toast.LENGTH_LONG).show();
        }finally {
            Toast.makeText(context,"下载完成",Toast.LENGTH_LONG).show();
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    Toast.makeText(context,"关闭输出流失败",Toast.LENGTH_LONG).show();
                }
            }
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    Toast.makeText(context,"关闭输入流失败",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
