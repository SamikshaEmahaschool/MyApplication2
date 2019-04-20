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

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>
        implements SwipeItemTouchHelper.SwipeHelperAdapter
{
    private Context mContext;
    private List<CartItems> cartList;
    private List<CartItems> items_swiped = new ArrayList<>();
    private int animation_type = 0;


    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, CartItems obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                for (CartItems s : items_swiped) {
                    int index_removed = cartList.indexOf(s);
                    if (index_removed != -1) {
                        cartList.remove(index_removed);
                        notifyItemRemoved(index_removed);
                    }
                }
                items_swiped.clear();
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onItemDismiss(int position) {

        // handle when double swipe
        if (cartList.get(position).swiped) {
            items_swiped.remove(cartList.get(position));
            cartList.remove(position);
            notifyItemRemoved(position);
            return;
        }

        cartList.get(position).swiped = true;
        items_swiped.add(cartList.get(position));
        notifyItemChanged(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements SwipeItemTouchHelper.TouchViewHolder {

        public TextView titleCart,priceCart,productID,edit_qty;

        public ImageView productIMG;


        public View lyt_parent;

//        public EditText edit_qty;

        Button wishlist,del;

        public MyViewHolder(View v) {
            super(v);
            wishlist = v.findViewById(R.id.wishlist);
//            del = v.findViewById(R.id.del);
            productID = v.findViewById(R.id.productID);
            titleCart = v.findViewById(R.id.titleCart);
            priceCart = v.findViewById(R.id.priceCart);
            productIMG =v.findViewById(R.id.productIMG);
            edit_qty  = v.findViewById(R.id.edit_qty);
            lyt_parent = v.findViewById(R.id.lyt_parent);

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(mContext.getResources().getColor(R.color.grey_5));
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public CartAdapter(Context context, List<CartItems> cartList,int animation_type)
    {
        this.mContext = context;
        this.cartList = cartList;
        this.animation_type = animation_type;
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_to_cart_row, parent, false);
        return new MyViewHolder(itemView);
    }


    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder myViewHolder, final int i)
    {
        CartItems cartitems = cartList.get(i);

        myViewHolder.productID.setText(cartitems.getproduct_ID());
        myViewHolder.titleCart.setText(cartitems.gettitlecart());
        myViewHolder.priceCart.setText("₹" + String.valueOf(cartitems.getpriceCart()));

        myViewHolder.edit_qty.setText(cartitems.getproductqty());

        Glide.with(mContext).load(cartitems.getproductIMG())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.productIMG);


        myViewHolder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, cartList.get(i), i);
                }
            }
        });


        myViewHolder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });


        if (cartitems.swiped) {
            myViewHolder.lyt_parent.setVisibility(View.GONE);
        } else {
            myViewHolder.lyt_parent.setVisibility(View.VISIBLE);
        }

        setAnimation(myViewHolder.itemView, i);
    }


    private int lastPosition = -1;
    private boolean on_attach = true;


    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount()
    {
        return cartList.size();
    }
}
