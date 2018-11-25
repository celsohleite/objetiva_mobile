package zi.objetivamobile.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leite on 12/10/17.
 */

public class CreateDataBaseUtil extends SQLiteOpenHelper {

    private static final String DATA_SCRIPT = "datascript.txt";
    private static final String NOME_BANCO = "zi_objetiva.db";
    private static final int VERSAO = 4;
    private Context context = null;
    private final static String TAG = "CreateDataBaseUtil";

    public CreateDataBaseUtil(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            for (String sql : getDataScriptFile()) {
                sqLiteDatabase.execSQL(sql);
            }
            Log.w(TAG,"BANCO CRIADO COM SUCESSO!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<String> getDataScriptFile() throws IOException {

        String line = null;
        StringBuilder lines = new StringBuilder();
        List<String> query = new ArrayList<String>();
        InputStream is = this.context.getResources().getAssets()
                .open(this.DATA_SCRIPT);
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));

        while ((line = bf.readLine()) != null) {
            lines.append(line);
        }

        for (String sql : lines.toString().split(";")) {
            query.add(sql);
        }

        return query;
    }

    public void getBanco() {

        String currentDBPath = "/user/0/zi.objetivamobile/databases/"
                .concat(NOME_BANCO);
        String backupDBPath = Environment.getDataDirectory().toString() + currentDBPath;
        File arquivoDb = new File(backupDBPath);

        if (arquivoDb.exists()) {

        } else {

        }

    }
}
