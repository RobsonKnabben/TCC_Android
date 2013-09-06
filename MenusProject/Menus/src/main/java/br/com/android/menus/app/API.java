package br.com.android.menus.app;

/**
 * Created by Robson on 26/07/13.
 */
public class API {

    public static  final String C_ID_WS = "id";

    private static final String BASE_URL = "http://192.168.254.2:8000/api/";

    public static final String URL_RAMOS = "ramos/";
    public static final String URL_ESTABELECIMENTOS = "estabelecimentos/";

    public static String getUrl(String url){
        return BASE_URL + url;
    }



}
