package com.emahaschool.EmsApp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class wishlistAdapter extends RecyclerView.Adapter<wishlistAdapter.MyViewHolder>{

    private Context wContext;
    private List<WishListItems> wishList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleCart,priceCart,productID;
        public ImageView productIMG;
        public View wishlist;

        Button btnToCart,delete;

        public MyViewHolder(View view){

            super(view);

            btnToCart = view.findViewById(R.id.btnToCart);
//            delete = view.findViewById(R.id.del);
            titleCart = view.findViewById(R.id.titleCart);
            productID = view.findViewById(R.id.productID);
            priceCart = view.findViewById(R.id.priceCart);
            productIMG = view.findViewById(R.id.productIMG);

        }

    }

    public wishlistAdapter(Context context, List<WishListItems> wishList)
    {
        this.wContext = context;
        this.wishList = wishList;
    }


    @Override
    public wishlistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_to_wishlist_rows,parent,false);

        return new wishlistAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder( wishlistAdapter.MyViewHolder myViewHolder, int i) {

        WishListItems wishListItems = wishList.get(i);

        myViewHolder.titleCart.setText(wishListItems.gettitlecart());
        myViewHolder.priceCart.setText(String.valueOf(wishListItems.getpriceCart()));
        myViewHolder.productID.setText(wishListItems.getproduct_ID());


        Glide.with(wContext).load(wishListItems.getproductIMG())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.productIMG);


    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }


}
