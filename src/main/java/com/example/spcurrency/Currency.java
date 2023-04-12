package com.example.spcurrency;
import com.google.gson.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;


public class Currency  {

    private String url_str;
    public Currency(String baseCode){
        url_str = "https://v6.exchangerate-api.com/v6/2dcb5fc45f23bdbd865dab78/latest/" + baseCode;
    }

    public String getUrl_str() {
        return url_str;
    }
    public void setUrl_str(String url_str){
        this.url_str = url_str;
    }

    public String convertTo(String currency) throws IOException{
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        JsonElement rates = jsonobj.get("conversion_rates");
        JsonObject ratesObj = rates.getAsJsonObject();
        String req_result = ratesObj.get(currency).getAsString();
        return req_result;
    }

    public String lotsConvert(String strFromConverTo, String strFromQuantity ){
        float fromConverTo = Float.parseFloat(strFromConverTo);
        int fromQuantity = Integer.parseInt(strFromQuantity);
        float sum = fromConverTo * fromQuantity;
        String strSum = Float.toString(sum);
        return strSum;
    }
}

