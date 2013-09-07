package br.com.android.menus.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.model.Linha;
import br.com.android.menus.model.Produto;

/**
 * Created by Robson on 12/08/13.
 */
public class ProdutosPorLinhasAdapter extends BaseExpandableListAdapter {
    Context mContext;
    List<Linha> mLinhas;

    public ProdutosPorLinhasAdapter(Context context, List<Linha> objects) {
        this.mContext = context;
        this.mLinhas = objects;
    }

    @Override
    public int getGroupCount() {
        return mLinhas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mLinhas.get(i).getProdutos().size();
    }

    @Override
    public Object getGroup(int i) {
        return mLinhas.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return mLinhas.get(i).getProdutos().get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return mLinhas.get(i).getId();
    }

    @Override
    public long getChildId(int i, int i2) {
        return mLinhas.get(i).getProdutos().get(i2).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_group_linha, viewGroup, false);
        }

        TextView groupName = (TextView) view.findViewById(R.id.groupName);

        Linha linha = (Linha) getGroup(i);

        groupName.setText(linha.getName());

        return view;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item_produto, viewGroup, false);
        }

        TextView itemName = (TextView) view.findViewById(R.id.itemName);
        TextView itemDescricao = (TextView) view.findViewById(R.id.itemDescricao);
        TextView itemPreco = (TextView) view.findViewById(R.id.itemPreco);

        final Produto produto = (Produto) getChild(i, i2);

        itemName.setText(produto.getName());
        itemDescricao.setText(produto.getDescription());
        itemPreco.setText("R$" + new DecimalFormat("0.00").format(produto.getPrice()).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(mContext);
                dialog.setTitle(produto.getName());
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
