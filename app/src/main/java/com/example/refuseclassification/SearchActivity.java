package com.example.refuseclassification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.refuseclassification.Database.Knowledge;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private Toolbar toolbar;
    private EditText editText;
    private RecyclerView recyclerView;
    List<Knowledge> knowledges = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        toolbar.setTitle("搜索");
        new setTitleCenter().setTitleCenter(toolbar);
        // 初始化数据列表
        knowledges = LitePal.findAll(Knowledge.class);
        recyclerView = findViewById(R.id.search_recyclerView);
        myAdapter = new SearchActivity.MyAdapter();
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(manager);
        // 实例化EditText
        editText = findViewById(R.id.search);
        Intent intent = getIntent();
        String record = intent.getStringExtra("record");
        if (record != null) {
            editText.setText(record);
            knowledges.clear();
            knowledges = LitePal.where("name like ?", "%" + record + "%").
                    find(Knowledge.class);
            myAdapter = new SearchActivity.MyAdapter();
            recyclerView.setAdapter(myAdapter);
        }
        editText.addTextChangedListener(new TextWatcher() {
            // 输入文本前的状态
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(myAdapter != null){
                    recyclerView.setAdapter(myAdapter);
                }
            }
            // 输入文本时的状态
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                knowledges.clear();
                knowledges = LitePal.where("name like ?", "%" + str + "%").
                        find(Knowledge.class);
                myAdapter = new SearchActivity.MyAdapter();
                recyclerView.setAdapter(myAdapter);
            }
            // 输入文本之后的状态
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(SearchActivity.this, R.layout.item_recyclerview, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Knowledge knowledge = knowledges.get(position);
            holder.name.setText(knowledge.getName());
            holder.kind.setText((knowledge.getKind()));
        }

        @Override
        public int getItemCount() {
            return knowledges.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView kind;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            kind = itemView.findViewById(R.id.kind);
        }
    }
}

