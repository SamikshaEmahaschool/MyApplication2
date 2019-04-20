package com.emahaschool.EmsApp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ComputerSliderImagesAdapter extends PagerAdapter
{
    Context context;
    LayoutInflater inflater;

    public int[] lst_image = {
            R.drawable.shoeshomepage,
            R.drawable.computerslide2,
            R.drawable.computerslide3,

    };

    public ComputerSliderImagesAdapter(Context context){

        this.context= context;
    }


    @Override
    public int getCount() {
        return lst_image.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);

    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);

        ImageView imgslides = (ImageView) view.findViewById(R.id.slideimg);
        imgslides.setImageResource(lst_image[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);

    }
}
