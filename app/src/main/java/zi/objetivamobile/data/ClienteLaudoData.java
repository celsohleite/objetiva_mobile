package zi.objetivamobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.util.CreateDataBaseUtil;

/**
 * Created by leite on 22/02/18.
 */

public class ClienteLaudoData {

    private static final String table = "LAUDO";

    private static final String Z_IdLaudo = "ID_LAUDO";

    private static final String Z_CdLaudo = "CD_LAUDO";
    private static final String Z_NumLaudo = "NUM_LAUDO";
    private static final String Z_CdUsuario = "CD_USUARIO";
    private static final String Z_Placa = "PLACA";
    private static final String Z_Cidade = "CIDADE";
    private static final String Z_UfPlaca = "UF_PLACA";
    private static final String Z_Chassi = "CHASSI";
    private static final String Z_IdentMotor = "IDENT_MOTOR";
    private static final String Z_IdentCambio = "IDENT_CAMBIO";
    private static final String Z_IdentCarroceria = "IDENT_CARROCERIA";
    private static final String Z_Leilao = "LEILAO";
    private static final String Z_Cor = "COR";
    private static final String Z_AnoModelo = "ANO_MODELO";
    private static final String Z_AnoFabricacao = "ANO_FABRICACAO";
    private static final String Z_Modelo = "MODELO";
    private static final String Z_Marca = "MARCA";
    private static final String Z_CdVistoriador = "CD_VISTORIADOR";
    private static final String Z_CdCliente = "CD_CLIENTE";
    private static final String Z_TipoCliente = "TIPO_CLIENTE";
    private static final String Z_CreatedAt = "CREATED_AT";
    private static final String Z_Observacao = "OBSERVACAO";
    private static final String Z_ObservacaoAdicional = "OBSERVACAO_ADICIONAL";
    private static final String Z_Status = "STATUS";
    private static final String Z_NomeClienteParticular = "NOME_CLIENTE_PARTICULAR";
    private static final String Z_Hodometro = "HODOMETRO";
    private static final String Z_ShowHodometro = "SHOW_HODOMETRO";
    private static final String Z_CdCancelamento = "CD_CANCELAMENTO";
    private static final String Z_DataCancelamento = "DATA_CANCELAMENTO";
    private static final String Z_IdLaudoItem = "ID_LAUDO_ITEM";
    private static final String Z_Tipo_laudo = "TIPO_LAUDO";


    private SQLiteDatabase db;
    private CreateDataBaseUtil banco;

    public ClienteLaudoData(Context context) {
        banco = new CreateDataBaseUtil(context);
    }

