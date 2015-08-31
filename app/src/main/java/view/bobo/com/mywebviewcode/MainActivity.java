package view.bobo.com.mywebviewcode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import view.bobo.com.mywebviewcode.net.HttpThread;

public class MainActivity extends Activity {
    /**
     * 这是网页展示
     */
    private WebView wv;
    /**
     * 这是title
     */
    private TextView tv_title;
    /**
     * 这是返回
     */
    private LinearLayout ll_up;
    /**
     * 这是刷新
     */
    private LinearLayout ll_resh;

    /**
     * 上下文
     */
    private Context context;

    /**
     * 错误提示码
     */
    private TextView tv_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        initView();
        initData();
        wv.loadUrl("http://zhushou.360.cn/");
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tv_title.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                view.loadUrl("file:///android_asset/error.html");
//            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.loadUrl("file:///android_asset/error.html");
            }
        });
        //下载文件的操作
        wv.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                //自己写的下载方法
//                new HttpThread(url, context).start();

                //调用系统的下载方法
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
//        initWeb();
    }

    private void initWeb() {

    }

    private void initData() {

        ll_resh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //刷新界面
//                wv.reload();
                wv.loadUrl("http://zhushou.360.cn/");

            }
        });
        ll_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上一页
                wv.goBack();
            }
        });

    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_up = (LinearLayout) findViewById(R.id.ll_up);
        ll_resh = (LinearLayout) findViewById(R.id.ll_resh);
        TextView tv_code = (TextView) findViewById(R.id.tv_code);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()){
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
