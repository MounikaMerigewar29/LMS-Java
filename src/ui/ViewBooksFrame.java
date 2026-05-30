//package ui;
//
//public class ViewBooksFrame {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}


package ui;

import db.DBConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewBooksFrame extends JFrame {
    private JTable tblBooks;
    private DefaultTableModel tableModel;

    public ViewBooksFrame() {
        setTitle("View Books Catalogue");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Book ID", "Title", "Author", "Category", "Publisher", "Total Qty", "Available Qty", "ISBN"};
        tableModel = new DefaultTableModel(columns, 0);
        tblBooks = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblBooks);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel();
        JButton btnClose = new JButton("Close Window");
        btnClose.addActionListener(e -> dispose());
        pnlBottom.add(btnClose);
        add(pnlBottom, BorderLayout.SOUTH);

        loadBookData();
    }

    private void loadBookData() {
    	tableModel.setRowCount(0);
        String query = "SELECT * FROM book";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("publisher"),
                    rs.getInt("quantity"),
                    rs.getInt("available_quantity"),
                    rs.getString("isbn")
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load catalogue.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}