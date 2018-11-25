package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.model.ClienteModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 10/03/18.
 */

public class ClienteData {

    private static final String table = "CLIENTE";

    private static final String Z_IdCliente = "ID_CLIENTE";
    private static final String Z_CdCliente = "CD_CLIENTE";
    private static final String Z_Nome = "NOME";
    private static final String Z_SobreNome = "SOBRENOME";
    private static final String Z_NomeFantasia = "NOMEFANTASIA";
    private static final String Z_RazaoSocial = "RAZAO_SOCIAL";
    private static final String Z_CNPJ = "CNPJ";
    private static final String Z_CEP = "CEP";
    private static final String Z_RUA = "RUA";
    private static final String Z_Numero = "NUMERO";
    private static final String Z_Complemento = "COMPLEMENTO";
    private static final String Z_Bairro = "BAIRRO";
    private static final String Z_Cidade = "CIDADE";
    private static final String Z_Estado = "ESTADO";
    private static final String Z_TelefoneFixo = "TELEFONEFIXO";
    private static final String Z_TelefoneCelular = "TELEFONECELULAR";
    private static final String Z_Email = "EMAIL";
    private static final String Z_Status = "STATUS";
    private static final String Z_Cd_Tipo_Cliente = "CD_TIPO_CLIENTE";
    private static final String Z_Cd_Usuario = "CD_USUARIO";
    private static final String Z_Sync = "SYNC";

    private static final String ATIVO = "S";
    private static final String INATIVO="N";

    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public ClienteData(Context context) {
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

    public void insert(ClienteModel cliente) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_CdCliente, cliente.getCdCliente());
        values.put(Z_Nome, cliente.getNome());
        values.put(Z_SobreNome, cliente.getSobreNome());
        values.put(Z_NomeFantasia, cliente.getNomeFantasia());
        values.put(Z_RazaoSocial, cliente.getRazaoSocial());
        values.put(Z_CNPJ, cliente.getCnpj());
        values.put(Z_CEP, cliente.getCep());
        values.put(Z_RUA, cliente.getRua());
        values.put(Z_Numero, cliente.getNumero());
        values.put(Z_Complemento, cliente.getComplemento());
        values.put(Z_Bairro, cliente.getBairro());
        values.put(Z_Cidade, cliente.getCidade());
        values.put(Z_Estado, cliente.getEstado());
        values.put(Z_TelefoneFixo, cliente.getTelefoneFixo());
        values.put(Z_TelefoneCelular, cliente.getTelefoneCelular());
        values.put(Z_Email, cliente.getEmail());
        values.put(Z_Status, cliente.getStatus());
        values.put(Z_Cd_Tipo_Cliente, cliente.getCdTipoCliente());
        values.put(Z_Cd_Usuario, cliente.getCdUsuario());

        values.put(Z_Sync, ATIVO);

        db.insert(table, null, values);

        Log.w(table, "registro inserido com sucesso!" + values);
        db.close();
    }

    public List<ClienteModel> getCliente(Integer cdUsuario) {

        StringBuilder query = new StringBuilder();
        ClienteModel cliente = null;
        Cursor cursor = null;
        List<ClienteModel> listCliente = null;

        try {

            query.append(" SELECT ID_CLIENTE,CD_CLIENTE,NOME,SOBRENOME,NOMEFANTASIA,RAZAO_SOCIAL,CNPJ,CEP,RUA,NUMERO,COMPLEMENTO,BAIRRO, ");
            query.append(" CIDADE,ESTADO,TELEFONEFIXO,TELEFONECELULAR,EMAIL,STATUS,CD_TIPO_CLIENTE,CD_USUARIO FROM CLIENTE WHERE CD_USUARIO=? ");

            db = banco.getReadableDatabase();

            cursor = db.rawQuery(query.toString(), new String[]{String.valueOf(cdUsuario)});

            listCliente = new ArrayList<ClienteModel>();

            if (cursor != null) {

                while (cursor.moveToNext()) {
                    cliente = new ClienteModel();
                    cliente.setIdCliente(cursor.getLong(cursor.getColumnIndex(Z_IdCliente)));
                    cliente.setCdCliente(cursor.getInt(cursor.getColumnIndex(Z_CdCliente)));
                    cliente.setNome(cursor.getString(cursor.getColumnIndex(Z_Nome)));
                    cliente.setSobreNome(cursor.getString(cursor.getColumnIndex(Z_SobreNome)));
                    cliente.setNomeFantasia(cursor.getString(cursor.getColumnIndex(Z_NomeFantasia)));
                    cliente.setRazaoSocial(cursor.getString(cursor.getColumnIndex(Z_RazaoSocial)));
                    cliente.setCnpj(cursor.getString(cursor.getColumnIndex(Z_CNPJ)));
                    cliente.setCep(cursor.getString(cursor.getColumnIndex(Z_CEP)));
                    cliente.setRua(cursor.getString(cursor.getColumnIndex(Z_RUA)));
                    cliente.setNumero(cursor.getInt(cursor.getColumnIndex(Z_Numero)));
                    cliente.setComplemento(cursor.getString(cursor.getColumnIndex(Z_Complemento)));
                    cliente.setBairro(cursor.getString(cursor.getColumnIndex(Z_Bairro)));
                    cliente.setCidade(cursor.getString(cursor.getColumnIndex(Z_Cidade)));
                    cliente.setEstado(cursor.getString(cursor.getColumnIndex(Z_Estado)));
                    cliente.setTelefoneFixo(cursor.getString(cursor.getColumnIndex(Z_TelefoneFixo)));
                    cliente.setTelefoneCelular(cursor.getString(cursor.getColumnIndex(Z_TelefoneCelular)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndex(Z_Email)));
                    cliente.setStatus(cursor.getInt(cursor.getColumnIndex(Z_Status)));
                    cliente.setCdTipoCliente(cursor.getInt(cursor.getColumnIndex(Z_Cd_Tipo_Cliente)));
                    cliente.setCdUsuario(cursor.getInt(cursor.getColumnIndex(Z_Cd_Usuario)));
                    listCliente.add(cliente);
                }
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCliente;
    }

}
