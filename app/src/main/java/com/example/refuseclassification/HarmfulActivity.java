package com.example.refuseclassification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.refuseclassification.Database.Knowledge;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class HarmfulActivity extends BaseActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    List<Knowledge> knowledges = new ArrayList<>();
    HarmfulActivity.MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmful);
        toolbar = (Toolbar) findViewById(R.id.harmful_toolbar);
        toolbar.setTitle("有害垃圾");
        new setTitleCenter().setTitleCenter(toolbar);
        // 编写列表内容
        recyclerView = findViewById(R.id.harmful_recyclerView);
        knowledges = LitePal.where("kind = ?", "有害垃圾").find(Knowledge.class);
        myAdapter = new HarmfulActivity.MyAdapter();
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(HarmfulActivity.this);
        recyclerView.setLayoutManager(manager);
    }

    class MyAdapter extends RecyclerView.Adapter<HarmfulActivity.MyViewHolder> {

        @NonNull
        @Override
        public HarmfulActivity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(HarmfulActivity.this, R.layout.item_recyclerview, null);
            HarmfulActivity.MyViewHolder myViewHolder = new HarmfulActivity.MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull HarmfulActivity.MyViewHolder holder, int position) {
            Knowledge knowledge = knowledges.get(position);
            holder.name.setText(knowledge.getName());
            //holder.kind.setText((knowledge.getKind()));
        }

        @Override
        public int getItemCount() {
            return knowledges.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView kind;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            kind = itemView.findViewById(R.id.kind);
        }
    }
}
