package ru.mirea.weatherdata;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDataSource {
    @Test
    public void testData(){
        DataSource ds = new DataSource();
        assertFalse(ds.connectionActive);
        ds.init();
        assertTrue(ds.connectionActive);
        /*System.out.println(ds.fetchWeather("Moscow", (float)100));
        System.out.println(ds.fetchWeather("Fryazino", (float)100));

        System.out.println(ds.fetchWeather("Moscow", (float)0));
        System.out.println(ds.fetchWeather("St. Petersburg", (float)0));
        System.out.println(ds.fetchWeather("Berlin", (float)0));
        System.out.println(ds.fetchWeather("London", (float)0));*/

        assertEquals("Sunny", ds.fetchWeather("Moscow", (float)0));
        assertEquals("Sunny", ds.fetchWeather("St. Petersburg", (float)0));
        assertEquals("Sunny", ds.fetchWeather("Berlin", (float)0));
        assertEquals("Rain", ds.fetchWeather("London", (float)0));

        ds.done();
        assertFalse(ds.connectionActive);
    }
}
