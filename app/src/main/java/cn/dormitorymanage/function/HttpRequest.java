package cn.dormitorymanage.function;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class HttpRequest {

    public static void postJSONArray(String link, JSONObject jsonObject, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        String links = "http://" + MyApplication.ipLocation + "/dormitoryManage/" + link;
        JsonArrayRequest request = new JsonArrayRequest(1, links, jsonObject, listener, errorListener);
        MyApplication.queue.add(request);
    }

    public static void postJSONObject(String link, JSONObject jsonObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String links = "http://" + MyApplication.ipLocation + "/dormitoryManage/" + link;
        MyJsonRequest request = new MyJsonRequest(1, links, jsonObject, listener, errorListener);
        MyApplication.queue.add(request);
    }

    public static class MyJsonRequest extends JsonObjectRequest {

        public MyJsonRequest(int i, String links, JSONObject jsonObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(1, links, jsonObject, listener, errorListener);
        }
        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
        {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(new String(response.data,"UTF-8"));
                return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return Response.error(new ParseError(e));
            } catch (JSONException e) {
                e.printStackTrace();
                return Response.error(new ParseError(e));
            }
        }
    }
}
