package com.zettafantasy.visualhousekeepingbook;

import android.app.DatePickerDialog;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by uije on 17. 2. 15.
 */

public class MoneyEntryFragment extends Fragment {

    private static final String TAG = MoneyEntryFragment.class.getSimpleName();
    private Unbinder unbinder;

    @BindView(R.id.tvPickDate)
    TextView tvPickDate;
    @BindView(R.id.tvNumber1)
    TextView tvNumber1;
    @BindView(R.id.tvNumber2)
    TextView tvNumber2;
    @BindView(R.id.tvNumber3)
    TextView tvNumber3;
    @BindView(R.id.tvNumber4)
    TextView tvNumber4;
    @BindView(R.id.tvNumber5)
    TextView tvNumber5;
    @BindView(R.id.tvNumber6)
    TextView tvNumber6;
    @BindView(R.id.tvNumber7)
    TextView tvNumber7;
    @BindView(R.id.tvNumber8)
    TextView tvNumber8;
    @BindView(R.id.tvNumber9)
    TextView tvNumber9;
    @BindView(R.id.tvNumber0)
    TextView tvNumber0;
    @BindView(R.id.tvNumber00)
    TextView tvNumber00;
    @BindView(R.id.tvNumberC)
    TextView tvNumberC;
    @BindView(R.id.tvAmount)
    TextView tvAmount;

    private final DecimalFormat currencyFormat = new DecimalFormat("#,###,###");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money_entry, container, false);
        unbinder = ButterKnife.bind(this, view);

        initNumberPad();
        initDatePicker();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.expense_type, R.layout.custom_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        //sEntryType.setAdapter(adapter);

        return view;
    }

    private void initDatePicker() {
        final Calendar rightNow = Calendar.getInstance();
        String formattedDate = String.format(
                Locale.KOREAN,
                "%04d.%02d.%02d",
                rightNow.get(Calendar.YEAR),
                rightNow.get(Calendar.MONTH) + 1,
                rightNow.get(Calendar.DAY_OF_MONTH));

        tvPickDate.setText(formattedDate);

        tvPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String formattedDate = String.format(Locale.KOREAN, "%04d.%02d.%02d", year, month + 1, dayOfMonth);
                                tvPickDate.setText(formattedDate);
                            }
                        },
                        rightNow.get(Calendar.YEAR),
                        rightNow.get(Calendar.MONTH),
                        rightNow.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
    }

    private void initNumberPad() {
        View.OnClickListener onClickNumberPadListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = tvAmount.getText().toString();

                switch (v.getId()) {
                    case R.id.tvNumber1:
                        amount += getContext().getString(R.string.number_1);
                        break;
                    case R.id.tvNumber2:
                        amount += getContext().getString(R.string.number_2);
                        break;
                    case R.id.tvNumber3:
                        amount += getContext().getString(R.string.number_3);
                        break;
                    case R.id.tvNumber4:
                        amount += getContext().getString(R.string.number_4);
                        break;
                    case R.id.tvNumber5:
                        amount += getContext().getString(R.string.number_5);
                        break;
                    case R.id.tvNumber6:
                        amount += getContext().getString(R.string.number_6);
                        break;
                    case R.id.tvNumber7:
                        amount += getContext().getString(R.string.number_7);
                        break;
                    case R.id.tvNumber8:
                        amount += getContext().getString(R.string.number_8);
                        break;
                    case R.id.tvNumber9:
                        amount += getContext().getString(R.string.number_9);
                        break;
                    case R.id.tvNumber0:
                        amount += getContext().getString(R.string.number_0);
                        break;
                    case R.id.tvNumber00:
                        amount += getContext().getString(R.string.number_00);
                        break;
                    case R.id.tvNumberC:
                        amount = getContext().getString(R.string.number_0);
                        break;
                    default:
                        throw new IllegalArgumentException("invalid number id " + v.getId());
                }

                // currency formatting
                try {
                    tvAmount.setText(
                            currencyFormat.format(
                                    Long.valueOf(amount.replaceAll("\\D", ""))));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getLocalizedMessage());
                    tvAmount.setText(getContext().getString(R.string.number_0));
                }
            }
        };

        tvNumber1.setOnClickListener(onClickNumberPadListener);
        tvNumber2.setOnClickListener(onClickNumberPadListener);
        tvNumber3.setOnClickListener(onClickNumberPadListener);
        tvNumber4.setOnClickListener(onClickNumberPadListener);
        tvNumber5.setOnClickListener(onClickNumberPadListener);
        tvNumber6.setOnClickListener(onClickNumberPadListener);
        tvNumber7.setOnClickListener(onClickNumberPadListener);
        tvNumber8.setOnClickListener(onClickNumberPadListener);
        tvNumber9.setOnClickListener(onClickNumberPadListener);
        tvNumber0.setOnClickListener(onClickNumberPadListener);
        tvNumber00.setOnClickListener(onClickNumberPadListener);
        tvNumberC.setOnClickListener(onClickNumberPadListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
