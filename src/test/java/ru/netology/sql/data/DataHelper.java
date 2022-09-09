package ru.netology.sql.data;


import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {

    }

    public static User getFirstUserInfo() {
        return new User("vasya", "qwerty123");
    }

    public static User getSecondUserInfo() {
        return new User("petya", "123qwerty");
    }

    public static User getIncorrectUserInfo() {
        return new User("vasya", "11111111");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @SneakyThrows
    public static String getVerificationCode(User info) {
        var codeSQL = "SELECT code FROM auth_codes WHERE user_id = (SELECT id FROM users WHERE login = ?) ORDER BY created DESC;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "user", "pass"
                );
        ) {
            return runner.query(conn, codeSQL, info.getLogin(), new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void shouldClearDataFromTables() {
        var deleteUsers = "DELETE FROM users;";
        var deleteCards = "DELETE FROM cards;";
        var deleteCodes = "DELETE FROM auth_codes;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "user", "pass"
                );
        ) {
            runner.update(conn, deleteCards);
            runner.update(conn, deleteCodes);
            runner.update(conn, deleteUsers);
        }

    }


}
