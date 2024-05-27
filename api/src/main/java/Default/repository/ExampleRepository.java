package Default.repository;

import Default.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExampleRepository extends JpaRepository<User, Long>{
}
