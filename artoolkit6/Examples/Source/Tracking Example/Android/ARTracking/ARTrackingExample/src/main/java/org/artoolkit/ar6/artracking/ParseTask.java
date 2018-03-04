package org.artoolkit.ar6.artracking;


import android.os.AsyncTask;

import org.artoolkit.ar6.artracking.model.Route;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ParseTask extends AsyncTask<Void, Void, String> {

    private final int GET_POPULAR_ROUTES = 0;
    private final int POST_USER_CHOOSE = 1;
    private final int GET_NEXT_VERTEX = 2;
    private final int GET_COLOR = 3;
    private static Random random = new Random();

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";
    private URL urlC;
    private int type;

    public ParseTask(String url, int type){
        this.type = type;
        try{
            urlC = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        // получаем данные с внешнего ресурса
        try {
            urlConnection = (HttpURLConnection) urlC.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);
        // выводим целиком полученную json-строку
        if (type == GET_POPULAR_ROUTES) {
            JSONArray dataJsonObj = null;

            try {
                List<Route> routes = new ArrayList<>();
                dataJsonObj = new JSONArray(strJson);
                String s = "";

                for (int i = 0; i < dataJsonObj.length(); i++){
                    JSONObject json = dataJsonObj.getJSONObject(i);
                    String desc = json.getString("description");
                    // TODO: add route
                    routes.add(new Route(desc));
                }

               // Handler.setRoutes(routes);
                // TODO: add

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type == GET_NEXT_VERTEX) {
            JSONObject dataJsonObj = null;

            try {
                dataJsonObj = new JSONObject(strJson);

                String action = dataJsonObj.getString("action");
                if (action.equals("vertex")){
                    Integer vertex = dataJsonObj.getInt("vertex");

                    MainActivity.setText("Vertex " + vertex.toString());
                    // TODO: vertex
                } else if (action.equals("color")){
                    // TODO: color
                    Integer color = dataJsonObj.getInt("color");
                    MainActivity.setText("Color " + color.toString());
                } else if (action.equals("exit")){
                    // TODO: exit
                    MainActivity.setText("Exit");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (type == GET_COLOR) {
            JSONObject dataJsonObj = null;

            try {
                dataJsonObj = new JSONObject(strJson);

                String action = dataJsonObj.getString("type");
                if (action.equals("color")) {
                    // TODO: color
                    Integer color = dataJsonObj.getInt("color");
                    MainActivity.setText("Color " + color.toString());
                } else if (action.equals("error")) {
                    // TODO: exit
                    MainActivity.setText("Error");
                }

            } catch (JSONException e) {

            }
        } else if (type == POST_USER_CHOOSE){
            MainActivity.setText("Done");
        }
    }
}
