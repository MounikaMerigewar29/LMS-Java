//package ui;
//
//public class ViewNotificationsFrame {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}


package ui;

import dao.NotificationDAO;
import model.Notification;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewNotificationsFrame extends JFrame {
    private JTable tblNotifications;
    private DefaultTableModel tableModel;
    private String memberId;

    public ViewNotificationsFrame(String memberId) {
        this.memberId = memberId;

        setTitle("My System Notifications Alerts");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("Your System Notifications");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Data Table Configuration
        String[] columns = {"Date Sent", "Message Alert Details", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        tblNotifications = new JTable(tableModel);
        add(new JScrollPane(tblNotifications), BorderLayout.CENTER);

        // Close Control Button
        JPanel pnlBottom = new JPanel();
        JButton btnClose = new JButton("Dismiss View");
        btnClose.addActionListener(ev -> dispose());
        pnlBottom.add(btnClose);
        add(pnlBottom, BorderLayout.SOUTH);

        loadMemberNotifications();
    }

    private void loadMemberNotifications() {
        tableModel.setRowCount(0); // Wipe old entries clean

        NotificationDAO dao = new NotificationDAO();
        // Dynamically fetch from your custom DAO method logic using memberId
        List<Notification> list = dao.getNotificationsByMember(memberId);

        if (list.isEmpty()) {
            tableModel.addRow(new Object[]{"-", "No new inbox messages found.", "-"});
            return;
        }

        for (Notification notif : list) {
            Object[] row = {
                notif.getNotificationDate(),
                notif.getMessage(),
                notif.getStatus()
            };
            tableModel.addRow(row);
        }
    }
}