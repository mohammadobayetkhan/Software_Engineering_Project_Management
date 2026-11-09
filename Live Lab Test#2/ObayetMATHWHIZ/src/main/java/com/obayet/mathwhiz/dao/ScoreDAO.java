
package com.obayet.mathwhiz.dao;

import com.obayet.mathwhiz.model.GameScore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {

    public boolean saveScore(GameScore gameScore) {

        String sql = "INSERT INTO game_scores " +
                "(student_id, player_name, score, difficulty) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, gameScore.getStudentId());
            statement.setString(2, gameScore.getPlayerName());
            statement.setInt(3, gameScore.getScore());
            statement.setString(4, gameScore.getDifficulty());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<GameScore> getTop10Scores() {

        List<GameScore> scores = new ArrayList<>();

        String sql = "SELECT * FROM game_scores " +
                "ORDER BY score DESC, played_at ASC LIMIT 10";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                GameScore gameScore = new GameScore();

                gameScore.setId(resultSet.getInt("id"));
                gameScore.setStudentId(resultSet.getString("student_id"));
                gameScore.setPlayerName(resultSet.getString("player_name"));
                gameScore.setScore(resultSet.getInt("score"));
                gameScore.setDifficulty(resultSet.getString("difficulty"));
                gameScore.setPlayedAt(resultSet.getTimestamp("played_at"));

                scores.add(gameScore);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scores;
    }
}
