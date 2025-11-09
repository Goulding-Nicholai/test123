public interface IMovieTickets {
    /**
     * Calculates the total ticket price including 14% VAT.
     * @param numberOfTickets The number of tickets purchased.
     * @param ticketPrice The price of a single ticket (excluding VAT).
     * @return The total price including VAT.
     */
    double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice);

    /**
     * Validates the data captured in the GUI.
     * @param movieTicketData An object containing the movie ticket data.
     * @return true if the data is valid, false otherwise.
     */
    boolean ValidateData(MovieTicketData movieTicketData);
}