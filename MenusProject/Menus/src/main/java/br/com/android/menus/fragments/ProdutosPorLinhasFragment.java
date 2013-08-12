package br.com.android.menus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.adapters.ProdutosPorLinhasAdapter;
import br.com.android.menus.model.Estabelecimento;
import br.com.android.menus.model.Linha;


public class ProdutosPorLinhasFragment extends BaseFragments {
    private final String KEY = "LIST_TO_PERSIST";
    private final String KEY_ESTABELECIMENTO = "ESTABELECIMENTO_TO_PERSIST";

    private Estabelecimento estabelecimento;
    private List<Linha> linhasList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_produtos, container, false);

        if (savedInstanceState == null){
            estabelecimento = (Estabelecimento) getSherlockActivity().getIntent().getSerializableExtra(EXTRA_ESTABELECIMENTO);
            linhasList = Linha.getLinhasByEstabelecimentoId(this.getSherlockActivity(), estabelecimento.getId());
        }
        else{
            estabelecimento = (Estabelecimento) savedInstanceState.getSerializable(KEY_ESTABELECIMENTO);
            linhasList = (List<Linha>) savedInstanceState.getSerializable(KEY);
        }

        this.getSherlockActivity().setTitle(estabelecimento.getName());

        ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.expandable_list_view_produtos);

        listView.setIndicatorBounds(0, 20);

        if (linhasList != null) listView.setAdapter(new ProdutosPorLinhasAdapter(this.getSherlockActivity(), linhasList));

        /*ArrayAdapter adapter = new RamoAdapter(this.getSherlockActivity(), R.layout.list_item_ramo, ramosList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ramo ramo = (Ramo) adapterView.getAdapter().getItem(i);

                startActivity(new Intent(view.getContext(), EstabelecimentosFragmentActivity.class).putExtra(EXTRA_RAMO, ramo));

            }
        });*/

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, (ArrayList)linhasList);
        outState.putSerializable(KEY_ESTABELECIMENTO, estabelecimento);
    }
}
