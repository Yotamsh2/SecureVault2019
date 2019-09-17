package com.securevault19.securevault2019.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.securevault19.securevault2019.R;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private List<Record> records = new ArrayList<>();

    private OnRecordListener mOnRecordListener;

    public RecordAdapter(ArrayList<Record> records, OnRecordListener onRecordListener) {
        this.records = records;
        this.mOnRecordListener = onRecordListener;
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);
        return new RecordHolder(itemView, mOnRecordListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        Record currentRecord = records.get(position);
        holder.textViewTitle.setText(currentRecord.getTitle());
        holder.textViewRecord.setText(currentRecord.getUserName());

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<Record> records) {
        this.records = records;
        notifyDataSetChanged();
    }

//    public Website getWebsiteAt(int position) {
//        return websites.get(position);
//    }


    class RecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewRecord;
        OnRecordListener onRecordListener;

        public RecordHolder(@NonNull View itemView, OnRecordListener onRecordListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewRecord = itemView.findViewById(R.id.record);
            this.onRecordListener = onRecordListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onRecordListener.onRecordClick(getAdapterPosition());
        }
    }

    public interface OnRecordListener {
        void onRecordClick(int position);
    }

}