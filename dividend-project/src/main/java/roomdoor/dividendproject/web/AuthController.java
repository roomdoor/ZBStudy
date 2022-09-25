package roomdoor.dividendproject.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomdoor.dividendproject.model.MemberEntity;
import roomdoor.dividendproject.model.constants.Auth;
import roomdoor.dividendproject.security.TokenProvider;
import roomdoor.dividendproject.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final MemberService memberService;

	private final TokenProvider tokenProvider;

	@PostMapping("/signup")
	public ResponseEntity<?> singUp(@RequestBody Auth.SignUp request) {
		MemberEntity result = memberService.register(request);

		return ResponseEntity.ok(request);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody Auth.SignIn request) {
		MemberEntity member = memberService.authenticate(request);
		String token = tokenProvider.generateToken(member.getUsername(), member.getRoles());
		return ResponseEntity.ok(token);
	}
}
