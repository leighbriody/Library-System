/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class ChangeLanguageCommand implements Command{

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
    
      String language = request.getParameter("language");
      
      
        
       if(language != null){
        // Create a locale based on the supplied language
        Locale currentLocale = new Locale(language);
        
        
        

                   
                }  
        return null;
        
        
    }
    
}
