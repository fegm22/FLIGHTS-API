package com.tuenti.sports.mvp.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private String playerName;
    private String nickname;
    private String number;
    private String teamName;
    private String position;

}
