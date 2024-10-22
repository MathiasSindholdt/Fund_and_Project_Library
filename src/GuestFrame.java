import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GuestFrame implements ActionListener {

    // GUI komponenter
    private JFrame frame;
    private JButton createProbButton;
    private JButton changeProbButton; 
    private JButton backButton;
    private JButton projectPropButton;
    private JButton projectButton;

    // Constructor til at opsætte GUI
    public GuestFrame() {
        initializeFrame();  // Initialiserer JFrame
        JPanel panel1 = createTopPanel();  // Opretter top-panel
        JPanel panel2 = createSidePanel();  // Opretter side-panel
        JPanel panel5 = createCenterPanel();  // Opretter center-panel

        // Tilføj paneler til frame
        frame.add(panel1, BorderLayout.NORTH);  
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel5, BorderLayout.CENTER);  
    }

    // Initialiserer JFrame
    private void initializeFrame() {
        frame = new JFrame("Gæst");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout(10, 10));
    }

    // Opretter top-panel med tilbageknap, label, og projekt knap
    private JPanel createTopPanel() {
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(213, 213, 213, 255)); 
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Elementer justeres til venstre med lidt afstand

        // Tilføj tilbageknap
        backButton = createBackButton();
        panel1.add(backButton);

        // Tilføj label
        JLabel label = new JLabel("Gæst", SwingConstants.LEFT);
        panel1.add(label);

        // Opret knap til projekt forslag
        projectPropButton = createProjectPropButton("Projekt forslag");
        panel1.add(projectPropButton);

        // Opret knap til Projekter (ny knap)
        projectButton = createProjectButton("Projekter");
        panel1.add(projectButton);

        return panel1;
    }

    // Opretter side-panel (kan fyldes med indhold senere)
    private JPanel createSidePanel() {
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(213, 213, 213, 255));
        panel2.setPreferredSize(new Dimension(100, 100));
        return panel2;
    }

    // Opretter center-panel med knapper
    private JPanel createCenterPanel() {
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel5.setBackground(new Color(213, 213, 213, 255));

        // Tilføj knapper til panel5
        createProbButton = createButton("Lav projekt forslag");
        changeProbButton = createButton("Ændre projekt forslag");

        panel5.add(createProbButton);
        panel5.add(changeProbButton);

        return panel5;
    }

    // Opretter en knap med action listener
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.addActionListener(this);
        return button;
    }

    // Opretter projekt forslag knap
    private JButton createProjectPropButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));  // Du kan justere størrelsen på knappen her
        button.addActionListener(this);
        return button;
    }

    // Opretter Projekter knap
    private JButton createProjectButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));  // Du kan justere størrelsen på knappen her
        button.addActionListener(this);
        return button;
    }

    // Opretter tilbageknappen
    private JButton createBackButton() {
        // Load and resize the icon image for the back button
        ImageIcon originalIcon = new ImageIcon("backArrow.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        button.setIcon(resizedIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
        return button;
    }

    // Metode til at vise vinduet
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createProbButton) {
            openProjectProposalDialog(); // Åbn popup til projekt forslag
        }
    }
    

    // Metode til at åbne popup-vindue til projekt forslag
    private void openProjectProposalDialog() {
        // Opret et nyt JDialog (popup-vindue)
        JDialog dialog = new JDialog(frame, "Lav Projekt Forslag", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel nameLabel = new JLabel("Titel:");
        JTextField nameField = new JTextField();
        dialog.add(nameLabel);
        dialog.add(nameField);

        JLabel ideaLabel = new JLabel("Idé/Formlål:");
        JTextField ideaField = new JTextField();
        dialog.add(ideaLabel);
        dialog.add(ideaField);

        JLabel descriptionLabel = new JLabel("Kort beskrivelse af projektet for at danne en mening:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        dialog.add(descriptionLabel);
        dialog.add(scrollPane);

        JLabel ideaFromLabel = new JLabel("Ideens oprindelse:");
        JTextField ideaFromField = new JTextField();
        dialog.add(ideaFromLabel);
        dialog.add(ideaFromField);

        JLabel ownerLabel = new JLabel("Ejer af idé/forslaget:");
        JTextField ownerField = new JTextField();
        dialog.add(ownerLabel);
        dialog.add(ownerField);

        JLabel targetLabel = new JLabel("Målgruppe (hvem gavner dette forslag:");
        JTextField targetField = new JTextField();
        dialog.add(targetLabel);
        dialog.add(targetField);


        JLabel budgetLabel = new JLabel("Anslået budget:");
        JTextField budgetField = new JTextField();
        dialog.add(budgetLabel);
        dialog.add(budgetField);
        
        

        // Tilføj submit-knap
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Her kan du håndtere indtastede data
                String projectName = nameField.getText();
                String projectDescription = descriptionArea.getText();
                String responsiblePerson = ideaField.getText();
                String ideaFrom = ideaFromField.getText();
                String owner = ownerField.getText();

                // Erstattes med vores gemmelogik
                JOptionPane.showMessageDialog(dialog, 
                    "Projekt navn: " + projectName + 
                    "\nBeskrivelse: " + projectDescription + 
                    "\nAnsvarlig: " + responsiblePerson + 
                    "\nDeadline: " + ideaFrom);

                // Luk dialogen
                dialog.dispose();
            }
        });

        // Tilføj submit-knappen til dialog
        dialog.add(new JLabel());  // tom label for at holde layoutet
        dialog.add(submitButton);

        // Vis dialogen
        dialog.setLocationRelativeTo(frame); // Center dialog over hovedvinduet
        dialog.setVisible(true);
    }

    // Main metode for at køre applikationen
    public static void main(String[] args) {
        GuestFrame guestFrame = new GuestFrame();
        guestFrame.show();
    }
}
