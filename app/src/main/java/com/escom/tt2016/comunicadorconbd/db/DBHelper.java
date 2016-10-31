package com.escom.tt2016.comunicadorconbd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.escom.tt2016.comunicadorconbd.model.Pictograma;

import java.util.ArrayList;
import java.util.List;

//Este es mi base de datos sqlite
public class DBHelper  extends SQLiteOpenHelper{


    //Nombre de la base de datos
    public static final  String NAME_DATABASE="pictogramas.sqlite";
    private static final int DB_SHEME_VERSION=1;//Version de la DB por si hay una nueva version y haya que actualizar la BD


    //Context context Es una referencia a la Activity
    public DBHelper(Context context) {
        super(context, NAME_DATABASE, null,DB_SHEME_VERSION);
    }
    //este metodo se encarga de inicializar la base de datos, se ejecuta siempre cuando se crea la clase
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PICTOGRAMA);

    }

    //metodo usado en el caso de que haga falta actualizar la version de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //se elimina la veersion anterior de la tabla
        db.execSQL("DROP TABLE IF EXIST "+TABLE_PICTOGRAMA);
        //se crea la nueva version de la tabla
        onCreate(db);
    }

    //Nombre de la tabla pictograma
    public static final  String TABLE_PICTOGRAMA="pictograma";


    //SNombre de los campos de la tabla usuarios
    public static final  String ID="idPictograma";
    public static final  String NOMBRE="nombre";
    public static final  String CATEGORIA="categoria";
    public static final  String ID_DRAWABLE="idDrawable";
    public static final  String TIPO="tipo";


    //sentencia para crear la tabla pictograma

    public static final String CREATE_TABLE_PICTOGRAMA="CREATE TABLE "+ TABLE_PICTOGRAMA+" ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NOMBRE +" TEXT NOT NULL,"
            + CATEGORIA +" INTEGER,"
            + ID_DRAWABLE +" INTEGER,"
            + TIPO +" INTEGER);";



    /*addUser() will add a new User to database*/
    public void addUser(Pictograma picto) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE, picto.getNombre());
        values.put(CATEGORIA, picto.getCategoria());
        values.put(ID_DRAWABLE,picto.getIdDrawable());
        values.put(TIPO,picto.getTipo());

        db.insert(TABLE_PICTOGRAMA, null, values); //Insert query to store the record in the database
        db.close();
    }

    /*getUser() will return he user's object if id matches*/
    public Pictograma getUser(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PICTOGRAMA, new String[]{ID,
                        NOMBRE, CATEGORIA,ID_DRAWABLE}, ID + "=?",
                new String[]{String.valueOf(user_id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pictograma pic = new Pictograma(cursor.getString(1), cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
        return pic;
    }

    /*getAllUsers() will return the list of all users*/
    public List<Pictograma> getAllUsers() {
        List<Pictograma> usersList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PICTOGRAMA;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pictograma pic = new Pictograma(cursor.getString(1), cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
                usersList.add(pic);
            } while (cursor.moveToNext());
        }
        return usersList;
    }



    public int count(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCount= db.rawQuery("select count(*) from "+ TABLE_PICTOGRAMA , null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();

        return count;
    }





    /*getUsersCount() will give the total number of records in the table*/
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PICTOGRAMA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();


    }

    /*updateUser() will be used to update the existing user record*/
    public int updateUser(Pictograma picto) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE, picto.getNombre());
        values.put(CATEGORIA, picto.getCategoria());
        values.put(ID_DRAWABLE,picto.getIdDrawable());
        // updating record
        return db.update(TABLE_PICTOGRAMA, values, ID + " = ?", // update query to make changes to the existing record
                new String[]{String.valueOf(picto.getId())});
    }

    /*deleteContact() to delete the record from the table*/
    public void deleteContact(Pictograma user) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_PICTOGRAMA, ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    //Para cerrar la conexión de la base de datos
    public void close(){
        this.close();
    }


}
