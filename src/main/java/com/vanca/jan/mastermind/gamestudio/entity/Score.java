package com.vanca.jan.mastermind.gamestudio.entity;

import java.io.Serializable;
import java.util.Date;

public class Score implements Comparable<Score>, Serializable {
    private String game;
    private String player;
    private int points;
    private Date playedOn;

    public Score(String game, String player, int points, Date playedOn) {
        this.game = game;
        this.player = player;
        this.points = points;
        this.playedOn = playedOn;
    }

    /**
     * @return Current game.
     */
    public String getGame() {
        return game;
    }

    /**
     * @param game Game to set.
     */
    public void setGame(String game) {
        this.game = game;
    }

    /**
     * @return Current player.
     */
    public String getPlayer() {
        return player;
    }

    /**
     * @param player Player to set.
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * @return Current points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points Points to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return The date when the game was played.
     */
    public Date getPlayedOn() {
        return playedOn;
    }

    /**
     * @param playedOn Date of game played to set.
     */
    public void setPlayedOn(Date playedOn) {
        this.playedOn = playedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o The object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Score o) {
        if (o == null) return -1;
        return this.getPoints() - o.getPoints();
    }
}
