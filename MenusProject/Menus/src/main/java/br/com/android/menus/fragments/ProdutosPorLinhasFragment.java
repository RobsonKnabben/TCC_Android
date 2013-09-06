package br.com.android.menus.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.activity.AppFragmentActivity;
import br.com.android.menus.activity.BaseFragmentsActivity;
import br.com.android.menus.adapters.DialogListAdapter;
import br.com.android.menus.adapters.ProdutosPorLinhasAdapter;
import br.com.android.menus.model.Estabelecimento;
import br.com.android.menus.model.Linha;
import br.com.android.menus.model.Telefone;


public class ProdutosPorLinhasFragment extends BaseFragments {
    private final String KEY = "LIST_TO_PERSIST";
    private final String KEY_ESTABELECIMENTO = "ESTABELECIMENTO_TO_PERSIST";

    private Estabelecimento estabelecimento;
    private List<Linha> linhasList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        final LayoutInflater inflat = inflater;
        final ViewGroup conteine = container;
        View rootView = inflater.inflate(R.layout.activity_produtos, container, false);

        if (savedInstanceState == null || savedInstanceState.getSerializable(KEY_ESTABELECIMENTO) == null){
            estabelecimento = (Estabelecimento) getSherlockActivity().getIntent().getSerializableExtra(EXTRA_ESTABELECIMENTO);
            linhasList = Linha.getLinhasByEstabelecimentoId(this.getSherlockActivity(), estabelecimento.getId());
        }
        else{
            estabelecimento = (Estabelecimento) savedInstanceState.getSerializable(KEY_ESTABELECIMENTO);
            linhasList = (List<Linha>) savedInstanceState.getSerializable(KEY);
        }

        this.getSherlockActivity().setTitle(estabelecimento.getName());

        ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.expandable_list_view_produtos);

        listView.setIndicatorBounds(0, 35);

        if (linhasList != null) listView.setAdapter(new ProdutosPorLinhasAdapter(this.getSherlockActivity(), linhasList));

        Button telefonar = (Button) rootView.findViewById(R.id.telefonar);
        telefonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflat.inflate(R.layout.dialog_list, conteine, false);
                Telefonar_Click(v.getContext(), view);
                //Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + (txtNumber.getText()).toString()));
                //startActivity(dialIntent);

            }
        });

        return rootView;
    }

    public void Telefonar_Click (Context context, View view){
        ListView list = (ListView) view.findViewById(R.id.telefones_list);
        list.setAdapter(new DialogListAdapter(context, R.layout.list_item_telefone_dialog, estabelecimento.getTelefones()));

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, (ArrayList)linhasList);
        outState.putSerializable(KEY_ESTABELECIMENTO, estabelecimento);
    }
}
