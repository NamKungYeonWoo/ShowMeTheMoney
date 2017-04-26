package com.zettafantasy.showmethemoney;

import android.app.Application;

/**
 * @author UJ
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MoneyEntryUtils.init(this);
    }
}
