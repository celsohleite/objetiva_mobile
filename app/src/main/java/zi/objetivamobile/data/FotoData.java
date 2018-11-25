package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.StringBuilderPrinter;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.model.FotoModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 12/10/17.
 */

public class FotoData {

    private static final String table = "FOTO_LAUDO";

    private static final String Z_IdCliente = "ID_CLIENTE";
    private static final String Z_NomeFoto = "NOME_FOTO";
    private static final String Z_Path = "PATH";
    private static final String Z_FotoArquivo = "FOTO_ARQUIVO";
    private static final String Z_Descricao = "DESCRICAO";
    private static final String Z_IdLaudo = "ID_LAUDO";
    private static final String Z_CodLaudo = "COD_LAUDO";
    private static final String Z_TMP_FOTO = "TMP_FOTO";
    private static final String Z_TipoLaudo = "TIPO_LAUDO";
    private static final String Z_Sync = "SYNC";

    private static final String Z_status = "STATUS";

    private static final String N ="N";
    private static final String S ="S";

    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public FotoData(Context context) {
        banco = new CreateDataBaseUtil(context);
    }

    public void insert(FotoModel foto) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_FotoArquivo, foto.getFotoArquivo());
        values.put(Z_Descricao, foto.getDescricao());
        values.put(Z_NomeFoto, foto.getNomeFoto());
        values.put(Z_Path, foto.getPathArquivo());
        values.put(Z_IdLaudo, foto.getIdLaudo());
        values.put(Z_CodLaudo, foto.getCodLaudo());
        values.put(Z_status, foto.getStatus());
        //CAMINHO TEMPORARIO
        values.put(Z_TMP_FOTO, foto.getTmpFoto());
        values.put(Z_TipoLaudo, foto.getTipoLaudo());
        values.put(Z_Sync, S); // nasce já com o status sincronizado ira ser enviado após a conclusão do laudo status passa ser N e após S.

        db.insert(table, null, values);

        Log.w(table, "registro inserido com sucesso!" + values);
        db.close();
    }

    public List<FotoModel> getFoto(Long mNumLaudo) {

        StringBuilder query = new StringBuilder();
        Cursor cursor = null;
        List<FotoModel> listFotos = null;
        FotoModel model = null;

            query.append(" SELECT ID_CLIENTE,NOME_FOTO,PATH,FOTO_ARQUIVO,DESCRICAO,STATUS,COD_LAUDO,TMP_FOTO,TIPO_LAUDO,SYNC FROM FOTO_LAUDO ");

        if(mNumLaudo != null) {
            query.append(" WHERE ID_LAUDO = ? ");
        }else {
            query.append(" WHERE SYNC = 'N' ");
        }

        try {
            listFotos = new ArrayList<FotoModel>();

            db = banco.getReadableDatabase();

            if(mNumLaudo !=null)
                cursor = db.rawQuery(query.toString(), new String[]{mNumLaudo.toString()});
                //cursor = db.rawQuery(query.toString(), new String[]{mNumLaudo.toString(), N});
            else
                cursor = db.rawQuery(query.toString(), null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new FotoModel();
                    model.setIdCliente(cursor.getLong(cursor.getColumnIndex(Z_IdCliente)));
                    model.setFotoArquivo(cursor.getString(cursor.getColumnIndex(Z_FotoArquivo)));
                    model.setDescricao(cursor.getString(cursor.getColumnIndex(Z_Descricao)));
                    model.setNomeFoto(cursor.getString(cursor.getColumnIndex(Z_NomeFoto)));
                    model.setPathArquivo(cursor.getString(cursor.getColumnIndex(Z_Path)));
                    model.setStatus(cursor.getString(cursor.getColumnIndex(Z_status)));
                    model.setCodLaudo(cursor.getLong(cursor.getColumnIndex(Z_CodLaudo)));
                    model.setTmpFoto(cursor.getString(cursor.getColumnIndex(Z_TMP_FOTO)));
                    model.setTipoLaudo(cursor.getString(cursor.getColumnIndex(Z_TipoLaudo)));
                    model.setSync(cursor.getString(cursor.getColumnIndex(Z_Sync)));
                    listFotos.add(model);
                }
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFotos;
    }

    public void updateFoto(FotoModel foto, long idFoto) {

        ContentValues values = new ContentValues();

        values.put(Z_status, foto.getStatus());
        values.put(Z_FotoArquivo, foto.getFotoArquivo());
        values.put(Z_Descricao, foto.getDescricao());
        values.put(Z_NomeFoto, foto.getNomeFoto());
        values.put(Z_Path, foto.getPathArquivo());
        values.put(Z_IdLaudo, foto.getIdLaudo());
        values.put(Z_CodLaudo, foto.getCodLaudo());
        values.put(Z_TMP_FOTO, foto.getTmpFoto());
        values.put(Z_TipoLaudo, foto.getTipoLaudo());

        db.update(table, values, Z_IdCliente.concat("=" + idFoto), null);
    }

    public void updateFotoSync(FotoModel foto) {

        ContentValues values = new ContentValues();

        values.put(Z_Sync, S);

        db.update(table, values, Z_IdCliente.concat("=" + foto.getIdCliente()), null);
    }

    public void updateFotoLaudoConcluido(FotoModel foto) {

        ContentValues values = new ContentValues();

        values.put(Z_Sync, N);

        db.update(table, values, Z_IdCliente.concat("=" + foto.getIdCliente()), null);
    }



    public void delete(Long numLaudo, String nomeFoto){
        db = banco.getWritableDatabase();
        StringBuilder query = new StringBuilder();
        db.delete(table, Z_CodLaudo.concat("=" + numLaudo +" and ").concat(Z_NomeFoto.concat("='" + nomeFoto+"'")), null);
        System.out.print("[ FOTO REMOVIDA ] : " + nomeFoto);
    }
}
