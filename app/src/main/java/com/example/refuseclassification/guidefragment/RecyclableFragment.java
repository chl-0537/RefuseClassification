package com.example.refuseclassification.guidefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.refuseclassification.R;

public class RecyclableFragment extends Fragment {

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recyclable, container, false);
        webView = (WebView) view.findViewById(R.id.recyclable_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        // 百度百科
        webView.loadUrl("https://baike.baidu.com/item/%E5%8F%AF%E5%9B%9E%E6%94%B6%E7%89%A9");
        return view;
    }
}
