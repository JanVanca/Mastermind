package com.vanca.jan.mastermind.gamestudio.service;

import com.vanca.jan.mastermind.gamestudio.entity.Score;
import com.vanca.jan.mastermind.gamestudio.service.exception.ScoreException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ScoreServiceJDBC implements ScoreService, DatabaseSettings {

    public static final String INSERT_SCORE =
    "INSERT INTO score (game, player, points, playedon) VALUES (?, ?, ?, ?)";

    public static final String SELECT_SCORE =
        "SELECT game, player, points, playedon FROM score WHERE game = ? ORDER BY points DESC LIMIT 10;";

    /**
     * Connects and adds score to the database.
     * @param score Entered score.
     * @throws ScoreException
     */
    @Override
    public void addScore(Score score) throws ScoreException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_SCORE)){
                ps.setString(1, score.getGame());
                ps.setString(2, score.getPlayer());
                ps.setInt(3, score.getPoints());
                ps.setDate(4, new Date(score.getPlayedOn().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ScoreException("Error saving score", e);
        }
    }

    /**
     * Connects and return the best score from the database.
     * @param game Current game.
     * @return
     * @throws ScoreException
     */
    @Override
    public List<Score> getBestScores(String game) throws ScoreException {
        List<Score> scores = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_SCORE)){
                ps.setString(1, game);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Score score = new Score(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getTimestamp(4)
                        );
                        scores.add(score);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ScoreException("Error loading score", e);
        }
        return scores;
    }

    public static void main(String[] args) throws Exception {
        Score score = new Score("mines", "jaro", 100, new java.util.Date());
        ScoreService scoreService = new ScoreServiceJDBC();
        //scoreService.addScore(score);
        System.out.println(scoreService.getBestScores("mines"));
    }
}
