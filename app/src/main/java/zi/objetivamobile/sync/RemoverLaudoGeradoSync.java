package zi.objetivamobile.sync;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zi.objetivamobile.business.LaudoBusiness;
import zi.objetivamobile.model.ClienteLaudoModel;

/**
 * Created by leite on 31/05/2018.
 */

public class RemoverLaudoGeradoSync {

    private LaudoBusiness laudoBusiness;

    public static ClienteLaudoModel removerLaudo(String url, ClienteLaudoModel model) {

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

        return converForObjectJson(result);
    }


    public static ClienteLaudoModel converForObjectJson(String result) {

        ClienteLaudoModel clienteLaudo = new ClienteLaudoModel();

        Gson gson = new Gson();

        try {
            clienteLaudo = gson.fromJson(result, ClienteLaudoModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clienteLaudo;
    }
}
