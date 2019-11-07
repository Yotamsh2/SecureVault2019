package com.securevault19.securevault2019.record;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.securevault19.securevault2019.R;

import java.util.ArrayList;
import java.util.List;

import cryptography.Cryptography;


public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private List<Record> records;

    private OnRecordListener mOnRecordListener;
    private Cryptography cryptography;
    private String userKey;

    public RecordAdapter(ArrayList<Record> records, OnRecordListener onRecordListener,String userKey) {
        this.userKey = userKey;
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
        cryptography = new Cryptography();  //used for decryption


        Record currentRecord = records.get(position);
        holder.textViewTitle.setText(currentRecord.getTitle());
//        final String userName = currentRecord.getUserName(); //decrypting username
//        Log.d("crypto", "1: "+ userName + " and: " + currentRecord.getUserName());
//        try {
//            cryptography.decrypt(userName, currentRecord.getUserName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Log.d("crypto", "2: "+ userName + " and: " + currentRecord.getUserName());
//        holder.textViewRecord.setText(userName);

        try {
            holder.textViewTitle.setText(cryptography.decrypt(currentRecord.getTitle(),userKey));
            holder.textViewRecord.setText(cryptography.decrypt(currentRecord.getUserName(),userKey));
        } catch (Exception e) {
            e.printStackTrace();
        }


        String iconID = currentRecord.getIcon();
        holder.recordIcon.setImageResource(Integer.valueOf(iconID));


       // holder.textViewType.setText(currentRecord.getType());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<Record> records) {
        Log.d("type",""+ records);
        this.records = records;
        notifyDataSetChanged();
    }




    class RecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewRecord;
        private ImageView recordIcon;
        OnRecordListener onRecordListener;
        private TextView textViewType;




        public RecordHolder(@NonNull View itemView, OnRecordListener onRecordListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewRecord = itemView.findViewById(R.id.record);
            recordIcon = itemView.findViewById(R.id.icon);
            textViewType = itemView.findViewById(R.id.typeOfRecord);

            this.onRecordListener = onRecordListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onRecordListener.onRecordClick(getAdapterPosition(), records);
        }
    }

    public interface OnRecordListener {
        void onRecordClick(int position, List<Record> records);
    }


}