package srb.testewebsrb.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
