package com.emahaschool.EmsApp;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener
{

    Boolean productstatus;
    private List<Product> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterProduct mAdapter;
    private static int currentPage = 0;
    private ViewPager viewPager,viewPage;
    private slideAdapter myadapter;
    private ComputerSliderImagesAdapter madapter;
    SessionManagement session;

    String clickbtn;

    EmsSqlserverDBHelper sqldbhelper;
    EditText et_search;
    ImageButton searchbtn;

    NavigationView nav_view;
    FloatingActionButton bagcon,compicon,favicon,shoeicon,sportcon,dresscon,tiffincon,stationary,bottels,idcard,book,likes;

    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        session = new SessionManagement(this);

        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(SessionManagement.KEY_NAME);

        nav_view = findViewById(R.id.nav_view);

        View header = nav_view.getHeaderView(0);
        name = header.findViewById(R.id.name);
        name.setText(username);

        initSlider();


        viewPage =(ViewPager) findViewById(R.id.viewPage);
        madapter = new ComputerSliderImagesAdapter(this);
        viewPage.setAdapter(madapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        bagcon = findViewById(R.id.bagcon);
        bagcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","2");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        compicon = findViewById(R.id.compicon);
        compicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","10");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        favicon = findViewById(R.id.favicon);
        favicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","5");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        shoeicon = findViewById(R.id.shoeicon);
        shoeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","8");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        sportcon = findViewById(R.id.sportcon);
        sportcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dresscon = findViewById(R.id.dresscon);
        dresscon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","1");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tiffincon = findViewById(R.id.tiffincon);
        tiffincon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","9");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        stationary = findViewById(R.id.stationary);
        stationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","11");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        idcard = findViewById(R.id.idcard);
        idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","7");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottels = findViewById(R.id.bottels);
        bottels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","3");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
                    i.putExtra("ID","6");
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        likes = findViewById(R.id.likes);
        likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLogin())
                {
                    Intent i = new Intent(getApplicationContext(),AddToWishList.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You should Login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

//                Snackbar.make(view, "Add to Cart", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent i = new Intent(getApplicationContext(), AddToCart.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        searchbtn = findViewById(R.id.searchbtn);
        et_search = findViewById(R.id.et_search);

        handleIntent(getIntent());

    }

    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent)
    {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }
    public void initSlider()
    {
        viewPager =(ViewPager)findViewById(R.id.viewPager);
        myadapter = new slideAdapter(this);
        viewPager.setAdapter(myadapter);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == myadapter.getCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            session.logoutUser();

        } else if (id == R.id.nav_about)
        {
            Intent i = new Intent(getApplicationContext(),AboutUs.class);
            startActivity(i);

        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_wishlist)
        {
            Intent i = new Intent(getApplicationContext(), AddToWishList.class);
            startActivity(i);
        }

        else if (id == R.id.nav_filter)
        {
            Intent i = new Intent(getApplicationContext(), AddToWishList.class);
            startActivity(i);
        }

        else if (id == R.id.nav_product) {
            Intent i = new Intent(getApplicationContext(),ProductListActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_profile ) {
            Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(i);
        }

        else if (id == R.id.nav_rating )
        {
            Intent i = new Intent(getApplicationContext(),RatingsAndReviews.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

