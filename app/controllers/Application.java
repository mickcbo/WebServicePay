package controllers;

import play.*;
import play.mvc.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static void getAuthentification(String num,String crypto,String dateValidite) {
	boolean validite=true;
	
	
	
	//creation Date 
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date dtValue = null;
	try {
		dtValue = dateFormat.parse(dateValidite);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	//end creation date
	
	
	if(dtValue!=null){
	    if(dtValue.before(new Date()) ){
		    validite=false;
		    
		}
	}else{
	    validite=false;
	}
	
	
	if(String.valueOf(crypto).length()!=3){
	    validite=false;
	}
	
	if(String.valueOf(num).length()!=12){
	    validite=false;
	}
	
	
	
	//Autorisation banque 
	 Random random = new Random();
	 boolean  validiteB= random.nextBoolean();	
	// end Autorisation banque 
	 if(validite && validiteB ){
	     validite=true;
	 }else{
	     validite=false;
	 }
	
        renderJSON(validite);
    }

}