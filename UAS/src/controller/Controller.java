package controller;
import model.Games;
import model.Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;

public class Controller {
         static DatabaseHandler conn = new DatabaseHandler();
        public boolean login(String email, String password) {
            try {
                conn.open();

                PreparedStatement statement = conn.connection.prepareStatement("SELECT * FROM Users WHERE email = ? AND password = ?");
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {

                    return true;
                }
            } catch (Exception ex) {
                new ExceptionLogger(ex.getMessage());

            }
            return false;
        }

    public String[] listGame() {
        try {
            conn.open();
            Statement statement = conn.connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT `name` FROM `games`");

            ArrayList<String> listFNB = new ArrayList<String>();
            while (result.next()) {
                listFNB.add(result.getString("name"));
            }
            result.close();
            statement.close();
            conn.close();

            return listFNB.toArray(new String[listFNB.size()]);
        } catch (Exception ex) {
            new ExceptionLogger(ex.getMessage());
            return null;
        }
    }
    public Users getUser (String email){
        try {
            conn.open();

            Statement statement = conn.connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM `users` WHERE `email`='" + email + "'");

            result.next();


            Users users = null;
            users = new Users(
                    result.getInt("id"),
                    result.getString("nama"),
                    result.getString("email"),
                    result.getString("password")
            );

            result.close();
            statement.close();
            conn.close();

            return users;
        } catch (Exception ex) {
            new ExceptionLogger(ex.getMessage());
            return null;
        }

    }

    public Games getGamesbyName (String gamename){
        try {
            conn.open();

            Statement statement = conn.connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM `games` WHERE `name`='" + gamename + "'");

            result.next();


            Games games = null;
            games = new Games(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("genre"),
                    result.getInt("price")
            );

            result.close();
            statement.close();
            conn.close();

            return games;
        } catch (Exception ex) {
            new ExceptionLogger(ex.getMessage());
            return null;
        }

    }
    public boolean buyGames(String emailuser, String gameName){
            int userId = getUser(emailuser).getId();
            int gameID = getGamesbyName(gameName).getId();
        try{
            conn.open();

            Statement statement = conn.connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO `games` (`user_id`,`game_id`) VALUES ('"+userId+"','"+gameID+"')");
            statement.close();
            conn.close();
            return true;
        } catch (Exception ex){
            new ExceptionLogger(ex.getMessage());
            return false;
        }
    }




}
