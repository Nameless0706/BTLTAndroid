package com.nhom13.recyclerviewwithindicator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nhom13.recyclerviewwithindicator.adapter.IconAdapter;
import com.nhom13.recyclerviewwithindicator.model.IconModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rclcon;
    private ArrayList<IconModel> arrayList1;
    private IconAdapter iconAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rclcon = findViewById(R.id.rclcon);
        arrayList1 = new ArrayList<>();

        // Add your data here.  Use actual resource IDs and text.
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "33213"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "eqwe"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "eeee"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "dfgfhynh sxd22f"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "jfj2djfj3213f djfdh"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "sdfd321f sddsafds"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "sdfdda22f sdfdd2s"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "dfgfhadsaynh sxdfdf"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "dfgfhycnh sxdfdf"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "jfjdjf2232132jf djfdh"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "sdfd32fff sdfds"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "sdfdacccf sdfds"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "dfgfhyccccnh sxdfdf"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "jfjdzzczjfjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "sdfdcxzf sdfds"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "sdfczcxdf sdfds"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "dfgfhynh sxdfdf"));
        arrayList1.add(new IconModel(R.drawable.ic_launcher_background, "dfgfhynh sxdfdf"));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);  // 3 columns
        rclcon.setLayoutManager(gridLayoutManager);

        iconAdapter = new IconAdapter(this, arrayList1);
        rclcon.setAdapter(iconAdapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });




    }

    private void filterListener(String text) {
        List<IconModel> list = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if (iconModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                list.add(iconModel);
            }
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            iconAdapter.setListenerList(list);
        }
    }




}

