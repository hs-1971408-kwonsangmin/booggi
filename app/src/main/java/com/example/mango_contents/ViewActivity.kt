package com.example.mango_contents

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class ViewActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        auth = Firebase.auth






        val webView=findViewById<WebView>(R.id.webView)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }

        webView.settings.javaScriptEnabled = true // JavaScript를 사용할 수 있도록 설정
        webView.settings.setSupportMultipleWindows(false)
        webView.loadUrl(intent.getStringExtra("URL").toString())







        val database = Firebase.database
        val myBookmark = database.getReference("bookmark_ref")

        val url=intent.getStringExtra("URL").toString()
        val title=intent.getStringExtra("title").toString()
        val imageURL=intent.getStringExtra("imageURL").toString()
        var now_url: String?



        val savebtn=findViewById<TextView>(R.id.save)
        savebtn.setOnClickListener{

            now_url = webView.getUrl()

            myBookmark.child(auth.currentUser!!.uid).push().setValue(contentsModel(now_url.toString(),imageURL,title))




        }


        /*
        // Step01. 현재 WebView의 방문 한(이전/이후) 주소들을 목록으로 가져옵니다.
        // Step01. 현재 WebView의 방문 한(이전/이후) 주소들을 목록으로 가져옵니다.
        val webBackForwardList: WebBackForwardList = WebView.copyBackForwardList()

// Step02. 가져온 목록 중 이전 위치(현재 위치-1)에 있는 Index를 가져옵니다.

// Step02. 가져온 목록 중 이전 위치(현재 위치-1)에 있는 Index를 가져옵니다.
        val prevIndex = webBackForwardList.currentIndex - 1

// Step03. 이전 위치의 Url을 가져옵니다.

// Step03. 이전 위치의 Url을 가져옵니다.
        val backUrl = webBackForwardList.getItemAtIndex(prevIndex).url
*/










    }
}