    public boolean insert(ClienteLaudoModel laudo) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();
        laudo.setStatus(0);
        try {
            values.put(Z_CdLaudo, laudo.getCdLaudo());
            values.put(Z_NumLaudo, laudo.getNumLaudo());
            values.put(Z_CdUsuario, laudo.getCdUsuario());
            values.put(Z_Placa, laudo.getPlaca());
            values.put(Z_Cidade, laudo.getCidade());
            values.put(Z_UfPlaca, laudo.getUf());
            values.put(Z_Chassi, laudo.getChassi());
            values.put(Z_IdentMotor, laudo.getIdentificacaoMotor());
            values.put(Z_IdentCambio, laudo.getIdentificacaoCambio());
            values.put(Z_IdentCarroceria, laudo.getIdentificacaoCarroceria());
            values.put(Z_Leilao, laudo.getLeilao());
            values.put(Z_Cor, laudo.getCor());
            values.put(Z_AnoModelo, laudo.getAnoModelo());
            values.put(Z_AnoFabricacao, laudo.getAnoFabricacao());
            values.put(Z_Modelo, laudo.getModelo());
            values.put(Z_Marca, laudo.getMarca());
            values.put(Z_CdVistoriador, laudo.getCdVistoriador());
            values.put(Z_CdCliente, laudo.getCdCliente());
            values.put(Z_CreatedAt, String.valueOf(new Date()));
            values.put(Z_Observacao, laudo.getObservacao());
            values.put(Z_Status, laudo.getStatus());
            values.put(Z_NomeClienteParticular, laudo.getNomeCliente());
            values.put(Z_Hodometro, laudo.getHodometro());
            values.put(Z_ShowHodometro, laudo.getShowHodemetro());
            values.put(Z_TipoCliente, laudo.getTipoCliente());
            values.put(Z_ObservacaoAdicional, laudo.getObservacaoAdicional());
            values.put(Z_IdLaudoItem, laudo.getIdLaudoItem());
            values.put(Z_Tipo_laudo, laudo.getTipoLaudo());

            db.insert(table, null, values);

            Log.w(table, "registro inserido com sucesso!" + values);
            db.close();

            return true;

        } catch (Exception e) {
            Log.i(table, "error : " + e.getMessage());
            return false;
        }
    }


    public void update(ClienteLaudoModel laudo) {

        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_CdLaudo, laudo.getCdLaudo());
        values.put(Z_NumLaudo, laudo.getNumLaudo());
        values.put(Z_CdUsuario, laudo.getCdUsuario());
        values.put(Z_Placa, laudo.getPlaca());
        values.put(Z_Cidade, laudo.getCidade());
        values.put(Z_UfPlaca, laudo.getUf());
        values.put(Z_Chassi, laudo.getChassi());
        values.put(Z_IdentMotor, laudo.getIdentificacaoMotor());
        values.put(Z_IdentCambio, laudo.getIdentificacaoCambio());
        values.put(Z_IdentCarroceria, laudo.getIdentificacaoCarroceria());
        values.put(Z_Leilao, laudo.getLeilao());
        values.put(Z_Cor, laudo.getCor());
        values.put(Z_AnoModelo, laudo.getAnoModelo());
        values.put(Z_AnoFabricacao, laudo.getAnoFabricacao());
        values.put(Z_Modelo, laudo.getModelo());
        values.put(Z_Marca, laudo.getMarca());
        values.put(Z_CdVistoriador, laudo.getCdVistoriador());
        values.put(Z_CdCliente, laudo.getCdCliente());
        values.put(Z_CreatedAt, String.valueOf(new Date()));
        values.put(Z_Observacao, laudo.getObservacao());
        values.put(Z_Status, laudo.getStatus());
        values.put(Z_NomeClienteParticular, laudo.getNomeCliente());
        values.put(Z_Hodometro, laudo.getHodometro());
        values.put(Z_ShowHodometro, laudo.getShowHodemetro());
        values.put(Z_CdCancelamento, laudo.getCdCancelamento());
        values.put(Z_DataCancelamento, String.valueOf(laudo.getDataCancelamento()));
        values.put(Z_TipoCliente, String.valueOf(laudo.getTipoCliente()));
        values.put(Z_ObservacaoAdicional, String.valueOf(laudo.getObservacaoAdicional()));
        values.put(Z_IdLaudoItem, String.valueOf(laudo.getIdLaudoItem()));

        db.update(table, values, null, new String[]{String.valueOf(laudo.getIdCliente())});

        Log.w(table, "registro atualizado com sucesso!" + values);
        db.close();
    }

    public List<ClienteLaudoModel> getLaudoCliente(Long numLaudo, int codVistoriador, int tela) {
        Cursor cursor = null;
        List<ClienteLaudoModel> listLaudo = null;
        ClienteLaudoModel model = null;

        StringBuilder query = new StringBuilder();

        query.append(" SELECT ID_LAUDO,CD_LAUDO,NUM_LAUDO,CD_USUARIO,PLACA,CIDADE,UF_PLACA,CHASSI,IDENT_MOTOR,");
        query.append(" IDENT_CAMBIO,IDENT_CARROCERIA,LEILAO,COR,ANO_MODELO,ANO_FABRICACAO,MODELO,MARCA,");
        query.append(" CD_VISTORIADOR,CD_CLIENTE,CREATED_AT,OBSERVACAO,STATUS,TIPO_CLIENTE,NOME_CLIENTE_PARTICULAR,");
        query.append(" HODOMETRO,SHOW_HODOMETRO,CD_CANCELAMENTO,OBSERVACAO_ADICIONAL,ID_LAUDO_ITEM, TIPO_LAUDO FROM LAUDO ");

        try {

            listLaudo = new ArrayList<ClienteLaudoModel>();

            db = banco.getReadableDatabase();

            if (numLaudo != 0) {
                query.append(" WHERE NUM_LAUDO = ? and CD_VISTORIADOR = ? ");
                cursor = db.rawQuery(query.toString(), new String[]{numLaudo.toString(), String.valueOf(codVistoriador)});
            } else {
                query.append(" WHERE CD_VISTORIADOR = ? ");
                cursor = db.rawQuery(query.toString(), new String[]{String.valueOf(codVistoriador)});
            }

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    model = new ClienteLaudoModel();
                    model.setIdCliente(cursor.getLong(cursor.getColumnIndex(Z_IdLaudo)));
                    model.setCdLaudo(cursor.getLong(cursor.getColumnIndex(Z_CdLaudo)));
                    model.setNumLaudo(cursor.getLong(cursor.getColumnIndex(Z_NumLaudo)));
                    model.setCdUsuario(cursor.getInt(cursor.getColumnIndex(Z_CdUsuario)));
                    model.setPlaca(cursor.getString(cursor.getColumnIndex(Z_Placa)));
                    model.setCidade(cursor.getString(cursor.getColumnIndex(Z_Cidade)));
                    model.setUf(cursor.getString(cursor.getColumnIndex(Z_UfPlaca)));
                    model.setChassi(cursor.getString(cursor.getColumnIndex(Z_Chassi)));
                    model.setIdentificacaoMotor(cursor.getString(cursor.getColumnIndex(Z_IdentMotor)));
                    model.setIdentificacaoCambio(cursor.getString(cursor.getColumnIndex(Z_IdentCambio)));
                    model.setIdentificacaoCarroceria(cursor.getString(cursor.getColumnIndex(Z_IdentCarroceria)));

                    model.setLeilao(cursor.getInt(cursor.getColumnIndex(Z_Leilao)));

                    model.setCor(cursor.getString(cursor.getColumnIndex(Z_Cor)));

                    model.setAnoFabricacao(cursor.getInt(cursor.getColumnIndex(Z_AnoFabricacao)));
                    model.setAnoModelo(cursor.getInt(cursor.getColumnIndex(Z_AnoModelo)));

                    model.setMarca(cursor.getString(cursor.getColumnIndex(Z_Marca)));
                    model.setModelo(cursor.getString(cursor.getColumnIndex(Z_Modelo)));
                    model.setCdVistoriador(cursor.getInt(cursor.getColumnIndex(Z_CdVistoriador)));

                    model.setCdCliente(cursor.getInt(cursor.getColumnIndex(Z_CdCliente)));
                    model.setObservacao(cursor.getString(cursor.getColumnIndex(Z_Observacao)));
                    model.setStatus(cursor.getInt(cursor.getColumnIndex(Z_Status)));
                    model.setTipoCliente(cursor.getInt(cursor.getColumnIndex(Z_TipoCliente)));
                    model.setNomeCliente(cursor.getString(cursor.getColumnIndex(Z_NomeClienteParticular)));
                    model.setHodometro(cursor.getString(cursor.getColumnIndex(Z_Hodometro)));
                    model.setObservacaoAdicional(cursor.getString(cursor.getColumnIndex(Z_ObservacaoAdicional)));
                    model.setIdLaudoItem(cursor.getLong(cursor.getColumnIndex(Z_IdLaudoItem)));
                    model.setTipoLaudo(cursor.getString(cursor.getColumnIndex(Z_Tipo_laudo)));

                    if (tela == 0) {
                        if (model.getStatus() == 0)
                            listLaudo.add(model);
                    } else {
                        if (model.getStatus() > 0)
                            listLaudo.add(model);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return listLaudo;
    }

    public String getTipoLaudo(Long numLaudo) {
        Cursor cursor = null;
        List<ClienteLaudoModel> listLaudo = null;
        String tipoLaudo = null;

        StringBuilder query = new StringBuilder();

        query.append(" SELECT TIPO_LAUDO FROM LAUDO ");
        query.append(" WHERE NUM_LAUDO = ? ");
        try {
            listLaudo = new ArrayList<ClienteLaudoModel>();
            db = banco.getReadableDatabase();
            cursor = db.rawQuery(query.toString(), new String[]{numLaudo.toString()});

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    tipoLaudo = cursor.getString(cursor.getColumnIndex(Z_Tipo_laudo));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return tipoLaudo;
    }

    public void concluirLaudo(ClienteLaudoModel laudo) {
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Z_Status, laudo.getStatus());
        values.put(Z_Observacao, laudo.getObservacao());

        db.update(table, values, Z_NumLaudo.concat("=" + laudo.getNumLaudo()), null);

        Log.w(table, "[ Concluido ] : registro atualizado com sucesso!" + values);
        db.close();
    }

}
