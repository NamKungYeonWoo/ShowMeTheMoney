package com.zettafantasy.showmethemoney.readdeleteentry;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zettafantasy.showmethemoney.R;

/**
 * @author UJ
 */

public class MoneyEntriesAdapter extends RecyclerView.Adapter<MoneyEntriesAdapter.ViewHolder> {

    private Cursor mCursor;

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
        //TODO 데이터 읽어오기
        mCursor.moveToPosition(position);


        holder.memoView.setText("hello");
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
