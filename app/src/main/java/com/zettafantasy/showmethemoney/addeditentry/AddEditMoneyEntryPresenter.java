package com.zettafantasy.showmethemoney.addeditentry;

import android.util.Log;

import com.zettafantasy.showmethemoney.entity.MoneyEntry;

/**
 * Created by uije on 17. 4. 5.
 */

public class AddEditMoneyEntryPresenter implements AddEditMoneyEntryContract.Presenter {
    private final String TAG = AddEditMoneyEntryPresenter.class.getSimpleName();
    private final AddEditMoneyEntryFragment view;
    private final long entryId;
    private MoneyEntry model;

    public AddEditMoneyEntryPresenter(AddEditMoneyEntryFragment moneyEntryFragment, long entryId) {
        this.view = moneyEntryFragment;
        this.entryId = entryId;
    }

    @Override
    public void start() {
        populateEntry();
    }

    @Override
    public void setAmount(long amount) {
        model.setAmount(amount);
    }

    @Override
    public void setMemo(String memo) {
        model.setMemo(memo);
    }

    @Override
    public void setDate(long date) {
        model.setDate(date);
    }

    @Override
    public void saveEntry() {
        Log.d(TAG, model.toString());
    }

    @Override
    public void populateEntry() {
        if (entryId == -1) {
            model = MoneyEntry.getInstance();
        } else {
            //TODO DB 조회 후 model 채움
        }

        view.onAmountChanged(model.getAmount());
        view.setType(model.getType());
        view.setSubType(model.getSubType());
        view.setDate(model.getDate());
        view.onMemoChanged(model.getMemo());
    }
}
