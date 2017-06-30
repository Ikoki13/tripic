package at.tripic.helpers;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class VolleyHelper {

    private static VolleyHelper instance = null;
    private static RequestQueue requestQueue = null;

    private VolleyHelper() {
    }

    private VolleyHelper(Context currentContext) {
        requestQueue = Volley.newRequestQueue(currentContext);
    }

    public static VolleyHelper getInstance(Context currentContext) {
        if(instance == null)
            instance = new VolleyHelper(currentContext);

        return instance;
    }

    public <T> void addRequestToQueue(Request<T> newRequest) {
        requestQueue.add(newRequest);
    }

    public StringRequest createVolleyRequest(String url, int method, Response.Listener<String> success, Response.ErrorListener error) {
        return new StringRequest(method, url, success, error);
    }

    public JsonObjectRequest createJsonObjectRequest(String url, int method, JSONObject jsonRequest, Response.Listener<JSONObject> success, Response.ErrorListener error) {
        return new JsonObjectRequest(method, url, jsonRequest, success, error);
    }
}
