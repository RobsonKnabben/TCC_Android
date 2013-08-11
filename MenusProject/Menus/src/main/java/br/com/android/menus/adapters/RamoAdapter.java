package br.com.android.menus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.model.Ramo;

/**
 * Created by Robson on 03/08/13.
 */
public class RamoAdapter extends ArrayAdapter<Ramo> {

    private final LayoutInflater inflater;

    private final int resourceId;

    public RamoAdapter(Context context, int resource, List<Ramo> objects) {
        super(context, resource, objects);

        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ramo ramo = getItem(position);

        convertView = inflater.inflate(resourceId, parent, false);

        TextView name = (TextView) convertView.findViewById(R.id.name);

        name.setText(ramo.getName());

        return convertView;
    }
}
