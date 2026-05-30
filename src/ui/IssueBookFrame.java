//package ui;
//
//public class IssueBookFrame {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package ui;

import dao.BorrowRecordDAO;
import model.BorrowRecord;
import java.awt.*;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.*;

public class IssueBookFrame extends JFrame {
    private JTextField txtMemId, txtBookId;
    private JButton btnIssue, btnCancel;

    public IssueBookFrame() {
        setTitle("Issue Book - Library Management System");
        setSize(400, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("Issue a New Book");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlForm = new JPanel(new GridLayout(2, 2, 10, 15));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        pnlForm.add(new JLabel("Member ID:"));
        txtMemId = new JTextField();
        pnlForm.add(txtMemId);

        pnlForm.add(new JLabel("Book ID:"));
        txtBookId = new JTextField();
        pnlForm.add(txtBookId);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnIssue = new JButton("Issue Book");
        btnCancel = new JButton("Cancel");
        pnlButtons.add(btnIssue);
        pnlButtons.add(btnCancel);
        add(pnlButtons, BorderLayout.SOUTH);

        btnIssue.addActionListener(e -> {
            String memId = txtMemId.getText().trim();
            String bookId = txtBookId.getText().trim();

            if (memId.isEmpty() || bookId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Set up borrowing periods
            Date issueDate = new Date(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(issueDate);
            cal.add(Calendar.DAY_OF_YEAR, 14); // 14 days borrowing period
            Date dueDate = new Date(cal.getTimeInMillis());

            // Build the object to match your DAO method signature
            BorrowRecord record = new BorrowRecord();
            record.setMemId(memId);
            record.setBookId(bookId);
            record.setIssueDate(issueDate);
            record.setDueDate(dueDate);
            record.setStatus("Issued");

            BorrowRecordDAO recordDAO = new BorrowRecordDAO();
            boolean success = recordDAO.issueBook(record);

            if (success) {
                JOptionPane.showMessageDialog(this, "Book Issued Successfully!\nDue Date: " + dueDate, "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to issue book. Verify IDs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
    }
}