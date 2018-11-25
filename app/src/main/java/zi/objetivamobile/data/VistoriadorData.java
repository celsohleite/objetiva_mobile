package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.model.AuthVistoriadorModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 26/05/2018.
 */

public class VistoriadorData {

    private static final String table = "VISTORIADOR";

    private static final String Z_IdVistoriador = "COD_VISTORIADOR";
    private static final String Z_EmailUsuario = "EMAIL_USUARIO";
    private static final String Z_EmailVistoriador = "EMAIL_VISTORIADOR";
    private static final String Z_CpfVistoriador = "CPF_VISTORIADOR";
    private static final String Z_NomeVistoriador = "NOME_VISTORIADOR";
    private static final String Z_SobrenomeVistoriador = "SOBRENOME_VISTORIADOR";
    private static final String Z_Status = "STATUS";
    private static final String Z_CodUsuario = "COD_USUARIO";
    private static final String Z_CodVistoriador = "COD_VISTORIADOR";

    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public VistoriadorData(Context context) {
        banco = new CreateDataBaseUtil(context);
    }


    public void insert(AuthVistoriadorModel vistoriador) {
        db = banco.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(Z_EmailUsuario, vistoriador.getEmailUsuario());
            values.put(Z_EmailVistoriador, vistoriador.getEmailVistoriador());
            values.put(Z_CpfVistoriador, vistoriador.getCpfVistoriador());
            values.put(Z_NomeVistoriador, vistoriador.getNomeVistoriador());
            values.put(Z_SobrenomeVistoriador, vistoriador.getSobrenomeVistoriador());
            values.put(Z_Status, vistoriador.getStatus());
            values.put(Z_CodUsuario, vistoriador.getCodUsuario());
            values.put(Z_CodVistoriador, vistoriador.getCodVistoriador());

            db.insert(table, null, values);

            Log.w(table, "registro inserido com sucesso!" + values);
            db.close();
    }

    public AuthVistoriadorModel getVistoriador(){

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        List<AuthVistoriadorModel> listVistoriador = null;
        AuthVistoriadorModel model = null;

        query.append(" SELECT EMAIL_USUARIO,EMAIL_VISTORIADOR,CPF_VISTORIADOR,NOME_VISTORIADOR,SOBRENOME_VISTORIADOR,STATUS,COD_USUARIO,COD_VISTORIADOR FROM VISTORIADOR ");

        try{
            listVistoriador = new ArrayList<AuthVistoriadorModel>();
            db = banco.getReadableDatabase();
            cursor = db.rawQuery(query.toString().toLowerCase(), null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new AuthVistoriadorModel();
                    model.setEmailVistoriador(cursor.getString(cursor.getColumnIndex(Z_EmailVistoriador)));
                    model.setEmailUsuario(cursor.getString(cursor.getColumnIndex(Z_EmailUsuario)));
                    model.setCpfVistoriador(cursor.getString(cursor.getColumnIndex(Z_CpfVistoriador)));
                    model.setNomeVistoriador(cursor.getString(cursor.getColumnIndex(Z_NomeVistoriador)));
                    model.setSobrenomeVistoriador(cursor.getString(cursor.getColumnIndex(Z_SobrenomeVistoriador)));
                    model.setStatus(cursor.getString(cursor.getColumnIndex(Z_Status)));
                    model.setCodUsuario(cursor.getInt(cursor.getColumnIndex(Z_CodUsuario)));
                    model.setCodVistoriador(cursor.getInt(cursor.getColumnIndex(Z_CodVistoriador)));

                    if(model.getCodUsuario() > 0)
                        listVistoriador.add(model);
                };
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return listVistoriador.get(0);
    }

    public void delete(){
        db = banco.getReadableDatabase();
        db.delete(table, null, null);
    }
}
