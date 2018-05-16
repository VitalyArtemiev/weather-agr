package ru.mirea.weatherapi;

import java.util.Date;

public interface DataSourceAPI {
    public void init();
    public String fetchWeather(String location, Float t);
    public void done();
}
