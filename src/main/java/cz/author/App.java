package cz.author;

import com.jayway.jsonpath.JsonPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class App
{
    public static final String DATA_URL = "http://jsonvat.com";

    public static List<Country> filterCountries(String data, FilterMethod method){
        List<Map<String,String>> countries = JsonPath.read(data,"$.rates");
        List<Double> lastRates = JsonPath.read(data, "$.rates[*].periods[0].rates.standard");
        Set<Double> ratesSet = new HashSet<>(lastRates);
        List<Country> filteredCountries = new ArrayList<>();
        double rate = getRate(ratesSet,method);

        for(Map<String,String> country: countries){
            Country c = new Country(country);
            if((method==FilterMethod.Lower&&c.getStdRate()<=rate)||(method==FilterMethod.Upper&&c.getStdRate()>=rate))
            {
                filteredCountries.add(c);
            };
        }
        return filteredCountries;
    }

    private static double getRate(Set<Double> setRates, FilterMethod method){
        List<Double> values = Arrays.asList(setRates.toArray(new Double[setRates.size()]));
        int index = 0;

        switch (method){
            case Upper: index = setRates.size() - 3;
            break;
            case Lower: index = 2;
            break;
        }
        return values.get(index);
    }

    public static String getData(String data_url) throws IOException {
        URL url = new URL(data_url);
        String data;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            data = br.readLine();
        }
        return data;
    }

    public static void main( String[] args ) throws IOException {

        String json = getData(DATA_URL);

        List<Country> upperSet = filterCountries(json,FilterMethod.Upper);
        List<Country> lowerSet = filterCountries(json,FilterMethod.Lower);

        System.out.println(upperSet.toString());
        System.out.println(lowerSet.toString());
    }
}
