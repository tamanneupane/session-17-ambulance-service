package vastika.training.ambulance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vastika.training.ambulance.Ambulance;

import java.util.List;
import java.util.Optional;


// CRUD - create, read, update, delete
@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {

    // select * from ambulance_tbl where city = ''
    Optional<Ambulance> findByCity(String city);

    // select * from ambulance_tbl where id = '' and city = ''
    Optional<Ambulance> findByAmbulanceIdAndCity(Long id,String city);
}
