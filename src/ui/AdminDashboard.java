//package ui;
//
//public class AdminDashboard {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame implements ActionListener {

    // Buttons
    private JButton btnAddBook;
    private JButton btnIssueBook;
    private JButton btnReturnBook;
    private JButton btnViewBooks;
    private JButton btnLogout;

    // Title Label
    private JLabel lblTitle;

    public AdminDashboard() {

        // Frame Settings
        setTitle("Admin Dashboard - Library Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        lblTitle = new JLabel("Library Management System - Admin Dashboard",
                JLabel.CENTER);

        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        add(lblTitle, BorderLayout.NORTH);

        // Panel for Buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 15, 15));

        // Initialize Buttons
        btnAddBook = new JButton("Add Book");
        btnIssueBook = new JButton("Issue Book");
        btnReturnBook = new JButton("Return Book");
        btnViewBooks = new JButton("View Books");
        btnLogout = new JButton("Logout");

        // Set Font
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        btnAddBook.setFont(buttonFont);
        btnIssueBook.setFont(buttonFont);
        btnReturnBook.setFont(buttonFont);
        btnViewBooks.setFont(buttonFont);
        btnLogout.setFont(buttonFont);

        // Add Action Listeners
        btnAddBook.addActionListener(this);
        btnIssueBook.addActionListener(this);
        btnReturnBook.addActionListener(this);
        btnViewBooks.addActionListener(this);
        btnLogout.addActionListener(this);

        // Add Buttons to Panel
        panel.add(btnAddBook);
        panel.add(btnIssueBook);
        panel.add(btnReturnBook);
        panel.add(btnViewBooks);
        panel.add(btnLogout);

        // Add Panel to Frame
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Add Book
        if (e.getSource() == btnAddBook) {

            new AddBookFrame();
        }

        // Issue Book
        else if (e.getSource() == btnIssueBook) {

            JOptionPane.showMessageDialog(this,
                    "Issue Book Module Opening...");
                    
            // new IssueBookFrame();
        }

        // Return Book
        else if (e.getSource() == btnReturnBook) {

            JOptionPane.showMessageDialog(this,
                    "Return Book Module Opening...");
                    
            // new ReturnBookFrame();
        }

        // View Books
        else if (e.getSource() == btnViewBooks) {

            JOptionPane.showMessageDialog(this,
                    "View Books Module Opening...");
                    
            // new ViewBooksFrame();
        }

        // Logout
        else if (e.getSource() == btnLogout) {

            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {

                dispose();

                new LoginFrame();
            }
        }
    }
}