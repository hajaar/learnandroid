package com.kartik.diabetesmonitoring;

/**
 * Created by kartikn on 20-09-2015.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InsulinReadingsDetailsAdapter extends RecyclerView.Adapter<InsulinReadingsDetailsAdapter.ViewHolder> {
    Context context;
    private List<InsulinReading> mDataset;
    private ClickListener clickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public InsulinReadingsDetailsAdapter(List<InsulinReading> myDataset, ClickListener clickListener) {
        mDataset = myDataset;
        this.clickListener = clickListener;
    }

    public void add(int position, InsulinReading item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void remove(InsulinReading item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public InsulinReadingsDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_insulin_readings_details, parent, false);
        // set the view's size, margins, paddings and layout parameters
        context = v.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final InsulinReading insulinReading = mDataset.get(position);
        holder.txtID.setText(insulinReading.getID() + "");
        holder.txtName.setText(insulinReading.getName());
        holder.txtType.setText(insulinReading.getType());
        holder.txtQuantity.setText(insulinReading.getQuantity() + "");
        Date date = new Date(insulinReading.getInjecteDateTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy HH:mm:ss", Locale.ENGLISH);
        holder.txtDay.setText(sdf.format(date) + "");
        holder.txtTimeOfDay.setText(insulinReading.getTimeOfDay());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView txtID;
        public TextView txtDay;
        public TextView txtTimeOfDay;
        public TextView txtName;
        public TextView txtType;
        public TextView txtQuantity;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            txtID = (TextView) v.findViewById(R.id.insulin_reading_id);
            txtDay = (TextView) v.findViewById(R.id.insulin_reading_day);
            txtTimeOfDay = (TextView) v.findViewById(R.id.insulin_reading_time_of_day);
            txtName = (TextView) v.findViewById(R.id.insulin_reading_name);
            txtType = (TextView) v.findViewById(R.id.insulin_reading_type);
            txtQuantity = (TextView) v.findViewById(R.id.insulin_reading_quantity);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }

}
