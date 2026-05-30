//package ui;
//
//public class LoginFrame {
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    // Labels
    private JLabel lblTitle;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblRole;

    // Text Fields
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    // Combo Box
    private JComboBox<String> comboRole;

    // Buttons
    private JButton btnLogin;
    private JButton btnClear;

    public LoginFrame() {
        // Frame Settings
        setTitle("Library Management System - Login");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Label
        lblTitle = new JLabel("Library Management System", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        // Form Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        // Initialize Labels
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        lblRole = new JLabel("Role:");

        // Initialize Fields
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        // Role Options
        String[] roles = {"Admin", "Member", "Student", "Faculty"};
        comboRole = new JComboBox<>(roles);

        // Initialize Buttons
        btnLogin = new JButton("Login");
        btnClear = new JButton("Clear");

        // Add Action Listeners
        btnLogin.addActionListener(this);
        btnClear.addActionListener(this);

        // Add Components
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblRole);
        panel.add(comboRole);
        panel.add(btnLogin);
        panel.add(btnClear);

        // Padding Panel
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        wrapper.add(panel, BorderLayout.CENTER);

        add(wrapper, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Login Button
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText().trim();
            String password = String.valueOf(txtPassword.getPassword()).trim();
            String role = comboRole.getSelectedItem().toString();

            // Validation
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password!");
                return;
            }

            // 1. Hardcoded Admin Login Logic
            if (role.equals("Admin")) {
                if (username.equals("admin") && password.equals("admin123")) {
                    JOptionPane.showMessageDialog(this, "Admin Login Successful!");
                    dispose();
                    new AdminDashboard();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Admin Credentials!");
                }
            } 
            // 2. DYNAMIC Database Login Logic for Members/Students/Faculty
            else {
                MemberDAO memberDAO = new MemberDAO();
                
                // Fetch the member records directly from MySQL using the username (mem_id)
                Member member = memberDAO.getMemberById(username);

                // Verify the member exists, the password matches, and they belong to a user role
                if (member != null && member.getPassword().equals(password)) {
                    
                    JOptionPane.showMessageDialog(this, role + " Login Successful! Welcome " + member.getName());
                    dispose();
                    
                    // Route them straight into the student/member dashboard panel
                    new MemberDashboard(member);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid " + role + " Credentials!");
                }
            }
        }
        // Clear Button
        else if (e.getSource() == btnClear) {
            txtUsername.setText("");
            txtPassword.setText("");
            comboRole.setSelectedIndex(0);
        }
    }
}