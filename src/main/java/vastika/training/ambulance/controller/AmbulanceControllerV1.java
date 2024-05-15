package vastika.training.ambulance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vastika.training.ambulance.Ambulance;
import vastika.training.ambulance.dto.SuccessResponse;
import vastika.training.ambulance.repository.AmbulanceRepository;
import vastika.training.ambulance.service.AmbulanceService;
import vastika.training.ambulance.utils.Constants;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
public class AmbulanceControllerV1 {

    @Autowired
    private AmbulanceService service;

    /*
        Different HTTP Methods

        1. GET -
            a. Get the data without send any data.
            b. Send some data and based on set data get the data. (Filter)
        2. POST -
            a. Send some data and based on set data get the data. (large data) (Security)
            b. Save or create some data
        3. PATCH -
            a. Update small portion of data
        4. PUT -
            a. Update whole data
        5. DELETE -
            a. When the use case is to delete the data from the database

     */

    /*
           HTTP Protocol - 80 (Port)
           HTTPS Protocol - 443 (Port)

           Different HTTP Status Code

            200 - Successful
            201 - Created => Creating new Data
            204 - No Content => Deleting existing data
            302 - Redirection
            400 - Bad Request
            401 - Unauthorized
            403 - Forbidden
            404 - Not found
            422 - Unprocessable Entity
            500 - Internal Server Error
            502 - Bad Gateway
            503 - Service not available
     */

    @GetMapping(value = "/ambulance")
    public ResponseEntity<SuccessResponse<List<Ambulance>>> getListOfAmbulance(){
        List<Ambulance> ambulances = service.getAllAmbulance();
        return new ResponseEntity<>(new SuccessResponse<>(Constants.StatusCode.SUCCESS, ambulances), HttpStatus.OK);
    }

    @GetMapping(value = "/ambulance/detail")
    public ResponseEntity<SuccessResponse<Ambulance>> getAmbulanceDetail(@RequestParam(value = "id", required = false) String ambulanceId, @RequestParam(value = "city", required = false) String city){
        // 200
        Ambulance ambulance = new Ambulance();

        if(!Objects.isNull(ambulanceId)){
            ambulance = service.getAmbulanceDetails(Long.parseLong(ambulanceId));
        }

        if(!Objects.isNull(city)){
            ambulance = service.getAmbulanceDetailsBasedOnCity(city);
        }

        return new ResponseEntity<>(new SuccessResponse<>(Constants.StatusCode.SUCCESS, ambulance), HttpStatus.OK);
    }

    @PostMapping(value = "/ambulance")
    public ResponseEntity<SuccessResponse<Ambulance>> createAmbulance(@Valid @RequestBody Ambulance ambulance){
        Ambulance savedAmbulance = service.saveAmbulanceToDatabase(ambulance);
        return new ResponseEntity<>(new SuccessResponse<>(Constants.StatusCode.SUCCESS, savedAmbulance), HttpStatus.CREATED);
    }

    @PutMapping(value = "/ambulance")
    public ResponseEntity<SuccessResponse<Ambulance>> updateAmbulance(@RequestParam(value = "id") String ambulanceId, @RequestBody Ambulance ambulance){
        // 200
        Ambulance updatedAmbulance = service.updateAmbulance(Long.parseLong(ambulanceId), ambulance);
        return new ResponseEntity<>(new SuccessResponse<>(Constants.StatusCode.SUCCESS, updatedAmbulance), HttpStatus.OK);
    }

    @PatchMapping(value = "/ambulance")
    public ResponseEntity<SuccessResponse<Ambulance>> updateAmbulanceAvailability(@RequestParam(value = "id") Long id, @RequestParam(value = "available") Boolean available){
        // 200
        Ambulance updatedAmbulance = service.updateAvailability(id, available);
        return new ResponseEntity<>(new SuccessResponse<>(Constants.StatusCode.SUCCESS, updatedAmbulance), HttpStatus.OK);
    }

    @DeleteMapping(value = "/ambulance")
    public ResponseEntity<String> deleteAmbulance(@RequestParam(value = "id")String ambulanceId){
        // 204
        service.deleteAmbulanceById(Long.parseLong(ambulanceId));
        return new ResponseEntity<>("Delete ambulance data", HttpStatus.NO_CONTENT);
    }

}
