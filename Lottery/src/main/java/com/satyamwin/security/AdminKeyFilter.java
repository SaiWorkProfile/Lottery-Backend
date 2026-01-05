package com.satyamwin.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdminKeyFilter implements Filter {

    @Value("${admin.secret}")
    private String adminSecret;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        /* ======================================================
           🔥 VERY IMPORTANT: ALLOW CORS PREFLIGHT (OPTIONS)
           Without this → Axios Network Error in browser
        ====================================================== */
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        /* ======================================================
           🔐 PROTECT ONLY /admin APIs
        ====================================================== */
        if (req.getRequestURI().startsWith("/x9p7kA2_2026/admin-panel")) {

            String key = req.getHeader("X-ADMIN-KEY");

            if (key == null || !key.equals(adminSecret)) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("Unauthorized Admin Access");
                return;
            }
        }

        // Allow request to continue
        chain.doFilter(request, response);
    }
}
