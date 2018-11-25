package zi.objetivamobile.rest;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zi.objetivamobile.model.FotoModel;

/**
 * Created by leite on 09/05/2018.
 */

public class ClientFoto {

    public static List<FotoModel> get(String url, FotoModel model) {

        String result="";
        OkHttpClient client;
        Request.Builder builder;
        Response response;
        Request request;

        Gson parametro;

        try {

            parametro = new Gson();

            client = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .build();

            client.socketFactory().createSocket();
            builder = new Request.Builder();
            builder.url(url);

            MediaType mediaType =
                    MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(mediaType, parametro.toJson(model));
            builder.post(body);

            request = builder.build();

            response = client.newCall(request).execute();
            result = response.body().string();

            System.out.println("[ enviando arquivo... ] : " + model.getFotoArquivo());

        }catch (IOException io){
            io.printStackTrace();
        }

        return converForObjectJson(result);
    }


    public static List<FotoModel> converForObjectJson(String result) {
        List<FotoModel> listCategoria = null;
        listCategoria = new ArrayList<FotoModel>();

        Gson gson = new Gson();

        try {
            if(!"".equals(result)) {
                JSONObject json = new JSONObject(result);
                listCategoria = Arrays.asList(gson.fromJson(json.getString("FotoModel"), FotoModel[].class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCategoria;
    }
}
