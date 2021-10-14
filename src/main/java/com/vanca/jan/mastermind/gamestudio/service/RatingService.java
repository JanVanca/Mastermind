package com.vanca.jan.mastermind.gamestudio.service;

import com.vanca.jan.mastermind.gamestudio.entity.Rating;
import com.vanca.jan.mastermind.gamestudio.service.exception.RatingException;

public interface RatingService {
    void setRating(Rating rating) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, String player) throws RatingException;
}
