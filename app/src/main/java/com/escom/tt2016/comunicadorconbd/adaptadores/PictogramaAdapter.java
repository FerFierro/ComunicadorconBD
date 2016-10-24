package com.escom.tt2016.comunicadorconbd.adaptadores;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.escom.tt2016.comunicadorconbd.R;
import com.escom.tt2016.comunicadorconbd.model.Pictograma;

import java.util.ArrayList;


public class PictogramaAdapter extends RecyclerView.Adapter<PictogramaAdapter.PictogramaViewHolder> {
    private ArrayList<Pictograma> mDataSet;

    public PictogramaAdapter(ArrayList<Pictograma> mDataSet) {
        this.mDataSet = mDataSet;
    }



    @Override
    public PictogramaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pictograma_categoria_content, parent, false);
        PictogramaViewHolder pictogramaViewHolder = new PictogramaViewHolder(v);
        return pictogramaViewHolder;
    }

    @Override
    public void onBindViewHolder(PictogramaViewHolder holder, int position) {
        holder.mItem = mDataSet.get(position);
        // holder.mIdView.setText(mValues.get(position).id);
        holder.mNombreView.setText(mDataSet.get(position).nombre);
        holder.mImageView.setImageResource(mDataSet.get(position).idDrawable);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class PictogramaViewHolder extends RecyclerView.ViewHolder {
        //campos respectivos de un item Pictograma
        public final View mView;
        // public final TextView mIdView;
        public final ImageView mImageView;
        public final TextView mNombreView;

        public Pictograma mItem;

        public PictogramaViewHolder(View view) {
            super(view);
            mView = view;
            // mIdView = (TextView) view.findViewById(R.id.txt_id);
            mImageView=(ImageView) view.findViewById(R.id.iv_PicElemento_categoria_comunicador);
            mNombreView = (TextView) view.findViewById(R.id.tv_PicElemento_categoria_comunicador);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}