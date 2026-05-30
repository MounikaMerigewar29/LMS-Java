//package ui;
//
//public class ReturnBookFrame {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package ui;

import dao.BorrowRecordDAO;
import java.awt.*;
import java.sql.Date;
import javax.swing.*;

public class ReturnBookFrame extends JFrame {
    private JTextField txtRecordId, txtFine;
    private JButton btnReturn, btnCancel;

    public ReturnBookFrame() {
        setTitle("Return Book - Library Management System");
        setSize(400, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(39, 174, 96));
        JLabel lblTitle = new JLabel("Process Book Return");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlForm = new JPanel(new GridLayout(2, 2, 10, 15));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        pnlForm.add(new JLabel("Record ID:"));
        txtRecordId = new JTextField();
        pnlForm.add(txtRecordId);

        pnlForm.add(new JLabel("Fine Amount:"));
        txtFine = new JTextField("0.00");
        pnlForm.add(txtFine);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnReturn = new JButton("Submit Return");
        btnCancel = new JButton("Cancel");
        pnlButtons.add(btnReturn);
        pnlButtons.add(btnCancel);
        add(pnlButtons, BorderLayout.SOUTH);

        btnReturn.addActionListener(e -> {
            try {
                int recordId = Integer.parseInt(txtRecordId.getText().trim());
                double fine = Double.parseDouble(txtFine.getText().trim());

                Date returnDate = new Date(System.currentTimeMillis());
                
                BorrowRecordDAO recordDAO = new BorrowRecordDAO();
                boolean success = recordDAO.returnBook(recordId, returnDate, fine);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Return failed. Verify Record ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
    }
}