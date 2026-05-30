//package ui;
//
//public class MyBorrowedBooksFrame {
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
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyBorrowedBooksFrame extends JFrame {
    private JTable tblHistory;
    private DefaultTableModel tableModel;
    private String memberId;

    public MyBorrowedBooksFrame(String memberId) {
        this.memberId = memberId;

        setTitle("My Borrowed Books Log Ledger");
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(39, 174, 96));
        JLabel lblTitle = new JLabel("Your Active & Past Borrow Records");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Table Grid Columns Configuration
        String[] columns = {"Record ID", "Book ID", "Issue Date", "Due Date", "Return Date", "Fine ($)", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        tblHistory = new JTable(tableModel);
        add(new JScrollPane(tblHistory), BorderLayout.CENTER);

        // Dismiss action
        JPanel pnlBottom = new JPanel();
        JButton btnClose = new JButton("Close View");
        btnClose.addActionListener(ev -> dispose());
        pnlBottom.add(btnClose);
        add(pnlBottom, BorderLayout.SOUTH);

        loadBorrowHistory();
    }

    private void loadBorrowHistory() {
        tableModel.setRowCount(0);

        BorrowRecordDAO dao = new BorrowRecordDAO();
        // Uses your exact method from BorrowRecordDAO to fetch historical records
        List<BorrowRecord> historyList = dao.getBorrowHistory(memberId);

        for (BorrowRecord rec : historyList) {
            Object[] row = {
                rec.getRecordId(),
                rec.getBookId(),
                rec.getIssueDate(),
                rec.getDueDate(),
                rec.getReturnDate() == null ? "Not Returned Yet" : rec.getReturnDate(),
                rec.getFine(),
                rec.getStatus()
            };
            tableModel.addRow(row);
        }
    }
}