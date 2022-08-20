package srb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import srb.models.UserModel;
import srb.repositories.UserRepository;

@RestController
public class UserController {
	
	final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/user/{nome}", method = RequestMethod.GET )
	@ResponseStatus(HttpStatus.OK)
	public String salvarUser(@PathVariable String nome) {
		UserModel user = new UserModel();
		user.setNome(nome);
		userRepository.save(user);
		return nome;
	}
	
	@PostMapping(value = "/salvar")
	@ResponseBody
	public ResponseEntity<UserModel> salvar(@RequestBody UserModel user){
		System.out.println("xxxxxxxxxxxxxxxx");
		UserModel usuario = userRepository.save(user);
		return new ResponseEntity<UserModel>(usuario, HttpStatus.CREATED);
	}
	
//	@PostMapping(value = "salvar")
//	@ResponseBody
//	public ResponseEntity<String> salvar(){
//		System.out.println("xxxxxxxxxxxxxxxx");
//		return new ResponseEntity<String>("SrB ok", HttpStatus.CREATED);
//	}	

}
