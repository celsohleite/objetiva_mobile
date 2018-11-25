package zi.objetivamobile.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zi.objetivamobile.model.CategoriaModel;

/**
 * Created by leite on 02/11/17.
 */

public class ClientCategoria {

    public static List<CategoriaModel> get(String url, CategoriaModel model) {

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
            builder.get();

            request = builder.build();

            response = client.newCall(request).execute();
            result = response.body().string();

        }catch (IOException io){
            io.printStackTrace();
        }

        return converForObjectJson(result);
    }


    public static List<CategoriaModel> converForObjectJson(String result) {
        List<CategoriaModel> listCategoria = null;
        listCategoria = new ArrayList<CategoriaModel>();

        Gson gson = new Gson();

        try {
            if(!"".equals(result)) {
                JSONObject json = new JSONObject(result);
                listCategoria = Arrays.asList(gson.fromJson(json.getString("categoriaModel"), CategoriaModel[].class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCategoria;
    }

}
