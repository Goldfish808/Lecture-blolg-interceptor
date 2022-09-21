package site.metacoding.red.web.dto.request.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.users.Users;

@Setter
@Getter
public class JoinDto {
	@Size(min = 2, max =20, message = "username 입력이 짧거나 깁니다")
	@NotBlank(message = "username null 이거나 공백입니다.") // null 인지와 공백을 검사
	private String username;
	
	@Size(min = 2, max =20, message = "password 입력이 짧거나 깁니다")
	@NotBlank(message =  "password null or none")
	private String password;
	
	@Size(min = 4, max =50, message = "email입력이 짧거나 깁니다")
	@NotBlank(message = "email null or none")
	private String email;
	
	public Users toEntity() {
		Users users = new Users(this.username, this.password, this.email);
		return users;
	}
}
