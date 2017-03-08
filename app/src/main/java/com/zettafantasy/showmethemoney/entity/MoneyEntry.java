package com.zettafantasy.showmethemoney.entity;

import java.util.Date;

/**
 * Created by CA on 2017-03-08.
 */

public class MoneyEntry {
    private long _id;
    private long amount;
    private Date date;
    private String memo;
    private Type type;
    private int subType;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    enum Type {
        EXPENSE(0), INCOME(1);

        private Type(int value) {
            this.value = value;
        }

        int value;
    }
}
