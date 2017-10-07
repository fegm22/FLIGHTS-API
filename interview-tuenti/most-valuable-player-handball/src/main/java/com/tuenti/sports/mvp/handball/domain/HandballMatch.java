package com.tuenti.sports.mvp.handball.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HandballMatch implements Serializable {

    private List<HandballPlayer> players;
}
