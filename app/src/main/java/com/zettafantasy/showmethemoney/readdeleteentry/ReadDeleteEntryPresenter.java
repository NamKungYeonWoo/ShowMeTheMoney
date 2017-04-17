package com.zettafantasy.showmethemoney.readdeleteentry;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.zettafantasy.showmethemoney.addeditentry.AddEditMoneyEntryContract;
import com.zettafantasy.showmethemoney.data.MoneyEntryColumns;
import com.zettafantasy.showmethemoney.data.MoneyEntryProvider;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

class ReadDeleteEntryPresenter implements ReadDeleteMoneyEntryContract.Presenter {

    private final Context mContext;
    private final ReadDeleteMoneyEntryContract.View mView;

    public ReadDeleteEntryPresenter(ReadDeleteMoneyEntryContract.View view, Context context) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void showEntries() {

    }
}
