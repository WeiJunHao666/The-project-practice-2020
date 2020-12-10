package com.example.erhuo2.wjh.allKind.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KindModel implements Model {
    @Override
    public void showRight(final String url, final KindListener listener) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    final Request request = new Request.Builder()
                            .get()
                            .url(url)
                            .build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            listener.onFailure("失败");
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String str = response.body().string();
                            Log.e("str", str);
                            listener.onSuccess(str);
                        }
                    });
                }
            }).start();
    }
}
