//package model;
//
//public class Notification {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package model;

import java.sql.Timestamp;

public class Notification {

    private int notificationId;
    private String memId;
    private String message;
    private Timestamp notificationDate;
    private String status;

    public Notification() {
    }

    public Notification(int notificationId, String memId,
                        String message, Timestamp notificationDate,
                        String status) {
        this.notificationId = notificationId;
        this.memId = memId;
        this.message = message;
        this.notificationDate = notificationDate;
        this.status = status;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}