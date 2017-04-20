package com.zettafantasy.showmethemoney.readdeleteentry;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.zettafantasy.showmethemoney.data.MoneyEntryColumns;
import com.zettafantasy.showmethemoney.data.MoneyEntryProvider;


class ReadDeleteEntryPresenter implements ReadDeleteMoneyEntryContract.Presenter {

    private static final String TAG = ReadDeleteEntryPresenter.class.getSimpleName();

    private final Context mContext;
    private final ReadDeleteMoneyEntryContract.View mView;
    private MoneyEntriesAdapter mAdapter;

    public ReadDeleteEntryPresenter(ReadDeleteMoneyEntryContract.View view, Context context,
                                    MoneyEntriesAdapter adaptor) {
        this.mContext = context;
        this.mView = view;
        this.mAdapter = adaptor;
    }

    @Override
    public void start() {

    }

    @Override
    public void showEntries() {
        // list all entry
        Cursor cursor = mContext.getContentResolver().query(MoneyEntryProvider.MoneyEntries.CONTENT_URI
                , null, null, null, null);

        mAdapter.setCursor(cursor);

        while (cursor.moveToNext()) {
            Log.v(TAG, cursor.getString(cursor.getColumnIndex(MoneyEntryColumns.MEMO)));
        }

    }
}
