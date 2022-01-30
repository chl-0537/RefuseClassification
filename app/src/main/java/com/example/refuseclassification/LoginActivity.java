package com.example.refuseclassification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private Button register;
    private CheckBox rememberPass;
    private Toolbar toolbar;
    private MyDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        toolbar.setTitle("登录");
        new setTitleCenter().setTitleCenter(toolbar);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        dbhelper = new MyDatabaseHelper(this, "Account password", null, 2);
        register = (Button) findViewById(R.id.register);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;   //表示账号和密码是否正确
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                Cursor cursor = db.query("Account", null, null,
                        null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String hadaccount = cursor.getString(cursor.getColumnIndex("account"));
                        String hadpassword = cursor.getString(cursor.getColumnIndex("password"));
                        if (account.equals(hadaccount) && password.equals(hadpassword)) {
                            editor = pref.edit();
                            if (rememberPass.isChecked()) {
                                editor.putBoolean("remember_password", true);
                                editor.putString("account", account);
                                editor.putString("password", password);
                            }
                            else {
                                editor.clear();
                            }
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            flag = 0;
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
                if (flag == 1) {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                ContentValues values = new ContentValues();
                values.put("account", account);
                values.put("password", password);
                db.insert("Account", null, values);
                Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
