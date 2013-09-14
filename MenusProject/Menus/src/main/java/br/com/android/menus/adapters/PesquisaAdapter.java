package br.com.android.menus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.android.menus.R;
import br.com.android.menus.fragments.ProdutoFragment;
import br.com.android.menus.fragments.ProdutosPorLinhasFragment;
import br.com.android.menus.model.AppPesquisaItem;
import br.com.android.menus.model.AppPesquisaGroup;
import br.com.android.menus.model.Estabelecimento;
import br.com.android.menus.model.Produto;

/**
 * Created by Robson on 12/08/13.
 */
public class PesquisaAdapter extends BaseExpandableListAdapter {
    protected final String EXTRA_PRODUTO = "EXTRA_PRODUTO";
    protected final String EXTRA_ESTABELECIMENTO = "EXTRA_ESTABELECIMENTO";

    private final SherlockFragmentActivity mContext;
    List<AppPesquisaGroup> mPesquisaList;
    List<AppPesquisaGroup> mPesquisaListDefault;

    List<Object> mObjectsList;

    public PesquisaAdapter(Context context, List<AppPesquisaGroup> objects) {
        this.mContext = (SherlockFragmentActivity) context;
        this.mPesquisaList = objects;
        this.mPesquisaListDefault = new ArrayList<AppPesquisaGroup>();
        this.mPesquisaListDefault.addAll(objects);
        this.mObjectsList = new ArrayList<Object>();
    }

    @Override
    public int getGroupCount() {
        return mPesquisaList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mPesquisaList.get(i).getItemPesquisaList().getItens().size();
    }

    @Override
    public Object getGroup(int i) {
        return mPesquisaList.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return mPesquisaList.get(i).getItemPesquisaList().getItens().get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i;
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

        //ExpandableListView listView = (ExpandableListView) viewGroup;
        //listView.expandGroup(i);

        TextView groupName = (TextView) view.findViewById(R.id.groupName);

        AppPesquisaGroup pesquisaGroup = (AppPesquisaGroup) getGroup(i);

        groupName.setText(pesquisaGroup.getTitle() + " (" + getChildrenCount(i) + ")");

        return view;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {

        final Object item = getChild(i, i2);

        if (item instanceof Estabelecimento){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item_estabelecimento, viewGroup, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //context.startActivity(new Intent(view.getContext(), ProdutosFragmentsActivity.class).putExtra(EXTRA_ESTABELECIMENTO, estabelecimento));
                    mContext.getIntent().putExtra(EXTRA_ESTABELECIMENTO, (Estabelecimento) item);
                    mContext.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new ProdutosPorLinhasFragment())
                            .addToBackStack("back")
                            .commit();
                }
            });

            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(((Estabelecimento) item).getName());


            final ImageButton isFavoriteButton = (ImageButton) view.findViewById(R.id.is_favorite);
            isFavoriteButton.setClickable(true);
            if (((Estabelecimento) item).getIsFavorite()){
                isFavoriteButton.setImageResource(R.drawable.favorite_star_full);
            }
            else{
                isFavoriteButton.setImageResource(R.drawable.favorite_star_empty);
            }
            isFavoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((Estabelecimento) item).ChangeIsFavorite(mContext)){
                        if (((Estabelecimento) item).getIsFavorite()){
                            isFavoriteButton.setImageResource(R.drawable.favorite_star_full);
                        }
                        else{
                            isFavoriteButton.setImageResource(R.drawable.favorite_star_empty);
                        }
                    }
                }
            });
        }

        if (item instanceof Produto){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item_produto, viewGroup, false);

            TextView itemName = (TextView) view.findViewById(R.id.itemName);
            TextView itemDescricao = (TextView) view.findViewById(R.id.itemDescricao);
            TextView itemPreco = (TextView) view.findViewById(R.id.itemPreco);

            final Produto produto = (Produto) getChild(i, i2);
            //Linha linha = (Linha) getGroup(i);
            //Estabelecimento estabelecimento = ((Linha) getGroup(i)).getEstabelecimento();
            //produto.setLinha(linha);
            //produto.setEstabelecimento(estabelecimento);

            itemName.setText(produto.getName());
            itemDescricao.setText(produto.getDescription());
            itemPreco.setText("R$" + new DecimalFormat("0.00").format(produto.getPrice()).toString());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.getIntent().putExtra(EXTRA_PRODUTO, produto);
                    mContext.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, new ProdutoFragment())
                            .addToBackStack("back")
                            .commit();
                }
            });
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());

        mPesquisaList.clear();
        if (charText.length() > 0) {
           // mPesquisaList.addAll(mPesquisaListDefault);
        // }
        //else
        // {
            for (AppPesquisaGroup pesquisaGroup : mPesquisaListDefault)
            {

                //if (wp.getCountry().toLowerCase(Locale.getDefault()).contains(charText))
                AppPesquisaGroup newPesquisaGroup = new AppPesquisaGroup(pesquisaGroup.getTitle());
                mObjectsList = new ArrayList<Object>();
                mObjectsList.clear();

                if (pesquisaGroup.getItemPesquisaList().getItens() != null){
                    for (Object itemObject : pesquisaGroup.getItemPesquisaList().getItens()){
                        //para estabelecimentos
                        if (itemObject instanceof Estabelecimento){
                            if (newPesquisaGroup.getItemPesquisaList() == null) newPesquisaGroup.setItemPesquisaList(new AppPesquisaItem<Estabelecimento>());

                            if(((Estabelecimento) itemObject).getName().toLowerCase(Locale.getDefault()).contains(charText)){
                                mObjectsList.add(itemObject);
                            }
                        }
                        //para os produtos
                        if (itemObject instanceof Produto){
                            if (newPesquisaGroup.getItemPesquisaList() == null) newPesquisaGroup.setItemPesquisaList(new AppPesquisaItem<Produto>());

                            if(((Produto) itemObject).getName().toLowerCase(Locale.getDefault()).contains(charText)){
                                mObjectsList.add(itemObject);
                            }
                        }
                        //outro if para outros tipos
                    }
                }


                if (mObjectsList.size() > 0){
                    newPesquisaGroup.getItemPesquisaList().setItens(mObjectsList);

                    mPesquisaList.add(newPesquisaGroup);
                }

            }
        }
        notifyDataSetChanged();
    }
}
