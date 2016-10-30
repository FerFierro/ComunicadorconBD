package com.escom.tt2016.comunicadorconbd.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.escom.tt2016.comunicadorconbd.R;
import com.escom.tt2016.comunicadorconbd.Utilidades;
import com.escom.tt2016.comunicadorconbd.model.Pictograma;

import java.util.List;

/**
 * Created by Angel on 30/10/2016.
 */

public  class PictogramaFraseAdapter  extends RecyclerView.Adapter<PictogramaFraseAdapter.FraseViewHolder>{

Context context;
    private List<Pictograma> mValues;

    public PictogramaFraseAdapter( List<Pictograma> items) {
        this.mValues = items;
    }


    public void setupRecyclerView(@NonNull RecyclerView recyclerView2,PictogramaFraseAdapter adapter) {
        recyclerView2.setAdapter(adapter);
        recyclerView2.setLayoutManager(new GridLayoutManager(context,5));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        /*si cambiáramos de idea y quisiéramos mostrar los datos de forma tabular tan sólo tendríamos
         que cambiar la asignación del LayoutManager anterior y utilizar un GridLayoutManager, al que
         pasaremos como parámetro el número de columnas a mostrar.*/
    }

    @Override
    public FraseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pictograma_frase_content, parent, false);
        return new PictogramaFraseAdapter.FraseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FraseViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        // holder.mIdView.setText(mValues.get(position).id);
        holder.mNombreViewFrase.setText(mValues.get(position).nombre);
        holder.mImageViewFrase.setImageResource(mValues.get(position).idDrawable);

        /* ************* Esta es la linea para colorear el pictograma de acuerdo a su categoria de pictograma **********************************************************************/
        // holder.mNombreView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Solo colorear el texto
        holder.mImageViewFrase.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Colorear

        //holder.mView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Colorear

        /*************************************************************************************************************************************************************************/
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FraseViewHolder extends RecyclerView.ViewHolder{
        public final View mView;

        public  final ImageView mImageViewFrase;
        public  final TextView mNombreViewFrase;
        public  Pictograma mItem;


        public FraseViewHolder (View view) {
            super(view);
            mView = view;
            mImageViewFrase=(ImageView) view.findViewById(R.id.iv_PicElemento_frase_comunicador);
            mNombreViewFrase=(TextView) view.findViewById(R.id.tv_PicElemento_frase_comunicador);
        }
    }
}
