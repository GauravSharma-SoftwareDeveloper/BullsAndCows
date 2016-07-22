package com.example.itachi.bullsandcows;

/**
 * Created by itachi on 23/5/16.
 */
public class PlayerInfo {

    private static int _id;
    private static String _name;
    private static String _username;
    private static String _password;
    private static int _score;


    public PlayerInfo(String _name) {
        this._name = _name;
    }

    public PlayerInfo(String name,String username ,String password) {
        _name=name;
        _username=username;
        _password=password;
        _score=0;
    }

    /**
     * Getters  And Setters
     **/
    public static int get_id() {
        return _id;
    }

    public static String get_username() {
        return _username;
    }

    public static String get_name() {
        return _name;
    }

    public static String get_password() {
        return _password;
    }

    public static int get_score() {
        return _score;
    }

    public static void set_id(int _id) {
        PlayerInfo._id = _id;
    }

    public static void set_score(int _score) {
        PlayerInfo._score = _score;
    }

    public static void set_username(String _username) {
        PlayerInfo._username = _username;
    }

    public static void set_name(String _name) {
        PlayerInfo._name = _name;
    }

    public static void set_password(String _password) {
        PlayerInfo._password = _password;
    }

    /**   Getters  And Setters   **/

}
