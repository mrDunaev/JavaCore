package lesson8;

import lesson8.entity.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository {
    private final String insertWeather = "insert into weather (city, localdate, temperature) values (?, ?, ?)";
    private final String getWeather = "select distinct city, localdate, temperature from weather where city = ?";
    private static final String DB_PATH = "jdbc:sqlite:weather.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveWeatherToDataBase(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getLocalDate());
            saveWeather.setDouble(3, weather.getTemperature());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Сохранение погоды в базу данных не выполнено!");
    }

    public List<Weather> getSavedToDBWeather(String selectedCity) {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement selectWeather = connection.prepareStatement(getWeather);
            selectWeather.setString(1, selectedCity);
            ResultSet resultSet = selectWeather.executeQuery();
            while (resultSet.next()) {
                weathers.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("localdate"),
                        resultSet.getDouble("temperature")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weathers;
    }
}