package br.edu.unidavi.unclewily.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.unclewily.model.Favorito;

/**
 * Created by Craitson on 28/04/2018.
 */

public class FavoritoDAO extends SQLiteOpenHelper {

    private static final String DB_NAME = "cantinaApp.db";
    private static final String TABLE_FAVORITO = "favorito";
    private static final int DB_VERSION = 1;

    //COLUMN_NAMES
    private static final String ROW_CODIGO_PRODUTO = "codigoProduto";

    public FavoritoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FAVORITO_TABLE = "CREATE TABLE "
                + TABLE_FAVORITO + "("
                + ROW_CODIGO_PRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT )";
        sqLiteDatabase.execSQL(CREATE_FAVORITO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITO);
        onCreate(db);
    }

    public void insertFavorito(Favorito favorito) {

        SQLiteDatabase db = this.getReadableDatabase();

        String ADDLINE = "INSERT INTO " + TABLE_FAVORITO + " ("
                + ROW_CODIGO_PRODUTO + ") Values ('" + favorito.getCodigoProduto() + " ')";
        db.execSQL(ADDLINE);
    }


    public List<Favorito> getAll() {
        List<Favorito> favoritoList = new ArrayList<Favorito>();

        String selectQuery = "SELECT * FROM " + TABLE_FAVORITO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Favorito favorito = new Favorito();
                favorito.setCodigoProduto(Integer.parseInt(cursor.getString(0)));
                // Adding contact to list
                favoritoList.add(favorito);
            } while (cursor.moveToNext());
        }

        return favoritoList;
    }

}