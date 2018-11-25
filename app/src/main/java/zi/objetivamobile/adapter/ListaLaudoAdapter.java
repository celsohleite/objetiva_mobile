package zi.objetivamobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zi.objetivamobile.R;
import zi.objetivamobile.model.LaudoItemModel;

/**
 * Created by leite on 16/10/17.
 */

public class ListaLaudoAdapter extends BaseAdapter {

    private Context mContext;
    List<LaudoItemModel> mLaudoItem;

    public ListaLaudoAdapter(Context context, List<LaudoItemModel> itens) {
        this.mContext = context;
        this.mLaudoItem = itens;
    }

    /* private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtPlaca;
        TextView txtModelo;
        TextView txtNomeCliente;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.content_item_lista_laudo, null);

            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.imgv_icone_laudo);
            holder.txtPlaca = (TextView) convertView.findViewById(R.id.txtv_identi_laudo);
            holder.txtModelo = (TextView) convertView.findViewById(R.id.txtv_porte_laudo);
            holder.txtNomeCliente = (TextView) convertView.findViewById(R.id.txtv_tipo_laudo);

            convertView.setTag(holder);
       }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        LaudoItemModel item = (LaudoItemModel) getItem(position);

        holder.imageView.setImageResource(item.getImageID());
        holder.txtPlaca.setText(item.getPlaca());
        holder.txtModelo.setText(item.getModelo());
        holder.txtNomeCliente.setText(item.getNome());

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
