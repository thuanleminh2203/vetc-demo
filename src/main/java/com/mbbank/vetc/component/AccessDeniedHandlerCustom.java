package com.mbbank.vetc.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AccessDeniedHandlerCustom implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        log.error("Responding with forbidden error. Message - {}", e.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }

}
