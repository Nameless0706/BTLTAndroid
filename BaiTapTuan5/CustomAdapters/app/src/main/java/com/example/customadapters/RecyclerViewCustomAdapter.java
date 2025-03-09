package com.example.customadapters;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import listView.SongAdapter;
import listView.SongModel;

public class RecyclerViewCustomAdapter extends AppCompatActivity {


    RecyclerView rvSongs;
    SongAdapter mSongAdapter;

    List<SongModel> mSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_custom_adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvSongs = (RecyclerView) findViewById(R.id.rv_songs);
        //Create song data
        mSongs = new ArrayList<>();
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu một tình yêu", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("60698", "NẮNG ẤM XA DẦN", "Nắng ấm xa dần rồi", "MTP"));
        mSongs.add(new SongModel("60622", "CƠN MƯA NGANG QUA", "Cơn mưa ngang qua, cơn mưa đi ngang qua", "MTP"));
        mSongs.add(new SongModel("60682", "CHẮC AI ĐÓ SẼ VỀ", "Anh tìm nỗi nhớ, anh tìm quá khứ", "MTP"));
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu một tình yêu", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu một tình yêu", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu một tình yêu", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu một tình yêu", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu một tình yêu", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu một tình yêu", "Trịnh Đình Quang"));


        mSongAdapter = new SongAdapter(this, mSongs);
        rvSongs.setAdapter(mSongAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSongs.setLayoutManager(linearLayoutManager);

    }


}