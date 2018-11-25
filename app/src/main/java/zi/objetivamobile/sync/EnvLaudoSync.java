package zi.objetivamobile.sync;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zi.objetivamobile.business.LaudoBusiness;
import zi.objetivamobile.model.CategoriaModel;
import zi.objetivamobile.model.CheckListModel;
import zi.objetivamobile.model.ClienteLaudoModel;

/**
 * Created by leite on 25/04/2018.
 */

public class EnvLaudoSync extends AsyncTask<String, Integer,  String> {

    private LaudoBusiness laudoBusiness;

    public static ClienteLaudoModel converForObjecClientetJson(String result) {

        ClienteLaudoModel clienteLaudo = new ClienteLaudoModel();

        Gson gson = new Gson();

        try {
            clienteLaudo = gson.fromJson(result, ClienteLaudoModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clienteLaudo;
    }


    public static CheckListModel sendCategoria(String url, CheckListModel model){
        String result = "";
        OkHttpClient client;
        Request.Builder builder;
        Response response;
        Request request;

        Gson parametro;

        try {

            parametro = new Gson();

            client = new OkHttpClient();
            builder = new Request.Builder();
            builder.url(url);

            MediaType mediaType =
                    MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(mediaType, parametro.toJson(model));
            builder.post(body);

            request = builder.build();

            response = client.newCall(request).execute();
            result = response.body().string();

        } catch (IOException io) {
            io.printStackTrace();
        }

        return converForObjecCategoriaJson(result);
    }


    public static CheckListModel converForObjecCategoriaJson(String result) {

        CheckListModel checkListModel = new CheckListModel();

        Gson gson = new Gson();

        try {
            checkListModel = gson.fromJson(result, CheckListModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkListModel;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


    public static ClienteLaudoModel sendLaudo(String url, ClienteLaudoModel model) {

        String result = "";
        OkHttpClient client;
        Request.Builder builder;
        Response response;
        Request request;

        Gson parametro;

        try {

            parametro = new Gson();

            client = new OkHttpClient();
            builder = new Request.Builder();
            builder.url(url);

            MediaType mediaType =
                    MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(mediaType, parametro.toJson(model));
            builder.post(body);

            request = builder.build();

            response = client.newCall(request).execute();
            result = response.body().string();

        } catch (IOException io) {
            io.printStackTrace();
        }

        return converForObjecClientetJson(result);
    }

}
