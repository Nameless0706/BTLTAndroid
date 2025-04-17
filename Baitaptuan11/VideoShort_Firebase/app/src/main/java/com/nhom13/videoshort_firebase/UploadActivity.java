package com.nhom13.videoshort_firebase;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 1002;
    private Cloudinary cloudinary;

    // Kết quả chọn video
    private final ActivityResultLauncher<Intent> videoPickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri videoUri = result.getData().getData();
                    String videoPath = getRealPathFromUri(videoUri);
                    if (videoPath != null) {
                        uploadToCloudinary(videoPath);
                    } else {
                        Toast.makeText(this, "Không thể lấy đường dẫn video!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Khởi tạo Cloudinary
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dc0pyfoqb",
                "api_key", "835554664568564",
                "api_secret", "cL74hzBi4C9LXRdCDrvT6RQdc2w"
        ));

        // Nút chọn video
        Button btnSelectVideo = findViewById(R.id.btnSelectVideo);
        btnSelectVideo.setOnClickListener(v -> {
            if (hasStoragePermission()) {
                selectVideoFromGallery();
            } else {
                requestStoragePermission();
            }
        });
    }

    private boolean hasStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_VIDEO}, REQUEST_PERMISSION_CODE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
        }
    }

    // Kết quả yêu cầu quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectVideoFromGallery();
        } else {
            Toast.makeText(this, "Bạn cần cấp quyền để chọn video!", Toast.LENGTH_SHORT).show();
        }
    }

    // Mở thư viện chọn video
    private void selectVideoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        videoPickerLauncher.launch(intent);
    }

    // Lấy đường dẫn thật từ URI
    private String getRealPathFromUri(Uri uri) {
        String filePath = null;

        if (DocumentsContract.isDocumentUri(this, uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            String[] split = documentId.split(":");
            String id = split[1];

            Uri contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            String selection = MediaStore.Video.Media._ID + "=?";
            String[] selectionArgs = new String[]{id};

            Cursor cursor = getContentResolver().query(contentUri, new String[]{MediaStore.Video.Media.DATA},
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                cursor.close();
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Video.Media.DATA},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                cursor.close();
            }
        }

        return filePath;
    }

    // Upload video lên Cloudinary
    private void uploadToCloudinary(String videoPath) {
        Toast.makeText(this, "Đang tải video lên Cloudinary...", Toast.LENGTH_SHORT).show();

        new Thread(() -> {
            try {
                File videoFile = new File(videoPath);
                Map uploadResult = cloudinary.uploader().upload(videoFile, ObjectUtils.asMap(
                        "resource_type", "video"
                ));

                String videoUrl = (String) uploadResult.get("secure_url");
                Log.d("Cloudinary", "Upload thành công: " + videoUrl);

                runOnUiThread(() -> Toast.makeText(this, "Upload thành công!\n" + videoUrl, Toast.LENGTH_LONG).show());

            } catch (Exception e) {
                Log.e("Cloudinary", "Upload thất bại", e);
                runOnUiThread(() -> Toast.makeText(this, "Upload thất bại!", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
