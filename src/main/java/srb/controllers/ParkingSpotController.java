package srb.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import srb.dtos.ParkingSpotDto;
import srb.dtos.UserDto;
import srb.models.ParkingSpotModel;
import srb.models.UserModel;
import srb.repositories.UserRepository;
import srb.services.ParkingSpotService;

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
	
/*
 * Ao usar o método abaixo (POST) com o postman instalado no win, postgres local e sistema
 * rodando localmente, tudo funciona. Mas o postman recebe mensagem de "erro interno do 
 * servidor quando tenta enviar dados para a aplicação rodando no Heroku.
 */
	
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
		
		/*
		 * Aqui essas 3 verificações estão dentro do próprio método Post, mas a posteriori seria
		 * interessante isolar todas essas responsabilidades em um "custom" Validator		
		 */
		if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
		}
		
		if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
		}
		
		if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
		}
		
		ParkingSpotModel parkingSpotModel = new ParkingSpotModel(); // parece que "var" não é necessário
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}
	
	
	
	@GetMapping
	public ResponseEntity<List <ParkingSpotModel>> getAllParkingSpots(){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
	}

}
