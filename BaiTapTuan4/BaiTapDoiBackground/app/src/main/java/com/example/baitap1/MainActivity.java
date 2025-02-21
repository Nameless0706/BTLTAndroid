package com.example.baitap1;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final int[] backgrounds = {
            R.drawable.top_background,
            R.drawable.top_background1,
            R.drawable.top_background2
    };

    Random random = new Random();

    int randomIndex = random.nextInt(backgrounds.length);

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
        ConstraintLayout layout = findViewById(R.id.main);
        Switch sw = findViewById(R.id.switch1);

        layout.setBackgroundResource(backgrounds[randomIndex]);


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    randomIndex = random.nextInt(backgrounds.length);
                }
                layout.setBackgroundResource(backgrounds[randomIndex]);

            }
        });
    }


}