package com.nhom13.videoshort_firebase;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_VIDEO = 1001;
    private Cloudinary cloudinary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dc0pyfoqb",
                "api_key", "835554664568564",
                "api_secret", "cL74hzBi4C9LXRdCDrvT6RQdc2w"
        ));

        Button btnSelectVideo = findViewById(R.id.btnSelectVideo);
        btnSelectVideo.setOnClickListener(v -> selectVideoFromGallery());
    }

    private void selectVideoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_VIDEO && resultCode == RESULT_OK && data != null) {
            Uri videoUri = data.getData();
            String videoPath = getRealPathFromUri(videoUri);
            uploadToCloudinary(videoPath);
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] proj = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            String result = cursor.getString(column_index);
            cursor.close();
            return result;
        }
        return null;
    }

    private void uploadToCloudinary(String videoPath) {
        new Thread(() -> {
            try {
                File videoFile = new File(videoPath);
                Map uploadResult = cloudinary.uploader().upload(videoFile, ObjectUtils.asMap(
                        "resource_type", "video"
                ));

                String videoUrl = (String) uploadResult.get("secure_url");
                Log.d("Cloudinary", "Upload thành công: " + videoUrl);

                // TODO: Lưu videoUrl vào Firestore hoặc hiển thị lên UI
            } catch (Exception e) {
                Log.e("Cloudinary", "Upload thất bại", e);
            }
        }).start();
    }
}
