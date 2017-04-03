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
    @BindView(R.id.vgDialPad)
    ViewGroup mDialPad;
    @BindView(R.id.tvAmount)
    TextView mTvAmount;
    @BindView(R.id.tvNumber1)
    View mTvNumber1;
    @BindView(R.id.tvNumber2)
    View mTvNumber2;
    @BindView(R.id.tvNumber3)
    View mTvNumber3;
    @BindView(R.id.tvNumber4)
    View mTvNumber4;
    @BindView(R.id.tvNumber5)
    View mTvNumber5;
    @BindView(R.id.tvNumber6)
    View mTvNumber6;
    @BindView(R.id.tvNumber7)
    View mTvNumber7;
    @BindView(R.id.tvNumber8)
    View mTvNumber8;
    @BindView(R.id.tvNumber9)
    View mTvNumber9;
    @BindView(R.id.tvNumber00)
    View mTvNumber00;
    @BindView(R.id.tvNumber0)
    View mTvNumber0;
    @BindView(R.id.tvNumberC)
    View mTvNumberC;
    private Unbinder unbinder;
    private long mAmount = 0;

    public static AddEditMoneyEntryFragment newInstance() {
        return new AddEditMoneyEntryFragment();
    }

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

        mTvNumber1.setOnClickListener(new NumberPadClickListener(1));
        mTvNumber2.setOnClickListener(new NumberPadClickListener(2));
        mTvNumber3.setOnClickListener(new NumberPadClickListener(3));
        mTvNumber4.setOnClickListener(new NumberPadClickListener(4));
        mTvNumber5.setOnClickListener(new NumberPadClickListener(5));
        mTvNumber6.setOnClickListener(new NumberPadClickListener(6));
        mTvNumber7.setOnClickListener(new NumberPadClickListener(7));
        mTvNumber8.setOnClickListener(new NumberPadClickListener(8));
        mTvNumber9.setOnClickListener(new NumberPadClickListener(9));
        mTvNumber0.setOnClickListener(new NumberPadClickListener(0));
        mTvNumber00.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAmount = (mAmount == 0 ? 0 : mAmount * 100);
                        mTvAmount.setText(StringUtils.makeNumberComma(mAmount));
                    }
                }
        );
        mTvNumberC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAmount = 0;
                        mTvAmount.setText(StringUtils.makeNumberComma(mAmount));
                    }
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class NumberPadClickListener implements View.OnClickListener {
        long number;

        NumberPadClickListener(long number) {
            this.number = number;
        }

        @Override
        public void onClick(View v) {
            mAmount = (mAmount == 0 ? number : mAmount * 10 + number);
            mTvAmount.setText(StringUtils.makeNumberComma(mAmount));
        }
    }
}