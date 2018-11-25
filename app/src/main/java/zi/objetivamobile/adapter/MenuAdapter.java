package zi.objetivamobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import zi.objetivamobile.R;
import zi.objetivamobile.adapter.holder.MenuHolder;
import zi.objetivamobile.model.MenuModel;

/**
 * Created by leite on 25/09/17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuHolder>  {

    private List<MenuModel> listaMenu;
    private Context context;

    public MenuAdapter(List<MenuModel> menus,Context context) {
        this.listaMenu = menus;
        this.context = context;
    }


    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_menu_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        MenuHolder menuHolder = (MenuHolder) holder;

        MenuModel menu = listaMenu.get(position);

        menuHolder.mMenuTitle.setText("teste de holder");

    }


    @Override
    public int getItemCount() {
        return listaMenu.size();
    }
}
