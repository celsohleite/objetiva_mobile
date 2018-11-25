package zi.objetivamobile.rest;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zi.objetivamobile.model.ClienteModel;

/**
 * Created by leite on 13/03/2018.
 */

public class ClientCliente {

    public static List<ClienteModel> get(String url) {

        String result="";
        OkHttpClient client;
        Request.Builder builder;
        Response response;
        Request request;

        try {

            client = new OkHttpClient();
            builder = new Request.Builder();
            builder.url(url);

            MediaType mediaType =
                    MediaType.parse("application/json; charset=utf-8");

            builder.get();

            request = builder.build();

            response = client.newCall(request).execute();
            result = response.body().string();

        }catch (IOException io){
            io.printStackTrace();
        }

        return converForObjectJson(result);
    }


    public static List<ClienteModel> converForObjectJson(String result) {
        List<ClienteModel> listCategoria = null;
        listCategoria = new ArrayList<ClienteModel>();

        Gson gson = new Gson();

        try {
            JSONObject json= new JSONObject(result);
            listCategoria = Arrays.asList(gson.fromJson(json.getString("clienteModel"),ClienteModel[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCategoria;
    }

}
