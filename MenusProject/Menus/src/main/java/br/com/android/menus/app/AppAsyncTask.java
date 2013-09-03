package br.com.android.menus.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.android.menus.activity.AppFragmentActivity;
import br.com.android.menus.activity.BaseActivity;
import br.com.android.menus.model.Estabelecimento;
import br.com.android.menus.model.Ramo;

/**
 * Created by Robson on 13/08/13.
 */
public class AppAsyncTask extends AsyncTask<Void, String, Integer> {
    protected final String EXTRA_MENU_DRAWER_OPENED = "EXTRA_MENU_DRAWER_OPENED";

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
        }
        else{

        }
    }
}
