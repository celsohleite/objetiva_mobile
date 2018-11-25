package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import zi.objetivamobile.model.AuthVistoriadorModel;
import zi.objetivamobile.model.UsuarioModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 10/03/18.
 */

public class UsuarioData {

    private static final String table = "USUARIO";

    private static final String Z_IdUsuario = "ID_USUARIO";
    private static final String Z_NomeUsuario = "NOME_USUARIO";
    private static final String Z_CdUsuario = "CD_USUARIO";
    private static final String Z_Email = "EMAIL";
    private static final String Z_Cpf = "CPF";
    private static final String Z_Status = "STATUS";

    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public UsuarioData(Context context) {
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

    public void insert(AuthVistoriadorModel usuario) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_NomeUsuario, usuario.getNomeVistoriador());
        values.put(Z_CdUsuario, usuario.getCodVistoriador());
        values.put(Z_Email, usuario.getEmailUsuario());
        values.put(Z_Cpf, usuario.getCpfVistoriador());
        values.put(Z_Status, usuario.getStatus());

        db.insert(table, null, values);

        Log.w(table, "registro inserido com sucesso!" + values);
        db.close();
    }
}
