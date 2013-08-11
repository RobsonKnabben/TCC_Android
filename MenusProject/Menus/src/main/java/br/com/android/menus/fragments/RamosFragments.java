package br.com.android.menus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.activity.EstabelecimentosFragmentActivity;
import br.com.android.menus.adapters.RamoAdapter;
import br.com.android.menus.model.Ramo;

public class RamosFragments extends BaseFragments {
    private final String KEY = "LIST_RAMO_TO_PERSISTI";

    private List<Ramo> ramosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_ramos, container, false);

        final ListView list = (ListView) rootView.findViewById(R.id.listView);

        if (savedInstanceState == null){
            ramosList = Ramo.getAllRamos(this.getSherlockActivity());
        }
        else{
            ramosList = (List<Ramo>) savedInstanceState.getSerializable(KEY);
        }

        ArrayAdapter adapter = new RamoAdapter(this.getSherlockActivity(), R.layout.list_item_ramo, ramosList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ramo ramo = (Ramo) adapterView.getAdapter().getItem(i);

                startActivity(new Intent(view.getContext(), EstabelecimentosFragmentActivity.class).putExtra(EXTRA_RAMO, ramo));

            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, (ArrayList)ramosList);
    }
}
