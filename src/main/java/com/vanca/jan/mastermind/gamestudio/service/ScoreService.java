package com.vanca.jan.mastermind.gamestudio.service;

import com.vanca.jan.mastermind.gamestudio.entity.Score;
import com.vanca.jan.mastermind.gamestudio.service.exception.ScoreException;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;
    List<Score> getBestScores(String game) throws ScoreException;
}
