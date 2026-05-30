//package dao;
//
//public class NotificationDAO {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

//This updates or adds messages for members regarding due date reminders or general announcements.
package dao;

import db.DBConnection;
import model.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    // Add Notification
    public boolean addNotification(Notification notification) {

        String query =
                "INSERT INTO notification(mem_id,message,status) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, notification.getMemId());
            ps.setString(2, notification.getMessage());
            ps.setString(3, notification.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get Notifications By Member
    public List<Notification> getNotificationsByMember(String memId) {

        List<Notification> notifications =
                new ArrayList<>();

        String query =
                "SELECT * FROM notification WHERE mem_id=? ORDER BY notification_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, memId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Notification notification =
                        new Notification();

                notification.setNotificationId(
                        rs.getInt("notification_id"));

                notification.setMemId(
                        rs.getString("mem_id"));

                notification.setMessage(
                        rs.getString("message"));

                notification.setNotificationDate(
                        rs.getTimestamp("notification_date"));

                notification.setStatus(
                        rs.getString("status"));

                notifications.add(notification);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }

    // Mark Notification As Read
    public boolean markAsRead(int notificationId) {

        String query =
                "UPDATE notification SET status='READ' WHERE notification_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, notificationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get All Notifications
    public List<Notification> getAllNotifications() {

        List<Notification> notifications =
                new ArrayList<>();

        String query =
                "SELECT * FROM notification ORDER BY notification_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Notification notification =
                        new Notification();

                notification.setNotificationId(
                        rs.getInt("notification_id"));

                notification.setMemId(
                        rs.getString("mem_id"));

                notification.setMessage(
                        rs.getString("message"));

                notification.setNotificationDate(
                        rs.getTimestamp("notification_date"));

                notification.setStatus(
                        rs.getString("status"));

                notifications.add(notification);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }
}