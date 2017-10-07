package com.tuenti.sports.mvp.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasketballPlayer extends Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer scored;
    private Integer rebounds;
    private Integer assists;

}
