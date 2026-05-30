//package ui;
//
//public class AddMemberFrame {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}

package ui;

import dao.MemberDAO;
import model.Member;
import java.awt.*;
import javax.swing.*;

public class AddMemberFrame extends JFrame {
    private JTextField txtMemId, txtName, txtEmail, txtPhone;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRole;
    private JButton btnSave, btnCancel;

    public AddMemberFrame() {
        setTitle("Add New Member - Library Management System");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(52, 73, 94));
        JLabel lblTitle = new JLabel("Register New Member");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Grid Form Panel
        JPanel pnlForm = new JPanel(new GridLayout(6, 2, 10, 15));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        pnlForm.add(new JLabel("Member ID (Unique):"));
        txtMemId = new JTextField();
        pnlForm.add(txtMemId);

        pnlForm.add(new JLabel("Full Name:"));
        txtName = new JTextField();
        pnlForm.add(txtName);

        pnlForm.add(new JLabel("Email Address:"));
        txtEmail = new JTextField();
        pnlForm.add(txtEmail);

        pnlForm.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        pnlForm.add(txtPassword);

        pnlForm.add(new JLabel("Contact Number:"));
        txtPhone = new JTextField();
        pnlForm.add(txtPhone);

        pnlForm.add(new JLabel("Account Role:"));
        String[] roles = {"Student", "Faculty", "Librarian"};
        cmbRole = new JComboBox<>(roles);
        pnlForm.add(cmbRole);

        add(pnlForm, BorderLayout.CENTER);

        // Buttons Panel
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        btnSave = new JButton("Register Member");
        btnCancel = new JButton("Cancel");
        pnlButtons.add(btnSave);
        pnlButtons.add(btnCancel);
        add(pnlButtons, BorderLayout.SOUTH);

        // Dynamic Save Logic
        btnSave.addActionListener(e -> {
            String memId = txtMemId.getText().trim();
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            String phone = txtPhone.getText().trim();
            String role = (String) cmbRole.getSelectedItem();

            // Validation check
            if (memId.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All input fields must be filled out dynamically!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Wrap into Model Object
            Member member = new Member();
            member.setMemId(memId);
            member.setName(name);
            member.setEmail(email);
            member.setPassword(password);
            member.setPhone(phone);
            member.setRole(role);
            member.setStatus("Active"); // Default registration status

            // Send to database via DAO
            MemberDAO memberDAO = new MemberDAO();
            
            // Note: If your MemberDAO uses a specific method signature, adjust it below:
            boolean success = memberDAO.addMember(member); 

            if (success) {
                JOptionPane.showMessageDialog(this, "Member successfully added to MySQL database!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close window dynamically
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register member. ID might already exist.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
    }
}