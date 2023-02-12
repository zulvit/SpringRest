package com.zulvit.userDatabaseSpring.security;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
private String email;
private String password;
}
