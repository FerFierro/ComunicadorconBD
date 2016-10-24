package com.escom.tt2016.comunicadorconbd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.escom.tt2016.comunicadorconbd.adaptadores.PictogramaAdapter;
import com.escom.tt2016.comunicadorconbd.db.DBHelper;
import com.escom.tt2016.comunicadorconbd.model.Pictograma;
import com.escom.tt2016.comunicadorconbd.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private PictogramaAdapter adapter;
    private DBHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictograma_list);

        recyclerView = (RecyclerView) findViewById(R.id.pictograma_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,5);
        recyclerView.setLayoutManager(layoutManager);
        dbHandler = new DBHelper(MainActivity.this);
        Log.d("agregar", "Se  agregaran nuevos pictogramas");
        dbHandler.addUser(new Pictograma("Nombre", "categoria",R.drawable.ic_pictograma_vaca));
        dbHandler.addUser(new Pictograma("Nombre", "categoria",R.drawable.ic_pictograma_conejo));
        dbHandler.addUser(new Pictograma("Nombre", "categoria",R.drawable.ic_pictograma_buho));
        Log.d("agregaron", "Se  agregaron nuevos pictogramas");

        // Reading all contacts
        Log.d("leyendo", "Se estan leyendo los datos de la base de datos");
        ArrayList<Pictograma> picto = dbHandler.getAllUsers();
        adapter = new PictogramaAdapter(picto);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}