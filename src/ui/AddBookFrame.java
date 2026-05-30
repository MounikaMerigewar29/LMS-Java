package ui;

import model.Book;
import service.LibraryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookFrame extends JFrame implements ActionListener {
	
	public class Main {

	    public static void main(String[] args) {

	        new LoginFrame();
	    }
	}

    // Labels
    private JLabel lblBookId, lblTitle, lblAuthor, lblQuantity, lblCategory, lblISBN;

    // Text Fields
    private JTextField txtBookId, txtTitle, txtAuthor, txtQuantity, txtCategory, txtISBN;

    // Buttons
    private JButton btnAddBook, btnClear;

    // Service Object
    private LibraryService libraryService;

    public AddBookFrame() {

        libraryService = new LibraryService();

        // Frame Settings
        setTitle("Add Book");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        // Initialize Labels
        lblBookId = new JLabel("Book ID:");
        lblTitle = new JLabel("Title:");
        lblAuthor = new JLabel("Author:");
        lblQuantity = new JLabel("Quantity:");
        lblCategory = new JLabel("Category:");
        lblISBN = new JLabel("ISBN:");

        // Initialize Text Fields
        txtBookId = new JTextField();
        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtQuantity = new JTextField();
        txtCategory = new JTextField();
        txtISBN = new JTextField();

        // Initialize Buttons
        btnAddBook = new JButton("Add Book");
        btnClear = new JButton("Clear");

        // Add Action Listeners
        btnAddBook.addActionListener(this);
        btnClear.addActionListener(this);

        // Add Components to Frame
        add(lblBookId);
        add(txtBookId);

        add(lblTitle);
        add(txtTitle);

        add(lblAuthor);
        add(txtAuthor);

        add(lblQuantity);
        add(txtQuantity);

        add(lblCategory);
        add(txtCategory);

        add(lblISBN);
        add(txtISBN);

        add(btnAddBook);
        add(btnClear);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Add Book Button
        if (e.getSource() == btnAddBook) {

            try {

                // Get Data from Fields
                String bookId = txtBookId.getText();
                String title = txtTitle.getText();
                String author = txtAuthor.getText();
                int quantity = Integer.parseInt(txtQuantity.getText());
                String category = txtCategory.getText();
                String isbn = txtISBN.getText();

                // Validation
                if (title.isEmpty() || author.isEmpty()
                        || category.isEmpty() || isbn.isEmpty()) {

                    JOptionPane.showMessageDialog(this,
                            "Please fill all fields!");

                    return;
                }

                // Create Book Object
                Book book = new Book();

                book.setBookId(bookId);
                book.setTitle(title);
                book.setAuthor(author);
                book.setCategory(category);

                book.setPublisher("Unknown");

                book.setQuantity(quantity);
                book.setAvailableQuantity(quantity);

                book.setIsbn(isbn);

                // Call Service Layer
                boolean added = libraryService.addBook(book);

                if (added) {

                    JOptionPane.showMessageDialog(this,
                            "Book Added Successfully!");

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Failed to Add Book!");
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Book ID and Quantity must be numbers!");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Error: " + ex.getMessage());
            }
        }

        // Clear Button
        if (e.getSource() == btnClear) {

            clearFields();
        }
    }

    // Method to Clear Fields
    private void clearFields() {

        txtBookId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtQuantity.setText("");
        txtCategory.setText("");
        txtISBN.setText("");
    }
}
