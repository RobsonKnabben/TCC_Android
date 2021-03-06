package br.com.android.menus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.fragments.ProdutosPorLinhasFragment;
import br.com.android.menus.model.Estabelecimento;

public class EstabelecimentoAdapter extends ArrayAdapter<Estabelecimento>{
    protected final String EXTRA_ESTABELECIMENTO = "EXTRA_ESTABELECIMENTO";

    private final LayoutInflater inflater;
    private final SherlockFragmentActivity context;
    private final int resourceId;

    public EstabelecimentoAdapter(Context context, int resource, List<Estabelecimento> objects) {
        super(context, resource, objects);

        this.inflater = LayoutInflater.from(context);
        this.context = (SherlockFragmentActivity) context;
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Estabelecimento estabelecimento = getItem(position);

        View viewInflater = inflater.inflate(resourceId, parent, false);
        viewInflater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(view.getContext(), ProdutosFragmentsActivity.class).putExtra(EXTRA_ESTABELECIMENTO, estabelecimento));
                    context.getIntent().putExtra(EXTRA_ESTABELECIMENTO, estabelecimento);
                    context.getSupportFragmentManager()
                            .beginTransaction()
                                .replace(R.id.content_frame, new ProdutosPorLinhasFragment())
                                .addToBackStack("back")
                            .commit();
            }
        });

        TextView name = (TextView) viewInflater.findViewById(R.id.name);
        name.setText(estabelecimento.getName());


        final ImageButton isFavoriteButton = (ImageButton) viewInflater.findViewById(R.id.is_favorite);
        isFavoriteButton.setClickable(true);
        if (estabelecimento.getIsFavorite()){
            isFavoriteButton.setImageResource(R.drawable.favorite_star_full);
        }
        else{
            isFavoriteButton.setImageResource(R.drawable.favorite_star_empty);
        }
        isFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estabelecimento.ChangeIsFavorite(context)){
                    if (estabelecimento.getIsFavorite()){
                        isFavoriteButton.setImageResource(R.drawable.favorite_star_full);
                    }
                    else{
                        isFavoriteButton.setImageResource(R.drawable.favorite_star_empty);
                    }
                }
            }
        });

        return viewInflater;
    }
}
