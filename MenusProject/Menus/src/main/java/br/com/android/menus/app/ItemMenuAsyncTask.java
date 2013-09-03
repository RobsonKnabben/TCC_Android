package br.com.android.menus.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import br.com.android.menus.R;
import br.com.android.menus.activity.BaseFragmentsActivity;
import br.com.android.menus.model.AppMenuItem;

public class ItemMenuAsyncTask extends AsyncTask<Void, Integer, Integer> {

    BaseFragmentsActivity mContext;
    AppMenuItem mItem;
    ProgressDialog mProgressDialog;

    public ItemMenuAsyncTask(BaseFragmentsActivity context, AppMenuItem item) {
        this.mContext = context;
        this.mItem = item;
    }

    @Override
    protected void onPreExecute() {
        //mProgressDialog = new ProgressDialog(mContext);
        //mProgressDialog.setIndeterminate(true);
        //mProgressDialog.setMessage("Aguarde...");
        //mProgressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        if (mItem.getFragment() != null){
            if (mItem.isTopFragment()){
                for(int i = 0; i < mContext.getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                    mContext.getSupportFragmentManager().popBackStack();
                }
                mContext.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mItem.getFragment()).commit();
            }
            else{
                mContext.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mItem.getFragment()).addToBackStack("back").commit();
            }

        }
        else if (mItem.getIntent() != null){
            mContext.startActivity(mItem.getIntent());
            if (mItem.ExecuteFinish()){
                mContext.finish();
            }
        }
        return 1;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        mProgressDialog.dismiss();
    }
}
