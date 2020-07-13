package com.example.android.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<News> mData;


    public RecyclerViewAdapter(Context mContext, List<News> Data) {
        this.mContext = mContext;
        this.mData = Data;
    }

    public void clear() {
        int size = this.mData.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mData.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void addAll(List<News> applications) {
        this.mData.addAll(applications);
        this.notifyItemRangeInserted(0, applications.size() - 1);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.section.setText(mData.get(position).getmSection());
        holder.title.setText(mData.get(position).getmTitle());
        if (mData.get(position).getmAuthor() != null){
        holder.author.setText(mData.get(position).getmAuthor());
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = inputFormat.parse(mData.get(position).getmDate());
            holder.date_title.setText(formatDate(date));
            holder.time_title.setText(formatTime(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUrls = Uri.parse(mData.get(position).getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUrls);

                // Send the intent to launch a new activity
                mContext.startActivity(websiteIntent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView section;
        TextView title;
        TextView date_title;
        TextView time_title;
        TextView author ;
        LinearLayout parentLayout;


        public MyViewHolder(View itemView) {
            super(itemView);

            section = (TextView) itemView.findViewById(R.id.section);
            title = (TextView) itemView.findViewById(R.id.title);
            date_title = (TextView) itemView.findViewById(R.id.date);
            time_title = (TextView) itemView.findViewById(R.id.time);
            author = (TextView) itemView.findViewById(R.id.author);
            parentLayout = (LinearLayout) itemView.findViewById(R.id.parent);

        }
    }


    // Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


    // Return the formatted date string (i.e. "4:30 PM") from a Date object.

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


}
