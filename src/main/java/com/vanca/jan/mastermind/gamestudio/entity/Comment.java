package com.vanca.jan.mastermind.gamestudio.entity;

import java.util.Date;

public class Comment {
    private String player;
    private String game;
    private String comment;
    private Date commentedOn;

    public Comment(String player, String game, String comment, Date commentedOn) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
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
     * @return Current comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment Comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return The date when the comment was added.
     */
    public Date getCommentedOn() {
        return commentedOn;
    }

    /**
     * @param commentedOn Date of comment to set.
     */
    public void setCommentedOn(Date commentedOn) {
        this.commentedOn = commentedOn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("player='").append(player).append('\'');
        sb.append(", game='").append(game).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", commentedOn=").append(commentedOn);
        sb.append('}');
        return sb.toString();
    }
}
