package view.bobo.com.mywebviewcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private WebView wv;
    private TextView tv_title;
    private LinearLayout ll_up;
    private LinearLayout ll_resh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
        initData();
        wv.loadUrl("http://www.baidu.com");
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
        });
    }

    private void initData() {

        ll_resh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //刷新界面
                wv.reload();
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
    }

//    /**
//     * 按返回键时， 不退出程序而是返回上一浏览页面：
//     *
//     * @param keyCode
//     * @param event
//     * @return
//     */
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && details_wv_data.canGoBack()) {
//            details_wv_data.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()){
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
