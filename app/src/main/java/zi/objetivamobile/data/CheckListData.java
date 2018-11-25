package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.model.CheckListModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 19/11/17.
 */

public class CheckListData {

    private static final String table = "CHECK_LIST";

    private static final String Z_IdCheckList = "ID_CHECK_LIST";
    private static final String Z_IdLaudo = "ID_LAUDO";
    private static final String Z_CdLaudo = "CD_LAUDO";
    private static final String Z_IdTitulo = "ID_TITULO";
    private static final String Z_CdCategoria = "CD_CATEGORIA";
    private static final String Z_IdUsuario = "ID_USUARIO";
    private static final String Z_IdSubTitulo = "ID_SUB_TITULO";
    private static final String Z_IdResposta = "ID_RESPOSTA";
    private static final String Z_Descricao = "DESCRICAO";
    private static final String Z_Categoria = "CATEGORIA";
    private static final String Z_Status = "STATUS";


    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public CheckListData(Context context) {
        banco = new CreateDataBaseUtil(context);
    }

    public void delete(Long idLaudo, int idTitulo, int idSubTitulo, int idResposta) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");

        query.append(table);

        query.append(" WHERE ID_LAUDO = ? AND ");
        query.append(" ID_TITULO = ? AND ");
        query.append(" ID_SUB_TITULO = ? AND ");
        query.append(" ID_RESPOSTA = ? ");

        try {
            db = banco.getWritableDatabase();

            db.execSQL(query.toString(), new String[]{String.valueOf(idLaudo), String.valueOf(idTitulo), String.valueOf(idSubTitulo), String.valueOf(idResposta)});

            Log.w(table, "removido com sucesso ID Laudo : " + idLaudo + " Titulo : " + idTitulo + " SubTitulo : " + idSubTitulo + " idResposta : " + idResposta);
            db.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public void insert(CheckListModel checkList) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_IdLaudo, checkList.getIdLaudo());
        values.put(Z_CdLaudo, checkList.getCdLaudo());
        values.put(Z_IdTitulo, checkList.getIdTitulo());
        values.put(Z_IdSubTitulo, checkList.getIdSubTitulo());
        values.put(Z_IdResposta, checkList.getIdResposta());
        values.put(Z_Descricao, checkList.getDescricao());
        values.put(Z_Categoria, checkList.getCategoria());
        values.put(Z_CdCategoria, checkList.getCdCategoria());
        values.put(Z_IdUsuario, checkList.getIdUsuario());
        values.put(Z_Status, checkList.getStatus());

        db.insert(table, null, values);

        Log.w(table, "registro inserido com sucesso!" + values);
        db.close();
    }


    public List<CheckListModel> getCheckList(Long idLaudo, Integer idTitulo, Integer idSubTitulo, Integer idResposta) {

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        List<CheckListModel> listCheckList = null;
        CheckListModel model = null;

        query.append(" SELECT ID_CHECK_LIST,ID_LAUDO,CD_LAUDO,ID_TITULO,ID_SUB_TITULO,ID_RESPOSTA,DESCRICAO,CD_CATEGORIA,STATUS FROM CHECK_LIST ");


        if (idLaudo != null)
            query.append("WHERE ID_LAUDO = ? ");

        if (idTitulo != null)
            query.append("AND ID_TITULO = ? ");

        if (idSubTitulo != null)
            query.append("AND ID_SUB_TITULO = ? ");

        if (idResposta != null)
            query.append("AND ID_RESPOSTA = ? ");


        try {
            listCheckList = new ArrayList<CheckListModel>();

            db = banco.getReadableDatabase();

            cursor = db.rawQuery(query.toString(), new String[]{idLaudo.toString(), idTitulo.toString(), idSubTitulo.toString(), idResposta.toString()});


            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new CheckListModel();
                    model.setIdCheckList(cursor.getLong(cursor.getColumnIndex(Z_IdCheckList)));
                    model.setIdLaudo(cursor.getLong(cursor.getColumnIndex(Z_IdLaudo)));
                    model.setCdLaudo(cursor.getLong(cursor.getColumnIndex(Z_CdLaudo)));
                    model.setIdTitulo(cursor.getInt(cursor.getColumnIndex(Z_IdTitulo)));
                    model.setIdSubTitulo(cursor.getInt(cursor.getColumnIndex(Z_IdSubTitulo)));
                    model.setIdResposta(cursor.getInt(cursor.getColumnIndex(Z_IdResposta)));
                    model.setDescricao(cursor.getString(cursor.getColumnIndex(Z_Descricao)));
                    model.setCategoria(cursor.getString(cursor.getColumnIndex(Z_CdCategoria)));
                    model.setStatus(cursor.getString(cursor.getColumnIndex(Z_Status)));

                    listCheckList.add(model);
                }
                ;
            }
        } catch (Exception e) {
            Log.w(table, "ERRO : " + e.getLocalizedMessage());
        }
        return listCheckList;
    }


    public List<CheckListModel> getCheckListItens(int idUsuario) {

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        List<CheckListModel> listCheckList = null;
        CheckListModel model = null;

        query.append(" SELECT ID_CHECK_LIST,ID_LAUDO,CD_LAUDO, ID_TITULO,ID_SUB_TITULO,ID_RESPOSTA,DESCRICAO,CD_CATEGORIA,ID_USUARIO,STATUS FROM CHECK_LIST ");


        if (idUsuario > 0)
            query.append("WHERE ID_USUARIO = ? ");

        try {
            listCheckList = new ArrayList<CheckListModel>();

            db = banco.getReadableDatabase();

            if (idUsuario > 0)
                cursor = db.rawQuery(query.toString(), new String[]{String.valueOf(idUsuario)});
            else
                cursor = db.rawQuery(query.toString(), null);


            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new CheckListModel();
                    model.setIdCheckList(cursor.getLong(cursor.getColumnIndex(Z_IdCheckList)));
                    model.setIdLaudo(cursor.getLong(cursor.getColumnIndex(Z_IdLaudo)));
                    model.setCdLaudo(cursor.getLong(cursor.getColumnIndex(Z_CdLaudo)));
                    model.setCdCategoria(cursor.getInt(cursor.getColumnIndex(Z_CdCategoria)));
                    model.setIdTitulo(cursor.getInt(cursor.getColumnIndex(Z_IdTitulo)));
                    model.setIdSubTitulo(cursor.getInt(cursor.getColumnIndex(Z_IdSubTitulo)));
                    model.setIdResposta(cursor.getInt(cursor.getColumnIndex(Z_IdResposta)));
                    model.setDescricao(cursor.getString(cursor.getColumnIndex(Z_Descricao)));
                    model.setIdUsuario(cursor.getInt(cursor.getColumnIndex(Z_IdUsuario)));
                    model.setStatus(cursor.getString(cursor.getColumnIndex(Z_Status)));

                    listCheckList.add(model);
                }
                ;
            }
        } catch (Exception e) {
            Log.w(table, "ERRO : " + e.getLocalizedMessage());
        }
        return listCheckList;
    }

}
