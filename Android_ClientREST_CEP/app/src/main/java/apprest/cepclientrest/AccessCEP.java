package apprest.cepclientrest;

/**
 * Created by Sayonara on 08/06/2017.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccessCEP extends AsyncTask<String, Void, String> {

    private TextView textView;

    public AccessCEP(TextView info) {
        this.textView = info;
    }

    protected String doInBackground(String... params) {
        try {
            String link = (String) params[0];
            URL url = new URL(link);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = null;
            String content = "";

            while ((data = reader.readLine()) != null) {
                content += data + "\n";
            }

            Log.v("web",content);

            JSONObject jsonObject = new JSONObject(content);
            String logradouro = jsonObject.getString("logradouro");
            String bairro = jsonObject.getString("bairro");
            String localidade = jsonObject.getString("localidade");
            String uf = jsonObject.getString("uf");

            return "Logradouro: "+logradouro+"\nBairro: "+bairro+"\nLocalidade: "+localidade+", "+uf;
        }
        catch (Exception e) {
            Log.e("Exception: ", e.getMessage());
            return "Endereço não encontrado!";
        }
    }

    protected void onProgressUpdate(String result) {}

    protected void onPostExecute(String result) {
        this.textView.setText(result);
    }
}