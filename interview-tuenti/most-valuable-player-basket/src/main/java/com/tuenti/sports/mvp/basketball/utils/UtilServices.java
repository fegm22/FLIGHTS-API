package com.tuenti.sports.mvp.basketball.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public final class UtilServices {

    private static Map<String, Map<String, Integer>> tableRatingPointsBasketball;

    //I'm moking the point data just for simplicity-time
    private UtilServices() {
        tableRatingPointsBasketball = new HashMap<>();

        //Basket
        Map<String, Integer> pointsGuard = new HashMap<>();
        pointsGuard.put("scored", 2);
        pointsGuard.put("rebounds", 3);
        pointsGuard.put("assists", 1);

        Map<String, Integer> pointsForward = new HashMap<>();
        pointsForward.put("scored", 2);
        pointsForward.put("rebounds", 2);
        pointsForward.put("assists", 2);

        Map<String, Integer> pointsCenter = new HashMap<>();
        pointsCenter.put("scored", 2);
        pointsCenter.put("rebounds", 1);
        pointsCenter.put("assists", 3);

        tableRatingPointsBasketball.put("guard", pointsGuard);
        tableRatingPointsBasketball.put("forward", pointsForward);
        tableRatingPointsBasketball.put("center", pointsCenter);

    }

    public static int getPointsScoredBasketPlayer(String position) {

        return tableRatingPointsBasketball.get(position).get("scored");

    }

    public static int getPointsReboundsBasketPlayer(String position) {

        return tableRatingPointsBasketball.get(position).get("rebounds");

    }

    public static int getPointsAssistsBasketPlayer(String position) {

        return tableRatingPointsBasketball.get(position).get("assists");

    }

}
