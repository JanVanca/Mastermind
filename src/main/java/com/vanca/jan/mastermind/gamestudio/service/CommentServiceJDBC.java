package com.vanca.jan.mastermind.gamestudio.service;

import com.vanca.jan.mastermind.gamestudio.entity.Comment;
import com.vanca.jan.mastermind.gamestudio.service.exception.CommentException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJDBC implements CommentService, DatabaseSettings {


    public static final String INSERT_COMMENT =
            "INSERT INTO comment (game, player, comment, commentedon) VALUES (?, ?, ?, ?)";

    public static final String SELECT_COMMENT =
            "SELECT player, game, comment, commentedon FROM comment WHERE game = ?;";

    /**
     * Connects and adds a comment to the database.
     *
     * @param comment Entered comment.
     * @throws CommentException
     */
    @Override
    public void addComment(Comment comment) throws CommentException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT)) {
                ps.setString(1, comment.getPlayer());
                ps.setString(2, comment.getGame());
                ps.setString(3, comment.getComment());
                ps.setDate(4, new Date(comment.getCommentedOn().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new CommentException("Error saving comment", e);
        }
    }

    /**
     * Connects and return a comment from the database.
     *
     * @param game Current game.
     * @return Comment.
     * @throws CommentException
     */
    @Override
    public List<Comment> getComments(String game) throws CommentException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_COMMENT)) {
                ps.setString(1, game);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Comment comment = new Comment(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getTimestamp(4)
                        );
                        comments.add(comment);
                    }
                }
            }
        } catch (SQLException e) {
            throw new CommentException("Error loading comment", e);
        }
        return comments;
    }
}
