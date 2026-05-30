//package ui;
//
//public class NotificationFrame {
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
import javax.swing.*;

public class NotificationFrame extends JFrame {
    private JTextField txtMemId;
    private JTextArea txtMessage;
    private JButton btnSend, btnCancel;

    public NotificationFrame() {
        setTitle("Send System Notifications");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(142, 68, 173));
        JLabel lblTitle = new JLabel("Create System Alert");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Form Panel
        JPanel pnlForm = new JPanel(new BorderLayout(5, 10));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel pnlTop = new JPanel(new BorderLayout(10, 0));
        pnlTop.add(new JLabel("Target Member ID: "), BorderLayout.WEST);
        txtMemId = new JTextField();
        pnlTop.add(txtMemId, BorderLayout.CENTER);
        pnlForm.add(pnlTop, BorderLayout.NORTH);

        JPanel pnlBody = new JPanel(new BorderLayout());
        pnlBody.add(new JLabel("Message Content:"), BorderLayout.NORTH);
        txtMessage = new JTextArea(6, 20);
        txtMessage.setLineWrap(true);
        txtMessage.setWrapStyleWord(true);
        pnlBody.add(new JScrollPane(txtMessage), BorderLayout.CENTER);
        pnlForm.add(pnlBody, BorderLayout.CENTER);

        add(pnlForm, BorderLayout.CENTER);

        // Buttons Panel
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnSend = new JButton("Send Alert");
        btnCancel = new JButton("Cancel");
        pnlButtons.add(btnSend);
        pnlButtons.add(btnCancel);
        add(pnlButtons, BorderLayout.SOUTH);

        // Logic Implementation
        btnSend.addActionListener(e -> {
            String memId = txtMemId.getText().trim();
            String message = txtMessage.getText().trim();

            if (memId.isEmpty() || message.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Both fields are mandatory.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create and populate the Notification model object to match your DAO
            Notification notification = new Notification();
            notification.setMemId(memId);
            notification.setMessage(message);
            notification.setStatus("Unread"); // Default status

            // Call the updated DAO method
            NotificationDAO notifDAO = new NotificationDAO();
            boolean success = notifDAO.addNotification(notification);

            if (success) {
                JOptionPane.showMessageDialog(this, "Notification sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to send notification. Please verify the Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
    }
}