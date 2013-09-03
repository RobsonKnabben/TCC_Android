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


public class EstabelecimentosFavoritosFragment extends BaseFragments {
    private final String KEY_ESTABELECIMENTOS = "LIST_ESTABELECIMENTOS_TO_PERSIST";

    private List<Estabelecimento> estabelecimentosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_estabelecimentos, container, false);

        if (savedInstanceState == null || savedInstanceState.getSerializable(KEY_ESTABELECIMENTOS) == null){
            estabelecimentosList = Estabelecimento.getEstabelecimentosFavoritos(this.getSherlockActivity());
        }
        else{
            estabelecimentosList = (List<Estabelecimento>) savedInstanceState.getSerializable(KEY_ESTABELECIMENTOS);
        }

        getSherlockActivity().setTitle(R.string.title_activity_estabelecimentos_favoritos_fragment);

        final ListView list = (ListView) rootView.findViewById(R.id.listView);

        if (estabelecimentosList != null){
            ArrayAdapter adapter = new EstabelecimentoAdapter(this.getSherlockActivity(), R.layout.list_item_estabelecimento, estabelecimentosList);
            list.setAdapter(adapter);
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_ESTABELECIMENTOS, (ArrayList)estabelecimentosList);
    }
}
