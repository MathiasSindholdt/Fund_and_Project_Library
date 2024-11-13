import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Frontpage extends JFrame {

    public Frontpage() {
        setTitle("Welcome");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel welcomeLabel = new JLabel("Velkommen! Venligst indtast kode:", SwingConstants.CENTER);
        panel.add(welcomeLabel);

        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        // Checkbox to toggle password visibility
        JCheckBox showPasswordCheckBox = new JCheckBox("Vis kode");
        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);  // Show password
                } else {
                    passwordField.setEchoChar('*');       // Hide password
                }
            }
        });
        panel.add(showPasswordCheckBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton guestButton = new JButton("Fortsæt som gæst");
        buttonPanel.add(guestButton);

        JButton submitButton = new JButton("Log ind");
        buttonPanel.add(submitButton);

        // Guest login action
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open GuestFrame when button is clicked
                GuestFrame guestFrame = new GuestFrame();
                guestFrame.show();
            }
        });

        // User login action with password verification
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[] password = passwordField.getPassword();
                String enteredPassword = new String(password);

                if (enteredPassword.equals("Bruger")) {
                    // Password is correct, open UserFrame
                    UserFrame userFrame = new UserFrame();
                    userFrame.setVisible(true);
                } else {
                    // Show an error message if the password is incorrect
                    JOptionPane.showMessageDialog(null, "Forkert kode, prøv igen", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(buttonPanel);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frontpage();
            }
        });
    }
}