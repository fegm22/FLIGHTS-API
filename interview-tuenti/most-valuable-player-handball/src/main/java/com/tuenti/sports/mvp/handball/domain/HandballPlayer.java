package com.tuenti.sports.mvp.handball.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HandballPlayer extends Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goalsMade;
    private Integer goalsReceived;

}
