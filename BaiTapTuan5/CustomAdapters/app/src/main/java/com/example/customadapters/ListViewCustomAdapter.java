package com.example.customadapters;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import listView.MonHoc;
import listView.MonHocAdapter;

public class ListViewCustomAdapter extends AppCompatActivity {

    ListView listView;
    ArrayList<MonHoc> arrayList;
    MonHocAdapter adapter;

    EditText editText1;

    Button btnNhap, btnCapNhat;

    int vitri;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_custom_adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        adapter = new MonHocAdapter(ListViewCustomAdapter.this, R.layout.row_monhoc, arrayList);
        //truyền dữ liệu từ adapter ra listview
        listView.setAdapter(adapter);
        buttonThem();
        buttonCapNhat();
    }

    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listView1);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.java));
        arrayList.add(new MonHoc("C#","C# 1",R.drawable.c_sharp));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.php));
        arrayList.add(new MonHoc("Kotlin","Kotlin 1",R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart","Dart 1",R.drawable.dart));
    }

    public void buttonThem(){


        MonHocAdapter adapter = (MonHocAdapter) listView.getAdapter();


        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                arrayList.add(new MonHoc(name, name + "1", R.drawable.ic_launcher_background    ));
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void buttonCapNhat(){
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        MonHocAdapter adapter = (MonHocAdapter) listView.getAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //lấy nội dung đua lên edittext
                editText1.setText(arrayList.get(i).getName());
                vitri = i;
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.get(vitri).setName(editText1.getText().toString());
                arrayList.get(vitri).setDesc(editText1.getText().toString() + "1");
                adapter.notifyDataSetChanged();
            }
        });


    }
}