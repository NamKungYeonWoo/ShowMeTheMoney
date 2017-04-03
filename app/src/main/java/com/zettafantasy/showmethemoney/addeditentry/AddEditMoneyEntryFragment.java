package com.zettafantasy.showmethemoney.addeditentry;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zettafantasy.showmethemoney.R;
import com.zettafantasy.showmethemoney.StringUtils;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

import java.util.Calendar;
import java.util.Locale;

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

    @BindView(R.id.tvDate)
    TextView mTvDate;
    Calendar mCalendar;

    @BindView(R.id.tvEntryType)
    TextView tvEntrySubType;
    int subTypeIndex; // TOD def 설정하기
    private String[] SUBTYPE_TEXT_LIST;

    public static AddEditMoneyEntryFragment newInstance() {
        return new AddEditMoneyEntryFragment();
    }

    @Override
    public void setPresenter(AddEditMoneyEntryContract.Presenter presenter) {

    }

    @Override
    public void setDate(long date) {
        mCalendar.setTimeInMillis(date);
        setDate(mCalendar);
    }

    private void setDate(Calendar mCalendar) {
        String formattedDate = String.format(Locale.KOREAN, "%04d.%02d.%02d",
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH) + 1,
                mCalendar.get(Calendar.DAY_OF_MONTH));
        mTvDate.setText(formattedDate);
    }

    @Override
    public void launchDatePicker() {
        new DatePickerDialog(
                getContext(),
                R.style.AppTheme_DatePicker,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(year, month, dayOfMonth);
                        setDate(mCalendar);
                    }
                },
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void setType(MoneyEntry.Type type) {

    }

    @Override
    public void setSubType(int subType) {
        subTypeIndex = subType;
        tvEntrySubType.setText(SUBTYPE_TEXT_LIST[subTypeIndex]);
    }

    @Override
    public void launchSubtypePicker() {
        new AlertDialog.Builder(getContext())
                .setTitle("타입")
                .setSingleChoiceItems(SUBTYPE_TEXT_LIST, subTypeIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        setSubType(i);
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    @Override
    public void setMemo(String memo) {

    }

    @Override
    public void setAmount(long amount) {
        mAmount = amount;
        mTvAmount.setText(StringUtils.makeNumberComma(mAmount));
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

        initDatePicker();
        initNumberPad();
        initEntrySubType();
    }

    private void initEntrySubType() {
        SUBTYPE_TEXT_LIST = getResources().getStringArray(R.array.entry_expense_type);
        setSubType(0);
        tvEntrySubType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSubtypePicker();
            }
        });
    }

    private void initDatePicker() {
        mCalendar = Calendar.getInstance();
        setDate(mCalendar);

        mTvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDatePicker();
            }
        });
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
                        setAmount(mAmount == 0 ? 0 : mAmount * 100);
                    }
                }
        );
        mTvNumberC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setAmount(0);
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
            setAmount(mAmount == 0 ? number : mAmount * 10 + number);
        }
    }
}