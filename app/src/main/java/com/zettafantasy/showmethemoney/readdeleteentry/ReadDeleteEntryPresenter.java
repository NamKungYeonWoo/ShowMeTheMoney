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
        Cursor cursor = mContext.getContentResolver().query(MoneyEntryProvider.MoneyEntries.CONTENT_URI
                , null, null, null, null);
        mAdapter.setCursor(cursor);
    }

    @Override
    public void delete(long id) {
        int rowsDeleted = 0;

        try {
            rowsDeleted = mContext.getContentResolver().delete(MoneyEntryProvider.MoneyEntries.CONTENT_URI,
                    "_id = ?", new String[]{Long.toString(id)});
        } catch (Exception e) {
            Log.e(TAG, "Fail to delete entry, id : " + id, e);
        }

        if (rowsDeleted == 0) {
            Log.e(TAG, "Fail to delete entry, id : " + id);
        }
    }
}
