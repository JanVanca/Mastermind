package com.vanca.jan.mastermind.console;

import com.vanca.jan.mastermind.gamestudio.entity.Comment;
import com.vanca.jan.mastermind.gamestudio.entity.Rating;
import com.vanca.jan.mastermind.gamestudio.entity.Score;
import com.vanca.jan.mastermind.gamestudio.service.CommentServiceJDBC;
import com.vanca.jan.mastermind.gamestudio.service.RatingServiceJDBC;
import com.vanca.jan.mastermind.gamestudio.service.ScoreServiceJDBC;
import com.vanca.jan.mastermind.gamestudio.service.exception.CommentException;
import com.vanca.jan.mastermind.gamestudio.service.exception.RatingException;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServiceConsole {
    private Scanner input = new Scanner(System.in);
    private String game = "mastermind";

    private ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();
    private CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();
    private RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();

    public ServiceConsole() {

    }

    /**
     * Starts the service console.
     */
    public void start() {
        int choice = 0;
        System.out.println("MASTERMIND");
        while (choice != 8) {
            System.out.println("Enter your choice:");
            System.out.println("1. Save score");
            System.out.println("2. Show score");
            System.out.println("3. Add comment");
            System.out.println("4. Read comments");
            System.out.println("5. Rate game");
            System.out.println("6. Load rates");
            System.out.println("7. Show average rates");
            System.out.println("8. Exit game");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    saveScore();
                    break;
                case 2:
                    loadScore();
                    break;
                case 3:
                    addComment();
                    break;
                case 4:
                    loadComment();
                    break;
                case 5:
                    rateGame();
                    break;
                case 6:
                    loadRate();
                    break;
                case 7:
                    averageRating();
                    break;
            }
        }
    }

    /**
     * Save the score to database.
     */
    private void saveScore() {
        Score score = new Score("mines", "skuska", 100, new java.util.Date());
        scoreServiceJDBC.addScore(score);
    }

    /**
     * Load score from database.
     */
    private void loadScore() {
        List<Score> scores = scoreServiceJDBC.getBestScores(game);
        for (Score score1 : scores) {
            System.out.println(score1);
        }
//        solution 2
//        for (int i = 0; i < scores.size(); i++) {
//            System.out.println(scores.get(i));
//        }
    }

    /**
     * Save comments to database.
     */
    private void addComment() {
        try {
            String player = "";
            String comment = "";
            System.out.println("Ented name: ");
            player = input.nextLine();
            System.out.println("Enter your comment: ");
            comment = input.nextLine();
            Comment addComment = new Comment(player, game, comment, new Date());
            commentServiceJDBC.addComment(addComment);
        } catch (CommentException e) {
            e.printStackTrace();
            System.out.println("Failed to save comment");
        }
    }

    /**
     * Load comment from database.
     */
    private void loadComment() {
        try {
            List<Comment> comments = commentServiceJDBC.getComments(game);
            for (Comment comment1 : comments) {
                System.out.println(comment1);
            }
//            solution 2
//            for (int i = 0; i < comments.size(); i++) {
//                System.out.println(comments.get(i));
//            }
        } catch (CommentException e) {
            e.printStackTrace();
            System.out.println("Failed to load comment");
        }
    }

    /**
     * Saves the game rating to the database
     */
    private void rateGame() {
        try {
            String player = "";
            int rating = 0;
            System.out.println("Enter name: ");
            player = input.nextLine();
            System.out.println("Enter the value from 1 (the worst) to 5 (the best): ");
            rating = input.nextInt();
            Rating addrating = new Rating(player, game, rating, new Date());
            ratingServiceJDBC.setRating(addrating);
        } catch (RatingException e) {
            e.printStackTrace();
            System.out.println("Failed to add game rating");
        }
    }

    /**
     * Loads the saved game rating from the database
     */
    private void loadRate() {
        try {
            String player = "";
            System.out.println("Enter name");
            player = input.nextLine();
            int rate = ratingServiceJDBC.getRating(player, game);
            if (rate != 0) {
                System.out.println("User rating: " + rate);
            } else {
                System.out.println("User has not entered a rating yet");
            }
        } catch (RatingException e) {
            e.printStackTrace();
            System.out.println("Rating failed to load");
        }
    }

    /**
     * Loads the average rating
     */
    private void averageRating() {
        try {
            int rate = ratingServiceJDBC.getAverageRating(game);
            System.out.println("Average game rating: " + rate);
        } catch (RatingException e) {
            e.printStackTrace();
            System.out.println("The average rating of the game failed to load");
        }
    }
}