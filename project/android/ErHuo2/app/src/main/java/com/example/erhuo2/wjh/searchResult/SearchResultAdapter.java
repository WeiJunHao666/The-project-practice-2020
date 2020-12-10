package com.example.erhuo2.wjh.searchResult;

<<<<<<< HEAD
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
=======
>>>>>>> 1a99eda8b7105ddf6f9a4ca82a257fa441769e83
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    List<DataBean> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
    @Override
<<<<<<< HEAD
    public int getItemViewType(int position) {
        return position;
    }

        @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.ViewHolder holder, int position) {
        holder.setData(dataList.get(position), position);
        Log.e("position", position+"");
=======
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

>>>>>>> 1a99eda8b7105ddf6f9a4ca82a257fa441769e83
    }

    @Override
    public int getItemCount() {
        return list!=null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
<<<<<<< HEAD

        public void setData(Object data, int position) {
            if (data != null) {
                String id =  (String)data;
//                Log.e("ImgId", R.drawable.product1 + "");
//                String url = "http://qkl7o9qw8.hb-bkt.clouddn.com/dsl";
//                down(id);

                Glide.with(imageView.getContext())
                        .asBitmap()
                        .load(id)
                        .error(R.drawable.write)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();//获取你要填充图片的布局的layoutParam
                                layoutParams.height = (int) (((float) resource.getHeight()) / resource.getWidth() * getScreenWidth(context) / 2 );
                                //因为是2列,所以宽度是屏幕的一半,高度是根据bitmap的高/宽*屏幕宽的一半
                                final float scale = context.getResources().getDisplayMetrics().density;
                                int width =  dip2px(context, 36);
                                layoutParams.width =  (getScreenWidth(context)-width)/ 2;//这个是布局的宽度
                                imageView.setLayoutParams(layoutParams);//容器的宽高设置好了
                                resource = zoomImg(resource, layoutParams.width, layoutParams.height);
                                // 然后在改变一下bitmap的宽高
                                imageView.setImageBitmap(resource);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }
                        });
            }
        }
        //改变bitmap尺寸的方法
        public Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
            // 获得图片的宽高
            int width = bm.getWidth();
            int height = bm.getHeight();
            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // 得到新的图片
            Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
            return newbm;
        }

        //获取屏幕宽度的方法
        public int getScreenWidth(Context context)
        {
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            return width;
        }
        //
        /**
         * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
         */
        public int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }
=======
>>>>>>> 1a99eda8b7105ddf6f9a4ca82a257fa441769e83
    }
}
