package com.zettafantasy.showmethemoney.addeditentry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zettafantasy.showmethemoney.R;
import com.zettafantasy.showmethemoney.StringUtils;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Main UI for the add entry screen.
 * Users can enter that date, type(income or expense), subtype, amount, memo
 */
public class AddEditMoneyEntryFragment extends Fragment implements AddEditMoneyEntryContract.View {
    public static final String ARGUMENT_EDIT_ENTRY_ID = "ENTRY_EDIT_ID";

    private Unbinder unbinder;

    @BindView(R.id.vgDialPad)
    ViewGroup mDialPad;
    @BindView(R.id.tvAmount)
    TextView mTvAmount;
    @BindView(R.id.tvNumber1)
    TextView mTvNumber1;
    @BindView(R.id.tvNumber2)
    TextView mTvNumber2;
    @BindView(R.id.tvNumber3)
    TextView mTvNumber3;

    private long mAmount = 0;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money_entry, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initNumberPad();
    }

    private void initNumberPad() {
        mTvAmount.setText(StringUtils.makeNumberComma(mAmount));

        mTvNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAmount = (mAmount == 0 ? 1 : mAmount * 10 + 1);
                mTvAmount.setText(StringUtils.makeNumberComma(mAmount));
            }
        });

        mTvNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAmount = (mAmount == 0 ? 2 : mAmount * 10 + 2);
                mTvAmount.setText(StringUtils.makeNumberComma(mAmount));
            }
        });

        mTvNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAmount = (mAmount == 0 ? 3 : mAmount * 10 + 3);
                mTvAmount.setText(StringUtils.makeNumberComma(mAmount));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}