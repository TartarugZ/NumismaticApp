package com.example.coursework;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.coursework.Launch.linkOnMainPageUcoin;

public class CoinSearcher {


    private Document mainPageDoc; //html код главной страницы сайта en.ucoin.net


    static private ArrayList<String> countries; //список со всеми странами

    private HashMap<String,CountryInformation> infoAboutCountrys =new HashMap<>(); // содержит в себе название страны и объект CountryInformation, в котором хранится информация о стране
    //черная заготовка кэша

    public CoinSearcher() throws IOException {  //отвечает за подгрузку нужной инфы для отпимизации поиска

        mainPageDoc =Jsoup.connect(linkOnMainPageUcoin).get(); // получает главную страницу сайта ucoin из html кода которой будет извлечаться информация
        getCountries();                                               //подгружает страны в список countries в  пользовательском виде


    }

    static public ArrayList<String> getCountry(){
        return countries;
    }


    public void getCountries() throws IOException { //отвечает за получение списка стран с сайта ucoin

        Elements timeVar = mainPageDoc.getElementsByAttributeValue("class","wrap nopad");
        countries =new ArrayList<>(timeVar.eachText()); //получаем список стран в пользовательском виде

        if(countries==null){System.out.println("Error parse countries"); };



    }


    public  void smartCoutrySelection(String country){

        System.out.println("im working");

    }



    public String getCounrtyLink(String requiredCountry){  //возвращает нужную часть http ссылки на определенную страну
        // название стран для пользователей и их название в http ссылке отличается

        Elements  listOfCountries = mainPageDoc.getElementsByAttributeValue("class","country");


        for(Element oneComparedCountry: listOfCountries){

            if(requiredCountry.equals(
                    oneComparedCountry
                            .getElementsByAttributeValue("class","wrap nopad")
                            .text())
            ) {
                return  oneComparedCountry.select("a[href]").attr("href");
            }


        }

        return "Not found, something wrong";

    }




    public void getInfoAboutCountry(String country) throws IOException {

        String partOfLinkCountry = getCounrtyLink(country); // получает часть ссылки на страну
        String correctCountryName = partOfLinkCountry.substring(18); // извлекает из ссылки название страны для http запрсов и более удобного сравнивания в html коде





        Element htmlCountryPeriods = mainPageDoc.selectFirst("[data-code="+correctCountryName+"]"); // получает html код, внутри которого информация о периодах в запрашиваемой стране с ссылками
        Elements countryPeriods = htmlCountryPeriods.getElementsByAttributeValue("class","period");

        infoAboutCountrys.put(country,new CountryInformation(countryPeriods));    // кладет информацию в мап,


       /*infoAboutCountry.put(correctCountryName ,
                                               Jsoup.connect("https://en.ucoin.net"+partOfLinkCountry)
                                                                                                                 .get());
        String adasd= String.valueOf(infoAboutCountry.get(correctCountryName)
                                                     .getElementsByAttributeValue("class","switcher active")
                                                     .attr("href"));
        System.out.println(adasd);
        infoAboutCountry.put(correctCountryName ,
                                               Jsoup.connect("https://en.ucoin.net/table/"+replaceAmpersand(adasd))
                                                                                                                        .get());

        */











    }



    public void testgetCounrtyLink() throws IOException {



        getInfoAboutCountry("Германия");


    }


    public void testCountries(Elements countries) {

        System.out.println(countries.size());

        countries.forEach(scope->{

            String rwer=scope.text();

            System.out.print(rwer+", ");


        });
        System.out.println();

    }


}
