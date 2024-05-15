package vastika.training.ambulance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vastika.training.ambulance.repository.AmbulanceRepository;

@RestController
@RequestMapping(value = "/api/v2")
public class AmbulanceControllerV2 {

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @GetMapping(value = "/create")
    public String handleCreateRequest(){
        return "Yes this application is running";
    }

    @GetMapping(value = "/update")
    public String handleUpdateRequest(){
        return "Yes this application is running";
    }

    @GetMapping(value = "/delete")
    public String handleDeleteRequest(){
        return "Yes this application is running";
    }

    // baseurl/api/v1/
}
