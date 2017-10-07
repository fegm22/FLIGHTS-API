package com.tuenti.sports.mvp.handball.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public final class UtilServices {

    private static Map<String, Map<String, Integer>> tableRatingPointsHandball;

    //I'm moking the point data just for simplicity-time
    private UtilServices() {

        tableRatingPointsHandball = new HashMap<>();
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


        //Handball
        Map<String, Integer> pointsGoalkeeper = new HashMap<>();
        pointsGoalkeeper.put("initial", 50);
        pointsGoalkeeper.put("made", 5);
        pointsGoalkeeper.put("received", -2);

        Map<String, Integer> pointsFieldPlayer = new HashMap<>();
        pointsFieldPlayer.put("initial", 20);
        pointsFieldPlayer.put("made", 1);
        pointsFieldPlayer.put("received", -1);


        tableRatingPointsHandball.put("goalkeeper", pointsGoalkeeper);
        tableRatingPointsHandball.put("fieldplayer", pointsFieldPlayer);
    }


    public static int getPointsInitialHandballPlayer(String position) {

        return tableRatingPointsHandball.get(position).get("initial");

    }

    public static int getPointsGoalMadeHandballPlayer(String position){

        return tableRatingPointsHandball.get(position).get("made");

    }

    public static int getPointsGoalReceivedHandballPlayer(String position){

        return tableRatingPointsHandball.get(position).get("received");

    }
}
