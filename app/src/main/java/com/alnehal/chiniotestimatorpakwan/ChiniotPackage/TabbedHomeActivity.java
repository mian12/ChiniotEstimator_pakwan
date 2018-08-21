package com.alnehal.chiniotestimatorpakwan.ChiniotPackage;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.Toast;

import com.alnehal.chiniotestimatorpakwan.HomeActivity;
import com.alnehal.chiniotestimatorpakwan.ParentActivity;
import com.alnehal.chiniotestimatorpakwan.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import DB.SQLiteDatabaseHelper;
import adapter.MyPackageMenuGridBaseAdapter;
import adapter.MyTabbedFragmentAdapter;
import model.ItemModel;
import model.PackageListModel;
import utilis.MyApplication;
import utilis.MySingleton;

public class TabbedHomeActivity extends AppCompatActivity {



    ViewPager viewPager;

    TabLayout tabLayout;
    public ArrayList<ItemModel> itemModelArrayList;
    public ProgressDialog progressDialog;
    SQLiteDatabaseHelper databaseHelper;
    MyTabbedFragmentAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_home);

        viewPager = findViewById(R.id.viewPager);


        databaseHelper=new SQLiteDatabaseHelper(TabbedHomeActivity.this);

        progressDialog = new ProgressDialog(TabbedHomeActivity.this);
        progressDialog.setTitle("Chiniot Pakwan");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);




        gettingTabPageItems();





        tabLayout = findViewById(R.id.tabLayout);






    }


    public void gettingTabPageItems() {

       // progressDialog.show();



        final StringRequest getRequest = new StringRequest(Request.Method.GET, MyApplication.URL_TABBED_PAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // progressDialog.dismiss();

                        if (response != null) {
                            try {
                                JSONArray jsonArray=new JSONArray(response);

                                handleFetchAllItemsRequestResponse(jsonArray);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                            Toast.makeText(TabbedHomeActivity.this, "Response" + " " + response, Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  progressDialog.dismiss();
                Log.e("error", error.toString());

                Toast.makeText(TabbedHomeActivity.this,
                        "failed to Get Data", Toast.LENGTH_SHORT).show();
            }
        });


        MySingleton.getInstance().addToReqQueue(getRequest);
    }


    private void handleFetchAllItemsRequestResponse(JSONArray response) {
        itemModelArrayList = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject each = response.getJSONObject(i);
                ItemModel itemModel;
                itemModel = new ItemModel();

                  itemModel.setPageNo(each.getString("page_no"));

                itemModel.setProduct_id(each.getInt("product_id"));
                itemModel.setMeat_type(each.getInt("meat_type"));
                itemModel.setDate(each.getString("date"));
                itemModel.setName(each.getString("name"));
                itemModel.setUrdu_name(each.getString("urdu_name"));
                itemModel.setUom(each.getString("uom"));
                try {
                    itemModel.setLabour(each.getString("labour"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setLabour("");
                }
                try {
                    itemModel.setPay_mode(each.getString("pay_mode"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setPay_mode("");
                }
                try {
                    itemModel.setParty_id(each.getInt("party_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setParty_id(-1);
                }
                itemModel.setRate_expt_meat(each.getDouble("rate_expt_meat"));
                itemModel.setWeight_meat(each.getDouble("weight_meat"));
                itemModel.setUrdu_uom(each.getString("urdu_uom"));
                try {
                    itemModel.setSpecification(each.getString("specification"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setSpecification("");
                }
                itemModel.setPer_person(each.getDouble("per_person"));
                itemModel.setStatus(each.getInt("status"));
                try {
                    itemModel.setId(each.getInt("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setId(-1);
                }
                itemModel.setServing(each.getDouble("serving"));
                itemModel.setRound(each.getInt("round"));
                itemModel.setUid(each.getInt("uid"));
                itemModel.setDate_time(each.getString("date_time"));
                itemModel.setCatid(each.getInt("catid"));
                try {
                    itemModel.setSubcatid(each.getInt("subcatid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setSubcatid(-1);
                }
                try {
                    itemModel.setKitchenid(each.getInt("kitchenid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setKitchenid(-1);
                }
                itemModel.setPhoto(each.getString("photo"));
                itemModel.setCategory_name(each.getString("category_name"));
                try {
                    itemModel.setSubcategory_name(each.getString("subcategory_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    itemModel.setSubcategory_name("");
                }
                itemModel.setKitchen_name(each.getString("kitchen_name"));
                // itemModel.setKitchen_name("");
                itemModel.setMrate(each.getDouble("mrate"));

                itemModelArrayList.add(itemModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (itemModelArrayList.size() > 0) {

            databaseHelper.insertProductsPageVise(itemModelArrayList);
            Toast.makeText(this, "data Found", Toast.LENGTH_SHORT).show();

            ArrayList<String> test=new ArrayList<>();
            test.add("Page1");
            test.add("Page2");
            test.add("Page3");
            test.add("Page4");
            test.add("Page5");
            test.add("Page6");
            test.add("Page7");
            test.add("Page8");
            test.add("Page9");
            test.add("Page10");

            adapter = new MyTabbedFragmentAdapter(getSupportFragmentManager(), TabbedHomeActivity.this,test);
            viewPager.setAdapter(adapter);

            tabLayout.setupWithViewPager(viewPager);


           // new ParentActivity.InsertProductsAsync().execute();
        } else {
            Toast.makeText(this, "No data Found", Toast.LENGTH_SHORT).show();
           // hideProgress();
           // showToast("No data Found", Toast.LENGTH_LONG, Gravity.CENTER);
        }
    }
}
