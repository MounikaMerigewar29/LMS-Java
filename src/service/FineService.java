//package service;
//
//public class FineService {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineService {

    // Fine per day
    private static final double FINE_PER_DAY = 5.0;

    /**
     * Calculate fine based on due date and return date
     */
    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {

        // If returned on time
        if (returnDate.isEqual(dueDate)
                || returnDate.isBefore(dueDate)) {

            return 0;
        }

        // Calculate overdue days
        long overdueDays = ChronoUnit.DAYS.between(
                dueDate,
                returnDate
        );

        // Fine Calculation
        return overdueDays * FINE_PER_DAY;
    }

    /**
     * Get overdue days
     */
    public long getOverdueDays(LocalDate dueDate,
                               LocalDate returnDate) {

        if (returnDate.isEqual(dueDate)
                || returnDate.isBefore(dueDate)) {

            return 0;
        }

        return ChronoUnit.DAYS.between(
                dueDate,
                returnDate
        );
    }

    /**
     * Check whether fine exists
     */
    public boolean hasFine(LocalDate dueDate,
                           LocalDate returnDate) {

        return returnDate.isAfter(dueDate);
    }

    /**
     * Display fine details
     */
    public void displayFineDetails(LocalDate dueDate,
                                   LocalDate returnDate) {

        long overdueDays = getOverdueDays(
                dueDate,
                returnDate
        );

        double fine = calculateFine(
                dueDate,
                returnDate
        );

        System.out.println("========== Fine Details ==========");
        System.out.println("Due Date      : " + dueDate);
        System.out.println("Return Date   : " + returnDate);
        System.out.println("Overdue Days  : " + overdueDays);
        System.out.println("Total Fine    : ₹" + fine);
        System.out.println("==================================");
    }
}