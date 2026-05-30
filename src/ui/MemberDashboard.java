//package ui;
//
//public class MemberDashboard {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package ui;

import model.Member;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberDashboard extends JFrame implements ActionListener {

    // Labels
    private JLabel lblTitle;

    // Buttons
    private JButton btnViewBooks;
    private JButton btnBorrowedBooks;
    private JButton btnNotifications;
    private JButton btnLogout;
    
    // Store logged-in member session dynamically
    private Member currentMember;

    public MemberDashboard(Member member) {
        this.currentMember = member;

        // Frame Settings
        setTitle("Member Dashboard - Welcome " + currentMember.getName());
        setSize(550, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Label
        lblTitle = new JLabel("Welcome, " + currentMember.getName(), JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitle, BorderLayout.NORTH);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        // Initialize Buttons
        btnViewBooks = new JButton("View Available Books");
        btnBorrowedBooks = new JButton("My Borrowed Books");
        btnNotifications = new JButton("Notifications");
        btnLogout = new JButton("Logout");

        // Set Button Font
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        btnViewBooks.setFont(buttonFont);
        btnBorrowedBooks.setFont(buttonFont);
        btnNotifications.setFont(buttonFont);
        btnLogout.setFont(buttonFont);

        // Add Action Listeners
        btnViewBooks.addActionListener(this);
        btnBorrowedBooks.addActionListener(this);
        btnNotifications.addActionListener(this);
        btnLogout.addActionListener(this);

        // Add Buttons to Panel
        panel.add(btnViewBooks);
        panel.add(btnBorrowedBooks);
        panel.add(btnNotifications);
        panel.add(btnLogout);

        // Add Panel to Frame
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // View Books
        if (e.getSource() == btnViewBooks) {
            // Opens your existing View Books script layout smoothly
            new ViewBooksFrame().setVisible(true);
        }

        // Borrowed Books
        else if (e.getSource() == btnBorrowedBooks) {
            // Opens history panel passing student unique ID dynamically
            new MyBorrowedBooksFrame(currentMember.getMemId()).setVisible(true);
        }

        // Notifications
        else if (e.getSource() == btnNotifications) {
            // Opens client message box passing student unique ID dynamically
            new ViewNotificationsFrame(currentMember.getMemId()).setVisible(true);
        }

        // Logout
        else if (e.getSource() == btnLogout) {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame();
            }
        }
    }
}