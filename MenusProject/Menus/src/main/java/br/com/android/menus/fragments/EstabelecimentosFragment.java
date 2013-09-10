package br.com.android.menus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.adapters.EstabelecimentoAdapter;
import br.com.android.menus.model.Estabelecimento;
import br.com.android.menus.model.Ramo;

public class EstabelecimentosFragment extends BaseFragment {
    private final String KEY_ESTABELECIMENTOS = "LIST_ESTABELECIMENTOS_TO_PERSIST";
    private final String KEY_RAMO = "RAMO_TO_PERSIST";

    private Ramo ramo;
    private List<Estabelecimento> estabelecimentosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_estabelecimentos, container, false);

        if (savedInstanceState == null || savedInstanceState.getSerializable(KEY_RAMO) == null){
            ramo = (Ramo) getSherlockActivity().getIntent().getSerializableExtra(EXTRA_RAMO);
            estabelecimentosList = Estabelecimento.getEstabelecimentosByRamoId(this.getSherlockActivity(), ramo.getId());
        }
        else{
            ramo = (Ramo) savedInstanceState.getSerializable(KEY_RAMO);
            estabelecimentosList = (List<Estabelecimento>) savedInstanceState.getSerializable(KEY_ESTABELECIMENTOS);
        }

        final ListView list = (ListView) rootView.findViewById(R.id.listView);

        this.getSherlockActivity().setTitle(ramo.getName());

        ArrayAdapter adapter = new EstabelecimentoAdapter(this.getSherlockActivity(), R.layout.list_item_estabelecimento, estabelecimentosList);
        list.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_ESTABELECIMENTOS, (ArrayList)estabelecimentosList);
        outState.putSerializable(KEY_RAMO, ramo);
    }
}
