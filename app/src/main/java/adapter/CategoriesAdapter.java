package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alnehal.chiniotestimatorpakwan.CategoriesActivity;
import com.alnehal.chiniotestimatorpakwan.ItemsActivity;
import com.alnehal.chiniotestimatorpakwan.ParentActivity;
import com.alnehal.chiniotestimatorpakwan.R;
import com.alnehal.chiniotestimatorpakwan.SubCategoriesActivity;

import java.util.ArrayList;

import model.CategoriesModel;
import model.ItemModel;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private ArrayList<CategoriesModel> categoriesModelArrayList;
    private Context context;

    private void showQuantityPickerDialog() {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvSr;
        private View row;

        MyViewHolder(View view) {
            super(view);
            row = view;
            tvSr = (TextView) view.findViewById(R.id.tvSr);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> categoriesModelArrayList) {
        this.context = context;
        this.categoriesModelArrayList = categoriesModelArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_categories, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CategoriesModel categoriesModel = categoriesModelArrayList.get(position);
        holder.tvSr.setText(String.valueOf(position + 1));
        holder.tvTitle.setText(Html.fromHtml(categoriesModel.getName()));


        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putInt("catID", categoriesModel.getCatid());
                ((CategoriesActivity) context).gotoActivity(ItemsActivity.class, extras);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesModelArrayList.size();
    }
}
