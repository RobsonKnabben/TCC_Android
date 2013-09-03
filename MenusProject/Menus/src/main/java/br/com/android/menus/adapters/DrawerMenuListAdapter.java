package br.com.android.menus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.model.AppMenuItem;

/**
 * Created by Robson on 10/08/13.
 */
public class DrawerMenuListAdapter extends ArrayAdapter<AppMenuItem> {
    // Declare Variables
    private final Context context;
    private final LayoutInflater inflater;
    private final int resourceId;

    public DrawerMenuListAdapter(Context context, int resource, List<AppMenuItem> objects){
        super(context, resource, objects);

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        AppMenuItem item = getItem(position);

        View itemView = inflater.inflate(resourceId, parent, false);

        TextView title = (TextView) itemView.findViewById(R.id.title);
        TextView subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        ImageView icon = (ImageView) itemView.findViewById(R.id.icon);

        title.setText(item.getTitle());
        if (subtitle != null) subtitle.setText(item.getSubtitle());
        icon.setImageResource(item.getIcon());

        return itemView;
    }

}
