package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.model.CategoriaAuxModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 30/11/17.
 */

public class CategoriaAuxData {


    private static final String table = "CATEGORIA_AUX_LAUDO";

    private static final String Z_IdCategoriaAux = "ID_CATEGORIA_AUX";
    private static final String Z_CdCategoria = "CD_CATEGORIA";
    private static final String Z_IdTitulo = "ID_TITULO";
    private static final String Z_IdSubTitulo = "ID_SUB_TITULO";
    private static final String Z_IdResposta = "ID_RESPOSTA";
    private static final String Z_Descricao = "DESCRICAO";
    private static final String Z_Categoria = "CATEGORIA";

    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public CategoriaAuxData(Context context) {
        banco = new CreateDataBaseUtil(context);
    }


    public void delete() {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(table);

        db = banco.getWritableDatabase();

        db.execSQL(query.toString());

        Log.w(table, "removido com sucesso");
        db.close();
    }


    public void insert(CategoriaAuxModel categoria) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_IdTitulo, categoria.getIdTitulo());
        values.put(Z_CdCategoria, categoria.getCd_categoria());
        values.put(Z_IdSubTitulo, categoria.getIdSubTitulo());
        values.put(Z_IdResposta, categoria.getIdResposta());
        values.put(Z_Descricao, categoria.getDescricao());
        values.put(Z_Categoria, categoria.getCategoria());

        db.insert(table, null, values);

        Log.w(table, "registro inserido com sucesso!" + values);
        db.close();
    }


    public List<CategoriaAuxModel> getCategoria(int idVistoria) {

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        List<CategoriaAuxModel> listCategoria = null;
        CategoriaAuxModel model = null;

        query.append(" SELECT ID_CATEGORIA_AUX,CD_CATEGORIA,ID_TITULO,ID_SUB_TITULO,ID_RESPOSTA,DESCRICAO,CATEGORIA FROM CATEGORIA_AUX_LAUDO ");

       /* if (!"placa".equals(vistoria)) {
            query.append("WHERE CATEGORIA = ? ");
        }*/

        query.append("WHERE ID_TITULO = ? ORDER BY ID_SUB_TITULO,ID_RESPOSTA ASC");


        try {
            listCategoria = new ArrayList<CategoriaAuxModel>();

            db = banco.getReadableDatabase();

            cursor = db.rawQuery(query.toString(), new String[]{String.valueOf(idVistoria)});


        /*    if (!"placa".equals(vistoria))
                cursor = db.rawQuery(query.toString(), new String[]{vistoria});
            else
                cursor = db.rawQuery(query.toString(), null);
*/

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new CategoriaAuxModel();
                    model.setId(cursor.getInt(cursor.getColumnIndex(Z_IdCategoriaAux)));
                    model.setCd_categoria(cursor.getInt(cursor.getColumnIndex(Z_CdCategoria)));
                    model.setIdTitulo(cursor.getInt(cursor.getColumnIndex(Z_IdTitulo)));
                    model.setIdSubTitulo(cursor.getInt(cursor.getColumnIndex(Z_IdSubTitulo)));
                    model.setIdResposta(cursor.getInt(cursor.getColumnIndex(Z_IdResposta)));
                    model.setDescricao(cursor.getString(cursor.getColumnIndex(Z_Descricao)));
                    model.setCategoria(cursor.getString(cursor.getColumnIndex(Z_Categoria)));

                    listCategoria.add(model);
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCategoria;
    }


    public int getTipoCategoria(String categoria) {
        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        int numCategoria = 0;

        query.append(" SELECT distinct(ID_TITULO) as ID_TITULO FROM CATEGORIA_AUX_LAUDO WHERE CATEGORIA = ? ");


        try {
            db = banco.getReadableDatabase();
            cursor = db.rawQuery(query.toString(), new String[]{categoria});

            if (!"placa".equals(categoria)) {
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        numCategoria = cursor.getInt(cursor.getColumnIndex(Z_IdTitulo));
                    }
                }
            } else {
                numCategoria = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numCategoria;
    }

}
