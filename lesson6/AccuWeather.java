package lesson6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AccuWeather {
    public static void main(String[] args) throws IOException {
        String apiKey = "u38jktG8BRuT1YdJRg07wiX6L1IGtRD5";
        String city = "Saint Petersburg";

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        HttpUrl urlLocation = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("/locations/v1/cities/RU/search")
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("q", city)
                .build();

        Request requestLocation = new Request.Builder()
                .url(urlLocation)
                .get()
                .build();

        Response responseLocation= okHttpClient.newCall(requestLocation).execute();

        String location = responseLocation.body().string();
        location = location.split("[:,]")[3];
        location = location.replace("\"", "");
        System.out.println("Code of " + city + ": " + location);

        HttpUrl urlForecast = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("/forecasts/v1/daily/1day")
                .addPathSegment(location)
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("language", "ru-RU")
                .addQueryParameter("metric", "true")
                .build();

        Request requestForecast = new Request.Builder()
                .url(urlForecast)
                .get()
                .build();

        Response responseForecast = okHttpClient.newCall(requestForecast).execute();

        String forecast = responseForecast.body().string();
        forecast = forecast.replace(",", ",\n");
        forecast = forecast.replace("{", "{\n");

        //System.out.println(responseForecast.code());
        System.out.println("Weather in " + city + ":");
        System.out.println(forecast);
    }
}
