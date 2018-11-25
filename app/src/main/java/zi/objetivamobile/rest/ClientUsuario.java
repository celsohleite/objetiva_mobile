package zi.objetivamobile.rest;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zi.objetivamobile.model.AuthVistoriadorModel;

/**
 * Created by leite on 19/09/17.
 */

public class ClientUsuario {

    public static AuthVistoriadorModel get(String url, AuthVistoriadorModel model) {

        String result="";
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

        }catch (IOException io){
            io.printStackTrace();
        }

        return converForObjectJson(result);
    }


    public static AuthVistoriadorModel converForObjectJson(String result) {

        AuthVistoriadorModel usuario = new AuthVistoriadorModel();

        Gson gson = new Gson();

        try {
            usuario = gson.fromJson(result, AuthVistoriadorModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
