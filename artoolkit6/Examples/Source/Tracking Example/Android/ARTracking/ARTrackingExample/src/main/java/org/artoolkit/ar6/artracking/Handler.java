package org.artoolkit.ar6.artracking;

import java.util.Random;

public class Handler {
    private static final int GET_POPULAR_ROUTES = 0;
    private static final int POST_USER_CHOOSE = 1;
    private static final int GET_NEXT_VERTEX = 2;
    private static final int GET_COLOR = 3;

    private static Random random = new Random();
    private static final Integer user_id = Math.abs(random.nextInt());

    private static String urlSelectRoute = "http://172.19.245.212:8080/Myapp/selectroute?used_id=" + user_id.toString() + "&pos=0";
    private static String urlUserChoose = "http://172.19.245.212:8080/Myapp/userchoose?user_id=" + user_id.toString() + "&route=";
    private static String urlNextVer = "http://172.19.245.212:8080/Myapp/nextver?user_id=" + user_id.toString();
    private static String urlGetColor = "http://172.19.245.212:8080/Myapp/getcolor?color=";

    public static void selectRoute(){
        new ParseTask(urlSelectRoute, GET_POPULAR_ROUTES).execute();
    }

    public static void setPostUserChoose(String desc){
        new ParseTask(urlUserChoose + desc, POST_USER_CHOOSE).execute();
    }

    public static void getNextVertex(){
        new ParseTask(urlNextVer, GET_NEXT_VERTEX).execute();
    }

    public static void getColor(String name){
        new ParseTask(urlGetColor + name, GET_COLOR).execute();
    }
}