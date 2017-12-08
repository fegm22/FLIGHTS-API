package com.ryanair.flights.interconnections.action;


import com.ryanair.flights.interconnections.domain.Interconnection;
import com.ryanair.flights.interconnections.service.InterconnectionService;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class InterconnectionController {

    @Autowired
    private InterconnectionService interconnectionService;

    @ApiMethod(description = "Serves information about possible direct and interconnected flights (maximum 1 stop)")
    @RequestMapping(value = "/interconnections",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Interconnection> getInterconnections(@ApiQueryParam(name = "departure", description = "a departure airport IATA code")
                                              @RequestParam(value = "departure")
                                                      String departure,
                                              @ApiQueryParam(name = "arrival", description = "an arrival airport IATA code")
                                              @RequestParam(value = "arrival")
                                                      String arrival,
                                              @ApiQueryParam(name = "departureDateTime", description = "a departure datetime in the departure airport timezone in ISO format")
                                              @RequestParam(value = "departureDateTime")
                                                      String departureDateTime,
                                              @ApiQueryParam(name = "arrivalDateTime", description = "an arrival datetime in the arrival airport timezone in ISO format")
                                              @RequestParam(value = "arrivalDateTime")
                                                      String arrivalDateTime,
                                              HttpServletRequest request) {

        return this.interconnectionService.execute(departure, arrival, departureDateTime, arrivalDateTime);
    }
}
