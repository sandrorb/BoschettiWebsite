package srb.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import srb.models.ParkingSpotModel;


/*O JpaRepository já nos traz uma série de métodos prontos de acesso a BD*/
/*Esta interface será um Bean do spring. Como se está estendendo JpaRepository,
 * não é necessário anotar esta interfaze como @Repository. Mas será feito mesmo
 * assim para ficar explícito */

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{
	
	/* Este é um método diferente de métodos já prontos como o save()
	 * e por isso teve de ser declarado aqui.
	 */
	boolean existsByLicensePlateCar(String licensePlateCar);
	
	boolean existsByParkingSpotNumber(String parkingSpotNumber);
	
	boolean existsByApartmentAndBlock(String apartment, String block);

}