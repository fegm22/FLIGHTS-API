package com.tuenti.sports.mvp.basketball.action;


import com.tuenti.sports.mvp.basketball.domain.BasketballMatch;
import com.tuenti.sports.mvp.basketball.domain.BasketballPlayer;
import com.tuenti.sports.mvp.basketball.service.BasketballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MostValuablePlayerController {

    @Autowired
    private BasketballService basketballService;


    @RequestMapping(value = "/mostValuablePlayerBasketball",
            method = RequestMethod.POST,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BasketballPlayer getMostValuablePlayerBasketball(@RequestBody BasketballMatch basketballMatch, HttpServletRequest request) {

        BasketballPlayer player = basketballService.getMostValuablePlayerBasketball( basketballMatch );
        return player;
    }

}
