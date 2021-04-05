/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class LogoutCommand implements Command{
     public String doAction(HttpServletRequest request, HttpServletResponse response){
        String forwardToJsp = "home.jsp";
        HttpSession session = request.getSession();
        // To log a user out, wipe their session clear
        session.invalidate();
        return forwardToJsp;
    }
}
