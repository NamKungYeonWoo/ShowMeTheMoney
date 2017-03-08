package com.zettafantasy.showmethemoney.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by UJ on 2017-02-08.
 */

public interface MoneyEntryColumns {
    @PrimaryKey
    @AutoIncrement
    @DataType(DataType.Type.INTEGER)
    public static final String _ID = "_id";

    @DataType(DataType.Type.INTEGER)
    @NotNull
    public static final String TYPE = "type"; // 0 : income, 1 : expense

    @DataType(DataType.Type.INTEGER)
    @NotNull
    public static final String SUB_TYPE = "sub_type";

    @DataType(DataType.Type.INTEGER)
    @NotNull
    public static final String AMOUNT = "amount";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String MEMO = "memo";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String DATE = "date";
}
