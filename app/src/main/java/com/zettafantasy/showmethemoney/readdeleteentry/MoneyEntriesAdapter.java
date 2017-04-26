package com.zettafantasy.showmethemoney.readdeleteentry;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zettafantasy.showmethemoney.MoneyEntryUtils;
import com.zettafantasy.showmethemoney.R;
import com.zettafantasy.showmethemoney.StringUtils;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

/**
 * @author UJ
 */

public class MoneyEntriesAdapter extends RecyclerView.Adapter<MoneyEntriesAdapter.ViewHolder> {

    private final Context mContext;
    private Cursor mCursor;

    public MoneyEntriesAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View moneyEntryView = inflater.inflate(R.layout.list_item_money_entry, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(moneyEntryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        MoneyEntry entry = MoneyEntryUtils.getMoneyEntry(mCursor);

        holder.memoView.setText(entry.getMemo());
        holder.amountView.setText(StringUtils.makeNumberComma(entry.getAmount()));
        if (entry.getType() == MoneyEntry.Type.INCOME) {
            holder.amountView.setTextColor(mContext.getResources().getColor(R.color.colorIncome, null));
        } else if (entry.getType() == MoneyEntry.Type.EXPENSE) {
            holder.amountView.setTextColor(mContext.getResources().getColor(R.color.colorExpense, null));
        } else {
            throw new IllegalArgumentException();
        }
        holder.subtypeView.setText(MoneyEntryUtils.getSubTypeText(entry.getSubType()));
        holder.dateView.setText(MoneyEntryUtils.getDateText(entry.getDate()));
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public void setCursor(Cursor cursor) {
        this.mCursor = cursor;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView dateView;
        public final TextView subtypeView;
        public final TextView memoView;
        public final TextView amountView;

        public ViewHolder(View itemView) {
            super(itemView);

            memoView = (TextView) itemView.findViewById(R.id.list_item_memo_tv);
            dateView = (TextView) itemView.findViewById(R.id.list_item_date_tv);
            subtypeView = (TextView) itemView.findViewById(R.id.list_item_subtype_tv);
            amountView = (TextView) itemView.findViewById(R.id.list_item_amount_tv);
        }
    }
}
