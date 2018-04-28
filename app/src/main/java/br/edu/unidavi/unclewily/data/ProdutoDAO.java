package br.edu.unidavi.unclewily.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.unclewily.model.Produto;

/**
 * Created by Craitson on 28/04/2018.
 */

public class ProdutoDAO extends SQLiteOpenHelper {

    private static final String DB_NAME = "cantinaApp.db";
    private static final String TABLE_PRODUTO = "produto";
    private static final int DB_VERSION = 1;

    //COLUMN_NAMES
    private static final String ROW_ID = "id";
    private static final String ROW_NOME = "nome";
    private static final String ROW_DESCRICAO = "descricao";
    private static final String ROW_PHOTOURL = "photoUrl";
    private static final String ROW_AVALIACAO = "avaliacao";
    private static final String ROW_DISPONIBILIDADE = "disponibilidade";

    public ProdutoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUTO_TABLE = "CREATE TABLE "
                + TABLE_PRODUTO + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ROW_NOME + " TEXT,"
                + ROW_DESCRICAO + " TEXT,"
                + ROW_PHOTOURL + " TEXT,"
                + ROW_AVALIACAO + " INTEGER,"
                + ROW_DISPONIBILIDADE + " INTEGER " + ")";
        sqLiteDatabase.execSQL(CREATE_PRODUTO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        onCreate(db);
    }

    public void insertProduto(Produto produto) {

        SQLiteDatabase db = this.getReadableDatabase();

        String ADDLINE = "INSERT INTO " + TABLE_PRODUTO + " ("
                + ROW_NOME + ", " + ROW_DESCRICAO + "," + ROW_PHOTOURL + ", " + ROW_AVALIACAO + ", " + ROW_DISPONIBILIDADE +
                ") Values ('" + produto.getNome() + ", "
                + produto.getDescricao() + ", "
                + produto.getPhotoUrl() + ", "
                + produto.getAvaliacao() + ", "
                + produto.getDisponibilidade() + " ')";
        db.execSQL(ADDLINE);
    }

    public Produto getById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUTO, new String[]{ROW_ID,
                        ROW_NOME, ROW_DESCRICAO, ROW_PHOTOURL, ROW_AVALIACAO, ROW_DISPONIBILIDADE},
                ROW_ID + "= ? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        Produto produto = null;
        if (cursor != null) {
            cursor.moveToFirst();
            produto = new Produto();
            produto.setCodigo(Integer.parseInt(cursor.getString(
                    0)));
            produto.setNome(cursor.getString(1));
            produto.setDescricao(cursor.getString(1));
            produto.setPhotoUrl(cursor.getString(1));
            produto.setAvaliacao(Integer.parseInt(cursor.getString(1)));
            produto.setDisponibilidade(Integer.parseInt(cursor.getString(1)));
            return produto;
        } else {
            throw new RuntimeException("Não existe");
        }
    }

    public List<Produto> getAll() {
        List<Produto> produtosList = new ArrayList<Produto>();

        String selectQuery = "SELECT * FROM " + TABLE_PRODUTO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Produto produto = new Produto();
                produto.setCodigo(Integer.parseInt(cursor.getString(0)));
                produto.setNome(cursor.getString(1));
                produto.setDescricao(cursor.getString(2));
                produto.setPhotoUrl(cursor.getString(3));
                produto.setAvaliacao(Integer.parseInt(cursor.getString(4)));
                produto.setDisponibilidade(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                produtosList.add(produto);
            } while (cursor.moveToNext());
        }

        return produtosList;
    }

}