import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class MovieTicketsGUI extends JFrame implements ActionListener {
    // GUI Components
    private JComboBox<String> movieComboBox;
    private JTextField ticketsField;
    private JTextField priceField;
    private JTextArea reportArea;
    
    // Menu Items
    private JMenuItem exitItem;
    private JMenuItem processItem;
    private JMenuItem clearItem;

    // Business Logic Instance
    private MovieTickets movieTicketsLogic = new MovieTickets();
    private static final DecimalFormat priceFormat = new DecimalFormat("0.0");

    public MovieTicketsGUI() {
        // --- Frame Setup ---
        super("MOVIE TICKETS");
        setLayout(null); // Use absolute positioning for simplicity
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        
        // --- Menu System Setup (Q.2.2, Q.2.3) ---
        JMenuBar menuBar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        
        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");
        processItem = new JMenuItem("Process");
        processItem.addActionListener(this);
        clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(this);
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // --- Component Setup (Q.2.1) ---
        int yOffset = 20;

        // MOVIE Label and Combo Box
        JLabel movieLabel = new JLabel("MOVIE:");
        movieLabel.setBounds(20, yOffset, 120, 20);
        add(movieLabel);
        
        String[] movies = {"Napoleon", "Oppenheimer", "Damsel"};
        movieComboBox = new JComboBox<>(movies);
        movieComboBox.setBounds(150, yOffset, 200, 20);
        add(movieComboBox);
        yOffset += 40;

        // NUMBER OF TICKETS Label and Text Field
        JLabel ticketsLabel = new JLabel("NUMBER OF TICKETS:");
        ticketsLabel.setBounds(20, yOffset, 120, 20);
        add(ticketsLabel);
        ticketsField = new JTextField();
        ticketsField.setBounds(150, yOffset, 200, 20);
        add(ticketsField);
        yOffset += 40;

        // TICKET PRICE Label and Text Field
        JLabel priceLabel = new JLabel("TICKET PRICE:");
        priceLabel.setBounds(20, yOffset, 120, 20);
        add(priceLabel);
        priceField = new JTextField();
        priceField.setBounds(150, yOffset, 200, 20);
        add(priceField);
        yOffset += 40;

        // TICKET REPORT Label and Text Area
        JLabel reportLabel = new JLabel("TICKET REPORT:");
        reportLabel.setBounds(20, yOffset, 120, 20);
        add(reportLabel);
        yOffset += 20;

        reportArea = new JTextArea();
        reportArea.setEditable(false); // Read-only text area
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBounds(20, yOffset, 330, 200);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitItem) {
            // Q.2.3: Close the application
            System.exit(0);
        } else if (e.getSource() == clearItem) {
            // Q.2.6: Clear the text fields and text area
            clearFields();
        } else if (e.getSource() == processItem) {
            // Q.2.4: Process the input and display the report
            processSale();
        }
    }

    /**
     * Q.2.6 Implementation
     */
    private void clearFields() {
        ticketsField.setText("");
        priceField.setText("");
        reportArea.setText("");
        movieComboBox.setSelectedIndex(0);
    }

    /**
     * Q.2.4 and Q.2.5 Implementation
     */
    private void processSale() {
        // 1. Capture input and parse (handle potential NumberFormatException)
        String movieName = (String) movieComboBox.getSelectedItem();
        int numTickets;
        double ticketPrice;

        try {
            numTickets = Integer.parseInt(ticketsField.getText());
            ticketPrice = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Tickets and Price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Validate data
        MovieTicketData data = new MovieTicketData(movieName, numTickets, ticketPrice);
        if (!movieTicketsLogic.ValidateData(data)) {
            // Validation rules are: Name not empty, Price > 0, Tickets > 0
            JOptionPane.showMessageDialog(this, "Validation Failed: Movie Price and Number of Tickets must be greater than zero.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Calculate total price (includes 14% VAT)
        double totalTicketPrice = movieTicketsLogic.CalculateTotalTicketPrice(numTickets, ticketPrice);

        // 4. Generate report string
        String report = String.format(
            "MOVIE TICKET REPORT\n" +
            "***********\n" +
            "MOVIE NAME: %s\n" +
            "MOVIE TICKET PRICE: R %s\n" +
            "NUMBER OF TICKETS: %d\n" +
            "TOTAL TICKET PRICE: R %s\n" +
            "***********",
            movieName,
            priceFormat.format(ticketPrice),
            numTickets,
            priceFormat.format(totalTicketPrice)
        );

        // 5. Display report in Text Area
        reportArea.setText(report);

        // 6. Save report to file (report.txt)
        saveReportToFile(report);
    }
    
    /**
     * Q.2.5 Implementation
     */
    private void saveReportToFile(String reportContent) {
        String filename = "report.txt";
        try (FileWriter writer = new FileWriter(filename, true)) { // 'true' for appending
            writer.write(reportContent + "\n\n");
            // Optionally: give user feedback that file was saved
            // JOptionPane.showMessageDialog(this, "Report saved to " + filename, "File Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving report to " + filename + ": " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MovieTicketsGUI());
    }
}