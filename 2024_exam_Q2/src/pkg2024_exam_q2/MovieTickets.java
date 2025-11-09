import java.text.DecimalFormat;

public class MovieTickets implements IMovieTickets {

    // VAT is 14% (0.14) as per Q.2.4 requirement
    private static final double VAT_RATE = 0.14;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        // Total price = (Number of Tickets * Ticket Price) * (1 + VAT_RATE)
        double subTotal = numberOfTickets * ticketPrice;
        double totalWithVAT = subTotal * (1 + VAT_RATE);
        // Round to two decimal places
        return Double.parseDouble(df.format(totalWithVAT));
    }

    @Override
    public boolean ValidateData(MovieTicketData movieTicketData) {
        // Validation Rule 1: Movie Ticket Name Cannot be empty
        if (movieTicketData.movieName == null || movieTicketData.movieName.trim().isEmpty()) {
            return false;
        }
        // Validation Rule 2: Movie Ticket Price Cannot be less than or equal to zero (0)
        if (movieTicketData.ticketPrice <= 0) {
            return false;
        }
        // Validation Rule 3: Number of Movie Tickets Cannot be less than or equal to zero (0)
        if (movieTicketData.numberOfTickets <= 0) {
            return false;
        }
        return true;
    }
}