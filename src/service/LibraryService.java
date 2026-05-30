//package service;
//
//public class LibraryService {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package service;

import dao.BookDAO;
import dao.BorrowRecordDAO;
import dao.NotificationDAO;

import model.Book;
import model.BorrowRecord;
import model.Notification;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LibraryService {

    private BookDAO bookDAO;
    private BorrowRecordDAO borrowRecordDAO;
    private NotificationDAO notificationDAO;
    private FineService fineService;

    public LibraryService() {

        bookDAO = new BookDAO();
        borrowRecordDAO = new BorrowRecordDAO();
        notificationDAO = new NotificationDAO();
        fineService = new FineService();
    }

    // Add Book
    public boolean addBook(Book book) {

        return bookDAO.addBook(book);
    }

    // Delete Book
    public boolean deleteBook(String bookId) {

        return bookDAO.deleteBook(bookId);
    }

    // Search Books
    public List<Book> searchBooks(String keyword) {

        return bookDAO.searchBooks(keyword);
    }

    // Get Book Details
    public Book getBookById(String bookId) {

        return bookDAO.getBookById(bookId);
    }

    // View All Books
    public List<Book> getAllBooks() {

        return bookDAO.getAllBooks();
    }

    // Issue Book
    public boolean issueBook(String memId,
                             String bookId) {

        Book book = bookDAO.getBookById(bookId);

        if (book == null) {

            System.out.println("Book not found");
            return false;
        }

        if (book.getAvailableQuantity() <= 0) {

            System.out.println("Book not available");
            return false;
        }

        BorrowRecord record =
                new BorrowRecord();

        record.setMemId(memId);
        record.setBookId(bookId);

        record.setIssueDate(
                Date.valueOf(LocalDate.now()));

        record.setDueDate(
                Date.valueOf(
                        LocalDate.now().plusDays(14)));

        record.setStatus("Issued");

        boolean success =
                borrowRecordDAO.issueBook(record);

        if (success) {

            bookDAO.updateAvailableQuantity(
                    bookId,
                    book.getAvailableQuantity() - 1
            );

            Notification notification =
                    new Notification();

            notification.setMemId(memId);

            notification.setMessage(
                    "Book issued successfully: "
                            + book.getTitle());

            notification.setStatus("UNREAD");

            notificationDAO.addNotification(
                    notification);

            return true;
        }

        return false;
    }

    // Return Book
    public boolean returnBook(String memId,
                              String bookId) {

        BorrowRecord record =
                borrowRecordDAO
                        .getActiveBorrowRecord(
                                memId,
                                bookId);

        if (record == null) {

            System.out.println(
                    "No active borrow record found");

            return false;
        }

        LocalDate dueDate =
                record.getDueDate().toLocalDate();

        LocalDate returnDate =
                LocalDate.now();

        double fine =
                fineService.calculateFine(
                        dueDate,
                        returnDate);

        boolean success =
                borrowRecordDAO.returnBook(
                        record.getRecordId(),
                        Date.valueOf(returnDate),
                        fine);

        if (success) {

            Book book =
                    bookDAO.getBookById(bookId);

            if (book != null) {

                bookDAO.updateAvailableQuantity(
                        bookId,
                        book.getAvailableQuantity() + 1
                );
            }

            Notification notification =
                    new Notification();

            notification.setMemId(memId);

            notification.setMessage(
                    "Book returned successfully. Fine: ₹"
                            + fine);

            notification.setStatus("UNREAD");

            notificationDAO.addNotification(
                    notification);

            return true;
        }

        return false;
    }

    // Borrow History
    public List<BorrowRecord> getBorrowHistory(
            String memId) {

        return borrowRecordDAO
                .getBorrowHistory(memId);
    }

    // Notifications
    public List<Notification>
    getNotifications(String memId) {

        return notificationDAO
                .getNotificationsByMember(memId);
    }
}