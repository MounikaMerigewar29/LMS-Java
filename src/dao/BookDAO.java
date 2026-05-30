//package dao;
//
//public class BookDAO {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

//This file allows the admin to add new books to the inventory and lets students search for books.
package dao;

import db.DBConnection;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Add a new book to the database
    public boolean addBook(String bookId, String title, String author, String category, String publisher, int quantity, String isbn) {
        String query = "INSERT INTO book (book_id, title, author, category, publisher, quantity, available_quantity, isbn) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, bookId);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, category);
            stmt.setString(5, publisher);
            stmt.setInt(6, quantity);
            stmt.setInt(7, quantity); // Initially, available quantity equals total quantity
            stmt.setString(8, isbn);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Search for books by title or category
    
    public boolean addBook(Book book) {

        String query =
                "INSERT INTO book(book_id,title,author,category,publisher,quantity,available_quantity,isbn) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, book.getBookId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setString(5, book.getPublisher());
            ps.setInt(6, book.getQuantity());
            ps.setInt(7, book.getAvailableQuantity());
            ps.setString(8, book.getIsbn());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean deleteBook(String bookId) {

        String query =
                "DELETE FROM book WHERE book_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bookId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();

        String query =
                "SELECT * FROM book";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getString("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setPublisher(rs.getString("publisher"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailableQuantity(rs.getInt("available_quantity"));
                book.setIsbn(rs.getString("isbn"));
                book.setCreatedAt(rs.getTimestamp("created_at"));

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
    
    public boolean updateAvailableQuantity(
            String bookId,
            int availableQuantity) {

        String query =
                "UPDATE book SET available_quantity=? WHERE book_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, availableQuantity);
            ps.setString(2, bookId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Book> searchBooks(String keyword) {

        List<Book> books = new ArrayList<>();

        String query =
                "SELECT * FROM book WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String search = "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getString("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setPublisher(rs.getString("publisher"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailableQuantity(rs.getInt("available_quantity"));
                book.setIsbn(rs.getString("isbn"));
                book.setCreatedAt(rs.getTimestamp("created_at"));

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
    
    public Book getBookById(String bookId) {

        String query =
                "SELECT * FROM book WHERE book_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getString("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setPublisher(rs.getString("publisher"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailableQuantity(rs.getInt("available_quantity"));
                book.setIsbn(rs.getString("isbn"));
                book.setCreatedAt(rs.getTimestamp("created_at"));

                return book;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}