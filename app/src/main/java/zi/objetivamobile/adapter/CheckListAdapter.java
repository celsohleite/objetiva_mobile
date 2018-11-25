package zi.objetivamobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import zi.objetivamobile.R;
import zi.objetivamobile.business.CategoriaBusiness;
import zi.objetivamobile.model.CategoriaAuxModel;

/**
 * Created by leite on 02/12/17.
 */

public class CheckListAdapter extends ArrayAdapter<CategoriaAuxModel> {

    public List<CategoriaAuxModel> mListModel;
    private Context mContext;
    private int mIdLayout;

    //depois trocar chamada business
    private CategoriaBusiness mBusiness;

    public CheckListAdapter(Context mContext, int mIdLayout, List<CategoriaAuxModel> mListModel) {
        super(mContext, mIdLayout, mListModel);
        this.mListModel = mListModel;
        this.mContext = mContext;
        this.mIdLayout = mIdLayout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetalhesModelHolder holder = null;
        mBusiness = new CategoriaBusiness(getContext());

        if (holder == null) {
            convertView = LayoutInflater.from(mContext).inflate(mIdLayout, parent, false);

            holder = new DetalhesModelHolder();

            holder.model = getItem(position);
            holder.mTxtResposta = convertView.findViewById(R.id.txtv_resposta);
            holder.mChkResposta = convertView.findViewById(R.id.chk_resposta);

            holder.mChkResposta.setChecked(holder.model.isSelected());
        }

        setupItem(holder);
        return convertView;
    }

    private void setupItem(final DetalhesModelHolder holder) {
        if (holder.getModel().getIdResposta() != 0) {
            holder.mTxtResposta.setText(holder.model.getDescricao());
        }else{
            holder.mTxtResposta.setCursorVisible(false);
            holder.mChkResposta.setCursorVisible(false);
            holder.mTxtResposta.setVisibility(View.GONE);
            holder.mChkResposta.setVisibility(View.GONE);
        }
    }

    private class DetalhesModelHolder {
        CategoriaAuxModel model;
        CheckBox mChkResposta;
        TextView mTxtResposta;

        public CategoriaAuxModel getModel() {
            return model;
        }

        public void setModel(CategoriaAuxModel model) {
            this.model = model;
        }

    }
}
