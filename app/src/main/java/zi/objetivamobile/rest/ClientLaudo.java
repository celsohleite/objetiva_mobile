package zi.objetivamobile.rest;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.LaudoItemModel;

/**
 * Created by leite on 22/04/2018.
 */

public class ClientLaudo {

    public static List<Long> getNovoNumLaudo(String url) {

        String result="";
        OkHttpClient client;
        Request.Builder builder;
        Response response;
        Request request;
        ClienteLaudoModel model = new ClienteLaudoModel();
        List<Long> listRetornoLaudo = new ArrayList<Long>();
        Gson parametro;

        try {

            parametro = new Gson();

            client = new OkHttpClient();
            builder = new Request.Builder();
            builder.url(url);

            MediaType mediaType =
                    MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(mediaType, "");
            builder.get();

            request = builder.build();

            response = client.newCall(request).execute();
            result = response.body().string();

        }catch (IOException io){
            io.printStackTrace();
        }

        model = converForObjectJson(result);

        //[0] codigo laudo e [1] numero do laudo
        listRetornoLaudo.add(model.getCdLaudo());
        listRetornoLaudo.add(model.getNumLaudo());

        return listRetornoLaudo;
    }


    public static ClienteLaudoModel converForObjectJson(String result) {

        ClienteLaudoModel usuario = new ClienteLaudoModel();

        Gson gson = new Gson();

        try {
            usuario = gson.fromJson(result, ClienteLaudoModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
