package br.com.android.menus.app;

import android.util.Log;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class AppRestTemplate extends RestTemplate {
    public AppRestTemplate() {
        if (getRequestFactory() instanceof SimpleClientHttpRequestFactory) {
            Log.d("HTTP", "HttpUrlConnection is used");
            ((SimpleClientHttpRequestFactory) getRequestFactory()).setConnectTimeout(5 * 1000);
            ((SimpleClientHttpRequestFactory) getRequestFactory()).setReadTimeout(5 * 1000);
        } else if (getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory) {
            Log.d("HTTP", "HttpClient is used");
            ((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setReadTimeout(5 * 1000);
            ((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setConnectTimeout(5 * 1000);
        }
    }
}
