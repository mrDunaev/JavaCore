package lesson7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Locale;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    //http://dataservice.accuweather.com/forecasts/v1/daily/5day/349727
    private static final String PROTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "2uudJm5EU7Gj0XH3ppmlnsfMBSGr3I5M";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LANGUAGE = "ru-RU";
    private static final String LANGUAGE_QUERY_PARAM = "language";
    private static final String METRIC = "true";
    private static final String METRIC_QUERY_PARAM = "metric";
    private static final String CITY_QUERY_PARAM = "q";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather(String selectedCity, Period period) throws IOException {
        String cityKey = detectCityKey(selectedCity);
        if (cityKey.isEmpty()) return;
        switch (period) {
            case NOW:
                HttpUrl urlForOneDay = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(cityKey)
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                        .addQueryParameter(METRIC_QUERY_PARAM, METRIC)
                        .build();

                Request requestForOneDay = new Request.Builder()
                        .url(urlForOneDay)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(requestForOneDay).execute();
                String weatherOneDayResponse = oneDayForecastResponse.body().string();
                String weatherOneDay = parseWeatherResponse(weatherOneDayResponse, period);
                System.out.println(weatherOneDay);
                break;
            case FIVE_DAYS:
                HttpUrl urlForFiveDays = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(cityKey)
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                        .addQueryParameter(METRIC_QUERY_PARAM, METRIC)
                        .build();

                Request requestForFiveDays = new Request.Builder()
                        .url(urlForFiveDays)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(requestForFiveDays).execute();
                String weatherFiveDaysResponse = fiveDaysForecastResponse.body().string();
                String weatherFiveDays = parseWeatherResponse(weatherFiveDaysResponse, period);
                System.out.println(weatherFiveDays);
                break;
        }
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter(CITY_QUERY_PARAM, selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = new String();
        try {
            cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        } catch (NullPointerException e) {
            System.out.println("Город не найден");
        }
        return cityKey;
    }

    private String parseWeatherResponse (String weatherResponse, Period period) throws JsonProcessingException {
        int daysNumber = 0;
        StringBuilder weather = new StringBuilder();

        switch (period) {
            case NOW:
                daysNumber = 1;
                break;
            case FIVE_DAYS:
                daysNumber = 5;
                break;
        }

        for (int i = 0; i < daysNumber; i++) {
            String date = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i).at("/Date").asText().split("T")[0];
            String dayPrecipitation = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i).at("/Day/IconPhrase").asText().toLowerCase(Locale.ROOT);
            String dayTemperature = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i).at("/Temperature/Maximum/Value").asText();
            String dayUnit = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i).at("/Temperature/Maximum/Unit").asText();
            String nightPrecipitation = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i).at("/Night/IconPhrase").asText().toLowerCase(Locale.ROOT);
            String nightTemperature = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i).at("/Temperature/Minimum/Value").asText();
            String nightUnit = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i).at("/Temperature/Minimum/Unit").asText();

            if (i != 0) weather.append("\n");
            weather.append("Погода на ").append(date);
            weather.append(": Днём ").append(dayPrecipitation);
            weather.append(", температура ").append(dayTemperature).append(" ").append(dayUnit);
            weather.append(". Ночью ").append(nightPrecipitation);
            weather.append(", температура ").append(nightTemperature).append(" ").append(nightUnit);
        }

        return weather.toString();
    }
}