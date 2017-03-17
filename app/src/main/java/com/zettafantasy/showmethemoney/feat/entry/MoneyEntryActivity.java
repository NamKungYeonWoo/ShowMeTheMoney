package com.zettafantasy.showmethemoney.feat.entry;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zettafantasy.showmethemoney.FragmentAdapter;
import com.zettafantasy.showmethemoney.R;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

import java.util.ArrayList;
import java.util.List;

public class MoneyEntryActivity extends AppCompatActivity {
    private static final String TAG = MoneyEntryActivity.class.getSimpleName();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int mCurPagePos;
    FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_entry);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_entry);
        setSupportActionBar(myToolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        initViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
//            MoneyEntryFragment fragment = (MoneyEntryFragment) mFragmentAdapter.getItem(mCurPagePos);
//            MoneyEntry moneyEntry = fragment.getMoneyEntry();
//
//            Log.v(TAG, moneyEntry.toString());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_main);

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.expense));
        titles.add(getString(R.string.income));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MoneyEntryFragment());
        fragments.add(new MoneyEntryFragment());

        mViewPager.setOffscreenPageLimit(1);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurPagePos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
