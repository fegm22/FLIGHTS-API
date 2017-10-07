package com.tuenti.sports.mvp.handball.action;


import com.tuenti.sports.mvp.handball.domain.HandballMatch;
import com.tuenti.sports.mvp.handball.domain.HandballPlayer;
import com.tuenti.sports.mvp.handball.service.HandballService;
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
    private HandballService handballService;

    @RequestMapping(value = "/mostValuablePlayerHandball",
            method = RequestMethod.POST,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    HandballPlayer getMostValuablePlayerHandball(@RequestBody HandballMatch handballMatch, HttpServletRequest request) {

        return handballService.getMostValuablePlayerHandball( handballMatch );
    }
}
