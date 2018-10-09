/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*; // IOException
import java.net.MalformedURLException;
import java.util.*; // Scanner
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;

/**
 *
 * @author piotr_000
 */
public class Coin {

    final private String urlBegin = "https://min-api.cryptocompare.com/data/generateAvg?fsym=";
    final private String urlEnd = "&tsym=USD&e=Bitfinex";
    private String urlFull;
    private String name;
    private String price;
    private String volume24h;
    private String open24h;
    private String high24;
    private String low24h;

    public Coin(String name) {
        this.name = name;
        urlFull = urlBegin + name + urlEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(String volume24h) {
        this.volume24h = volume24h;
    }

    public String getOpen24h() {
        return open24h;
    }

    public void setOpen24h(String open24h) {
        this.open24h = open24h;
    }

    public String getHigh24() {
        return high24;
    }

    public void setHigh24(String high24) {
        this.high24 = high24;
    }

    public String getLow24h() {
        return low24h;
    }

    public void setLow24h(String low24h) {
        this.low24h = low24h;
    }

    public String GETAPI() throws IOException {
        URL urlObj = new URL(urlFull);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        // read all the lines of the response into response StringBuffer
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }

        bufferedReader.close();

        // if you don't want to use Gson, you can just print the plain response
        //System.out.println(response.toString());
        // print result in nice format using the Gson library
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.toString());
        String prettyJsonResponse = gson.toJson(je);

        String[] small = prettyJsonResponse.split(",");
        String newResponse = "";
//        for (int i = 0; i < small.length; i++) {
//            System.out.println(i + " " + small[i]);
//        }
        
        setPrice(small[4]);
        setVolume24h(small[9]);
        setOpen24h(small[11]);
        setHigh24(small[12]);
        setLow24h(small[13]);
        
        newResponse = small[1].replace("FROMSYMBOL", "NAME") + " " + getPrice() + getVolume24h() + getOpen24h() + getHigh24() + getLow24h();
        newResponse = newResponse.replaceAll("\"", " ");
        newResponse = newResponse.replaceAll(" ", "");

        return newResponse;
    }

}
