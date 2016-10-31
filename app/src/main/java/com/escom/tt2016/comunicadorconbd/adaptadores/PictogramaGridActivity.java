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

import static com.escom.tt2016.comunicadorconbd.model.Pictograma.PIC_NORMAL;
import static com.escom.tt2016.comunicadorconbd.model.Pictograma.PIC_SELECCIONADO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PictogramaGridActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private DBHelper dbHandler;
    private DiferenteAdapter adapter;
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


  /////////////////////////////////////////////////////////////////////////////

    public void playFrase(View view){

        String x= getTextDatosSeleccionados(picto_seleccionados);
        speak(x);

        //Toast.makeText(getApplicationContext(), x , Toast.LENGTH_LONG).show();
    }

    public void deleteFrase(View view){

        getTextDatosSeleccionados2(picto_seleccionados);


//        Toast.makeText(getApplicationContext(), x , Toast.LENGTH_LONG).show();
    }

    public String getTextDatosSeleccionados(List<Pictograma> items){
        String frase="";

        for(int i=0; i<items.size(); i++){
            frase += items.get(i).getNombre()+ " ";
        }

        return frase;
    }

    public void getTextDatosSeleccionados2(List<Pictograma> items){

        picto_seleccionados.clear();
        mostrarDatosSeleccionados(picto_seleccionados);
        View recyclerView2 = findViewById(R.id.pictograma_list_frase);

        assert recyclerView2 != null;

        adapter = new DiferenteAdapter(picto_seleccionados);
        setupRecyclerView((RecyclerView) recyclerView2,(DiferenteAdapter) adapter,PIC_SELECCIONADO);

    }

    public void Delete(int index){
        picto_seleccionados.remove(index);
        mostrarDatosSeleccionados(picto_seleccionados);
        View recyclerView2 = findViewById(R.id.pictograma_list_frase);

        assert recyclerView2 != null;

        adapter = new DiferenteAdapter(picto_seleccionados);
        setupRecyclerView((RecyclerView) recyclerView2,(DiferenteAdapter) adapter,PIC_SELECCIONADO);

    }


