package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class LoginDTO {

	private String ID;
	private String password;
	private Boolean rememberMe;

}
