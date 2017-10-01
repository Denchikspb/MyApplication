package com.cherepanov.myapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.db.tables.RemindTable;
import com.cherepanov.myapplication.model.Remind;

import java.util.List;

/**
 * Created by Денис on 29.09.2017.
 */

public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.RemindViewHolder> {

    public interface OnDeleteRemindListener {
        public void onDelete();
    }

    private List<Remind> remindList;
    private View view;

    OnDeleteRemindListener onDeleteRemind;
    public void setOnDeleteRemindListener(OnDeleteRemindListener onDeleteRemind){
        this.onDeleteRemind = onDeleteRemind;
    }

    public RemindAdapter(List<Remind> remindList) {
        this.remindList = remindList;
    }

    public void setRemindList(List<Remind> remindList){
        this.remindList = remindList;
    }

    @Override
    public RemindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_remind, parent, false);

        return new RemindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RemindViewHolder holder, int position) {
        final Remind remind = remindList.get(position);
        holder.titleTV.setText(remind.getTitle());
        holder.descriptionTV.setText(remind.getDescription());
        holder.deleteRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int k = RemindTable.deleteRemind(holder.deleteRemind.getContext(), remind.getId());
                if(onDeleteRemind != null){
                    onDeleteRemind.onDelete();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return remindList.size();
    }

    public static class RemindViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView titleTV;
        TextView descriptionTV;
        Button deleteRemind;

        public RemindViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.remind_card_view);
            titleTV = (TextView) itemView.findViewById(R.id.remind_title);
            descriptionTV = (TextView) itemView.findViewById(R.id.remind_description);
            deleteRemind = (Button) itemView.findViewById(R.id.delete_remind);
        }
    }
}
