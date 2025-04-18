package com.example.demo.Controller;

import javax.crypto.SecretKey;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Config.AppSecurityConfig;
import com.example.demo.Dtos.PasswordDto;
import com.example.demo.Dtos.UsersDto;
import com.example.demo.Entity.Users;
import com.example.demo.Service.UsersService;
import com.example.demo.ServieImpl.JWTService;
import com.example.demo.ServieImpl.UserServices;

@RestController

public class UserController {
	@Autowired
	public UsersService userSer;
	
	@Autowired
	public PasswordEncoder passEncoder;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	 public RestTemplate restTemplate;
	
    @Autowired
    private UserServices service;
	@Autowired
    private JWTService jwtService;
    
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Users user)
	{
		String encodedpass=passEncoder.encode(user.getPassword());
		user.setPassword(encodedpass);
		userSer.saveUser(user);
		return ResponseEntity.ok("registered successfull");
	}
	@PostMapping("/login")
	public String loginCheck(@RequestBody Users c) {
		
		  return service.verify(c);
	}

    @GetMapping("/home")
    public ResponseEntity<String> callOtherService(@RequestHeader("Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8082/greet", HttpMethod.GET, entity, String.class);
        return ResponseEntity.ok("User-Service → " + response.getBody());
    }
	
  
    @GetMapping("/secret/{username}")
    public ResponseEntity<String> getSecretKey(@PathVariable String username) {
        SecretKey key = jwtService.getKeyForUser(username);
        return ResponseEntity.ok(java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
    }
//using resttemplate pass the token of this home url token to the other microservice api there i have to check that this token is validate or not

    @GetMapping("/about")
    public ResponseEntity<String> about()
    {
    	return ResponseEntity.ok("This Website holds User details");
    }
}
