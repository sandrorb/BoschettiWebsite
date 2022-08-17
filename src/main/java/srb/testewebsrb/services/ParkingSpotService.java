package srb.testewebsrb.services;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srb.testewebsrb.repositories.ParkingSpotRepository;

/*Inicialmente esta classe não fará muita coisa e será apenas uma 
 * intermediária. Mas aqui é onde ficam regras de negócios */
/*Esta classe é um Bean do Spring. Em aplicações mais maduras, cria-se
 * também uma interface que é implementada por esta classe */

/*Aqui será criado um ponto de injeção para o uso do Repository */

@Service
public class ParkingSpotService {
	
	/* Aqui entra a injeção de dependência */
	
	/* As duas linhas abaixo fazem a injeção de dependência necessária,
	 * mas parece que é possível (e talvez preferível) que seja feita
	 * por meio do construtor
	 */
	//@Autowired
	//ParkingSpotRepository parkingSpotRepository;
	
	final ParkingSpotRepository parkingSpotRepository;
	
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
	}
	
	

}
