package com.example.erhuo2.wjh.setInfo.view;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.erhuo2.R;
import com.example.erhuo2.uploadUtils.Etag;
import com.example.erhuo2.uploadUtils.FileUtils;
import com.example.erhuo2.uploadUtils.UploadFile;
import com.example.erhuo2.wjh.ConfigUtil;
import com.example.erhuo2.wjh.setInfo.bean.InfoData;
import com.example.erhuo2.wjh.setInfo.presenter.SetInfoPresenter;
import com.example.erhuo2.zsl.activity.LoginActivity;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SetUserInfo_activity extends AppCompatActivity implements View.OnClickListener,SetInfoView{
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    private ImageView iv_setHead;
    private EditText et_setName;
    private Button btn_setInfo_save;
    private Button btn_skip;
    private ImageView iv_random;
    private TextView tv_random;
    private int[] image;
    private String[] name;
    private PopupWindow popupWindow;
    private SetInfoPresenter presenter;
    private UploadFile uploadFile;
    private String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info_activity);
        findViews();
        setListener();
        image = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e
                    ,R.drawable.f, R.drawable.g, R.drawable.h};
        name = new String[]{"路飞", "索隆", "山治", "娜美", "乔巴", "乌索普", "罗宾", "弗兰奇", "布鲁克", "甚平"};
        setHeadShape(iv_setHead, R.drawable.a);
        presenter = new SetInfoPresenter(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(String msg) {
        try {
            Log.e("token", msg);
            //上传图片
            File file = new File(path);
            uploadFile = new UploadFile();
            String hash = null;
            hash = Etag.file(file);
            uploadFile.upload(file, hash, msg);

            SharedPreferences sp = getApplication().getSharedPreferences("data", MODE_PRIVATE);
            int id = sp.getInt("userId", 1);
            String nickname = sp.getString("nickname", "");
            String s = ConfigUtil.UPDATE;
            Log.e("mmmm", String.valueOf(sp.getInt("userId", 1)));
            Log.e("yyyy", "1");
            Log.e("hash", hash);

            Gson gson = new Gson();
            InfoData userInfo = new InfoData(id, hash);
            String sendInfo = gson.toJson(userInfo);

            presenter.uploadFWQ(s, sendInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap drawableToBitamp(Drawable drawable)
    {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        return Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(getApplicationContext(), "上传失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess1(String msg) {
        Toast.makeText(getApplicationContext(), "上传成功1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure1(String msg) {
        Toast.makeText(getApplicationContext(), "上传失败1", Toast.LENGTH_SHORT).show();
    }

    private void setHeadShape(ImageView iv, int res) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), res));
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()));
        iv.setImageDrawable(circleDrawable);
    }


    private void setListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        iv_setHead.setOnClickListener(myOnClickListener);
        btn_setInfo_save.setOnClickListener(myOnClickListener);
        iv_random.setOnClickListener(myOnClickListener);
        tv_random.setOnClickListener(myOnClickListener);
        btn_skip.setOnClickListener(myOnClickListener);
    }

    private void findViews() {
        iv_setHead = findViewById(R.id.iv_setHead);
        et_setName = findViewById(R.id.et_setName);
        btn_setInfo_save = findViewById(R.id.btn_setInfo_save);
        btn_skip = findViewById(R.id.btn_skip);
        iv_random = findViewById(R.id.iv_random);
        tv_random = findViewById(R.id.tv_random);
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.iv_setHead:
                    showAnimation();
                    break;
                case R.id.btn_setInfo_save:
                    String s = ConfigUtil.GET_TOKEN;
                    presenter.uploadQNY(s);
                    Intent i = new Intent();
                    i.setClass(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    break;
                case R.id.iv_random:
                    int random_one = (int)(Math.random()*8);
                    int random_two = (int)(Math.random()*10);
                    setHeadShape(iv_setHead, image[random_one]);
                    et_setName.setText(name[random_two]);
                case R.id.tv_random:
                    int random_three = (int)(Math.random()*8);
                    int random_four = (int)(Math.random()*10);
                    setHeadShape(iv_setHead, image[random_three]);
                    et_setName.setText(name[random_four]);
                    break;
                case R.id.btn_skip:
                    Intent j = new Intent();
                    j.setClass(getApplicationContext(), LoginActivity.class);
                    startActivity(j);
                    break;

            }
        }
    }

    //
    private void showAnimation() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popupwindow_photo, null, false);//引入弹窗布局
        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置背景透明
        addBackground();

        //设置进出动画
        popupWindow.setAnimationStyle(R.style.BottomPopupWindowAnimation);

        //引入依附的布局
        View parentView = LayoutInflater.from(SetUserInfo_activity.this).inflate(R.layout.activity_set_user_info_activity, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        //设置监听事件
        TextView popup_album = (TextView) vPopupWindow.findViewById(R.id.popup_album);
        TextView popup_camera = (TextView) vPopupWindow.findViewById(R.id.popup_camera);
        TextView popup_cancel = (TextView) vPopupWindow.findViewById(R.id.popup_cancel);

        //点击相册
        popup_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAlbum();
            }
        });

        popup_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        popup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    //
    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this, "你否认了许可", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void chooseAlbum() {
        if(ContextCompat.checkSelfPermission(SetUserInfo_activity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SetUserInfo_activity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else{
            openAlbum();
        }
    }

    private void openCamera() {
        File outputImage = new File(this.getExternalCacheDir(), "output_image.jpg");
        Log.e("kkkkkkkk", outputImage.getPath());
        //

        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(SetUserInfo_activity.this,
                    "com.example.project2020.fileprovider", outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        Log.e("zzz", "1");
                        if(popupWindow!=null){
                            popupWindow.dismiss();
                        }
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().
                                openInputStream(imageUri));
                        iv_setHead.setImageBitmap(bitmap);
                        path = FileUtils.getPath(this, imageUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:

                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    try {
                        if(popupWindow!=null){
                            popupWindow.dismiss();
                        }
                        Log.e("uri", uri+"");
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeStream(getContentResolver().openInputStream(uri)));
                        circleDrawable.getPaint().setAntiAlias(true);
                        circleDrawable.setCornerRadius(Math.min(bitmap.getWidth(), bitmap.getHeight()));
                        iv_setHead.setImageDrawable(circleDrawable);
                        path = FileUtils.getPath(this, uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //判断手机系统版本号
//                    if(Build.VERSION.SDK_INT>=19){
//                        handleImageOnKitKat(data);
//                    }else{
//                        handleImageBeforeKitKat(data);
//                    }
                }
                break;
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    private void handleImageOnKitKat(Intent data) {
//        String imagePath = null;
//        Uri uri = data.getData();
//        if(DocumentsContract.isDocumentUri(this, uri)){
//            //如果是document类型的uri，则通过document id处理
//            String docId= DocumentsContract.getDocumentId(uri);
//            if("com.android.providers.media.documents".equals(uri.getAuthority())){
//                String id = docId.split(":")[1];//解析出数字格式的id
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
//                        Long.valueOf(docId));
//                imagePath = getImagePath(contentUri, null);
//            }
//        }else if("content".equalsIgnoreCase(uri.getScheme())){
//            //如果是content类型的Uri。则通过普通方式处理
//            imagePath = getImagePath(uri, null);
//        }else if("file".equalsIgnoreCase(uri. getScheme())){
//            //如果是file类型的Uri,直接获取图片的路径即可
//            imagePath = uri.getPath();
//        }
//        displayImage(imagePath);
//    }
//
//    private void handleImageBeforeKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = getImagePath(uri, null);
//        displayImage(imagePath);
//    }
//
//    private void displayImage(String imagePath) {
//        if(imagePath != null){
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            Log.e("222", imagePath);
//            iv_setHead.setImageBitmap(bitmap);
//        }else{
//            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private String getImagePath(Uri uri, String selection) {
//        String path = null;
//        //通过Uri和selection来获取真实的图片路径
//        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
//        if(cursor != null){
//            if(cursor.moveToFirst()){
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(popupWindow!=null){
            popupWindow.dismiss();
        }
    }
}