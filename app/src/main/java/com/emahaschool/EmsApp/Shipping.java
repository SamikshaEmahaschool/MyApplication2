package com.emahaschool.EmsApp;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Shipping extends AppCompatActivity {

    private ShippingAdapter sAdapter;

    SessionManagement session;
    InnerSessionmanagement innerSession;
    EmsSqlserverDBHelper sqlserDB;
    RecyclerView ShippingfinalRecylerView;

    com.balysv.materialripple.MaterialRippleLayout lyt_next;

    public int totalcost = 0;

    public int totalqty = 0;


    Boolean statusordrplace;

    String userid,usercartsessionid,orderid,customername,totquantity,totcost,txtproductID,txtIMGUrl,websiteURL;

    public List<CartItems> cartList = new ArrayList<>();


    Random rndnumber = new Random();
    public int rndnumber1 = rndnumber.nextInt(1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        session = new SessionManagement(this);
        HashMap<String,String> user = session.getUserDetails();
        userid = user.get(SessionManagement.KEY_ID);
        customername = user.get(SessionManagement.KEY_NAME);


        innerSession = new InnerSessionmanagement(this);
        HashMap<String,String> usercartssnid = innerSession.getUserCartSessionDetails();
        usercartsessionid = usercartssnid.get(InnerSessionmanagement.KEY_USERCARTID);

        sqlserDB = new EmsSqlserverDBHelper();


        lyt_next = findViewById(R.id.lyt_next);
        lyt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderid = "EMSOrder" + rndnumber1;
                totquantity = String.valueOf(totalqty);
                totcost = String.valueOf(totalcost);
                placeOrder(userid,usercartsessionid,orderid,customername,totquantity,totcost);
            }
        });


        websiteURL = "http://www.emahaschool.com/";


        ShippingfinalRecylerView = findViewById(R.id.ShippingfinalRecylerView);


        sAdapter = new ShippingAdapter(getApplicationContext(),cartList);
        RecyclerView.LayoutManager cLayoutManager = new GridLayoutManager(this, 1);
        ShippingfinalRecylerView.setLayoutManager(cLayoutManager);
        ShippingfinalRecylerView.addItemDecoration(new Shipping.GridSpacingItemDecoration(1, dpToPx(10), true));
        ShippingfinalRecylerView.setItemAnimator(new DefaultItemAnimator());
        ShippingfinalRecylerView.setAdapter(sAdapter);


        prepareShippingList();

    }


    public void prepareShippingList()
    {
        try {

            JSONObject cartlistObject = new JSONObject(sqlserDB.retriveCartData(usercartsessionid));
            JSONArray cartItemsArrayList = cartlistObject.getJSONArray("cartItemsArrayList");

            // looping through All items
            for (int i = 0; i < cartItemsArrayList.length(); i++)
            {
                CartItems crtitems = new CartItems();
                JSONObject c = cartItemsArrayList.getJSONObject(i);

                txtproductID = c.getString("UserID");
                crtitems.setproduct_ID(c.getString("ProductID"));
                crtitems.settitlecart(sqlserDB.getProductname(c.getString("ProductID")));

                txtIMGUrl = websiteURL + sqlserDB.getImgByProductID(c.getString("ProductID"));
                crtitems.setproductIMG(txtIMGUrl);

                crtitems.setpriceCart(c.getString("Amount"));

                totalcost = totalcost + Integer.parseInt(c.getString("Amount"));

                totalqty = totalqty + Integer.parseInt(c.getString("QuantityPurchase"));

                crtitems.setproductqty(c.getString("QuantityPurchase"));

                cartList.add(crtitems);

            }


        } catch (Exception e)
        {

        }

        sAdapter.notifyDataSetChanged();
    }


    public void placeOrder(String userid,String usercartsessionid,String orderid,String customername,String totquantity,String totcost)
    {
        if (session.checkLogin())
        {
            statusordrplace = sqlserDB.orderplace(userid,usercartsessionid,orderid,customername,totquantity,totcost);

            if(statusordrplace)
            {
                Toast.makeText(getApplicationContext(), "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                innerSession.logoutUserCartSessionID();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Order Placed failed", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please Login First", Toast.LENGTH_SHORT).show();
        }
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge)
        {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount)
                { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
