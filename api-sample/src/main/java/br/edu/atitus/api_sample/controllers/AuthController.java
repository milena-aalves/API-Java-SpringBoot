package br.edu.atitus.api_sample.controllers;

import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.api_sample.dtos.SignupDTO;
import br.edu.atitus.api_sample.entities.UserEntity;
import br.edu.atitus.api_sample.entities.UserType;
import br.edu.atitus.api_sample.services.UserServices;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
		private final UserServices service;
	
		public AuthController(UserServices service) {
			super();
			this.service = service;
		}


		@PostMapping ("/signup")
		public ResponseEntity<UserEntity> signup(@RequestBody SignupDTO dto) throws Exception{
			
			
			UserEntity user = new UserEntity();
			
		
			BeanUtils.copyProperties(dto, user);
			
		
			user.setType(UserType.Common);
			
			service.save(user);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		}
		
		@ExceptionHandler(value = Exception.class)
		public ResponseEntity<String> handlerException(Exception ex) {
			String message = ex.getMessage().replaceAll("\r\n", "");
			return ResponseEntity.badRequest().body(message);
			}

}