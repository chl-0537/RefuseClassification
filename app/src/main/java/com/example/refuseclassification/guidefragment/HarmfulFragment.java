package com.example.refuseclassification.guidefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.refuseclassification.R;

public class HarmfulFragment extends Fragment {

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_harmful, container, false);
        webView = (WebView) view.findViewById(R.id.harmful_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        // 百度百科
        webView.loadUrl("https://baike.baidu.com/item/%E6%9C%89%E5%AE%B3%E5%9E%83%E5%9C%BE");
        return view;
    }
}
