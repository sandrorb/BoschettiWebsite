package srb.wapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Wapp {
	
	@PostMapping(value = "/wapp")
	public String resposta(@RequestParam String From, @RequestParam String Body) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá, " + From + "!");
		sb.append("Você digitou: " + Body);
		return sb.toString();
	}

}
