package ie.jbmnetworks.pubgo;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by  hannah on 05/04/2017.
 */


public class Drinks extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://pubgo-jackbourkemckenna.c9users.io/app/drinksApp.php";


    public Drinks(int method, int url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);

        params.put("name", name);
        params.put("price", price);
        params.put("drink_id", drink_id);
        params.put("type", type);
        params.put("pub_id", pub_id);
    }
}


