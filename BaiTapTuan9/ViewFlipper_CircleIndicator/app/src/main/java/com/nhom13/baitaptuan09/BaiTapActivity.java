package com.nhom13.baitaptuan09;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.nhom13.baitaptuan09.adapter.BaiTapImageSlidesAdapter;
import com.nhom13.baitaptuan09.adapter.ImagesViewPageAdapter;
import com.nhom13.baitaptuan09.api.ApiService;
import com.nhom13.baitaptuan09.api.RetrofitClient;
import com.nhom13.baitaptuan09.model.Images;
import com.nhom13.baitaptuan09.model.ImagesSlider;
import com.nhom13.baitaptuan09.model.MessageModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class BaiTapActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<ImagesSlider> imagesList;

    private BaiTapImageSlidesAdapter adapter;


    private ApiService apiService;


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == imagesList.size() - 1){
                viewPager.setCurrentItem(0);
            }
            else{
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_tap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager = findViewById(R.id.viewpage_2);
        circleIndicator = findViewById(R.id.circle_indicator_2);
        apiService = RetrofitClient.getClient().create(ApiService.class);


        loadImageSlider(1);


        handler.postDelayed(runnable, 3000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume(){
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    private void loadImageSlider(int position) {
        imagesList = new ArrayList<>();
        apiService.loadImageSlider(position).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    imagesList = response.body().getResult();
                    adapter = new BaiTapImageSlidesAdapter(imagesList);
                    viewPager.setAdapter(adapter);
                    circleIndicator.setViewPager(viewPager);
                } else {
                    Toast.makeText(BaiTapActivity.this, "Failed to load images", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Toast.makeText(BaiTapActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
