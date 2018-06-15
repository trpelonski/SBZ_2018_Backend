package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.UserRepository;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiseaseService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils 
{

	@Value("${utils.token.secret}")
    private String secret;

	@Value("${utils.token.expiration}")
    private Long expiration;

    @Autowired
    private UserRepository userRepository;
    
    public String getUsernameFromToken(String token) 
    {
        String username;
        try 
        {
            Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } 
        catch (Exception e) 
        {
            username = null;
        }
       
        return username;
    }

    
    public Claims getClaimsFromToken(String token) 
    {
        Claims claims;
        try 
        {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        } 
        catch (Exception e) 
        {
            claims = null;
        }
        return claims;
    }

    
    public Date getExpirationDateFromToken(String token) 
    {
        Date expiration;
        try 
        {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } 
        catch (Exception e) 
        {
            expiration = null;
        }
        return expiration;
    }

    
    private boolean isTokenExpired(String token) 
    {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    
    public boolean validateToken(String token, UserDetails userDetails) 
    {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
        
        
    }

    @Transactional
    public String generateToken(UserDetails userDetails) 
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date(System.currentTimeMillis()));
        claims.put("role", userDetails.getAuthorities());
        
        User user = userRepository.findOneByUsername(userDetails.getUsername());
        if(user==null) {
        	throw new UsernameNotFoundException("Ne postojeci korisnik");
        }
          
        try
        {
            claims.put("id", user.getId());
        }
        catch(NullPointerException e){}

        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
