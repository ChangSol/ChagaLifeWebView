package com.incus.chagalifewebview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    private val myUrl = "https://chagalife.com" // 접속 URL (내장HTML의 경우 왼쪽과 같이 쓰고 아니면 걍 URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myWebView = findViewById<View>(R.id.webview) as WebView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        val settings = myWebView.settings
        settings.javaScriptEnabled = true //자바스크립트 허용 유무

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)

        // Enable zooming in web view
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true

        // Zoom web view text
        settings.textZoom = 125

        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true

        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }

        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true //세로 가로 시 페이지처리
        settings.loadWithOverviewMode = true  //최대축소
        settings.javaScriptCanOpenWindowsAutomatically = true //javascript의 window.open 허용
        settings.mediaPlaybackRequiresUserGesture = false

        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true //HTML5 localStorage 기능 사용 유무
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true

        myWebView.fitsSystemWindows = true
        myWebView.setBackgroundColor(0) //배경색
        myWebView.isHorizontalScrollBarEnabled = false//가로 스크롤
        myWebView.isVerticalScrollBarEnabled = false //세로 스크롤
        myWebView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY // 스크롤 노출 타입
        myWebView.isScrollbarFadingEnabled = true // 스크롤 페이딩 처리 여부

        myWebView.loadUrl(myUrl) // OPEN URL
        myWebView.webChromeClient = WebChromeClient() //크롬을 사용할 시 ALERT를 띄움
        myWebView.webViewClient = WebViewClientClass()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val myWebView = findViewById<View>(R.id.webview) as WebView
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private class WebViewClientClass : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: String): Boolean {
            Log.d("TAG", "check URL : $request")
            view.loadUrl(request)
            return true
        }
    }
}
