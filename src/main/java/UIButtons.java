import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIButtons extends JPanel {

    public void changeCursor(JButton button) {
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            }
        });
    }

    public JButton createMenuButton() {
        ImageIcon originalIcon = new ImageIcon("img/house.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton("Hjem", resizedIcon);
        button.setIconTextGap(5);
        button.setHorizontalTextPosition(JButton.LEFT);
        button.setPreferredSize(new Dimension(50, 50));
        button.setIcon(resizedIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    public JButton createProjectPropButton(String text) {
        JButton button = new JButton(text);
        return button;
    }

    public JButton createProjectButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));
        return button;
    }

    public JButton createFundsButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));
        return button;
    }

    public JButton createArchiveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));
        return button;
    }

    public JButton createLogutButton() {
        ImageIcon originalIcon = new ImageIcon("img/Logout.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton("Log ud", resizedIcon);
        button.setIconTextGap(10); // Set gap between icon and text
        button.setHorizontalTextPosition(JButton.LEFT); // Position text to the left of the icon
        button.setFont(button.getFont().deriveFont(Font.BOLD));
        button.setFont(button.getFont().deriveFont(14.0f));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    public JButton createXButton() {
        ImageIcon originalIcon = new ImageIcon("img/X_button.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setIcon(resizedIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    public JButton createLoopButton() {
        ImageIcon originalIcon = new ImageIcon("Glass_loop.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setIcon(resizedIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    public JButton createNewListButton(JLabel infoLabel, Boolean rightSide) {
        JButton listButton = new JButton(infoLabel.getText());
        if (rightSide) {
            listButton.setMaximumSize(new Dimension(1000, 40)); // Set maximum size
        } else {
            listButton.setMaximumSize(new Dimension(850, 40)); // Set maximum size
        }
        listButton.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Set font
        listButton.setPreferredSize(new Dimension(300, 30));
        listButton.setFocusPainted(false); // Remove focus border
        listButton.setBackground(new Color(245, 245, 245)); // Set background color
        listButton.setForeground(Color.DARK_GRAY); // Set text color
        listButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Set border
        listButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor

        // Add hover effect
        listButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (!listButton.getBackground().equals(new Color(150, 150, 150))) {
                listButton.setBackground(new Color(220, 220, 220)); // Change background on hover
            }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            if (!listButton.getBackground().equals(new Color(150, 150, 150))) {
                listButton.setBackground(new Color(245, 245, 245)); // Revert background on exit
            }
            }
        });

        return listButton;
    }

    public JButton createListCatagoryButton(String text){
        JButton listButton = new JButton(text);
        listButton.setMaximumSize(new Dimension(150, 40)); // Set maximum size
        listButton.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Set font
        listButton.setPreferredSize(new Dimension(300, 30));
        listButton.setFocusPainted(false); // Remove focus border
        listButton.setBackground(new Color(245, 245, 245)); // Set background color
        listButton.setForeground(Color.DARK_GRAY); // Set text color
        listButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Set border
        listButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor

        // Add hover effect
        listButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listButton.setBackground(new Color(220, 220, 220)); // Change background on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listButton.setBackground(new Color(245, 245, 245)); // Revert background on exit
            }
        });

        return listButton;
    }

    public JButton sortingButtons(String typeOfButton, int[] clickCount) {
        System.out.println(clickCount[0]);
        System.out.println(typeOfButton);
        JButton button = new JButton();
        String arrow;
        button.setMaximumSize(new Dimension(150, 40)); // Set maximum size

        switch (typeOfButton) {
            case "title":
                System.out.println("updating title");
                arrow = clickCount[0] % 3 == 1 ? "↑" : clickCount[0] % 3 == 2 ? "↓" : "";
                button.setText("Titel " + arrow);
                break;
            case "owner":
                arrow = clickCount[1] % 3 == 1 ? "↑" : clickCount[1] % 3 == 2 ? "↓" : "";
                button.setText("Ejer " + arrow);
                break;
            case "deadline":
                arrow = clickCount[2] % 3 == 1 ? "↑" : clickCount[2] % 3 == 2 ? "↓" : "";
                button.setText("Deadline " + arrow);
                break;
            case "date":
                arrow = clickCount[3] % 3 == 1 ? "↑" : clickCount[3] % 3 == 2 ? "↓" : "";
                button.setText("Oprettelsesdato " + arrow);
                break;
            case "budget":
                arrow = clickCount[4] % 3 == 1 ? "↑" : clickCount[4] % 3 == 2 ? "↓" : "";
                button.setText("Budget " + arrow);
                break;
            // case "fundDeadline":
            //     arrow = clickCount[5] % 3 == 1 ? "↑" : "";
            //     button.setText("Deadline " + arrow);
            //     break;
            default:
                System.out.println("Error in sortingButtons");
                break;
        }
        return button;
    }
}
