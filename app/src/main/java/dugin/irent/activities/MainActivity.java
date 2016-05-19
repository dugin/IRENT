package dugin.irent.activities;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import dugin.irent.R;
import dugin.irent.adapter.CustomViewPager;
import dugin.irent.adapter.ViewPagerAdapter;
import dugin.irent.fragments.CarOwnerInfoFragment;
import dugin.irent.fragments.RentChoiceFragment;
import dugin.irent.util.FacebookUtil;
import dugin.irent.util.PrefManagerUtil;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    Toolbar toolbar;
    PagerSlidingTabStrip tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrefManagerUtil prefManagerUtil = new PrefManagerUtil(this, "FacebookUtil");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = (Toolbar) findViewById(R.id.app_bar); // Attaching the layout to the toolbar object

        setSupportActionBar(toolbar);

        NavigationDrawer();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim,
                android.R.animator.fade_in, android.R.animator.fade_out);

        if (prefManagerUtil.getIsLogged()) {

            final int numOfTabs = 2;

            ViewPagerAdapter adapter = new ViewPagerAdapter(MainActivity.this, getFragmentManager(), numOfTabs);

            // Assigning ViewPager View and setting the adapter
            CustomViewPager pager = (CustomViewPager) findViewById(R.id.pager);

            pager.setPagingEnabled(true);
            pager.setAdapter(adapter);

            // Assiging the Sliding Tab Layout View
            tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

            tabs.setViewPager(pager);

            tabs.setVisibility(View.VISIBLE);

        } else {
            transaction.replace(R.id.mainLayout, new RentChoiceFragment(), "RentChoiceFragment");
            transaction.addToBackStack(null);
        }

        transaction.commit();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        FacebookUtil.getCallbackManager().onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 1)
            finish();

        super.onBackPressed();

    }


    private void NavigationDrawer() {

        AccountHeader headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withOnlyMainProfileImageVisible(true)
                .withSelectionListEnabled(false)
                .withTextColor(Color.WHITE)
                .build();


        Drawer drawer = new DrawerBuilder(this)

                .withSliderBackgroundColorRes(R.color.md_blue_50)
                .withActionBarDrawerToggleAnimated(true)
                .withToolbar(toolbar)
                .withAccountHeader(headerNavigationLeft)
                .withSelectedItemByPosition(1)
                .withActionBarDrawerToggleAnimated(false)

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch (position) {
                            case 2:


                                tabs.setVisibility(View.GONE);
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim,
                                        android.R.animator.fade_in, android.R.animator.fade_out);


                                transaction.replace(R.id.mainLayout, new CarOwnerInfoFragment(), "CarOwnerInfoFragment");
                                transaction.addToBackStack("CarListFragment");
                                transaction.commit();


                                break;
                            case 3:


                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                        "mailto", "hmdugin@gmail.com", null));
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sugestão/Comentário para IRent");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                                startActivity(Intent.createChooser(emailIntent, "Enviando Email..."));
                                break;


                            default:
                        }


                        return false;
                    }
                })

                .build();


        drawer.addItem(new PrimaryDrawerItem()
                .withName("Início")

                .withTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Leelawadee.ttf")));


        drawer.addItem(new PrimaryDrawerItem()
                .withName("Tenho carro")

                .withTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Leelawadee.ttf")));


        drawer.addItem(new PrimaryDrawerItem()
                .withName("Fale conosco")

                .withTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Leelawadee.ttf")));

    }
}

