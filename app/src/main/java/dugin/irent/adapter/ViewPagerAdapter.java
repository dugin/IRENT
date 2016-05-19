package dugin.irent.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;

import dugin.irent.R;
import dugin.irent.fragments.CarListFragment;
import dugin.irent.fragments.CarMapFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    private final String TAG = this.getClass().getSimpleName();

    int switch_icons[] = {R.drawable.ic_view_list_white_24dp, R.drawable.ic_map_white_24dp};

    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context context;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(Context context, FragmentManager fm, int mNumbOfTabsumb) {
        super(fm);
        this.context = context;

        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {

            return new CarListFragment();


        } else

            return new CarMapFragment();


    }

    // This method return the titles for the Tabs in the Tab Strip


    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getPageIconResId(int position) {


        return switch_icons[position];
    }
}