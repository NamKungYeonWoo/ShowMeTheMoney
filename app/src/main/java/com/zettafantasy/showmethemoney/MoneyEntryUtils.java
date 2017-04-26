package com.zettafantasy.showmethemoney;

import android.content.Context;
import android.database.Cursor;

import com.zettafantasy.showmethemoney.data.MoneyEntryColumns;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author UJ
 */
public class MoneyEntryUtils {
    private static String[] SUBTYPE_TEXT_LIST;
    private static Calendar mCalendar = Calendar.getInstance();

    public static void init(Context context) {
        SUBTYPE_TEXT_LIST = context.getResources().getStringArray(R.array.entry_expense_type);
    }

    public static MoneyEntry getMoneyEntry(Cursor mCursor) {
        if (mCursor == null || mCursor.isClosed()) {
            return null;
        }

        MoneyEntry entry = new MoneyEntry();
        long date = mCursor.getLong(mCursor.getColumnIndex(MoneyEntryColumns.DATE));
        entry.setDate(date);
        int type = mCursor.getInt(mCursor.getColumnIndex(MoneyEntryColumns.TYPE));
        entry.setType(MoneyEntry.Type.valueOf(type));
        int subType = mCursor.getInt(mCursor.getColumnIndex(MoneyEntryColumns.SUB_TYPE));
        entry.setSubType(subType);
        long amount = mCursor.getLong(mCursor.getColumnIndex(MoneyEntryColumns.AMOUNT));
        entry.setAmount(amount);
        String memo = mCursor.getString(mCursor.getColumnIndex(MoneyEntryColumns.MEMO));
        entry.setMemo(memo);

        return entry;
    }

    public static String[] getSubTypeText() {
        return SUBTYPE_TEXT_LIST;
    }

    public static String getSubTypeText(int subType) {
        if (subType < 0 || subType >= SUBTYPE_TEXT_LIST.length) {
            throw new IllegalArgumentException();
        }

        return SUBTYPE_TEXT_LIST[subType];
    }

    public static String getDateText(long date) {
        mCalendar.setTimeInMillis(date);

        return String.format(Locale.KOREAN, "%02d.%02d",
                mCalendar.get(Calendar.MONTH) + 1,
                mCalendar.get(Calendar.DAY_OF_MONTH));
    }


}
