package com.example.erhuo2.uploadUtils;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

public class DownloadFile {

    //图片上传到七牛之后，
    // 默认会将文件的hash和key(文件的文件名)响应回来，
    // 然后在空间设置->域名设置里，找到空间域名，
    // 通过http://空间域名/key的形式，拿到文件的url。
    public void clickDown(final ImageView img, String fileName){
//        String domain_name = "qkl7o9qw8.hb-bkt.clouddn.com";
        String downUrl = fileName;
        Log.e("url", downUrl);
        AsyncHttpClient client = new SyncHttpClient();
        client.get(downUrl, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] binaryData) {
                if (binaryData != null) {
                    img.setImageBitmap(BitmapFactory.decodeByteArray(binaryData, 0, binaryData.length));
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] binaryData, Throwable error) {
                Log.e("Error", "onFailure: 图片下载失败" );
            }

        });
    }
}
