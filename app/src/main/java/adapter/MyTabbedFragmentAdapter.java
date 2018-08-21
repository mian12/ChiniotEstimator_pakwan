package adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page1Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page2Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page3Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page4Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page5Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page6Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page7Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page8Fragment;
import com.alnehal.chiniotestimatorpakwan.ChiniotPackage.fragments.Page9Fragment;

import java.util.ArrayList;

public class MyTabbedFragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    ArrayList<String> tabNames;

    public MyTabbedFragmentAdapter(FragmentManager fm, Context context, ArrayList<String> tabNames) {
        super(fm);
        this.context = context;
        this.tabNames=tabNames;

    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return new Page1Fragment();
        else if (position == 1)
            return new Page2Fragment();
        else if (position == 2)
            return new Page3Fragment();
        else if (position == 3)
            return new Page4Fragment();
        else if (position == 4)
            return new Page5Fragment();
        else if (position == 5)
            return new Page6Fragment();
        else if (position == 6)
            return new Page7Fragment();
        else if (position == 7)
            return new Page8Fragment();
        else if (position == 8)
            return new Page9Fragment();
        else if (position == 9)
            return new Page9Fragment();
        else
            return null;


    }

    @Override
    public int getCount() {
        return tabNames.size();
    }

    // ctrl+o

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return tabNames.get(position);

       }





 }