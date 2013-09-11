package br.com.android.menus.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.android.menus.R;
import br.com.android.menus.adapters.PesquisaAdapter;
import br.com.android.menus.adapters.ProdutosPorLinhasAdapter;
import br.com.android.menus.model.AppPesquisaGroup;


public class PesquisaFragment extends BaseFragment {
    final String KEY = "LIST_PESQUISA_TO_PERSIST";

    List<AppPesquisaGroup> pesquisaList;
    List<AppPesquisaGroup> pesquisaListDefault;

    ExpandableListView listView;
    EditText txtPesquisa;
    PesquisaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_pesquisa, container, false);


        if (savedInstanceState == null || savedInstanceState.getSerializable(KEY) == null){
            pesquisaList = getAppSingleton().getDefaultPesquisaList();
            pesquisaListDefault = pesquisaList;
        }
        else{
            pesquisaList = (List<AppPesquisaGroup>) savedInstanceState.getSerializable(KEY);
        }

        getSherlockActivity().setTitle(R.string.pesquisa_fragment_name);

        txtPesquisa = (EditText) rootView.findViewById(R.id.txtPesquisa);
        txtPesquisa.setHint("Pesquisar conteúdo");
        txtPesquisa.setText("");
        txtPesquisa.addTextChangedListener(textWatcher);
        txtPesquisa.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(txtPesquisa.getWindowToken(), 0);
                }
            }
        });


        listView = (ExpandableListView) rootView.findViewById(R.id.listViewPesquisa);

        listView.setIndicatorBounds(0, 35);

        if (pesquisaList != null){
            adapter = new PesquisaAdapter(this.getSherlockActivity(), pesquisaList);
            adapter.filter("---------");
            listView.setAdapter(adapter);
            for(int i=0; i < adapter.getGroupCount(); i++)
                listView.expandGroup(i);
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, (ArrayList)pesquisaListDefault);
    }

    // EditText TextWatcher
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            String text = txtPesquisa.getText().toString().toLowerCase(Locale.getDefault());
            adapter.filter(text);
            for(int i=0; i < adapter.getGroupCount(); i++)
                listView.expandGroup(i);
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            // TODO Auto-generated method stub

        }

    };


}
