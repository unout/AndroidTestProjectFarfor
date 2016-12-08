package com.example.user.testproject11.support;


public class Constants {

    public static final String myLogs = "|||||||||||||||||||";

    public static final int CODE_SUCCESS = 1;
    public static final int CODE_NETWORK_ERROR = 2;
    public static final int CODE_COMMON_ERROR = 3;

    public static final String BASE_URL = "http://ufa.farfor.ru";
    public static final int CONTACT = 100;
    public static final int ITEMID1 = 88;
    public static final int ORDER1 = 88;
    public static final int ITEMID2 = 89;
    public static final int ORDER2 = 89;


    private static final String additives = "additives";
    private static final String desserts = "desserts";
    private static final String drinks = "drinks";
    private static final String partymaker = "partymaker";
    private static final String noodles = "noodles";
    private static final String sets = "set";
    private static final String rolls = "rolls";
    private static final String sushi = "sushi";
    private static final String soups = "soup";
    private static final String warm = "warm";
    private static final String snacks = "snacks";
    private static final String pizza = "pizza";
    private static final String salad = "salad";
    private static final String map =  "map";

    private static final String additivesRU = "добавки";
    private static final String dessertsRU = "десерты";
    private static final String drinksRU = "напитки";
    private static final String partymakerRU = "патимейкер";
    private static final String noodlesRU = "лапша";
    private static final String setsRU = "сеты";
    private static final String rollsRU = "роллы";
    private static final String sushiRU = "суши";
    private static final String soupsRU = "супы";
    private static final String warmRU = "теплое";
    private static final String snacksRU = "закуски";
    private static final String pizzaRU = "пицца";
    private static final String saladRU = "салаты";
    private static final String mapRU =  "карта";


    public static String translateTitle (String titleRU){
        switch (titleRU) {
            case additivesRU:  return additives;
            case dessertsRU:   return desserts;
            case drinksRU:     return drinks;
            case partymakerRU: return partymaker;
            case noodlesRU:    return noodles;
            case setsRU:       return sets;
            case rollsRU:      return rolls;
            case sushiRU:      return sushi;
            case soupsRU:      return soups;
            case warmRU:       return warm;
            case snacksRU:     return snacks;
            case pizzaRU:      return pizza;
            case saladRU:      return salad;
            case mapRU:        return map;
            default:
                return "";
        }
    }

}
