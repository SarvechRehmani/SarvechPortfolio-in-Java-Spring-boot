package com.portfolio.sarvech.helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {

    public static void removeMessage(){
        try{
            System.out.println("Removing message from session");
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
            session.removeAttribute("fileMessage");
        }catch(Exception e){
            System.out.println("Error in SessionHelper");
            e.printStackTrace();
        }
    }
}
