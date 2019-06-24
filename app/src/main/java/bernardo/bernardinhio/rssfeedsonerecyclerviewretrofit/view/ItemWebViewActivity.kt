package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.R

class ItemWebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var webSettings: WebSettings
    private lateinit var pageUrl : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_web_view)

        setPageUrl()
        configureWebView()
        loadPage()
    }

    private fun setPageUrl() {
        val intent = this.intent
        if (intent != null) {
            pageUrl = intent.getStringExtra("feedItemUrl")
        }
    }

    private fun configureWebView() {
        webView = findViewById<WebView>(R.id.webview_feed_item)
        webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = true

        webSettings.minimumFontSize = 18

        if (!CookieManager.getInstance().acceptCookie()) {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
            } else {
                CookieManager.getInstance().setAcceptCookie(true)
            }
        }

        webView.webViewClient = FeedItemWebViewClient(this)
    }

    private fun loadPage() {
        try {
            webView.loadUrl(pageUrl)
        } catch (e : Exception ) {
            Log.d("loadError", e.message)
        }
    }
}