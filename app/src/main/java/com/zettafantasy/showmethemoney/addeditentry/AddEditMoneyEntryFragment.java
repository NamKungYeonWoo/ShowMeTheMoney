package com.zettafantasy.showmethemoney.addeditentry;

import android.support.v4.app.Fragment;

import com.zettafantasy.showmethemoney.entity.MoneyEntry;

/**
 * Main UI for the add entry screen.
 * Users can enter that date, type(income or expense), subtype, amount, memo
 */
public class AddEditMoneyEntryFragment extends Fragment implements AddEditMoneyEntryContract.View {
    public static final String ARGUMENT_EDIT_ENTRY_ID = "ENTRY_EDIT_ID";

    @Override
    public void setPresenter(AddEditMoneyEntryContract.Presenter presenter) {

    }

    @Override
    public void setDate(String date) {

    }

    @Override
    public void launchDatePicker() {

    }

    @Override
    public void setType(MoneyEntry.Type type) {

    }

    @Override
    public void setSubType(int subType) {

    }

    @Override
    public void launchSubtypePicker() {

    }

    @Override
    public void setMemo(String memo) {

    }

    @Override
    public void setAmount(long amount) {

    }

    @Override
    public void showEntryList() {

    }

    @Override
    public void showEmptyAmountError() {

    }

    public static AddEditMoneyEntryFragment newInstance() {
        return new AddEditMoneyEntryFragment();
    }
}
