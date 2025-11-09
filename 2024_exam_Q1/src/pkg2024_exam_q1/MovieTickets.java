// MovieTickets.java
public class MovieTickets implements IMovieTickets {

    /**
     * Calculates the total number of tickets sold for a single movie.
     * @param movieTicketSales An array of ticket sales for a movie (e.g., across 3 months).
     * @return The total number of tickets sold.
     */
    @Override
    public int TotalMovieSales(int[] movieTicketSales) {
        // Initialize accumulator for total sales
        int total = 0;
        // Loop through the array and sum up the sales
        for (int sales : movieTicketSales) {
            total += sales;
        }
        return total;
    }

    /**
     * Determines the top performing movie based on total sales.
     * @param movies An array of movie names.
     * @param totalSales An array of the corresponding total sales for each movie.
     * @return The name of the top performing movie.
     */
    @Override
    public String TopMovie(String[] movies, int[] totalSales) {
        // Assume the first movie is the top movie initially
        String topMovieName = movies[0];
        int maxSales = totalSales[0];

        // Loop through the total sales array starting from the second element (index 1)
        for (int i = 1; i < totalSales.length; i++) {
            // Check if current sales are greater than the maximum found so far
            if (totalSales[i] > maxSales) {
                maxSales = totalSales[i];
                topMovieName = movies[i]; // Update the top movie name
            }
        }
        return topMovieName;
    }
}
