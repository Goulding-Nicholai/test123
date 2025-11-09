// MovieSalesReporter.java (Main Application Class)
public class MovieSalesReporter {

    public static void main(String[] args) {
        // 1. Declare and populate arrays (single and two-dimensional) 
        String[] movieNames = {"Napoleon", "Oppenheimer"};

        // Two-dimensional array for monthly sales: [movieIndex][monthIndex]
        int[][] monthlySales = {
            // JAN, FEB, MAR
            {3000, 1500, 1700}, // Napoleon sales 
            {3500, 1200, 1600}// Oppenheimer sales 
        };

        // Single array to store the calculated total sales for each movie
        int[] totalSales = new int[movieNames.length];

        // Instantiate the class that implements the calculation logic 
        IMovieTickets calculator = new MovieTickets();

        // 2. Calculate total sales for each movie
        for (int i = 0; i < movieNames.length; i++) {
            // Use the TotalMovieSales method to calculate the sum for each row (movie)
            totalSales[i] = calculator.TotalMovieSales(monthlySales[i]);
        }

        // 3. Determine the top performing movie [cite: 71]
        String topMovie = calculator.TopMovie(movieNames, totalSales);

        // 4. Display the report 
        System.out.println("MOVIE TICKET SALES REPORT - 2024");
        System.out.println("---------------------------------");
        System.out.printf("%-12s %-5s %-5s %-5s\n", "", "JAN", "FEB", "MAR");
        System.out.println("---------------------------------");

        for (int i = 0; i < movieNames.length; i++) {
            System.out.printf("%-12s %-5d %-5d %-5d\n", 
                movieNames[i], 
                monthlySales[i][0], // January
                monthlySales[i][1], // February
                monthlySales[i][2]  // March
            );
        }

        System.out.println();
        // Display total sales for each movie 
        for (int i = 0; i < movieNames.length; i++) {
            System.out.printf("Total movie ticket sales for %s %d\n", movieNames[i], totalSales[i]);
        }
        
        System.out.println();
        // Display the top performing movie [cite: 71]
        System.out.printf("Top performing movie: %s\n", topMovie);
    }
}