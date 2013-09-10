package br.com.android.menus.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.adapters.DialogListAdapter;
import br.com.android.menus.adapters.ProdutosPorLinhasAdapter;
import br.com.android.menus.model.Estabelecimento;
import br.com.android.menus.model.Linha;


public class ProdutosPorLinhasFragment extends BaseFragment {
    private final String KEY = "LIST_TO_PERSIST";
    private final String KEY_ESTABELECIMENTO = "ESTABELECIMENTO_TO_PERSIST";

    private Estabelecimento estabelecimento;
    private List<Linha> linhasList;

    private LayoutInflater mInflater;
    private ViewGroup mConteiner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        mInflater = inflater;
        mConteiner = container;

        View rootView = inflater.inflate(R.layout.activity_produtos_por_linha, container, false);

        if (savedInstanceState == null || savedInstanceState.getSerializable(KEY_ESTABELECIMENTO) == null){
            estabelecimento = (Estabelecimento) getSherlockActivity().getIntent().getSerializableExtra(EXTRA_ESTABELECIMENTO);
            linhasList = Linha.getLinhasByEstabelecimentoId(this.getSherlockActivity(), estabelecimento.getId());
            for (Linha linha : linhasList){
                linha.setEstabelecimento(estabelecimento);
            }
        }
        else{
            estabelecimento = (Estabelecimento) savedInstanceState.getSerializable(KEY_ESTABELECIMENTO);
            linhasList = (List<Linha>) savedInstanceState.getSerializable(KEY);
        }

        this.getSherlockActivity().setTitle(estabelecimento.getName());

        ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.expandable_list_view_produtos);

        listView.setIndicatorBounds(0, 35);

        if (linhasList != null){
            ProdutosPorLinhasAdapter adapter = new ProdutosPorLinhasAdapter(this.getSherlockActivity(), linhasList);
            listView.setAdapter(adapter);
            for(int i=0; i < adapter.getGroupCount(); i++)
                listView.expandGroup(i);
        }


        ImageButton telefonar = (ImageButton) rootView.findViewById(R.id.telefonar);
        telefonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Telefonar_Click(v.getContext());
            }
        });

        return rootView;
    }

    public void Telefonar_Click (Context context){
        View view = mInflater.inflate(R.layout.dialog_telefones, mConteiner, false);

        ListView list = (ListView) view.findViewById(R.id.telefones_list);

        if (estabelecimento.getTelefones() != null){
            list.setAdapter(new DialogListAdapter(context, R.layout.list_item_telefone_dialog, estabelecimento.getTelefones()));

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(view);
            Dialog dialog = builder.create();
            dialog.show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, (ArrayList)linhasList);
        outState.putSerializable(KEY_ESTABELECIMENTO, estabelecimento);
    }
}
