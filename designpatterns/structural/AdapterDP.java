package designpatterns.structural;

import java.util.Arrays;

/**
 * Used to work with existing system, which returns some result that needs to be
 * refined so that client can use it.
 * Basically adapting the existing functionality
 */
public class AdapterDP {
    public static void main(String[] args) {
        // if we directly get data from existing functionality
        // we might receive data in unwanted format
        WeatherReport weatherReport = new WeatherReport();
        System.out.println(weatherReport.getTemperature() + " Kelvin");

        // hence adding layer of adapter, which will convert the data in required format
        TemperatureAdapter temperatureAdapter = new TemperatureAdapterImpl();
        System.out.println(temperatureAdapter.requestTemperature() + " Celsius");

    }
}

// WeatherReport functionality
class WeatherReport {

    // returns temperature in kelvin
    public Double getTemperature(){
        return 300.00;
    }
}

// Adapter requests temperature report to existing functionality
interface TemperatureAdapter {
    Double requestTemperature();
}

// Request the temperature and performs the coversion
// so that client can use it
class TemperatureAdapterImpl implements  TemperatureAdapter {
    private WeatherReport weatherReport;

    public TemperatureAdapterImpl(){
        weatherReport = new WeatherReport();
    }

    @Override
    public Double requestTemperature() {
        Double temperatureInKelvin = weatherReport.getTemperature();
        Double temperatureInCelsius = temperatureInKelvin - 273.15;
        return temperatureInCelsius;
    }
}
