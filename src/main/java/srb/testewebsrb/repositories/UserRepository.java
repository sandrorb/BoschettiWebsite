package srb.testewebsrb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import srb.testewebsrb.models.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

}