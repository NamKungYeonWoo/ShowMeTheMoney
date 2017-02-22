package com.zettafantasy.visualhousekeepingbook;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zettafantasy.visualhousekeepingbook.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * Created by uije on 17. 2. 15.
 */

public class MoneyEntryFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.tvPickDate)
    TextView tvPickDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money_entry, container, false);
        unbinder = ButterKnife.bind(this, view);

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

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
