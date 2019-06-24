package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class FeedItemWebViewClient(private val activity: Activity) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {

        val stringOriginalUri = view.originalUrl

        val originalUri = Uri.parse(stringOriginalUri)

        val childUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            request.url
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        if (childUri == originalUri)
            return false
        else {
            val intent = Intent(Intent.ACTION_VIEW, request.url)
            activity.startActivity(intent)
            return true
        }
    }
}
