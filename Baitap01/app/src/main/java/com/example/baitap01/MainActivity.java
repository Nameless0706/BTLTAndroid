package com.example.baitap01;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(101);
            numbers.add(randomNumber);
        }

        for (Integer number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);  // Số chẵn
            } else {
                oddNumbers.add(number);   // Số lẻ
            }
        }

        Log.d("EvenNumbers", "Số chẵn trong mảng: " + evenNumbers.toString());
        Log.d("OddNumbers", "Số lẻ trong mảng: " + oddNumbers.toString());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        EditText editText;
        Button btnReverse;
        TextView textViewResult;


        editText = findViewById(R.id.editTextInput);
        btnReverse = findViewById(R.id.buttonRev);

        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //viết code điều khiển textView1
                String inputText = editText.getText().toString().trim();
                String reverse = "";
                if (!inputText.isEmpty()) {
                    // Đảo ngược chuỗi và chuyển thành chữ hoa
                    String[] inputStrArr = inputText.split(" ");
                    for(int i = inputStrArr.length - 1; i >= 0; i--){
                        reverse += inputStrArr[i] + " ";
                    }
                }

                Toast.makeText(MainActivity.this, reverse, Toast.LENGTH_SHORT).show();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}