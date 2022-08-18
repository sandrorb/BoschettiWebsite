package srb.testewebsrb.services;

import javax.transaction.Transactional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srb.testewebsrb.models.ParkingSpotModel;
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
	
	
/* Importante a anatação Transactional para métodos destrutivos
 * para que haja um rollback em caso de algum problema	
 */
	@Transactional
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return parkingSpotRepository.save(parkingSpotModel);
	}
	
	
	/* Este é um método um pouco mais "customizado" e não é um método
	 * "pronto" como o save(). Ele deverá ser declarado dentro de Repository
	 * antes de ser chamado no aqui no Service */
	public boolean existsByLicensePlateCar(String licensePlateCar) {
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}
	
	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}
	
	public boolean existsByApartmentAndBlock(String apartment, String block) {
		return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
	}

}
