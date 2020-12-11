package com.example.erhuo2.zsl.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.erhuo2.R;
import com.example.erhuo2.zsl.entities.ProductImgEntity;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ProductImgEntity> mImages;
    private OnItemClickListener onItemClickListener;

    public ViewPagerAdapter(Context context, List<ProductImgEntity> mImages) {
        this.mContext = context;
        this.mImages = mImages;
    }

    public void setData(List<ProductImgEntity> mImages) {
        this.mImages = mImages;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.mImages.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ImageView image = new ImageView(mContext);
        Glide.with(mContext)
                .load("http://"+mImages.get(position))
                .error(R.drawable.img_error)
                .into(image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setCropToPadding(true);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick();
                }
            }
        });
        ((ViewPager) container).addView(image);
        return image;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
