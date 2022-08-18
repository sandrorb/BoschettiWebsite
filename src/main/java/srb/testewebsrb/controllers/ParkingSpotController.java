package srb.testewebsrb.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import srb.testewebsrb.dtos.ParkingSpotDto;
import srb.testewebsrb.models.ParkingSpotModel;
import srb.testewebsrb.services.ParkingSpotService;

/* Esta classe é um Bean do Spring e é a mais próxima
 * ao frontend.
 * O CrossOrigin parece que tem a ver com pode estar acessível
 * de várias origens.
 * Assim como foi feita uma injeção do Repository dentro do Service,
 * será feito uma injeção do Service dentro do Controller.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
	
	final ParkingSpotService parkingSpotService;
	
	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}
	
	
/* a linha com a palavra reservada "var" só é possível com Java 10 ou posterior. Mas,
 * mesmo usando JavaSE-14 na configuração JRE System Library, o projeto não funciona
 * no Heroku se tiver "var".
 * Object é usado pois futuramente poderá retornar outros objetos.
 * Parece que o RequestBody é para que os dados venha como JSON para construir o parkingSpotDto.
 * O Valid vai validar os dados chegados conforme anotações no Dto. Se algo der errado aqui
 * o cliente já recebe um Bad Request */
	
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
		ParkingSpotModel parkingSpotModel = new ParkingSpotModel(); // parece que "var" não é necessário
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}

}
