package com.projet_6.pay_my_buddy.JB.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }


    public static String getUserMail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            userName = userDetails.getUsername();

        }
        return userName;
    }

    public static boolean isUserConnected() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String authority = authentication.getAuthorities().toString();
        if (authority.equals("[ROLE_ANONYMOUS]")) {

            return false;

        } else {
            return true;
        }

    }
}
