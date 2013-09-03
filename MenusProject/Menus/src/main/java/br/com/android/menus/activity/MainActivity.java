package br.com.android.menus.activity;

import android.app.ProgressDialog;
import android.content.Context;
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

        new AppAsyncTask(this).execute();

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
                startActivity(new Intent(this, AppFragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra(EXTRA_MENU_DRAWER_OPENED, true));
                finish();
                return true;
            //mais cases para mais opções
        }
        return super.onOptionsItemSelected(item);
    }

    private class AppAsyncTask extends AsyncTask<Void, String, Integer> {
        Context mContext;
        ProgressDialog mProgressDialog;

        public AppAsyncTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Aguarde...");
            mProgressDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try{
                // Create a new RestTemplate instance
                RestTemplate restTemplate = new AppRestTemplate();

                // Add the SyndFeed message converter to the RestTemplate instance
                restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

                publishProgress("Buscando informações...");

                // Initiate the request and return the results
                Ramo[] ramos = restTemplate.getForObject(API.getUrl(API.URL_RAMOS), Ramo[].class);

                // Initiate the request and return the results
                Estabelecimento[] estabelecimentos = restTemplate.getForObject(API.getUrl(API.URL_ESTABELECIMENTOS), Estabelecimento[].class);


                if (ramos != null && estabelecimentos != null){
                    publishProgress("Atualizando informações...");
                    for (Ramo ramo : ramos){
                        ramo.CreateOrUpdate(null);
                    }

                    for (Estabelecimento estabelecimento : estabelecimentos){
                        estabelecimento.CreateOrUpdate(null);
                    }
                }
                return 0;
            } catch (RestClientException e){
                e.printStackTrace();
                Log.d("sync--", e.getMessage(), e);
            } catch (Exception e) {
                Log.d("sync--", e.getMessage(), e);
            }
            return 1;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mProgressDialog.setMessage(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer == 0){
                mProgressDialog.dismiss();
                startActivity(new Intent(mContext, AppFragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra(EXTRA_MENU_DRAWER_OPENED, true));
                finish();
            }
            else{

            }
        }
    }
}
