package zi.objetivamobile.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by leite on 12/10/17.
 */

public class Image64Util {

    private String imageBase4Convert = null;
    private static final int COMPRESS_QUALITY = 80;

    public String encodeImageBase64(Bitmap bitmap) {
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 99, baos);
        byte[] imageBytes = baos.toByteArray();

        imageBase4Convert = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return imageBase4Convert;
    }

    public String encodeImageBase64Str(String imageName) {
        //encode image to base64 string
        byte[] imageBytes = imageName.getBytes();
        return imageBase4Convert = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }

    // Decodifica a imagem e escala para a redução do consumo de memória
    public Bitmap decodeFile(String path, String tipo) {
        try {
            File imagem = new File(path);
            // Decodifica o tamanho da imagem
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(imagem), null, o);
            int REQUIRED_SIZE = 0;
            // O novo tamanho que queremos
            if(!tipo.equals("sync")) {
                REQUIRED_SIZE = 300;
            }else{
                REQUIRED_SIZE = 1024;
            }

            // Achar o valor correto para a escala
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decodifica com o inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(imagem), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

    public String imageToSend (Bitmap bimg) throws Exception {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bimg.compress(Bitmap.CompressFormat.JPEG, COMPRESS_QUALITY, baos);
            // Codifica em base 64
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        }
        catch (Exception ex) {
            throw new Exception("Houve erro com a imagem.", ex);
        }
    }

}
