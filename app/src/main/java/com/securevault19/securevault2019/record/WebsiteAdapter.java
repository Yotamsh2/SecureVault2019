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

public class WebsiteAdapter extends RecyclerView.Adapter<WebsiteAdapter.WebsiteHolder> {

    private List<Website> websites = new ArrayList<>();

    private OnWebsiteListener mOnWebsiteListener;

    public WebsiteAdapter(ArrayList<Website> websites, OnWebsiteListener onWebsiteListener) {
        this.websites = websites;
        this.mOnWebsiteListener = onWebsiteListener;
    }

    @NonNull
    @Override
    public WebsiteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);
        return new WebsiteHolder(itemView, mOnWebsiteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteHolder holder, int position) {
        Website currentWebsite = websites.get(position);
        holder.textViewTitle.setText(currentWebsite.getTitle());
        holder.textViewWebsite.setText(currentWebsite.getWebsite());

    }

    @Override
    public int getItemCount() {
        return websites.size();
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
        notifyDataSetChanged();
    }

//    public Website getWebsiteAt(int position) {
//        return websites.get(position);
//    }


    class WebsiteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewWebsite;
        OnWebsiteListener onWebsiteListener;

        public WebsiteHolder(@NonNull View itemView, OnWebsiteListener onWebsiteListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewWebsite = itemView.findViewById(R.id.website);
            this.onWebsiteListener = onWebsiteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onWebsiteListener.onWebsiteClick(getAdapterPosition());
        }
    }

    public interface OnWebsiteListener {
        void onWebsiteClick(int position);
    }

}
