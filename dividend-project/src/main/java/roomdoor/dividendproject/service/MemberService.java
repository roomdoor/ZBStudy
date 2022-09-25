package roomdoor.dividendproject.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import roomdoor.dividendproject.model.MemberEntity;
import roomdoor.dividendproject.model.constants.Auth;
import roomdoor.dividendproject.repository.MemberRepository;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + username));
	}


	public MemberEntity register(Auth.SignUp member) {
		boolean exists = memberRepository.existsByUsername(member.getUsername());
		if (exists) {
			throw new RemoteTimeoutException("이미 사용 중인 아이디 입니다.");
		}

		member.setPassword(passwordEncoder.encode(member.getPassword()));
		return memberRepository.save(member.toEntity());
	}

	public MemberEntity authenticate(Auth.SignIn member) {
		MemberEntity user = memberRepository.findByUsername(member.getUsername())
			.orElseThrow(() -> new RuntimeException("not found Id"));

		if (passwordEncoder.matches(user.getPassword(), member.getPassword())) {
			throw new RuntimeException("not match password");
		}

		user.setRoles(user.getRoles());

		return user;
	}
}
