package srb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import srb.models.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

}