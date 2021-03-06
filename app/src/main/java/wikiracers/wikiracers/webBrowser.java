/* WikiRacers - Web Browser Activity
 *
 *
 *
 * */
package wikiracers.wikiracers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
import java.util.ArrayList;
import java.util.List;


/////////////////////////////////////////////
//test with git testing 5


//webBrowser class

public class webBrowser extends Activity {

    private WebView mWebView;  //New Webview Element
    static int pageCount = 0;
    static String currentURL = "";
    static String startingURL = "";
    static String target_URL = "";
    static String target_URL_full = "";
    static List<String> list_URL = new ArrayList<String>();
    static Boolean gameStart = false;
    static Boolean gameRun = false; // might be the same as gameStart
    static Boolean peekMode = false; // toggles when the user is playing or looking at target
    static Boolean backSwitch = true; //acts as a switch for the back button (prevents spamming)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        final TextView countText = (TextView) findViewById(R.id.textView2);


        //Links Activity Element to refrencable object
        mWebView = (WebView) findViewById(R.id.browser_webView_Window);
        //Sets internal JavaScript to ON
        mWebView.getSettings().setJavaScriptEnabled(true);
        //Sets Starting URL
        mWebView.loadUrl("http://en.wikipedia.org/wiki/Special:Random");
        mWebView.setWebViewClient(new mWebViewClient(){

            //counts when a page is loaded completely
            //used instead of onPageStarted for counting reasons (redirections, etc.)
            //TODO: make sure the count doesn't go up when page load fails
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                if(peekMode){

                }
                else{backSwitch = true;
                if (!url.equals(currentURL)) {
                    currentURL = url;
                    if(gameRun) {
                        pageCount++;
                    }
                    Log.d("game", url + " ~ " + String.valueOf(pageCount) + "target:" + target_URL + " start:" + startingURL);
                    countText.setText(String.valueOf(pageCount));
                    backSwitch = true;
                    if (get_page_title(url).equals(target_URL)){
                        startActivity(new Intent(getApplicationContext(), winnerPage.class));
                        list_URL.add(url);
                        TextView url_target = (TextView) findViewById(R.id.browser_webView_Text);
                        url_target.setText("Winner");
                        int i = 0;
                        for (;i<list_URL.size();++i){
                            Log.d("victory", list_URL.get(i));
                        }
                        Log.d("path", "url: " + url);
                        gameRun = gameStart = false; //allows player to browse around post game without messing with stats

                    }
                    if(startingURL.equals("") && !url.equals("http://en.m.wikipedia.org/wiki/Special:Random")){
                        mWebView.loadUrl("http://en.m.wikipedia.org/wiki/Special:Random");
                        startingURL = url;
                    }
                    else if(target_URL.equals("") && !url.equals("http://en.m.wikipedia.org/wiki/Special:Random")){
                        TextView url_target;
                        url_target = (TextView)findViewById(R.id.browser_webView_Text);
                        url_target.setText(get_page_title(url));
                        target_URL = get_page_title(url);
                        target_URL_full = url;
                        mWebView.loadUrl(startingURL);
                        pageCount = -1;
                        gameStart = gameRun = true;
                    }else if (gameStart){
                        //Todo: test this (play the game all the way through)
                        list_URL.add(url);
                    }
                    }
                }
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                backSwitch = false; //to prevent cancelling a load
                super.onPageStarted(view,url,favicon);
            }
        });
        // Back button functionality
        final Button webBack = (Button)findViewById(R.id.browser_webView_Back_Button);
        final TextView targetPageText = (TextView)findViewById(R.id.browser_webView_Text);
        View.OnClickListener listen = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (view == webBack){
                    Log.d("game", "back clicked");
                    if(backSwitch){Log.d("game","backswitch true");}
                    if(mWebView.canGoBack()){Log.d("game","GoBack true");}
                    if(mWebView.canGoBack() && backSwitch){
                        Log.d("game", "going back");
                        backSwitch = false;
                        mWebView.goBack();
                    }
                }
                else if (view == targetPageText){
                    if(peekMode){
                        peekMode = false;
                        backSwitch = true;
                        mWebView.goBack();
                    }else {
                        Log.d("game", "text clicked");
                        peekMode = true;
                        mWebView.loadUrl(target_URL_full);
                    }
                }
            }
        };
        webBack.setOnClickListener(listen);
        targetPageText.setOnClickListener(listen);
    }

    //Removes Web Client default buttons and bounds the browser space to
    //our WebView activity.

    private class mWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url)
        {
            Log.d("game","override view loading");
            if (!peekMode) {
                webview.loadUrl(url);
            }
            return true;
        }
    }


    public String get_page_title(String url){
        //Gets everything after the final / in the Url aka the page_title
        int get_last_slash = url.lastIndexOf('/');
        String page_title = url.substring(get_last_slash+1);
        return page_title;
    }



}
