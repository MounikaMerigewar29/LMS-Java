//package dao;
//
//public class BorrowRecordDAO {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

//This file handles tracking when a member issues a book, returns it, and updates any calculated fine data.
package dao;

import db.DBConnection;
import model.BorrowRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAO {

    // Issue Book
    public boolean issueBook(BorrowRecord record) {

        String query =
                "INSERT INTO borrow_record(mem_id,book_id,issue_date,due_date,status) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, record.getMemId());
            ps.setString(2, record.getBookId());
            ps.setDate(3, record.getIssueDate());
            ps.setDate(4, record.getDueDate());
            ps.setString(5, record.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Return Book
    public boolean returnBook(int recordId,
                              Date returnDate,
                              double fine) {

        String query =
                "UPDATE borrow_record SET return_date=?, fine=?, status='Returned' WHERE record_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setDate(1, returnDate);
            ps.setDouble(2, fine);
            ps.setInt(3, recordId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get Active Borrow Record
    public BorrowRecord getActiveBorrowRecord(String memId,
                                              String bookId) {

        String query =
                "SELECT * FROM borrow_record WHERE mem_id=? AND book_id=? AND status='Issued'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, memId);
            ps.setString(2, bookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                BorrowRecord record = new BorrowRecord();

                record.setRecordId(rs.getInt("record_id"));
                record.setMemId(rs.getString("mem_id"));
                record.setBookId(rs.getString("book_id"));
                record.setIssueDate(rs.getDate("issue_date"));
                record.setDueDate(rs.getDate("due_date"));
                record.setReturnDate(rs.getDate("return_date"));
                record.setFine(rs.getDouble("fine"));
                record.setStatus(rs.getString("status"));

                return record;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Borrow History of Member
    public List<BorrowRecord> getBorrowHistory(String memId) {

        List<BorrowRecord> records = new ArrayList<>();

        String query =
                "SELECT * FROM borrow_record WHERE mem_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, memId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BorrowRecord record = new BorrowRecord();

                record.setRecordId(rs.getInt("record_id"));
                record.setMemId(rs.getString("mem_id"));
                record.setBookId(rs.getString("book_id"));
                record.setIssueDate(rs.getDate("issue_date"));
                record.setDueDate(rs.getDate("due_date"));
                record.setReturnDate(rs.getDate("return_date"));
                record.setFine(rs.getDouble("fine"));
                record.setStatus(rs.getString("status"));

                records.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    // View All Borrow Records
    public List<BorrowRecord> getAllBorrowRecords() {

        List<BorrowRecord> records = new ArrayList<>();

        String query = "SELECT * FROM borrow_record";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                BorrowRecord record = new BorrowRecord();

                record.setRecordId(rs.getInt("record_id"));
                record.setMemId(rs.getString("mem_id"));
                record.setBookId(rs.getString("book_id"));
                record.setIssueDate(rs.getDate("issue_date"));
                record.setDueDate(rs.getDate("due_date"));
                record.setReturnDate(rs.getDate("return_date"));
                record.setFine(rs.getDouble("fine"));
                record.setStatus(rs.getString("status"));

                records.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}