package com.ryanair.flights.interconnections.action;


import com.ryanair.flights.interconnections.domain.Interconnection;
import com.ryanair.flights.interconnections.service.CitiesServices;
import com.ryanair.flights.interconnections.service.InterconnectionService;
import com.ryanair.flights.interconnections.service.RouteService;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class InterconnectionController {

    @Autowired
    private InterconnectionService interconnectionService;

    @Autowired
    private RouteService routesService;

    @Autowired
    private CitiesServices citiesServices;

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
                                                      LocalDateTime departureDateTime,
                                              @ApiQueryParam(name = "arrivalDateTime", description = "an arrival datetime in the arrival airport timezone in ISO format")
                                              @RequestParam(value = "arrivalDateTime")
                                                      LocalDateTime arrivalDateTime) {

        LocalDateTime start = departureDateTime.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime end = arrivalDateTime.truncatedTo(ChronoUnit.MINUTES);

        return this.interconnectionService.execute(departure, arrival, start, end);
    }

    @GetMapping(value = "/available/routes")
    public Map<String, Set<String>> getAllAvailableRoutes() {

        return routesService.getAllAvailableRoutes();

    }

    @GetMapping(value = "/available/airports")
    public Map<String, String> getAllAvailableAirports() {

        return citiesServices.getAllAvailableAirports();
    }

    @GetMapping(value = "/routes")
    public List<String> findRoutesBetween(@RequestParam(value = "departure") String departure,
                                               @RequestParam(value = "arrival") String arrival) {

        return routesService.getAirportConnections(departure, arrival);
    }

}
