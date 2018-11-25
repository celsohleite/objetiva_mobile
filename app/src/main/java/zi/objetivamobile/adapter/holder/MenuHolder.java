package zi.objetivamobile.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by leite on 26/09/17.
 */

public class MenuHolder extends RecyclerView.ViewHolder{

    public TextView mMenuTitle;
    public ImageView mMenuIcon;
    public Context context;

    public MenuHolder(View view) {
        super(view);
        //mMenuTitle = (TextView) view.findViewById(R.id.menu_title);
        //mMenuIcon = (ImageView) view.findViewById(R.id.image_menu);
    }

}