/////////////////////////////////////////////////////////////////////////

    public void Guardar(String nombre,int categoria,int idDrawable,int tipo){
        Pictograma nuevo_pictograma=new Pictograma(nombre,categoria,idDrawable,tipo);
        picto_seleccionados.add(nuevo_pictograma);
        mostrarDatosSeleccionados(picto_seleccionados);
        View recyclerView2 = findViewById(R.id.pictograma_list_frase);

        assert recyclerView2 != null;

        adapter = new DiferenteAdapter(picto_seleccionados);
        setupRecyclerView((RecyclerView) recyclerView2,(DiferenteAdapter) adapter,PIC_SELECCIONADO);

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
        System.out.println("********");
        adapter = new DiferenteAdapter(picto);
        System.out.println("********");
        setupRecyclerView((RecyclerView) recyclerView,(DiferenteAdapter) adapter,PIC_NORMAL);

    }

    public void InicializarDatos(){
        dbHandler = new DBHelper(this);

        Log.d("count", "--> "+dbHandler.count());

        if(dbHandler.count()==0){
            Log.d("agregar", "Se  agregaran nuevos pictogramas");

            dbHandler.addUser(new Pictograma("Aguila", 1,R.drawable.ic_pic_animales_aguila,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Borrego cimarron", 1,R.drawable.ic_pic_animales_borrego_cimarron,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Buho", 1,R.drawable.ic_pic_animales_buho,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Camaleon", 1,R.drawable.ic_pic_animales_camaleon,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Conejo", 1,R.drawable.ic_pic_animales_conejo,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Jirafa", 1,R.drawable.ic_pic_animales_jirafa,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Libelula", 1,R.drawable.ic_pic_animales_libelula,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Loro", 1,R.drawable.ic_pic_animales_loro,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Mapache", 1,R.drawable.ic_pic_animales_mapache,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Vaca", 1,R.drawable.ic_pic_animales_vaca,Pictograma.PIC_NORMAL));

            dbHandler.addUser(new Pictograma("Coca", 2,R.drawable.ic_pic_alimentos_coke,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Hok dog", 2,R.drawable.ic_pic_alimentos_dog,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Dona", 2,R.drawable.ic_pic_alimentos_dona,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Hamburguesa", 2,R.drawable.ic_pic_alimentos_hamburger,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("huevo", 2,R.drawable.ic_pic_alimentos_huevo,Pictograma.PIC_NORMAL));

            dbHandler.addUser(new Pictograma("Hermana", 3,R.drawable.ic_pic_familia_hermana,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Hermano", 3,R.drawable.ic_pic_familia_hermano,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Prima", 3,R.drawable.ic_pic_familia_prima,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Primo", 3,R.drawable.ic_pic_familia_primo,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Vaca", 3,R.drawable.ic_pic_animales_vaca,Pictograma.PIC_NORMAL));

            dbHandler.addUser(new Pictograma("Astronauta", 4,R.drawable.ic_pic_profesiones_astronauta,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Capitán", 4,R.drawable.ic_pic_profesiones_capitan,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Detective", 4,R.drawable.ic_pic_profesiones_detective,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Doctor", 4,R.drawable.ic_pic_profesiones_doctor,Pictograma.PIC_NORMAL));
            dbHandler.addUser(new Pictograma("Ingeniero", 4,R.drawable.ic_pic_profesiones_ingeniero,Pictograma.PIC_NORMAL));


            Log.d("agregaron", "Se  agregaron nuevos pictogramas");
        }


        // Reading all contacts
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView,DiferenteAdapter adapter,int tipo) {

        recyclerView.setAdapter(adapter);
        if (tipo==0){
            recyclerView.setLayoutManager(new GridLayoutManager(this,5));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this,8));
        }

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

    public class DiferenteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        //private ArrayList<Pictograma> mDataSet;
        private List<Pictograma> mValues;

        public DiferenteAdapter(List<Pictograma> items) {
            this.mValues = items;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            switch (viewType){
                case PIC_NORMAL:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pictograma_categoria_content, parent, false);
                    return new PictogramaViewHolder(view);
                case PIC_SELECCIONADO:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pictograma_frase_content, parent, false);
                    return new FraseViewHolder(view);

            }
            return null;
        }

        @Override
        public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
            Pictograma object= mValues.get(position);
            if (object != null) {
                switch (object.getTipo()) {
                    case PIC_NORMAL:
                        ((PictogramaViewHolder) holder).mNombreView.setText(mValues.get(position).nombre);
                        ((PictogramaViewHolder) holder).mImageView.setImageResource(mValues.get(position).idDrawable);
                        ((PictogramaViewHolder) holder).mImageView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria()));

                        break;
                    case PIC_SELECCIONADO:
                        ((FraseViewHolder) holder).mNombreViewFrase.setText(mValues.get(position).nombre);
                        ((FraseViewHolder) holder).mImageViewFrase.setImageResource(mValues.get(position).idDrawable);
                        ((FraseViewHolder) holder).mImageViewFrase.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria()));

                        break;
                }
            }


        /* ************* Esta es la linea para colorear el pictograma de acuerdo a su categoria de pictograma **********************************************************************/
            // holder.mNombreView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Solo colorear el texto
           // holder.mImageView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Colorear

            //holder.mView.setBackgroundResource(Utilidades.getBackground(mValues.get(position).getCategoria())); //Colorear

            /*************************************************************************************************************************************************************************/
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
        @Override
        public int getItemViewType(int position) {
            if (mValues != null) {
               final  Pictograma object = mValues.get(position);
                if (object != null) {
                    return object.getTipo();
                }
            }
            return 0;
        }

        public  class PictogramaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            //campos respectivos de un item Pictograma

            // public final TextView mIdView;
            public final ImageView mImageView;
            private final TextView mNombreView;

            Pictograma mItem;

            public PictogramaViewHolder(View view) {
                super(view);


                view.setOnClickListener(this);

                // mIdView = (TextView) view.findViewById(R.id.txt_id);
                mImageView = (ImageView) view.findViewById(R.id.iv_PicElemento_categoria_comunicador);
                mNombreView = (TextView) view.findViewById(R.id.tv_PicElemento_categoria_comunicador);
            }

            @Override
            public void onClick(View v) {

                int posicion=getAdapterPosition();
                Pictograma m= mValues.get(posicion);

           /* Toast toast=Toast.makeText(v.getContext(), mItem.getNombre(), Toast.LENGTH_SHORT);

            View toastView = toast.getView();
            toastView.setBackgroundResource(R.color.colorAccent);
            toast.setGravity(Gravity.RIGHT | Gravity.BOTTOM, 0, 0);//BOTTOM /END
            toast.show();*/
               // v.setBackgroundResource(Utilidades.getBackground(m.getCategoria()));
                Locale locSpanish = new Locale("spa", "MEX");
                textToSpeech.setLanguage(locSpanish);
               speak(m.getNombre());

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
                textToast.setText(m.getNombre());

                //Añadimos la vista al Toast y lo mostramos
                _mToast.setView(custom_toast);
                _mToast.show();

                Guardar(m.getNombre(), m.getCategoria(), m.getIdDrawable(),Pictograma.PIC_SELECCIONADO);


            }
        }
////////////////////////////////////////////////////////////////////////////
public class FraseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final View mView;

    public  final ImageView mImageViewFrase;
    public  final TextView mNombreViewFrase;
    public  Pictograma mItem;


    public FraseViewHolder(View view) {
        super(view);
        mView = view;
        mImageViewFrase=(ImageView) view.findViewById(R.id.iv_PicElemento_frase_comunicador);
        mNombreViewFrase=(TextView) view.findViewById(R.id.tv_PicElemento_frase_comunicador);

        view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();



        Delete(position);

        //Pictograma pictograma_frase = mValues.get(position);
        //String x= position + " - " + pictograma_frase.getNombre();
        //Toast.makeText(getApplicationContext(), x , Toast.LENGTH_LONG).show();

    }
}






        ////////////////////////////////////////////////////////////////////
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}
