package com.example.retrofit2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit2.Adapter.CategoryAdapter;
import com.example.retrofit2.Data.APIService;
import com.example.retrofit2.Model.Category;
import com.example.retrofit2.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcCate;

    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        GetCategory();
    }

    private void Anhxa(){
        rcCate = (RecyclerView) findViewById(R.id.rc_category);
    }

    private void GetCategory(){
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()
                            , LinearLayoutManager.HORIZONTAL, false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else{
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }
}