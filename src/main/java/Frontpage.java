import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Frontpage extends JFrame {
    private JFrame frame;

    public Frontpage() {
        initializeFrame();  // Initialize JFrame

        frame.getContentPane().setLayout(new GridLayout(4, 1));

        JLabel welcomeLabel = new JLabel("Velkommen! Venligst indtast kode:", SwingConstants.CENTER);
        frame.getContentPane().add(welcomeLabel);

        JPasswordField passwordField = new JPasswordField();
        frame.getContentPane().add(passwordField);

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
        frame.getContentPane().add(showPasswordCheckBox);

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
                frame.dispose();
            }
        });

        // Define the login action logic in a method for reuse
        ActionListener loginAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[] password = passwordField.getPassword();
                String enteredPassword = new String(password);

                if (enteredPassword.equals("Bruger")) {
                    // Password is correct, open UserFrame
                    UserFrame userFrame = new UserFrame();
                    userFrame.setVisible(true);
                    frame.dispose();
                } else {
                    // Show an error message if the password is incorrect
                    JOptionPane.showMessageDialog(null, "Forkert kode, prøv igen", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        // User login action with password verification
        submitButton.addActionListener(loginAction);

        // Add KeyListener to passwordField to handle Enter key
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction.actionPerformed(null);
                }
            }
        });

        frame.getContentPane().add(buttonPanel);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Welcome");
        frame.setTitle("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             new Frontpage();
    //         }
    //     });
    // }
}
