package am.itspace.myfriendee.service;

import am.itspace.myfriendee.db.DBConnectionProvider;
import am.itspace.myfriendee.model.FriendRequest;
import am.itspace.myfriendee.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendRequestService {


    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserService userService = new UserService();

    public void add(int fromId, int toId) {
        String sql = "INSERT INTO friend_request(from_id,to_id, date) VALUES " +
                "(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, fromId);
            preparedStatement.setInt(2, toId);
            preparedStatement.setString(3, DateUtil.fromDateToSqlDateTimeString(new Date()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FriendRequest> getFriendRequestsByToId(int toId) {
        String sql = "SELECT * from friend_request WHERE to_id = " + toId;
        List<FriendRequest> friendRequestList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                friendRequestList.add(FriendRequest.builder()
                        .id(resultSet.getInt(1))
                        .fromUser(userService.getUserById(resultSet.getInt(2)))
                        .date(DateUtil.fromSqlStringToDate(resultSet.getString(4)))
                        .build());
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return friendRequestList;
    }


    public List<FriendRequest> getFriendRequestsByFromId(int fromId) {
        String sql = "SELECT * from friend_request WHERE from_id = " + fromId;
        List<FriendRequest> friendRequestList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                friendRequestList.add(FriendRequest.builder()
                        .id(resultSet.getInt(1))
                        .toUser(userService.getUserById(resultSet.getInt(3)))
                        .date(DateUtil.fromSqlStringToDate(resultSet.getString(4)))
                        .build());
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return friendRequestList;
    }

    public FriendRequest getFriendRequestById(int requestId) {
        String sql = "SELECT * from friend_request WHERE id = " + requestId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return FriendRequest.builder()
                        .id(resultSet.getInt(1))
                        .fromUser(userService.getUserById(resultSet.getInt(2)))
                        .toUser(userService.getUserById(resultSet.getInt(3)))
                        .date(DateUtil.fromSqlStringToDate(resultSet.getString(4)))
                        .build();
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFriendRequest(int requestId) {
        String sql = "DELETE FROM friend_request WHERE id = " + requestId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
