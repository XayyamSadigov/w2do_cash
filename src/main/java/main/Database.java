package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static final String db = "src\\main\\java\\main\\db.s3db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + db);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static List<Record> getData() {
        try {
            List<Record> list = new ArrayList<>();
            Record handObj;
            String sql = "SELECT * FROM HANDS";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                handObj = new Record(resultSet.getString("hand"),
                        resultSet.getString("position"),
                        resultSet.getString("vs"),
                        resultSet.getString("action"),
                        resultSet.getInt("percentage"),
                        resultSet.getString("result"));
                list.add(handObj);
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
}
