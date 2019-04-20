package com.emahaschool.EmsApp;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class ShippingAdapter extends RecyclerView.Adapter<ShippingAdapter.MyViewHolder>
{

    private Context scontext;
    private List<CartItems> shippingfinallists;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleCart,priceCart,productID;

        public ImageView productIMG;

        public TextView edit_qty;

        Button wishlist,del;

        public MyViewHolder(View v) {
            super(v);

            productID = v.findViewById(R.id.txtproductID);
            titleCart = v.findViewById(R.id.titleCart);
            priceCart = v.findViewById(R.id.price);
            productIMG =v.findViewById(R.id.proimg);
            edit_qty  = v.findViewById(R.id.quantity);

        }
    }


    public ShippingAdapter(Context context, List<CartItems> cartList)
    {
        this.scontext = context;
        this.shippingfinallists = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shippingcartrow, viewGroup, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ShippingAdapter.MyViewHolder myViewHolder, int i) {


        CartItems cartitems = shippingfinallists.get(i);

        myViewHolder.productID.setText(cartitems.getproduct_ID());
        myViewHolder.titleCart.setText(cartitems.gettitlecart());
        myViewHolder.priceCart.setText(String.valueOf(cartitems.getpriceCart()));

        myViewHolder.edit_qty.setText(cartitems.getproductqty());

        Glide.with(scontext).load(cartitems.getproductIMG())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.productIMG);




    }

    @Override
    public int getItemCount() {
        return shippingfinallists.size();
    }

}
