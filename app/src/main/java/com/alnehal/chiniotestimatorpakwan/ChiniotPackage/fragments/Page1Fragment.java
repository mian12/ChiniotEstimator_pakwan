package com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alnehal.chiniotestimatorpakwan.R;

import java.util.ArrayList;

import DB.SQLiteDatabaseHelper;
import model.Child;
import model.Group;
import model.ItemModel;


public class Page1Fragment extends Fragment {

SQLiteDatabaseHelper databaseHelper;

RecyclerView recyclerView;
    public Page1Fragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_page1, container, false);
        recyclerView=view.findViewById(R.id.page1_recycler);


        databaseHelper=new SQLiteDatabaseHelper(getActivity());



        ArrayList<ItemModel> productPageViseArrayList=databaseHelper.getProductsByPageVisw("page1");

        String prevCategory="";

        Group group=null;

        ArrayList<Child> childArrayList=null;

        ArrayList<Group> groupArrayList=new ArrayList<>();


        for (int i=0; i<productPageViseArrayList.size(); i++)
        {


            if (!prevCategory.contains(productPageViseArrayList.get(i).getCategory_name()))
            {
                if (i!=0)
                {
                     group.setItems(childArrayList);
                    groupArrayList.add(group);
                }

                childArrayList=new ArrayList<>();

                prevCategory=productPageViseArrayList.get(i).getCategory_name();
                //setting header

                 group=new Group();
                group.setName(prevCategory);
            }


            Child child=new Child();
            child.setName(productPageViseArrayList.get(i).getSubcategory_name());

            childArrayList.add(child);

            //remaing items
            if(i==productPageViseArrayList.size()-1){
                group.setItems(childArrayList);
                groupArrayList.add(group);

            }
        }

        if (groupArrayList.size()>=1)
        {
            Log.e("e",groupArrayList.size()+"");
        }



        if (productPageViseArrayList.size()>1)
        {
            Toast.makeText(getActivity(), "Data Exits", Toast.LENGTH_SHORT).show();
        }




        return  view;
    }

}
