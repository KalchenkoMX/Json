package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {

        Weather weather = new Weather();
        System.out.println(weather.getWeatherData(""));
    }
}

class Weather{

    private static String Base_URL = "http://api.openweathermap.org/data/2.5/forecast/city?q=cherkassy&APPID=b31cf2036a89c4d27c457418e408602e";
    public String getWeatherData(String location) throws IOException {
        HttpURLConnection con = null ;
        InputStream is = null;
      try {
          con = (HttpURLConnection) (new URL(Base_URL + location)).openConnection();
          con.setRequestMethod("GET");
          con.setDoInput(true);
          con.setDoOutput(true);
          con.connect();
        StringBuffer buffer = new StringBuffer();
        is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
          String line = null;
          while ( (line = br.readLine()) != null )
              buffer.append(line + "rn");
          is.close();
          con.disconnect();
          return buffer.toString();
      }
      catch(Throwable t) {
          t.printStackTrace();
      }
      finally {
          try { is.close(); } catch(Throwable t) {}
          try { con.disconnect(); } catch(Throwable t) {}
      }
        return null;
  }
    }
