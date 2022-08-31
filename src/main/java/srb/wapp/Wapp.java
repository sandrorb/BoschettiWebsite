package srb.wapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class Wapp {
	
	@PostMapping(value = "/wapp")
	public String resposta(@RequestParam String From, @RequestParam String Body) {
		String numTelefone = From.substring(From.indexOf(":")+1);
		StringBuilder sb = new StringBuilder();
		sb.append("Olá, " + numTelefone + "!\n");
		sb.append("Esta é uma mensagem automática criada e enviada por um webservice escrito em Java por Sandro Boschetti\n");
		sb.append("Você digitou: " + Body);
		return sb.toString();
	}
	
	
	@GetMapping(value = "/enviaMsg")
	public void enviaMsg(@RequestParam String accountSid, @RequestParam String authToken, 
			             @RequestParam String phoneNumberOriginStr, @RequestParam String phoneNumberDestStr) {
		
		Twilio.init(accountSid, authToken);	
		
		phoneNumberOriginStr = "whatsapp:" + phoneNumberOriginStr;
		  phoneNumberDestStr = "whatsapp:" + phoneNumberDestStr;
		
		PhoneNumber phoneNumberOrigin = new PhoneNumber(phoneNumberOriginStr);
		PhoneNumber phoneNumberDest = new PhoneNumber(phoneNumberDestStr);
	
		String body = "SrB: Isso é um teste";
		 
		Message msg = Message.creator( phoneNumberOrigin, phoneNumberDest, body).create();
		
		System.out.println(msg.getSid());				
	}

}

