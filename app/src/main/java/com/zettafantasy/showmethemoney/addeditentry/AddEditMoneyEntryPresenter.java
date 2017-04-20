package com.zettafantasy.showmethemoney.addeditentry;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zettafantasy.showmethemoney.data.MoneyEntryColumns;
import com.zettafantasy.showmethemoney.data.MoneyEntryProvider;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;
import com.zettafantasy.showmethemoney.readdeleteentry.MoneyEntriesAdapter;

/**
 * @author UJ
 */

public class AddEditMoneyEntryPresenter implements AddEditMoneyEntryContract.Presenter {
    private final String TAG = AddEditMoneyEntryPresenter.class.getSimpleName();
    private final AddEditMoneyEntryContract.View view;
    private final long entryId;
    private final Context context;
    private MoneyEntry model;

    public AddEditMoneyEntryPresenter(AddEditMoneyEntryContract.View view, Context context, long entryId) {
        this.view = view;
        this.context = context;
        this.entryId = entryId;
    }

    @Override
    public void start() {
        if (!isEntryNew()) {
            populateEntry();
        }
    }

    @Override
    public void saveEntry(MoneyEntry entry) {
        Log.v(TAG, entry.toString());

        ContentValues cv = new ContentValues();
        cv.put(MoneyEntryColumns.TYPE, entry.getType().getValue());
        cv.put(MoneyEntryColumns.DATE, entry.getDate());
        cv.put(MoneyEntryColumns.SUB_TYPE, entry.getSubType());
        cv.put(MoneyEntryColumns.AMOUNT, entry.getAmount());
        cv.put(MoneyEntryColumns.MEMO, entry.getMemo());

        Uri uri = context.getContentResolver()
                .insert(MoneyEntryProvider.MoneyEntries.CONTENT_URI, cv);

        Log.v(TAG, uri.toString());
    }

    @Override
    public void populateEntry() {
        if (isEntryNew()) {
            throw new RuntimeException("populateEntry() was called but entry is new.");
        }

        //TODO DB 조회 후 model 채움
        model = MoneyEntry.getInstance();

        view.setEntry(model);
    }

    private boolean isEntryNew() {
        return entryId < 0;
    }
}
