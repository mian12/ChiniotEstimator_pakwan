package com.alnehal.chiniotestimatorpakwan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import DB.SQLiteDatabaseHelper;
import adapter.ItemsAdapter;
import helper.PixelDpConverter;
import helper.VerticalSpaceItemDecoration;

public class ItemsActivity extends ParentActivity {


    public SQLiteDatabaseHelper databaseHelper;

    public SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.items);

        databaseHelper=new SQLiteDatabaseHelper(context);

        swipeRefreshLayout=findViewById(R.id.swipe_to_refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                databaseHelper.deleteAllProducts();

                fetchAllProductsRequest();

                swipeRefreshLayout.setRefreshing(false);

            }
        });

        ititViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void ititViews() {
        context = this;
        dialog = new ProgressDialog(context);
        extras = getIntent().getExtras();
        databaseHelper = new SQLiteDatabaseHelper(context);
         rvItems = (RecyclerView) findViewById(R.id.rvItems);

        LinearLayoutManager itemsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvItems.setLayoutManager(itemsLayoutManager);
        rvItems.addItemDecoration(new VerticalSpaceItemDecoration((int) PixelDpConverter.convertPixelsToDp(getResources().getInteger(R.integer.dp5), context)));

        if (databaseHelper.getTotalProducts() < 1) {
            fetchAllProductsRequest();
        } else {
            itemModelArrayList = databaseHelper.getProductsByCatAndSubCat(extras.getInt("catID"),extras.getInt("subCatID"));
//            itemModelArrayList = databaseHelper.getProductsByCategory(extras.getInt("catID"));
            itemsAdapter = new ItemsAdapter(context, itemModelArrayList, databaseHelper);
            rvItems.setAdapter(itemsAdapter);


        }

//        if (swipeRefreshLayout.isRefreshing())
//        {
//
//        }
    }
}
