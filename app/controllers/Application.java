package controllers;

import play.*;
import play.mvc.*;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static void getAuthentification() 
    {
    	try{
    		String urlFacturation=Play.configuration.getProperty("droit.url");
    		URL myURL = new URL(urlFacturation);
    	    URLConnection myURLConnection = myURL.openConnection();
    	    myURLConnection.connect();
    	}catch(Exception e){
    		System.out.println(e.getCause());
    	}
    	
    	
    }
   

}