package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.model.LaudoItemModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 23/10/17.
 */

public class LaudoItemData {

    private static final String table = "LAUDO_ITEM";

    private static final String Z_IdLaudo = "ID_LAUDO";
    private static final String Z_CdLaudo = "CD_LAUDO";
    private static final String Z_ImageID = "IMAGEID";
    private static final String Z_Identificacao = "IDENTIFICACAO";
    private static final String Z_Vistoria = "VISTORIA";
    private static final String Z_Numeracao = "NUMERACAO";
    private static final String Z_Observacao = "OBSERVACAO";
    private static final String Z_Lat = "LAT";
    private static final String Z_Lng = "LNG";
    private static final String Z_Status = "STATUS";

    private static final String Z_Nome_Cliente = "NOME_CLIENTE_PARTICULAR";
    private static final String Z_Placa = "PLACA";
    private static final String Z_Modelo = "MODELO";

    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public LaudoItemData(Context context) {
        banco = new CreateDataBaseUtil(context);
    }


    public void insert(LaudoItemModel item) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_IdLaudo, item.getIdLaudo());
        values.put(Z_CdLaudo, item.getCdLaudo());
        values.put(Z_Identificacao, item.getIdentificacao());
        values.put(Z_Vistoria, item.getVistoria());
        values.put(Z_Numeracao, item.getNumeracao());
        values.put(Z_ImageID, item.getImageID());
        values.put(Z_Lat, item.getLat());
        values.put(Z_Lng, item.getLng());
        values.put(Z_Observacao, item.getObservacao());
        values.put(Z_Status, item.getStatus());

        db.insert(table, null, values);

        Log.w(table, "registro inserido com sucesso!" + values);
        db.close();
    }


    public List<LaudoItemModel> getItemLaudo(String status) {

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        List<LaudoItemModel> listItemLaudo = null;
        LaudoItemModel model = null;

        if ("AT".equals(status)) {

            query.append(" SELECT I.CD_LAUDO AS CD_LAUDO, I.ID_LAUDO AS ID_LAUDO,I.IDENTIFICACAO AS IDENTIFICACAO,I.IMAGEID AS IMAGEID,I.VISTORIA AS VISTORIA,I.NUMERACAO AS NUMERACAO,I.LAT AS LAT,I.LNG AS LNG,");
            query.append(" I.OBSERVACAO AS OBSERVACAO,I.STATUS AS STATUS, L.NOME_CLIENTE_PARTICULAR AS NOME_CLIENTE_PARTICULAR, L.PLACA AS PLACA, L.MODELO FROM LAUDO_ITEM AS I, LAUDO AS L WHERE I.STATUS='AT' AND L.ID_LAUDO_ITEM = I.ID_LAUDO ");

        }else if ("IN".equals(status))
            query.append(" SELECT ID_LAUDO,IDENTIFICACAO,IMAGEID,VISTORIA,NUMERACAO,LAT,LNG,OBSERVACAO,STATUS FROM LAUDO_ITEM ");
        else
            query.append(" SELECT ID_LAUDO,IDENTIFICACAO,IMAGEID,VISTORIA,NUMERACAO,LAT,LNG,OBSERVACAO,STATUS FROM LAUDO_ITEM ");


        try {
            listItemLaudo = new ArrayList<LaudoItemModel>();

            db = banco.getReadableDatabase();

            cursor = db.rawQuery(query.toString(), null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new LaudoItemModel();

                    model.setIdLaudo(cursor.getLong(cursor.getColumnIndex(Z_IdLaudo)));
                    model.setCdLaudo(cursor.getLong(cursor.getColumnIndex(Z_CdLaudo)));
                    model.setIdentificacao(cursor.getString(cursor.getColumnIndex(Z_Identificacao)));
                    model.setImageID(cursor.getInt(cursor.getColumnIndex(Z_ImageID)));
                    model.setNumeracao(cursor.getString(cursor.getColumnIndex(Z_Numeracao)));
                    model.setVistoria(cursor.getString(cursor.getColumnIndex(Z_Vistoria)));
                    model.setLat(cursor.getDouble(cursor.getColumnIndex(Z_Lat)));
                    model.setLng(cursor.getDouble(cursor.getColumnIndex(Z_Lng)));
                    model.setObservacao(cursor.getString(cursor.getColumnIndex(Z_Observacao)));
                    model.setStatus(cursor.getString(cursor.getColumnIndex(Z_Status)));

                    //model.setNome(cursor.getString(cursor.getColumnIndex(Z_Nome_Cliente)));
                    //model.setPlaca(cursor.getString(cursor.getColumnIndex(Z_Placa)));
                    //model.setModelo(cursor.getString(cursor.getColumnIndex(Z_Modelo)));

                    listItemLaudo.add(model);
                }
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listItemLaudo;
    }

    public void updateConcluirLaudo(Long idLaudo, String observacao) {
        StringBuilder query = new StringBuilder();
        LaudoItemModel model = null;

        query.append("UPDATE LAUDO_ITEM SET STATUS='IN', OBSERVACAO= ? WHERE ID_LAUDO = ? ");

        try {

            db = banco.getWritableDatabase();

            db.execSQL(query.toString(), new String[]{observacao, String.valueOf(idLaudo)});


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getMax() {

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        int max = 0;

        query.append(" SELECT max(ID_LAUDO) as LAUDO_END FROM LAUDO_ITEM ");

        try {

            db = banco.getReadableDatabase();
            cursor = db.rawQuery(query.toString(), null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    max = cursor.getInt(cursor.getColumnIndex("LAUDO_END"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max++;
    }

    public void deleteLaudo(Long idLaudo) {
        StringBuilder query = new StringBuilder();

        db = banco.getWritableDatabase();

        try {
            db.delete(table, Z_IdLaudo + " =" + idLaudo, null);
            Log.w(table, "removido com sucesso ID Laudo : " + idLaudo);
            db.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
