package com.vanca.jan.mastermind.gamestudio.entity;

import java.util.Date;

public class Rating {
    private String player;
    private String game;
    private int rating;
    private Date ratedOn;

    public Rating(String player, String game, int rating, Date ratedOn) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
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
     * @return Current rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating Rating to set.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return The date when the rate was added.
     */
    public Date getRatedOn() {
        return ratedOn;
    }

    /**
     * @param ratedOn Date of rate to set.
     */
    public void setRatedOn(Date ratedOn) {
        this.ratedOn = ratedOn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rating{");
        sb.append("player='").append(player).append('\'');
        sb.append(", game='").append(game).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", ratedOn=").append(ratedOn);
        sb.append('}');
        return sb.toString();
    }
}