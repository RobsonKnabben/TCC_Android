package br.com.android.menus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.adapters.RamoAdapter;
import br.com.android.menus.model.Ramo;

public class RamosFragment extends BaseFragment {
    private final String KEY = "LIST_RAMO_TO_PERSISTI";

    private List<Ramo> ramosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_ramos, container, false);

        final ListView list = (ListView) rootView.findViewById(R.id.listView);

        if (savedInstanceState == null || savedInstanceState.getSerializable(KEY) == null){
            ramosList = Ramo.getAllRamos(this.getSherlockActivity());
        }
        else{
            ramosList = (List<Ramo>) savedInstanceState.getSerializable(KEY);
        }

        getSherlockActivity().setTitle(R.string.title_activity_ramo);

        ArrayAdapter adapter = new RamoAdapter(this.getSherlockActivity(), R.layout.list_item_ramo, ramosList);
        if (ramosList != null) list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ramo ramo = (Ramo) adapterView.getAdapter().getItem(i);

                //startActivity(new Intent(view.getContext(), EstabelecimentosFragmentActivity.class).putExtra(EXTRA_RAMO, ramo));
                getSherlockActivity().getIntent().putExtra(EXTRA_RAMO, ramo);
                getSherlockActivity()
                         .getSupportFragmentManager()
                         .beginTransaction()
                         .replace(R.id.content_frame, new EstabelecimentosFragment())
                         .addToBackStack("back")
                         .commit();
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
