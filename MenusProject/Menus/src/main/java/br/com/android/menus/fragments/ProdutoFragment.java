package br.com.android.menus.fragments;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.adapters.RamoAdapter;
import br.com.android.menus.model.Produto;
import br.com.android.menus.model.Ramo;

public class ProdutoFragment extends BaseFragment {

    private final String KEY = "PRODUTO_TO_PERSIST";

    private Produto mProduto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_produto, container, false);

        if (savedInstanceState == null || savedInstanceState.getSerializable(KEY) == null){
            mProduto = (Produto) getSherlockActivity().getIntent().getSerializableExtra(EXTRA_PRODUTO);
        }
        else{
            mProduto = (Produto) savedInstanceState.getSerializable(KEY);
        }

        TextView productName = (TextView) rootView.findViewById(R.id.productName);
        TextView productLinha = (TextView) rootView.findViewById(R.id.productLinha);
        TextView productDescription = (TextView) rootView.findViewById(R.id.productDescription);
        TextView productPrice = (TextView) rootView.findViewById(R.id.productPrice);

        productName.setText(mProduto.getName());
        productLinha.setText(mProduto.getmLinha().getName());
        productDescription.setText(mProduto.getDescription());
        productPrice.setText("R$" + new DecimalFormat("0.00").format(mProduto.getPrice()).toString());

        getSherlockActivity().setTitle(mProduto.getEstabelecimento().getName());

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, mProduto);
    }
    
}
