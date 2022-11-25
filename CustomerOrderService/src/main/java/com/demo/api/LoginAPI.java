package com.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.JwtTokenService;

//su dung http://localhost:8080/swagger-ui/index.html  de xem api doc

@RestController
public class LoginAPI {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenService jwtTokenProvider;

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		return jwtTokenProvider.createToken(username);
	}
}
