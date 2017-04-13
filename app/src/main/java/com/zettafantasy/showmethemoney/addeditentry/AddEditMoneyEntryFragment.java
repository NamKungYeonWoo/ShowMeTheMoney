package com.zettafantasy.showmethemoney.addeditentry;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
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
    @BindView(R.id.tvDate)
    TextView mTvDate;
    Calendar mCalendar = Calendar.getInstance();
    @BindView(R.id.tvEntrySubType)
    TextView tvEntrySubType;
    int subTypeIndex; // TOD def 설정하기
    @BindView(R.id.etMemo)
    TextView etMemo;
    @BindView(R.id.rg_entry_type)
    RadioGroup rgEntryType;
    private Unbinder unbinder;
    private long lastUpdatedAmount = 0;
    private String[] SUBTYPE_TEXT_LIST;
    private AddEditMoneyEntryContract.Presenter presenter;

    public static AddEditMoneyEntryFragment newInstance() {
        return new AddEditMoneyEntryFragment();
    }

    @Override
    public void setPresenter(AddEditMoneyEntryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDateChanged(long date) {
        presenter.setDate(date);
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
                        onDateChanged(mCalendar.getTimeInMillis());
                    }
                },
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void setType(MoneyEntry.Type type) {
        switch (type) {
            case INCOME:
                rgEntryType.check(R.id.radio_income);
                break;
            case EXPENSE:
                rgEntryType.check(R.id.radio_expense);
                break;
        }
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
    public void onMemoChanged(String memo) {
        presenter.setMemo(memo);
    }

    @Override
    public void onAmountChanged(long amount) {
        lastUpdatedAmount = amount;
        mTvAmount.setText(StringUtils.makeNumberComma(amount));
        presenter.setAmount(amount);
    }

    @Override
    public void showEntryList() {

    }

    @Override
    public void showEmptyAmountError() {

    }

    @Override
    public void onSaveButtonClick() {
        presenter.saveEntry();
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
        initMemo();

        presenter.start();
    }

    private void initMemo() {
        etMemo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                onMemoChanged(s.toString());
            }
        });
    }

    private void initEntrySubType() {
        SUBTYPE_TEXT_LIST = getResources().getStringArray(R.array.entry_expense_type);
        tvEntrySubType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSubtypePicker();
            }
        });
    }

    private void initDatePicker() {
        mTvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDatePicker();
            }
        });
    }

    private void initNumberPad() {
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
                        onAmountChanged(lastUpdatedAmount == 0 ? 0 : lastUpdatedAmount * 100);
                    }
                }
        );
        mTvNumberC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onAmountChanged(0);
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
            onAmountChanged(lastUpdatedAmount == 0 ? number : lastUpdatedAmount * 10 + number);
        }
    }
}