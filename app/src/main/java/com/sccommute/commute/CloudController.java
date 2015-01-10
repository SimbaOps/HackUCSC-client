package com.sccommute.commute;

import android.util.Log;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by simba on 1/10/15.
 */
public class CloudController {
    private String url;

    public CloudController(String url) {
        this.url = url;
    }

    private static String httpPostCall(String urlName, ParamPairs params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            PrintWriter stream = new PrintWriter(connection.getOutputStream());
            String encodedParams = params.getPairsEncoded();
            stream.write(encodedParams);
            stream.close();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return new String(out.toByteArray());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            connection.disconnect();
        }
    }

    private static String httpGetCall(String urlName) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlName);
            connection = (HttpURLConnection) url.openConnection();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return new String(out.toByteArray());
        }catch(Exception e){
            Log.e("TAG", "" + e.getMessage());
            e.printStackTrace();
            return "";
        }finally{
            try {
                connection.disconnect();
            } catch(Exception e) {
                e.printStackTrace();
                Log.e("TAG", "FAILED");
            }
        }
    }

    public void LogLocations() {
        Log.e("TAG", httpGetCall(url + "/location/get"));
    }

    public JSONArray getLocations() throws Exception{
        return new JSONArray(httpGetCall(url + "/location/get"));
    }
}