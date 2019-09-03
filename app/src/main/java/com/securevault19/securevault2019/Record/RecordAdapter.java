package com.securevault19.securevault2019.Record;

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

    private List<Website> websites = new ArrayList<>();

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_explorer_main, parent, false);
        return new RecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        Website currentWebsite = websites.get(position);
        holder.textViewTitle.setText(currentWebsite.getTitle());
        holder.textViewUserName.setText(currentWebsite.getUserName());
        holder.textViewPassword.setText(currentWebsite.getPassword());
        holder.textViewWebsite.setText(currentWebsite.getWebsite());
        holder.textViewEmail.setText(currentWebsite.getEmail());
        holder.textViewExpiringDateDay.setText(currentWebsite.getExpiringDate());
        holder.textViewExpiringDateMonth.setText(currentWebsite.getExpiringDate());
        holder.textViewExpiringDateYear.setText(currentWebsite.getExpiringDate());
        holder.textViewOther.setText(currentWebsite.getOther()); //Note input by the User
    }

    @Override
    public int getItemCount() {
        return websites.size();
    }

    public void setWebsites(List<Website> websites){
        this.websites = websites;
        notifyDataSetChanged();
    }
    public Website getWebsiteAt(int position) { return websites.get(position); }


    class RecordHolder extends RecyclerView.ViewHolder {
        private TextView textViewUserName;
        private TextView textViewTitle;
        private TextView textViewPassword;
        private TextView textViewWebsite;
        private TextView textViewEmail;
        private TextView textViewExpiringDateDay;
        private TextView textViewExpiringDateMonth;
        private TextView textViewExpiringDateYear;
        private TextView textViewOther;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.userName);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewPassword = itemView.findViewById(R.id.password);
            textViewEmail = itemView.findViewById(R.id.email);
            textViewWebsite = itemView.findViewById(R.id.website);
            textViewExpiringDateDay = itemView.findViewById(R.id.expiringDate_day);
            textViewExpiringDateMonth = itemView.findViewById(R.id.expiringDate_month);
            textViewExpiringDateYear = itemView.findViewById(R.id.expiringDate_year);
            textViewOther = itemView.findViewById(R.id.note);

        }
    }
}
