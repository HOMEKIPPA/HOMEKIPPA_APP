package com.example.homekippa.ui.searchAddress;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import android.util.Log;
import android.webkit.JavascriptInterface;

import android.webkit.WebChromeClient;

import android.webkit.WebSettings;
import android.webkit.WebView;

import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homekippa.R;


public class searchAddress extends AppCompatActivity {

    private WebView daum_webView;

    private TextView daum_result;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_address);

        daum_result = (TextView) findViewById(R.id.daum_result);

        // WebView 초기화

        init_webView();


        // 핸들러를 통한 JavaScript 이벤트 반응

        handler = new Handler();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        String address = daum_result.getText().toString().trim();
    }


    public void init_webView() {

        // WebView 설정


        daum_webView = (WebView) findViewById(R.id.daum_webview);


        // JavaScript 허용
        daum_webView.getSettings().setJavaScriptEnabled(true);


        // JavaScript의 window.open 허용

        daum_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌

        daum_webView.addJavascriptInterface(new AndroidBridge(), "Android");


        // web client 를 chrome 으로 설정

        daum_webView.setWebViewClient(new WebViewClient());


        // webview url load. php 파일 주소

        daum_webView.loadUrl("http://101.101.208.180:3000/searchAddress.html");
    }


    private class AndroidBridge {

        @JavascriptInterface

        public void setAddress(final String arg1, final String arg2, final String arg3) {

            handler.post(new Runnable() {

                @Override

                public void run() {

                    daum_result.setText(String.format("(%s) %s %s", arg1, arg2, arg3));

//                    // WebView를 초기화 하지않으면 재사용할 수 없음

//                    init_webView();

                    Intent intent = new Intent();
                    intent.putExtra("address", daum_result.getText().toString());

                    setResult(RESULT_OK, intent);

                    finish();

                }

            });



        }

    }

}
