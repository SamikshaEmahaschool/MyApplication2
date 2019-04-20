package com.emahaschool.EmsApp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddToWishList extends AppCompatActivity {

    private wishlistAdapter wAdapter;
    EmsSqlserverDBHelper sqlserDB;
    RecyclerView wishlist_RecylerView;
    Button btn;

    TextView titleCart,priceCart,productID;
    String txtproductID,txtIMGUrl,websiteURL;
    public List<WishListItems> wishList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_wish_list);

        sqlserDB = new EmsSqlserverDBHelper();
        wishlist_RecylerView = findViewById(R.id.wishlist_RecylerView);

        websiteURL = "http://www.emahaschool.com/";

        wAdapter = new wishlistAdapter(getApplicationContext(),wishList);

        RecyclerView.LayoutManager cLayoutManager = new GridLayoutManager(this, 1);
        wishlist_RecylerView.setLayoutManager(cLayoutManager);
        wishlist_RecylerView.addItemDecoration(new AddToWishList.GridSpacingItemDecoration(1, dpToPx(10), true));
        wishlist_RecylerView.setItemAnimator(new DefaultItemAnimator());
        wishlist_RecylerView.setAdapter(wAdapter);


        prepareCartList();
    }
    public void delRow(View v)
    {

        LinearLayout linearLayout = (LinearLayout) v.getParent().getParent();
        productID = linearLayout.findViewById(R.id.productID);
        titleCart = linearLayout.findViewById(R.id.titleCart);
        priceCart = linearLayout.findViewById(R.id.priceCart);
        String prodID = productID.getText().toString();


        Boolean deleteRow = sqlserDB.deleteWishlistItems(prodID);

        if (deleteRow)
        {
            Toast.makeText(getApplicationContext(), "Item Removed from Wishlist", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(getApplicationContext(), "Unable To Remove", Toast.LENGTH_SHORT).show();
        }

        finish();
        startActivity(getIntent());

    }


    private void prepareCartList() {

        try {

            JSONObject cartlistObject = new JSONObject(sqlserDB.retriveWishlistData());
            JSONArray cartItemsArrayList = cartlistObject.getJSONArray("wishlistItemsArrayList");

            // looping through All items
            for (int i = 0; i < cartItemsArrayList.length(); i++)
            {
                WishListItems wshitems = new WishListItems();
                JSONObject c = cartItemsArrayList.getJSONObject(i);

                txtproductID = c.getString("UserID");
                wshitems.setproduct_ID(c.getString("ProductID"));
                wshitems.settitlecart(sqlserDB.getProductname(c.getString("ProductID")));

                txtIMGUrl = websiteURL + sqlserDB.getImgByProductID(c.getString("ProductID"));
                wshitems.setproductIMG(txtIMGUrl);

                wshitems.setpriceCart(c.getString("Amount"));

                wishList.add(wshitems);
            }

        } catch (Exception e)
        {

        }


        wAdapter.notifyDataSetChanged();
    }

    private Object dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        public GridSpacingItemDecoration(int i, Object dpToPx, boolean b)
        {

        }
    }
}
