package br.com.android.menus.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.model.Telefone;

/**
 * Created by Robson on 03/08/13.
 */
public class DialogListAdapter extends ArrayAdapter<Telefone> {

    private final LayoutInflater inflater;

    private final int resourceId;

    public DialogListAdapter(Context context, int resource, List<Telefone> objects) {
        super(context, resource, objects);

        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Telefone telefone = getItem(position);

        convertView = inflater.inflate(resourceId, parent, false);

        TextView numero = (TextView) convertView.findViewById(R.id.numero);

        numero.setText(telefone.getNumero());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0" + telefone.getNumero()));
                getContext().startActivity(dialIntent);
            }
        });

        return convertView;
    }
}
