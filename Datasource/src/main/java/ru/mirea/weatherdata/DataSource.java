package ru.mirea.weatherdata;

import ru.mirea.weatherapi.DataSourceAPI;

import java.util.Date;
import java.util.Random;

import static java.lang.Math.abs;

public class DataSource implements DataSourceAPI {

    public boolean connectionActive;

    Random r;

    @Override
    public void init() {
        r = new Random();
        r.setSeed(0);
        connectionActive = true;
    }

    @Override
    public String fetchWeather(String location, Float t) {
        String result = "";

        int i = (location + String.valueOf(t)).hashCode() % 4;

        switch (abs(i)) {
            case 0: result = "Sunny"; break;
            case 1: result = "Cloudy"; break;
            case 2: result = "Rain"; break;
            case 3: result = "Snow"; break;
        }
        return result;
    }

    @Override
    public void done() {
        connectionActive = false;

    }
}
