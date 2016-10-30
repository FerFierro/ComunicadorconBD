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


import com.escom.tt2016.comunicadorconbd.R;
import com.escom.tt2016.comunicadorconbd.db.DBHelper;
import com.escom.tt2016.comunicadorconbd.model.Pictograma;
import com.escom.tt2016.comunicadorconbd.Utilidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PictogramaGridActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private DBHelper dbHandler;
    private PictogramaAdapter adapter;
    private TextToSpeech textToSpeech;

    private List<Pictograma> picto_seleccionados=new ArrayList<Pictograma>();
    private GridLayoutManager mLayoutManager;
    private PictogramaFraseAdapter adapterFrase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictograma_list);
        textToSpeech = new TextToSpeech( this, this );
        View recyclerView = findViewById(R.id.pictograma_list_categoria);
        assert recyclerView != null;

        InicializarDatos();
        InicializarAdaptador(recyclerView);




    }

    public void Guardar(String nombre,int categoria,int idDrawable){
        Pictograma nuevo_pictograma=new Pictograma(nombre,categoria,idDrawable);
        picto_seleccionados.add(nuevo_pictograma);
        mostrarDatosSeleccionados(picto_seleccionados);
        View recyclerView2 = findViewById(R.id.pictograma_list_frase);
        assert recyclerView2 != null;

        adapter = new PictogramaAdapter(picto_seleccionados);
        setupRecyclerView((RecyclerView) recyclerView2,(PictogramaAdapter) adapter);

    }

    public  void mostrarDatosSeleccionados(List<Pictograma> items){
        Iterator m=items.iterator();
        System.out.println("*************************************");
        System.out.println("El arreglo contiene: "+items.size()+" elementos");
        while (m.hasNext())
            System.out.println("\n"+m.next());
        System.out.println("*************************************");
    }

    public void InicializarAdaptador(View recyclerView){
        Log.d("leyendo", "Se estan leyendo los datos de la base de datos");
        List<Pictograma> picto = dbHandler.getAllUsers();
        adapter = new PictogramaAdapter(picto);
        setupRecyclerView((RecyclerView) recyclerView,(PictogramaAdapter) adapter);

    }

    public void InicializarDatos(){
        dbHandler = new DBHelper(this);
        Log.d("agregar", "Se  agregaran nuevos pictogramas");
        dbHandler.addUser(new Pictograma("Aguila", 1,R.drawable.ic_pic_animales_aguila));
        dbHandler.addUser(new Pictograma("Borrego cimarron", 1,R.drawable.ic_pic_animales_borrego_cimarron));
        dbHandler.addUser(new Pictograma("Buho", 1,R.drawable.ic_pic_animales_buho));
        dbHandler.addUser(new Pictograma("Camaleon", 1,R.drawable.ic_pic_animales_camaleon));
        dbHandler.addUser(new Pictograma("Conejo", 1,R.drawable.ic_pic_animales_conejo));
        dbHandler.addUser(new Pictograma("Jirafa", 1,R.drawable.ic_pic_animales_jirafa));
        dbHandler.addUser(new Pictograma("Libelula", 1,R.drawable.ic_pic_animales_libelula));
        dbHandler.addUser(new Pictograma("Loro", 1,R.drawable.ic_pic_animales_loro));
        dbHandler.addUser(new Pictograma("Mapache", 1,R.drawable.ic_pic_animales_mapache));
        dbHandler.addUser(new Pictograma("Vaca", 1,R.drawable.ic_pic_animales_vaca));

        dbHandler.addUser(new Pictograma("Coca", 2,R.drawable.ic_pic_alimentos_coke));
        dbHandler.addUser(new Pictograma("Hok dog", 2,R.drawable.ic_pic_alimentos_dog));
        dbHandler.addUser(new Pictograma("Dona", 2,R.drawable.ic_pic_alimentos_dona));
        dbHandler.addUser(new Pictograma("Hamburguesa", 2,R.drawable.ic_pic_alimentos_hamburger));
        dbHandler.addUser(new Pictograma("huevo", 2,R.drawable.ic_pic_alimentos_huevo));

        dbHandler.addUser(new Pictograma("Hermana", 3,R.drawable.ic_pic_familia_hermana));
        dbHandler.addUser(new Pictograma("Hermano", 3,R.drawable.ic_pic_familia_hermano));
        dbHandler.addUser(new Pictograma("Prima", 3,R.drawable.ic_pic_familia_prima));
        dbHandler.addUser(new Pictograma("Primo", 3,R.drawable.ic_pic_familia_primo));
        dbHandler.addUser(new Pictograma("Vaca", 3,R.drawable.ic_pic_animales_vaca));

        dbHandler.addUser(new Pictograma("Astronauta", 4,R.drawable.ic_pic_profesiones_astronauta));
        dbHandler.addUser(new Pictograma("Capitán", 4,R.drawable.ic_pic_profesiones_capitan));
        dbHandler.addUser(new Pictograma("Detective", 4,R.drawable.ic_pic_profesiones_detective));
        dbHandler.addUser(new Pictograma("Doctor", 4,R.drawable.ic_pic_profesiones_doctor));
        dbHandler.addUser(new Pictograma("Ingeniero", 4,R.drawable.ic_pic_profesiones_ingeniero));


        Log.d("agregaron", "Se  agregaron nuevos pictogramas");
        // Reading all contacts
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

            return new PictogramaViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final PictogramaViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            // holder.mIdView.setText(mValues.get(position).id);
            holder.mNombreView.setText(mValues.get(position).nombre);
            holder.mImageView.setImageResource(mValues.get(position).idDrawable);

        /* ************* Esta es la linea para colorear el pictograma de acuerdo a su categoria de pictograma **********************************************************************/
            // holder.mNombreView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Solo colorear el texto
            holder.mImageView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Colorear

            //holder.mView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Colorear

            /*************************************************************************************************************************************************************************/
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class PictogramaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
                mImageView = (ImageView) view.findViewById(R.id.iv_PicElemento_categoria_comunicador);
                mNombreView = (TextView) view.findViewById(R.id.tv_PicElemento_categoria_comunicador);
            }

            @Override
            public void onClick(View v) {

           /* Toast toast=Toast.makeText(v.getContext(), mItem.getNombre(), Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.color.colorAccent);
            toast.setGravity(Gravity.RIGHT | Gravity.BOTTOM, 0, 0);//BOTTOM /END
            toast.show();*/
                // v.setBackgroundResource(Utilidades.getBackground(mItem.getCategoria()));
                Locale locSpanish = new Locale("spa", "MEX");
                textToSpeech.setLanguage(locSpanish);
                speak(mItem.getNombre());

                //Instanciamos un nuevo Toast
                Toast _mToast = new Toast(getApplicationContext());

                //Definimos la ubicación del Toast
                _mToast.setGravity(Gravity.CENTER | Gravity.RIGHT, 0, 0);
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

                Guardar(mItem.getNombre(), mItem.getCategoria(), mItem.getIdDrawable());


            }
        }
////////////////////////////////////////////////////////////////////////////
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

        ////////////////////////////////////////////////////////////////////
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}
