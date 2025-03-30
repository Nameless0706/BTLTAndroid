package com.nhom13.baitaptuan09.api;

import com.nhom13.baitaptuan09.model.MessageModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("newimagesmanager.php")
    Call<MessageModel> loadImageSlider(@Field("position") int position);
}
