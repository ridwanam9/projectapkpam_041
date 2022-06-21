package com.example.uaspam_041;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.uaspam_041.adapter.LNAdapter;
import com.example.uaspam_041.database.DBLightNovel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private LNAdapter adapter;
    private ArrayList<LN> LNArrayList;
    DBLightNovel controller = new DBLightNovel(this);
    String id_novel,judul, penulis, sinopsis;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        BacaData();
        adapter = new LNAdapter(LNArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,add_novel.class);
                startActivity(intent);
            }
        });
    }

    public void BacaData(){
        ArrayList<HashMap<String,String>> daftarnovel = controller.getAllNovel();
        LNArrayList = new ArrayList<>();
        for (int i = 0; i<daftarnovel.size(); i++){
            LN ln = new LN();
            ln.setId_novel(daftarnovel.get(i).get("id_novel").toString());
            ln.setJudul(daftarnovel.get(i).get("judul").toString());
            ln.setPenulis(daftarnovel.get(i).get("penulis").toString());
            ln.setSinopsis(daftarnovel.get(i).get("sinopsis").toString());

            LNArrayList.add(ln);
        }
    }
}