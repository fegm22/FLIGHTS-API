package com.tuenti.sports.mvp.basketball.service;

import com.tuenti.sports.mvp.basketball.domain.BasketballMatch;
import com.tuenti.sports.mvp.basketball.utils.UtilServices;
import com.tuenti.sports.mvp.basketball.domain.BasketballPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasketballService {


    @Autowired
    private UtilServices utilServices;

    public BasketballPlayer getMostValuablePlayerBasketball(BasketballMatch basketballMatch) {

        return getPlayerWithMorePoints(basketballMatch);

    }

    //I'm not doing any validation about the data.. but I should do it, I know :)
    private BasketballPlayer getPlayerWithMorePoints(BasketballMatch basketballMatch) {
        List<BasketballPlayer> basketballPlayersList = basketballMatch.getPlayers();
        BasketballPlayer bestPlayer = null;
        int maximumPlayerPoints = 0;
        String winnerTeam = getWinnerTeam(basketballMatch);

        for (BasketballPlayer player : basketballPlayersList) {
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

    private String getWinnerTeam(BasketballMatch basketballMatch) {
        List<BasketballPlayer> basketballPlayersList = basketballMatch.getPlayers();
        Map<String, Integer> teamTable = new HashMap<>();
        int maximumScoreTeam = 0;
        String winnerTeam = "";

        for (BasketballPlayer player : basketballPlayersList) {
            if (teamTable.containsKey(player.getTeamName())) {
                int sumTeam = teamTable.get(player.getTeamName()) + player.getScored();
                teamTable.put(player.getTeamName(), sumTeam);
                if (maximumScoreTeam < sumTeam) {
                    maximumScoreTeam = sumTeam;
                    winnerTeam = player.getTeamName();
                }
            } else {
                teamTable.put(player.getTeamName(), player.getScored());
            }
        }
        return winnerTeam;
    }

    private int calculateTotalPoints(BasketballPlayer basketballPlayer) {

        int assistsPoints = utilServices.getPointsAssistsBasketPlayer(basketballPlayer.getPosition()) * basketballPlayer.getAssists();
        int reboundsPoints = utilServices.getPointsReboundsBasketPlayer(basketballPlayer.getPosition()) * basketballPlayer.getRebounds();
        int scoredPoints = utilServices.getPointsScoredBasketPlayer(basketballPlayer.getPosition()) * basketballPlayer.getScored();

        return assistsPoints + reboundsPoints + scoredPoints;
    }
}
