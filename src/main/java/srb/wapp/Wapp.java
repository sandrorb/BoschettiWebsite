package srb.wapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Wapp {
	
	@PostMapping(value = "/wapp")
	public String resposta() {
		return "Olá, eu sou um programa Java te respondendo!";
	}

}
