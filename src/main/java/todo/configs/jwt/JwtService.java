package todo.configs.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
//Oauth
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import todo.domains.entities.UserEntity;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Service
public class JwtService {
    @Value("${security.jwt.TOKEN-EXPIRATION-HOURS}")
    private String expirationTime;

    private final static SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public String getToken (UserEntity user) {
        long expString = Long.valueOf(this.expirationTime);
        LocalDateTime expiresIn = LocalDateTime.now().plusHours(expString);
        Instant instant = expiresIn.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        System.out.println("USEEEEEEEEEEEEEEERRRRRRR" + user);
        return Jwts
                .builder()
                .subject(user.getEmail())
                .expiration(date)
                .signWith(secretKey)
                .compact();
    }

    private Claims getClaims (String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValid (String token) {
        try {
            System.out.println("*****************************" + token);
            Claims claims = this.getClaims(token);
            Date expiresIn = claims.getExpiration();
            LocalDateTime date = expiresIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            System.out.println("LocalDateTime.now().isAfter(date)" + LocalDateTime.now().isAfter(date));

            return !LocalDateTime.now().isAfter(date);
        } catch (Exception e) {
            System.out.println("ERRROOOORRRRRR" + e);
            return false;
        }
    }

    public String getUserEmail (String token) throws ExpiredJwtException {
        return (String) this.getClaims(token).getSubject();
    }
}
