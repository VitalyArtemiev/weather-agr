package ru.mirea.weatherservice;

import ru.mirea.weatherdata.DataSource;

import java.util.Date;
import java.util.NoSuchElementException;
//import java.util.concurrent.ThreadSafeQueue;

import static java.lang.Thread.sleep;

class WeatherTask {
    Float time;
    String city;
    int ID;

    WeatherTask(Float t, String c, int tid) {
        time = t;
        city = c;
        ID = tid;
    }
}

class WeatherResult {
    int ID;
    String weather;
}

public class WeatherService {
    ThreadSafeQueue<WeatherTask> input;
    ThreadSafeQueue<WeatherResult> output;

    DataSource src;

    boolean active;
    int taskID;

    class ServiceThread implements Runnable {
        @Override
        public void run() {
            while (active) {
                WeatherTask wt;
                try {
                    wt = input.remove();
                }
                catch (NoSuchElementException e) {
                    continue;
                }

                WeatherResult wr = new WeatherResult();
                wr.ID = wt.ID;
                wr.weather = "Weather in " + wt.city + ": " + src.fetchWeather(wt.city, wt.time);

                //pretending to fetch weather
                try {
                    sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                output.add(wr);
            }
        }
    }

    public WeatherService() {
        input = new ThreadSafeQueue<>();
        output = new ThreadSafeQueue<>();

        src = new DataSource();

        src.init();

        //System.out.println("ws");
        active = true;
        ServiceThread st = new ServiceThread();
        new Thread(st).start();
        new Thread(st).start();
        new Thread(st).start();
        new Thread(st).start();
    }

    public void AddTask(Float t, String c) {
        //System.out.println("add");
        input.add(new WeatherTask(t, c, taskID));
        taskID++;
    }

    public void shutDown(){
        active = false;
        src.done();
    }
}
