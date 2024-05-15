package vastika.training.ambulance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import vastika.training.ambulance.Ambulance;
import vastika.training.ambulance.exceptions.AmbulanceNotFoundException;
import vastika.training.ambulance.repository.AmbulanceRepository;
import vastika.training.ambulance.utils.Constants;

import java.util.List;
import java.util.Optional;

@Service
public class AmbulanceService {

    @Autowired
    private AmbulanceRepository repository;

    public Ambulance saveAmbulanceToDatabase(Ambulance ambulance){
        return repository.save(ambulance);
    }

    public List<Ambulance> getAllAmbulance(){
        return repository.findAll();
    }

    public Ambulance getAmbulanceDetails(Long id){
       Optional<Ambulance> optionalAmbulance =  repository.findById(id);
       if(optionalAmbulance.isPresent()){
           return optionalAmbulance.get();
       }else {
           throw new AmbulanceNotFoundException(Constants.ErrorMessage.AMBULANCE_NOT_FOUND_MESSAGE);
       }
    }

    public Ambulance getAmbulanceDetailsBasedOnCity(String city)
    {
        Optional<Ambulance> optionalAmbulance = repository.findByCity(city);
        if(optionalAmbulance.isPresent()){
            return optionalAmbulance.get();
        }else {
            throw new AmbulanceNotFoundException(Constants.ErrorMessage.AMBULANCE_NOT_FOUND_MESSAGE);
        }
    }

    public Ambulance updateAmbulance(Long id, Ambulance ambulance){
        Optional<Ambulance> optionalAmbulance = repository.findById(id);
        if(optionalAmbulance.isPresent()){
            // Update
            Ambulance ambulanceOnDB = optionalAmbulance.get();
            ambulanceOnDB.setVehicleNumber(ambulance.getVehicleNumber());
            ambulanceOnDB.setLatitude(ambulance.getLatitude());
            ambulanceOnDB.setLongitude(ambulance.getLongitude());
            ambulanceOnDB.setHospitalName(ambulance.getHospitalName());
            ambulanceOnDB.setCity(ambulance.getCity());
            ambulanceOnDB.setIsAvailable(ambulance.getIsAvailable());
            return repository.save(ambulanceOnDB);
        }else{
            throw new AmbulanceNotFoundException(Constants.ErrorMessage.AMBULANCE_NOT_FOUND_MESSAGE);
        }
    }

    public Ambulance updateAvailability(Long id, Boolean available){
        Optional<Ambulance> optionalAmbulance = repository.findById(id);
        if(optionalAmbulance.isPresent()){
            // Update
            Ambulance ambulanceOnDB = optionalAmbulance.get();
            ambulanceOnDB.setIsAvailable(available);
            return repository.save(ambulanceOnDB);
        }else{
            throw new AmbulanceNotFoundException(Constants.ErrorMessage.AMBULANCE_NOT_FOUND_MESSAGE);
        }
    }

    public void deleteAmbulanceById(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException exception){
            throw new AmbulanceNotFoundException(Constants.ErrorMessage.AMBULANCE_NOT_FOUND_MESSAGE);
        }
    }
}
