package com.kartik.diabetesmonitoring;

/**
 * Created by kartikn on 26-09-2015.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SetupMedicinesAdapter extends RecyclerView.Adapter<SetupMedicinesAdapter.ViewHolder> {
    Context context;
    private List<Medicine> mDataset;
    private ClickListener clickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SetupMedicinesAdapter(List<Medicine> myDataset, ClickListener clickListener) {
        mDataset = myDataset;
        this.clickListener = clickListener;
    }

    public void add(int position, Medicine item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void remove(Medicine item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SetupMedicinesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_medicines, parent, false);
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
        final Medicine medicine = mDataset.get(position);
        holder.txtID.setText(medicine.getID() + "");
        holder.txtName.setText(medicine.getTypeOfMedicine() + " " + medicine.getName());
        holder.txtPurpose.setText(medicine.getPurpose() + "");
        holder.txtDosage.setText(medicine.getDosage());
        holder.txtQuantity.setText(medicine.getActualQuantity());
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
        public TextView txtName;
        public TextView txtPurpose;
        public TextView txtDosage;
        public TextView txtQuantity;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            txtID = (TextView) v.findViewById(R.id.medicine_id);
            txtName = (TextView) v.findViewById(R.id.medicine_name);
            txtPurpose = (TextView) v.findViewById(R.id.medicine_purpose);
            txtDosage = (TextView) v.findViewById(R.id.medicine_dosage);
            txtQuantity = (TextView) v.findViewById(R.id.medicine_quantity);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }

}
