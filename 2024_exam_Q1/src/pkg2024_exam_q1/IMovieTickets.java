// IMovieTickets.java
public interface IMovieTickets {
    // Calculates the sum of ticket sales in the provided array.
    int TotalMovieSales(int[] movieTicketSales);

    // Determines the name of the top performing movie based on total sales.
    String TopMovie(String[] movies, int[] totalSales);
}
