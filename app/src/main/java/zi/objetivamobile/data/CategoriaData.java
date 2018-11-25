package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.model.CategoriaModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 04/11/17.
 */

public class CategoriaData {

    private static final String table = "CATEGORIA_LAUDO";

    private static final String Z_CodCategoria = "COD_CATEGORIA";
    private static final String Z_CodLaudo = "COD_LAUDO";
    private static final String Z_DescCategoria = "DESC_CATEGORIA";
    private static final String Z_OrdemCategoria = "ORDEM_CATEGORIA";
    private static final String Z_TipoVistoria = "TIPO_VISTORIA";

    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;


    public CategoriaData(Context context) {
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


    public void insert(CategoriaModel categoria) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_CodCategoria, categoria.getCodCategoria());
        values.put(Z_CodLaudo, categoria.getNumLaudo());
        values.put(Z_DescCategoria, categoria.getDescCategoria());
        values.put(Z_OrdemCategoria, categoria.getOrdemCategoria());
        values.put(Z_TipoVistoria, categoria.getTipoVistoria());

        db.insert(table, null, values);

        Log.w(table, "registro inserido com sucesso!" + values);
        db.close();
    }


    public List<CategoriaModel> getCategoria(String vistoria) {

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        List<CategoriaModel> listCategoria = null;
        CategoriaModel model = null;

        query.append(" SELECT COD_LAUDO,COD_CATEGORIA,DESC_CATEGORIA,ORDEM_CATEGORIA,TIPO_VISTORIA FROM CATEGORIA_LAUDO ");

        if(vistoria != null){
            query.append("WHERE TIPO_VISTORIA = ? ");
        }

        try {
            listCategoria = new ArrayList<CategoriaModel>();

            db = banco.getReadableDatabase();

            cursor = db.rawQuery(query.toString(), new String[]{vistoria});

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new CategoriaModel();
                    model.setCodCategoria(cursor.getInt(cursor.getColumnIndex(Z_CodCategoria)));
                    model.setNumLaudo(cursor.getLong(cursor.getColumnIndex(Z_CodLaudo)));
                    model.setDescCategoria(cursor.getString(cursor.getColumnIndex(Z_DescCategoria)));
                    model.setOrdemCategoria(cursor.getInt(cursor.getColumnIndex(Z_OrdemCategoria)));
                    model.setTipoVistoria(cursor.getString(cursor.getColumnIndex(Z_TipoVistoria)));

                    listCategoria.add(model);
                }
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCategoria;
    }


}
