package view.bobo.com.mywebviewcode.cookies;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BoBo on 2015/9/1.
 */
public class HttpCookie extends Thread {
    @Override
    public void run() {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://192.168.1.7:80/cookie/login");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("username", "chenbobo"));
        list.add(new BasicNameValuePair("pwd", "cbb1994"));
        try {
            post.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200){
                //正常返回
                AbstractHttpClient absClient = (AbstractHttpClient)client;
                List<Cookie> cookies =  absClient.getCookieStore().getCookies();
                for (int i = 0;i<cookies.size();i++){
                    System.out.print(cookies.get(i).getName()+":");
                    System.out.println(cookies.get(i).getValue());
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
