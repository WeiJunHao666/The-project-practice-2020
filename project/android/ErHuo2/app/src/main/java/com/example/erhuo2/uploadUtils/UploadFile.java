package com.example.erhuo2.uploadUtils;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.http.dns.Dns;
import com.qiniu.android.http.dns.IDnsNetworkAddress;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class UploadFile {

    private UploadManager uploadManager;
    private long uploadFileLength;
    private String hash;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String hash1 = (String) msg.obj;
                    setHash(hash1);
                    break;
            }
        }
    };
    private void setHash(String hash){
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public Handler getHandler() {
        return handler;
    }

    public void upload(File uploadFile, String keyname, String uploadToken){
        final long startTime = System.currentTimeMillis();
        //可以自定义zone
        //Zone zone = new FixedZone(new String[]{"domain1","domain2"});

        //手动指定上传区域
        //Zone zone = FixedZone.zone0;//华东

        //配置断点续传
        /**
         FileRecorder fileRecorder = null;
         try {
         fileRecorder = new FileRecorder("directory");
         } catch (IOException e) {
         e.printStackTrace();
         }
         */

        //config配置上传参数
        Configuration configuration = new Configuration.Builder()
                .connectTimeout(10)
                //.zone(zone)
                //.dns(buildDefaultDns())//指定dns服务器
                .responseTimeout(60).build();

        if (this.uploadManager == null) {
            //this.uploadManager = new UploadManager(fileRecorder);
            this.uploadManager = new UploadManager(configuration);
        }

        UploadOptions opt = new UploadOptions(null, null, true, new UpProgressHandler() {
            @Override
            public void progress(String key, double percent) {
                Log.i("qiniutest", "percent:" + percent);
            }
        }, null);

        uploadFileLength = uploadFile.length();

        Log.e("1", com.example.erhuo2.R.string.qiniu_upload_file+"");

        this.uploadManager.put(uploadFile, keyname, uploadToken,
                new UpCompletionHandler() {
                    @Override

                    public void complete(String key, ResponseInfo respInfo,
                                         JSONObject jsonData) {
                        long endTime = System.currentTimeMillis();
                        if (respInfo.isOK()) {
                            try {
                                Log.e("2", jsonData.toString() + respInfo.toString());
                                Log.e("3", "--------------------------------UPTime/ms: " + (endTime - startTime));
                                String fileKey = jsonData.getString("key");
                                String hash = jsonData.getString("hash");
                                Log.e("4", "File Size: " + Tools.formatSize(uploadFileLength));
                                Log.e("5", "File Key: " + fileKey);
                                Log.e("6","File Hash: " + hash);
                                Log.e("7", "X-Reqid: " + respInfo.reqId);
                                Log.e("8", "X-Via: " + respInfo.xvia);
                                Log.e("9", "--------------------------------" + "\n上传成功");
                                Message msg = new Message();
                                msg.obj = jsonData.getString("hash");
                                msg.what = 1;
                                handler.sendMessage(msg);

                            } catch (JSONException e) {
                                Log.e("10", com.example.erhuo2.R.string.qiniu_upload_file_response_parse_error+"");
                                if (jsonData != null) {
                                    Log.e("11", jsonData.toString());
                                }
                                Log.e("12", "--------------------------------" + "\n上传失败");
                            }
                        } else {
                            Log.e("12", respInfo.toString());
                            if (jsonData != null) {
                                Log.e("13", jsonData.toString());
                            }
                            Log.e("14", "--------------------------------" + "\n上传失败");
                        }
                    }

                }, opt);

    }



    /**
     * 需要自定义DNS解析时配置
     *
     * @return
     */
    public Dns buildDefaultDns() {
        ArrayList<IResolver> rs = new ArrayList<IResolver>(3);
        try {
            IResolver r1 = new Resolver(InetAddress.getByName("119.29.29.29"));
            rs.add(r1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        try {
//            rs.add(new Resolver(InetAddress.getByName("114.114.114.114")));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        try {
//            // 读取系统相关属性
//            // android 27 及以上 会报错
//            IResolver r2 = AndroidDnsServer.defaultResolver();
//            rs.add(r2);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        if (rs.size() == 0) {
            return null;
        }
        final DnsManager happlyDns = new DnsManager(NetworkInfo.normal, rs.toArray(new IResolver[rs.size()]));
        Dns dns = new Dns() {
            // 若抛出异常 Exception ，sdk 会使用 okhttp 组件默认 dns 解析结果
            @Override
            public List<IDnsNetworkAddress> lookup(String hostname) throws UnknownHostException {
                Domain domain = new Domain(hostname);
                List<IDnsNetworkAddress> addressList = null;
                try {
                    Record[] records = happlyDns.queryRecords(domain);
                    if (records != null && records.length > 0){
                        addressList = new ArrayList<>();
                        for (Record record : records) {
                            String source = "customized";
                            DemoDnsNetworkAddress address = new DemoDnsNetworkAddress(hostname, record.value, (long)record.ttl, source, record.timeStamp);
                            addressList.add(address);
                        }
                    }
                } catch (IOException ignored) {
                }
                return addressList;
            }
        };
        return dns;
    }

}
