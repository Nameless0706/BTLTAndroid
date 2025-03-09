package com.example.customadapters;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import listView.MonHoc;
import listView.MonHocAdapter;

public class GridViewCustomAdapter extends AppCompatActivity {

    //khai báo
    GridView gridView;
    ArrayList<MonHoc> arrayList;
    MonHocAdapter adapter;

    EditText editText1;
    Button btnNhap;
    Button btnCapNhat;
    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grid_view_custom_adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AnhXa();
        adapter = new MonHocAdapter(GridViewCustomAdapter.this, R.layout.col_monhoc, arrayList);
        gridView.setAdapter(adapter);
        onRowClick();
        buttonThem();
        btnCapNhat();
    }

    private void AnhXa(){
        //ánh xạ
        gridView = (GridView) findViewById(R.id.gridView1);
        editText1 = (EditText) findViewById(R.id.editText2);
        btnNhap = (Button) findViewById(R.id.btnNhap2);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat2);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.java));
        arrayList.add(new MonHoc("C#","C# 1",R.drawable.c_sharp));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.php));
        arrayList.add(new MonHoc("Kotlin","Kotlin 1",R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart","Dart 1",R.drawable.dart));
    }

    private void onRowClick(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>
                                            adapterView, View view, int i, long l) {
            //code yêu cầu
            //i: trả về vị trí click chuột trên GridView -> i ban đầu =0
                Toast.makeText(GridViewCustomAdapter.this,"" + i, Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            //code yêu cầu
            //i: trả về vị trí click chuột trên GridView -> i ban đầu =0
                Toast.makeText(GridViewCustomAdapter.this,"Bạn đang nhấn giữ "+ i + "-" + arrayList.get(i) , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void buttonThem(){
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                arrayList.add(new MonHoc(name, name + "1", 0));
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void btnCapNhat(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                adapter.notifyDataSetChanged();
            }
        });
    }
}