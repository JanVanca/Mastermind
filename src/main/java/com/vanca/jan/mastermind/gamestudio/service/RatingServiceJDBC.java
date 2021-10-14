package com.vanca.jan.mastermind.gamestudio.service;

import com.vanca.jan.mastermind.gamestudio.entity.Rating;
import com.vanca.jan.mastermind.gamestudio.service.exception.RatingException;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RatingServiceJDBC implements RatingService, DatabaseSettings {

    public static final String INSERT_RATING =
            "INSERT INTO rating (game, player, rating, ratedon) VALUES (?, ?, ?, ?)";

    public static final String SELECT_RATING =
            "SELECT Avg(rating) FROM rating WHERE game = ?;";


    //TODO rating sa ma v pripade, ze user s rovnakym menom uz raz rating zadal UPDATEnut a nie INSERTnut
    @Override
    public void setRating(Rating rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_RATING)){
                ps.setString(1, rating.getGame());
                ps.setString(2, rating.getPlayer());
                ps.setInt(3, rating.getRating());
                ps.setDate(4, new Date(rating.getRatedOn().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RatingException("Error saving rating", e);
        }
    }

    //TODO na toto existuje v SQL priamo prikaz, ktorym si vies vytiahnut priemer zo stlpca, skus pouzit ten
    @Override
    public int getAverageRating(String game) throws RatingException {
        List<Integer> ratings = new ArrayList<>();
        int rating = 0;
        int i = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_RATING)){
                ps.setString(1, game);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        ratings.add(rs.getInt("rating"));

                    }
                }
            }
        } catch (SQLException e) {
            throw new RatingException("Error loading rating", e);
        }
        while (i < ratings.size()) {
            rating = rating + ratings.get(i);
            i++;
        }
        rating = rating / 2;

        return rating;

    }

    //TODO toto ti urcite nevytiahne rating pre konkretneho hraca, skus pouzit novu SQL query a v nej klauzulu "WHERE"
    @Override
    public int getRating(String game, String player) throws RatingException {
        List<Rating> ratings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_RATING)){
                ps.setString(1, game);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Rating rating = new Rating(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getTimestamp(4)
                        );
                        ratings.add(rating);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RatingException("Error loading rating", e);
        }
        return ratings.get(0).getRating();
    }
}
