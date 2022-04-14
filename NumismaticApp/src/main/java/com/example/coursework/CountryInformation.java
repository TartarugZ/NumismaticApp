package com.example.coursework;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CountryInformation {                           // содержит информацию о стране

    public ArrayList<CountryPeriod> periods;                 // список с периодами страны

    public CountryInformation(Elements countryPeriods) throws IOException {

        periods = new ArrayList<>();

        countryPeriods.forEach(period->{                   //на каждый период создается свой объект класса Period

            try {
                periods.add(new CountryPeriod(period));
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        periods.get(1).setCurrenciesAndNominalValues(); //получает список валют самого современного периода страны

        System.out.println(periods.get(1).getNominalValues());
        System.out.println(periods.get(1).getCurrencies());



    }


}

