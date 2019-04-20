package com.emahaschool.EmsApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    SessionManagement session;


    EmsSqlserverDBHelper sqlserDB;
    private RecyclerView recyclerView;
    private List<Product> productList = new ArrayList<>();
    private AdapterProduct mAdapter;
    String product_id,txtproductID,userID,usersession;
    String rtproductid;
    Button likefloating;
    TextView productID,title,add_to_cart,product_cost;

    ProgressDialog progress;

    Toolbar toolbar;

    public int productcount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar action = getSupportActionBar();

        action.setDisplayHomeAsUpEnabled(true);

        session = new SessionManagement(this);

        HashMap<String,String> hashMap = session.getUserDetails();
        userID = hashMap.get(SessionManagement.KEY_ID);

        sqlserDB = new EmsSqlserverDBHelper();


        rtproductid = getIntent().getStringExtra("ID");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new AdapterProduct(getApplicationContext(), productList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareProductList();

        progress = new ProgressDialog(this);
        progress.setTitle("Please Wait");
        progress.setMessage("");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

    }


    private void prepareProductList() {

        try {

            JSONObject productlistObject = new JSONObject(sqlserDB.getProductdata());
            JSONArray productListArray = productlistObject.getJSONArray("productObj");

            // looping through All items
            for (int i = 0; i < productListArray.length(); i++)
            {
                Product prditems = new Product();
                JSONObject c = productListArray.getJSONObject(i);

                product_id = c.getString("ProductCategoryID");
                if(rtproductid.equalsIgnoreCase(c.getString("ProductCategoryID")))
                {

                    txtproductID = c.getString("ProductName");
                    prditems.setproduct_ID(c.getString("ProductID"));
                    prditems.setproduct_name(c.getString("ProductName"));
                    prditems.setproduct_cost(Integer.parseInt(c.getString("Amount")));
                    prditems.setThumbnail(c.getString("ProductImage"));
//                    prditems.setproductQuick(c.getString("RProductID"));
                    productList.add(prditems);

                }

            }
        } catch (Exception e) {


        }


        mAdapter.notifyDataSetChanged();
    }

    public void getID(View v)
    {
        RelativeLayout rlayout = (RelativeLayout) v.getParent();
        productID = rlayout.findViewById(R.id.productID);
        title = rlayout.findViewById(R.id.title);

        product_cost = rlayout.findViewById(R.id.product_cost);

        Intent i = new Intent(getApplicationContext(),CardFullView.class);
        i.putExtra("productid",productID.getText().toString());
        i.putExtra("title",title.getText().toString());
        i.putExtra("cost",product_cost.getText().toString());
        startActivity(i);
//        Toast.makeText(getApplicationContext(), productID.getText().toString()+ title.getText(), Toast.LENGTH_SHORT).show();

    }



    public void getCart(View view)
    {
        LinearLayout rlayout = (LinearLayout) view.getParent();
        TextView proid = rlayout.findViewById(R.id.productID);
        TextView title = rlayout.findViewById(R.id.title);
        TextView product_cost = rlayout.findViewById(R.id.product_cost);
        String txt_proID = proid.getText().toString();
        String txt_prod_name = title.getText().toString();
        String qty = "1";
        String cost = product_cost.getText().toString();
        add_to_cart = rlayout.findViewById(R.id.add_to_cart);
        Intent i = new Intent(getApplicationContext(), AddToCart.class);
        startActivity(i);

        addtocart(txt_prod_name,userID,usersession,txt_proID,qty,cost);

    }

    public void addtocart(String txt_prod_name,String userID,String usersession, String txt_proID, String qty, String cost)
    {

        boolean retCartStatus = sqlserDB.addtocartTbl(txt_prod_name,userID,usersession,txt_proID,qty,cost);
        if (retCartStatus)
        {
            Toast.makeText(getApplicationContext(), "Items Inserted Sucessfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Items are not Inserted", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
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

                if (position < spanCount) { // top edge
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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));

    }



}
