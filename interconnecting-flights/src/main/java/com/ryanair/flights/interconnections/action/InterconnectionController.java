package com.ryanair.flights.interconnections.action;


import com.ryanair.flights.interconnections.domain.Interconnection;
import com.ryanair.flights.interconnections.service.InterconnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class InterconnectionController {

    @Autowired
    private InterconnectionService interconnectionService;


    @RequestMapping(value = "/interconnections",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Interconnection> getInterconnections(@RequestParam(value = "departure") String departure,
                                              @RequestParam(value = "arrival") String arrival,
                                              @RequestParam(value = "departureDateTime") String departureDateTime,
                                              @RequestParam(value = "arrivalDateTime") String arrivalDateTime,
                                              HttpServletRequest request) {

        return this.interconnectionService.execute(departure, arrival, departureDateTime, arrivalDateTime);
    }
}
