package zi.objetivamobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zi.objetivamobile.R;
import zi.objetivamobile.model.FotoModel;
import zi.objetivamobile.util.Image64Util;

/**
 * Created by leite on 02/10/17.
 */

public class FotoAdapter extends BaseAdapter {

    private Context mContext;
    List<FotoModel> mLaudoItem;
    private List<Bitmap> mFotosLaudos;
    private Image64Util image64Util;
    private static String mDirFoto="/laudos_objetiva/";

    public FotoAdapter(Context context, List<FotoModel> itens) {
        this.mContext = context;
        this.mLaudoItem = itens;
        //this.mFotosLaudos = mFotos;
        image64Util = new Image64Util();
    }

    /* private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtDescFoto;
        TextView txtVistoria;
        TextView txtNumeracao;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        FotoAdapter.ViewHolder holder = null;
        Bitmap bitmap;

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.content_item_foto_laudo, null);

            FotoModel item = (FotoModel) getItem(position);

            holder = new FotoAdapter.ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.imgv_foto_laudo);
            holder.txtDescFoto = (TextView) convertView.findViewById(R.id.txt_desc_title);

            holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.imageView.setPadding(2, 2, 2, 2);

            holder.imageView.setImageBitmap(image64Util.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
                    .concat(mDirFoto)
                    .concat(item.getNomeFoto()),"view"));

            holder.txtDescFoto.setText(item.getDescricao());
            holder.txtDescFoto.setTextColor(Color.BLACK);

            convertView.setTag(holder);

        } else {
            holder = (FotoAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public int getCount() {
        if (mLaudoItem != null)
            return mLaudoItem.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mLaudoItem != null)
            return mLaudoItem.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mLaudoItem.indexOf(getItem(position));
    }
}
