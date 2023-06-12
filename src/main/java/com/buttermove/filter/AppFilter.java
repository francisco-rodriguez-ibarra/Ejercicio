package com.buttermove.filter;

import com.buttermove.dto.ErrorDTO;
import com.buttermove.errors.HeaderException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppFilter extends OncePerRequestFilter {
    private final AuthenticationManager appFilterAuthenticationManager;
    public AppFilter(AuthenticationManager authenticationManager){
        appFilterAuthenticationManager=authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeaderIpClient = request.getHeader("ip-client");
        InetAddressValidator validator = InetAddressValidator.getInstance();

        try{
            //revisa que traiga una ip y no venga nula
            if(authHeaderIpClient == null) throw new HeaderException("Invalid Acces");
            //revisa si la es ipv4 o ipv6 y que sean validas
            if(!validator.isValid(authHeaderIpClient)) throw new HeaderException("Invalid Acces");

        }catch (HeaderException e){
            //Se prepara el response que sera enviado en caso de algun error
            response.setStatus(401);;
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(),new ErrorDTO(e.getMessage()));
        }
        //Continua con el flujo
        filterChain.doFilter(request,response);
    }
}
