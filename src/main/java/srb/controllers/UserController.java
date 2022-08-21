package srb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
//	@PostMapping(value = "/salvar")
//	@ResponseBody
//	public ResponseEntity<UserModel> salvar(@RequestBody UserModel user){
//		UserModel usuario = userRepository.save(user);
//		return new ResponseEntity<UserModel>(usuario, HttpStatus.CREATED);
//	}
	
	@PostMapping(value = "/salvar")
	@ResponseBody
	public ResponseEntity<Object> salvar(@RequestBody UserModel user){
		if(user.getNome().trim().equals("")) {
			return new ResponseEntity<Object>("O nome não pode ser vazio", HttpStatus.CREATED);
		}else {
			UserModel usuario = userRepository.save(user);
			return new ResponseEntity<Object>(usuario, HttpStatus.CREATED);
		}
	}	
	
	@PostMapping(value = "/buscartodos")
	@ResponseBody
	public ResponseEntity<List<UserModel>> buscarTodos(){
		List<UserModel> usuarios = userRepository.findAll();
		return new ResponseEntity<List<UserModel>>(usuarios, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long id){
		System.out.println("O parâmetro id passado é: " + id);
		userRepository.deleteById(id);
		return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
	}

}
