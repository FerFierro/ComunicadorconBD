package com.escom.tt2016.comunicadorconbd.adaptadores;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.escom.tt2016.comunicadorconbd.MainActivity;
import com.escom.tt2016.comunicadorconbd.R;
import com.escom.tt2016.comunicadorconbd.db.DBHelper;
import com.escom.tt2016.comunicadorconbd.model.Pictograma;

import java.util.List;
import java.util.Locale;

public class PictogramaGridActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private DBHelper dbHandler;
    private PictogramaAdapter adapter;
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictograma_list);
        textToSpeech = new TextToSpeech( this, this );
        View recyclerView = findViewById(R.id.pictograma_list);
        assert recyclerView != null;

        dbHandler = new DBHelper(this);
        Log.d("agregar", "Se  agregaran nuevos pictogramas");
        dbHandler.addUser(new Pictograma("Aguila", "animales",R.drawable.ic_pic_animales_aguila));
        dbHandler.addUser(new Pictograma("Borrego cimarron", "animales",R.drawable.ic_pic_animales_borrego_cimarron));
        dbHandler.addUser(new Pictograma("Buho", "animales",R.drawable.ic_pic_animales_buho));
        dbHandler.addUser(new Pictograma("Camaleon", "animales",R.drawable.ic_pic_animales_camaleon));
        dbHandler.addUser(new Pictograma("Conejo", "animales",R.drawable.ic_pic_animales_conejo));
        dbHandler.addUser(new Pictograma("Jirafa", "animales",R.drawable.ic_pic_animales_jirafa));
        dbHandler.addUser(new Pictograma("Libelula", "animales",R.drawable.ic_pic_animales_libelula));
        dbHandler.addUser(new Pictograma("Loro", "animales",R.drawable.ic_pic_animales_loro));
        dbHandler.addUser(new Pictograma("Mapache", "animales",R.drawable.ic_pic_animales_mapache));
        dbHandler.addUser(new Pictograma("Vaca", "animales",R.drawable.ic_pic_animales_vaca));

        Log.d("agregaron", "Se  agregaron nuevos pictogramas");
        // Reading all contacts
        Log.d("leyendo", "Se estan leyendo los datos de la base de datos");
        List<Pictograma> picto = dbHandler.getAllUsers();
        adapter = new PictogramaAdapter(picto);
        setupRecyclerView((RecyclerView) recyclerView,(PictogramaAdapter) adapter);

    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView,PictogramaAdapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,5));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        /*si cambiáramos de idea y quisiéramos mostrar los datos de forma tabular tan sólo tendríamos
         que cambiar la asignación del LayoutManager anterior y utilizar un GridLayoutManager, al que
         pasaremos como parámetro el número de columnas a mostrar.*/
    }
    /*****************************************************************************************************
    *    En esta parte se implementa y reproduce con voz el nombre del pictograma seleccionado
    *****************************************************************************************************/
    @Override
    public void onInit(int status) {
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED )
        {
            Toast toast=Toast.makeText(PictogramaGridActivity.this,"ola",Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    private void speak( String str )
    {
        textToSpeech.speak( str, TextToSpeech.QUEUE_FLUSH, null );
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }


    @Override
    protected void onDestroy()
    {
        if ( textToSpeech != null )
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    /*******************************************************************************************************
     *                                    Fin de TextoSpeech
     *******************************************************************************************************/

    public class PictogramaAdapter extends RecyclerView.Adapter<PictogramaAdapter.PictogramaViewHolder> {
    //private ArrayList<Pictograma> mDataSet;
    private List<Pictograma> mValues;

    public PictogramaAdapter(List<Pictograma> items) {
        this.mValues = items;
    }



    @Override
    public PictogramaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pictograma_categoria_content, parent, false);
        return new  PictogramaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PictogramaViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        // holder.mIdView.setText(mValues.get(position).id);
        holder.mNombreView.setText(mValues.get(position).nombre);
        holder.mImageView.setImageResource(mValues.get(position).idDrawable);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class PictogramaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //campos respectivos de un item Pictograma
        public final View mView;
        // public final TextView mIdView;
        public final ImageView mImageView;
        public final TextView mNombreView;

        public Pictograma mItem;

        public PictogramaViewHolder(View view) {
            super(view);
            mView = view;
            mView.setOnClickListener(this);
            // mIdView = (TextView) view.findViewById(R.id.txt_id);
            mImageView=(ImageView) view.findViewById(R.id.iv_PicElemento_categoria_comunicador);
            mNombreView = (TextView) view.findViewById(R.id.tv_PicElemento_categoria_comunicador);
        }

        @Override
        public void onClick(View v) {

           /* Toast toast=Toast.makeText(v.getContext(), mItem.getNombre(), Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.color.colorAccent);
            toast.setGravity(Gravity.RIGHT | Gravity.BOTTOM, 0, 0);//BOTTOM /END
            toast.show();*/
            Locale locSpanish = new Locale("spa", "MEX");
            textToSpeech.setLanguage(locSpanish);
            speak( mItem.getNombre() );

            //Instanciamos un nuevo Toast
            Toast _mToast = new Toast(getApplicationContext());

            //Definimos la ubicación del Toast
            _mToast.setGravity(Gravity.CENTER | Gravity.RIGHT , 0, 0);
            _mToast.setDuration(Toast.LENGTH_SHORT);


            //Instanciamos un LayoutInflater donde definimos el archivo XML a utilizar (R.layout.layout_toast) e
            // indicamos el el objeto [LinearLayout] contenedor (R.id.Linearlayout_toast)
            LayoutInflater inflater = getLayoutInflater();
            View custom_toast = inflater.inflate(R.layout.toast,
                    (ViewGroup) findViewById(R.id.Linearlayout_toast));

            //Instanciamos un nuevo TextView y lo asociamos al del layout
            TextView textToast = (TextView) custom_toast.findViewById(R.id.toast_textView);

            //Aqui definimos el texto que se mostrará en el Toast
            textToast.setText(mItem.getNombre());

            //Añadimos la vista al Toast y lo mostramos
            _mToast.setView(custom_toast);
            _mToast.show();



        }
    }
////////////////////////////////////////////////////////////////////////////




    ////////////////////////////////////////////////////////////////////
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
}