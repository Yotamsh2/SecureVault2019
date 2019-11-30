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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cryptography.Cryptography;


public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private List<Record> records;

    private OnRecordListener mOnRecordListener;
    private Cryptography cryptography;
    private String CRYPTO_KEY;
    Field[] allDrawablesfromRes_drawable = com.securevault19.securevault2019.R.drawable.class.getFields();


    public RecordAdapter(ArrayList<Record> records, OnRecordListener onRecordListener,String CRYPTO_KEY) {
        this.CRYPTO_KEY = CRYPTO_KEY;
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

        try {
            Log.d("holferTest","entered try");
            Log.d("holferTest","key " + CRYPTO_KEY);
            holder.textViewTitle.setText(cryptography.decrypt(currentRecord.getTitle(),CRYPTO_KEY));
            Log.d("holferTest","passed first decryption");
            holder.textViewRecord.setText(cryptography.decrypt(currentRecord.getUserName(),CRYPTO_KEY));
            Log.d("holferTest","passed second decryption");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("iconCheck",""+currentRecord.getIcon());
        String iconName = currentRecord.getIcon();
        int iconID = convertIconNameToID(iconName);
        holder.recordIcon.setImageResource(iconID);

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

    private int convertIconNameToID(String iconName) {
        int iconID;                        //for loop to get the proper drawable's id
        for (Field field : allDrawablesfromRes_drawable) {

            if (field.getName().equals(iconName)) {
                try {
                    iconID = field.getInt(null);
                    return iconID;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}