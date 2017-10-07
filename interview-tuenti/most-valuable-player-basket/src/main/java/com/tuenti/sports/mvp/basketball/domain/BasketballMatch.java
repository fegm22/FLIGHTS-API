package com.tuenti.sports.mvp.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasketballMatch implements Serializable {

    private List<BasketballPlayer> players;

}
