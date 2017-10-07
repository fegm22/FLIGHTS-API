package com.tuenti.sports.mvp.handball.service;

import com.tuenti.sports.mvp.handball.domain.HandballMatch;
import com.tuenti.sports.mvp.handball.domain.HandballPlayer;
import com.tuenti.sports.mvp.handball.utils.UtilServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HandballService {

    @Autowired
    private UtilServices utilServices;

    public HandballPlayer getMostValuablePlayerHandball(HandballMatch handballMatch) {

        return getPlayerWithMorePoints(handballMatch);

    }

    //I'm not doing any validation about the data.. but I should do it, I know :)
    private HandballPlayer getPlayerWithMorePoints(HandballMatch handballMatch) {
        List<HandballPlayer> handballPlayerList = handballMatch.getPlayers();
        HandballPlayer bestPlayer = null;
        int maximumPlayerPoints = 0;
        String winnerTeam = getWinnerTeam(handballMatch);

        for (HandballPlayer player : handballPlayerList) {
            int totalPoints = calculateTotalPoints(player);
            if(player.getTeamName().equals(winnerTeam)) {
                totalPoints = totalPoints + 10;
            }
            if (maximumPlayerPoints < totalPoints) {
                maximumPlayerPoints = totalPoints;
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }

    private String getWinnerTeam(HandballMatch handballMatch) {
        List<HandballPlayer> handballPlayerList = handballMatch.getPlayers();
        Map<String, Integer> teamTable = new HashMap<>();
        int maximumScoreTeam = 0;
        String winnerTeam = "";

        for (HandballPlayer player : handballPlayerList) {
            if (teamTable.containsKey(player.getTeamName())) {
                int sumTeam = teamTable.get(player.getTeamName()) + player.getGoalsMade();
                teamTable.put(player.getTeamName(), sumTeam);
                if (maximumScoreTeam < sumTeam) {
                    maximumScoreTeam = sumTeam;
                    winnerTeam = player.getTeamName();
                }
            } else {
                teamTable.put(player.getTeamName(), player.getGoalsMade());
            }
        }
        return winnerTeam;
    }



    private int calculateTotalPoints(HandballPlayer handballPlayer){

        int initialPoints = utilServices.getPointsInitialHandballPlayer(handballPlayer.getPosition());
        int goalMadePoints = utilServices.getPointsGoalMadeHandballPlayer(handballPlayer.getPosition())*handballPlayer.getGoalsMade();
        int goalReceivedPoints = utilServices.getPointsGoalReceivedHandballPlayer(handballPlayer.getPosition())*handballPlayer.getGoalsReceived();

        return initialPoints + goalMadePoints + goalReceivedPoints;
    }
}
