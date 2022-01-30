package com.example.refuseclassification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class AgreementActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        toolbar = (Toolbar) findViewById(R.id.agreement_toolbar);
        toolbar.setTitle("用户协议与隐私");
        new setTitleCenter().setTitleCenter(toolbar);
    }
}
