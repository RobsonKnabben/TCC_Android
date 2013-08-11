package br.com.android.menus.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.android.menus.R;
import br.com.android.menus.app.API;
import br.com.android.menus.app.AppRestTemplate;
import br.com.android.menus.model.Estabelecimento;
import br.com.android.menus.model.Ramo;


public class MainActivity extends BaseActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //opção de voltar no menu
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.text = (TextView) findViewById(R.id.Texto);
        this.text.setText("Mudeio Texto");

        new DownloadRssFeedTask().execute();

        //startActivity(new Intent(this, RamosActivity.class));
        //finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //função da opção de voltar do menu
            //case R.id.homeAsUp:
            //    NavUtils.navigateUpFromSameTask(this);
            //    return true;
            case R.id.action_settings:
                this.text.setText("aPERTEI SETTINGS");
                startActivity(new Intent(this, RamosFragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra(EXTRA_MENU_DRAWER_OPENED, true));
                finish();
                return true;
            //mais cases para mais opções
        }
        return super.onOptionsItemSelected(item);
    }

    private class DownloadRssFeedTask extends AsyncTask<Void, String, Integer> {
        @Override
        protected void onPreExecute() {
            text.setText("inicio sync ");
            Log.e("sync--", "inicio sync");
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                // Create a new RestTemplate instance
                RestTemplate restTemplate = new AppRestTemplate();

                // Add the SyndFeed message converter to the RestTemplate instance
                restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

                // Initiate the request and return the results
                Ramo[] ramos = restTemplate.getForObject(API.getUrl(API.URL_RAMOS), Ramo[].class);
                publishProgress("sync ramo");

                for (Ramo ramo : ramos){
                    ramo.CreateOrUpdate(null);
                    publishProgress("sync ramo " + ramo.getName());
                }

                // Initiate the request and return the results
                Estabelecimento[] estabelecimentos = restTemplate.getForObject(API.getUrl(API.URL_ESTABELECIMENTOS), Estabelecimento[].class);
                publishProgress("sync estabelecimento");

                for (Estabelecimento estabelecimento : estabelecimentos){
                    estabelecimento.CreateOrUpdate(null);
                    publishProgress("sync estabelecimento " + estabelecimento.getName());
                }


                return 0;
            } catch (RestClientException e){
                publishProgress("sync erro: " + e.getMessage());
                e.printStackTrace();
                Log.d("sync--", e.getMessage(), e);

            } catch (Exception e) {
                publishProgress("sync erro: " + e.getMessage());
                Log.d("sync--", e.getMessage(), e);

            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // hide the progress indicator when the network request is complete
            if (result == 0){
                publishProgress("fim sync ");
            }
            Log.d("sync--", "fim sync ");
        }

        @Override
        protected void onProgressUpdate(String... values) {
                text.setText(values[0]);
        }
    }

}
