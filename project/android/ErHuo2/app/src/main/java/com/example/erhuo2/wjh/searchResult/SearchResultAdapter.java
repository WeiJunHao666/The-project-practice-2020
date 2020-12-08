package com.example.erhuo2.wjh.searchResult;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.erhuo2.R;

import java.util.ArrayList;


public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private Context context;
    private View inflater;
    private ArrayList<String> dataList = new ArrayList<>();

    public SearchResultAdapter(Context context, ArrayList<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    //移除数据使用notifyItemRemoved
    public void removeData(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item_search_result,parent,false);
        SearchResultAdapter.ViewHolder viewHolder = new SearchResultAdapter.ViewHolder(inflater);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.ViewHolder holder, int position) {
        holder.setData(dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList!=null ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.goods_img);
            textView = (TextView) itemView.findViewById(R.id.goods_title);
        }

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
                                layoutParams.width =  getScreenWidth(context) / 2;//这个是布局的宽度
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
            DisplayMetrics outMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(outMetrics);
            return outMetrics.widthPixels;
        }



//        public void down(final String id){
//            new Thread(){
//                @Override
//                public void run() {
//                    super.run();
//                    DownloadFile downloadFile = new DownloadFile();
//                    downloadFile.clickDown(imageView, id);
//                }
//            }.start();
//        }

    }
}
