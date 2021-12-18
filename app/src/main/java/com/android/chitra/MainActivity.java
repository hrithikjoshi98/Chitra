package com.android.chitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.chitra.adapter.ImageListAdapter;
import com.library.chitra.bildbekommen.Bildbekommen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn_first, btn_second, btn_thired, btn_fourth;
    private TextView lists;
    private ArrayList<String> list;
    private RecyclerView rv_listofimage;
    Bildbekommen bildbekommen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_listofimage = findViewById(R.id.rv_listofimage);
        bildbekommen = new Bildbekommen();
        btn_first = findViewById(R.id.btn_first);
        btn_second = findViewById(R.id.btn_second);
        btn_thired = findViewById(R.id.btn_thired);
        btn_fourth = findViewById(R.id.btn_fourth);
        lists = findViewById(R.id.lists);
        list = new ArrayList<>();
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.execute(MainActivity.this);
            }
        });
        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.setLimit(3).execute(MainActivity.this);
            }
        });

        btn_thired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.setList(list).execute(MainActivity.this);
            }
        });

        btn_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bildbekommen.setList(list).setLimit(3).execute(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 15 && resultCode == RESULT_OK) {
            String l = "";
            list = data.getStringArrayListExtra("album_images_layout");

            rv_listofimage.setHasFixedSize(true);
            rv_listofimage.setLayoutManager(new LinearLayoutManager(this));
            rv_listofimage.setAdapter(new ImageListAdapter(this, list));

            for (int i = 0; i < list.size(); i++) {
                String name = list.get(i);
                l += name + "\n";
            }
            lists.setText(l);
        }
    }
}