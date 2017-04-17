package com.zettafantasy.showmethemoney.data;

import android.database.sqlite.SQLiteDatabase;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.OnUpgrade;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by UJ on 2017-02-08.
 */
@Database(version = MoneyEntryDatabase.VERSION)
public final class MoneyEntryDatabase {
    private MoneyEntryDatabase() {
    }

    public static final int VERSION = 1;

    @Table(MoneyEntryColumns.class)
    public static final String MONEY_ENTRIES = "money_entries";

    @OnUpgrade
    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MONEY_ENTRIES);
        db.execSQL(com.zettafantasy.showmethemoney.data.generated.MoneyEntryDatabase.MONEY_ENTRIES);
    }
}
