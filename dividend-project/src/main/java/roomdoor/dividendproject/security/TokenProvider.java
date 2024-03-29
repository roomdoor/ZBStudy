package roomdoor.dividendproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import roomdoor.dividendproject.service.MemberService;

@Component
@RequiredArgsConstructor
public class TokenProvider {

	private static final String KEY_ROLES = "roles";
	private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60;

	private final MemberService memberService;

	@Value("{spring.jwt.secret}")
	private String secretKey;

	public String generateToken(String username, List<String> roles) {

		Claims claims = Jwts.claims().setSubject(username);
		claims.put(KEY_ROLES, roles);

		var now = new Date();
		var expireDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(expireDate)
			.signWith(SignatureAlgorithm.HS512, this.secretKey)
			.compact();
	}

	@Transactional(readOnly = true)
	public Authentication getAuthentication(String jwt) {
		UserDetails userDetails = memberService.loadUserByUsername(this.getUsername(jwt));

		return new UsernamePasswordAuthenticationToken(userDetails, "",
			userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		String subject = this.parseClaims(token).getSubject();
		return subject;
	}

	public boolean validateToken(String token) {
		if (!StringUtils.hasText(token)) {
			return false;
		}

		Claims claims = this.parseClaims(token);
		return !claims.getExpiration().before(new Date());
	}


	private Claims parseClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}