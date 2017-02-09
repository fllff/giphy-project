package com.example.hn.imagesearchforgiphy.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by hn on 2017-02-08.
 */

public class GiphyModel {
    private StringBuffer bufferResponse;

    public GiphyModel() {
    }

    /**
     * url:String to giphy-result:List
     */
    public List<GiphyItem> request(String requestUrl){
        bufferResponse = new StringBuffer();

        HttpThread getHttpRespondThread = new HttpThread(requestUrl);
        try {
            getHttpRespondThread.start();
            getHttpRespondThread.join(); // wait for thread
        }catch (Exception e) {}

        GiphyParser giphyParser = new GiphyParser();
        String s = bufferResponse.toString();
        List<GiphyItem> giphyItemList = giphyParser.parse(s);

    return giphyItemList;
    }

    /**
     * network Thread
     */
    private class HttpThread extends Thread {
        private String url;

        HttpThread(String url) {
            this.url = url;
        }

        @Override
        public synchronized void run() {
            try {
                URL httpURL = new URL(url);

                HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    String line = null;

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while((line = reader.readLine()) != null) {
                        bufferResponse.append(line+"\n");
                    }

                    reader.close();
                    conn.disconnect();
                }
            } catch(MalformedURLException e){
               // Toast.makeText(context,"err toast",Toast.LENGTH_SHORT).show();
            } catch(IOException e){
                // Toast.makeText(context,"err toast",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
