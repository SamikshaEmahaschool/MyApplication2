package com.emahaschool.EmsApp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder>
{

    private Context mContext;
    private List<Product> productList;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ViewPager pager;
        public TextView title,cost,productID;
        public ImageView thumbnail, add_to_wishlist;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            cost = view.findViewById(R.id.product_cost);
            productID = view.findViewById(R.id.productID);
            thumbnail = view.findViewById(R.id.thumbnail);
            pager = view.findViewById(R.id.pager);
            add_to_wishlist = view.findViewById(R.id.add_to_wishlist);
        }
    }

    public AdapterProduct(Context context,List<Product> productList){
        this.mContext = context;
        this.productList = productList;
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position){

//        Image image = productList.get(position);

        Product product = productList.get(position);

        holder.title.setText(product.getproduct_name());
        holder.cost.setText(String.valueOf(product.getproduct_cost()));
        holder.productID.setText(product.getproduct_ID());
//        Glide.with(mContext).load(product.getThumbnail()).into(holder.thumbnail);


        Glide.with(mContext).load(product.getThumbnail())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);


        holder.add_to_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return productList.size();
    }


}
