package roomdoor.dividendproject.model.constants;

import java.util.List;
import lombok.*;
import roomdoor.dividendproject.entity.MemberEntity;


public class Auth {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class SignIn {

		private String username;
		private String password;

	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class SignUp {

		private String username;
		private String password;
		private List<String> roles;


		public MemberEntity toEntity() {
			return MemberEntity.builder()
				.username(this.username)
				.password(this.password)
				.roles(this.roles)
				.build();
		}

	}

}
