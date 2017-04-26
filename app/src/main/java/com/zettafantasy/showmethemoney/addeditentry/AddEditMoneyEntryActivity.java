/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zettafantasy.showmethemoney.addeditentry;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zettafantasy.showmethemoney.MoneyEntryUtils;
import com.zettafantasy.showmethemoney.R;
import com.zettafantasy.showmethemoney.StringUtils;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Displays an add or edit money entry screen.
 */
public class AddEditMoneyEntryActivity extends AppCompatActivity implements AddEditMoneyEntryContract.View {
    public static final String ARGUMENT_EDIT_ENTRY_ID = "ENTRY_EDIT_ID";

    private AddEditMoneyEntryContract.Presenter mPresenter;

    @BindView(R.id.tvAmount)
    TextView mAmountView;
    @BindView(R.id.tvNumber1)
    View mNumber1View;
    @BindView(R.id.tvNumber2)
    View mNumber2View;
    @BindView(R.id.tvNumber3)
    View mNumber3View;
    @BindView(R.id.tvNumber4)
    View mNumber4View;
    @BindView(R.id.tvNumber5)
    View mNumber5View;
    @BindView(R.id.tvNumber6)
    View mNumber6View;
    @BindView(R.id.tvNumber7)
    View mNumber7View;
    @BindView(R.id.tvNumber8)
    View mNumber8View;
    @BindView(R.id.tvNumber9)
    View mNumber9View;
    @BindView(R.id.tvNumber00)
    View mNumber00View;
    @BindView(R.id.tvNumber0)
    View mNumber0View;
    @BindView(R.id.tvNumberC)
    View mNumberCView;
    @BindView(R.id.tvDate)
    TextView mDateView;
    @BindView(R.id.tvEntrySubType)
    TextView mSubTypeView;
    @BindView(R.id.etMemo)
    TextView mMemoView;
    @BindView(R.id.rg_entry_type)
    RadioGroup mTypeRadioGroup;

    private Unbinder unbinder;

    private long mEntryAmount;
    private long mEntryId;
    private Calendar mCalendar = Calendar.getInstance();
    int mEntrySubtype; // TOD def 설정하기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_add_edit_entry);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initDatePicker();
        initEntrySubType();
        initNumberPad();

        mEntryId = getIntent().getLongExtra(ARGUMENT_EDIT_ENTRY_ID, -1);

        setPresenter(new AddEditMoneyEntryPresenter(this, getApplicationContext(), mEntryId));
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void initDatePicker() {
        setDate(mCalendar);
        mDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDatePicker();
            }
        });
    }

    private void setDate(Calendar mCalendar) {
        String formattedDate = String.format(Locale.KOREAN, "%04d.%02d.%02d",
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH) + 1,
                mCalendar.get(Calendar.DAY_OF_MONTH));
        mDateView.setText(formattedDate);
    }


    public void launchDatePicker() {
        new DatePickerDialog(
                this,
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

    private void initEntrySubType() {
        setSubType(0);
        mSubTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSubtypePicker();
            }
        });
    }

    public void setSubType(int subType) {
        mEntrySubtype = subType;
        mSubTypeView.setText(MoneyEntryUtils.getSubTypeText(mEntrySubtype));
    }

    public void launchSubtypePicker() {
        new AlertDialog.Builder(this)
                .setTitle("타입")
                .setSingleChoiceItems(MoneyEntryUtils.getSubTypeText(), mEntrySubtype, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        setSubType(i);
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private void initNumberPad() {
        mNumber1View.setOnClickListener(new NumberPadClickListener(1));
        mNumber2View.setOnClickListener(new NumberPadClickListener(2));
        mNumber3View.setOnClickListener(new NumberPadClickListener(3));
        mNumber4View.setOnClickListener(new NumberPadClickListener(4));
        mNumber5View.setOnClickListener(new NumberPadClickListener(5));
        mNumber6View.setOnClickListener(new NumberPadClickListener(6));
        mNumber7View.setOnClickListener(new NumberPadClickListener(7));
        mNumber8View.setOnClickListener(new NumberPadClickListener(8));
        mNumber9View.setOnClickListener(new NumberPadClickListener(9));
        mNumber0View.setOnClickListener(new NumberPadClickListener(0));
        mNumber00View.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setAmount(mEntryAmount == 0 ? 0 : mEntryAmount * 100);
                    }
                }
        );
        mNumberCView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setAmount(0);
                    }
                }
        );
    }

    class NumberPadClickListener implements View.OnClickListener {
        long number;

        NumberPadClickListener(long number) {
            this.number = number;
        }

        @Override
        public void onClick(View v) {
            setAmount(mEntryAmount == 0 ? number : mEntryAmount * 10 + number);
        }
    }

    public void setAmount(long amount) {
        this.mEntryAmount = amount;
        mAmountView.setText(StringUtils.makeNumberComma(amount));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            mPresenter.saveEntry(getMoneyEntry());
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private MoneyEntry getMoneyEntry() {
        MoneyEntry moneyEntry = new MoneyEntry();
        moneyEntry.setType(mTypeRadioGroup.getCheckedRadioButtonId() == R.id.radio_income ?
                MoneyEntry.Type.INCOME : MoneyEntry.Type.EXPENSE);
        moneyEntry.setDate(mCalendar.getTimeInMillis());
        moneyEntry.setSubType(mEntrySubtype);
        moneyEntry.setMemo(mMemoView.getText().toString());
        moneyEntry.setAmount(mEntryAmount);
        return moneyEntry;
    }

    @Override
    public void setPresenter(AddEditMoneyEntryContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showEntryList() {

    }

    @Override
    public void showEmptyAmountError() {

    }

    @Override
    public void onSaveButtonClick() {

    }

    @Override
    public void setEntry(MoneyEntry model) {

    }
}
