package Labs1.UserInterface;

import Labs1.Domain.Reservation;
import Labs1.Domain.dtos.RoomNumberWithMeanRating;
import Labs1.Service.ReservationService;


import java.util.Scanner;

public class Console {
    private ReservationService reservationService;
    private Scanner scanner = new Scanner(System.in);

    public Console(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    private void showMenu() {
        System.out.println("1. Check in");
        System.out.println("2. Check out");
        System.out.println("3. Display the rooms sorted by mean descending rating");
        System.out.println("a. Show history");
        System.out.println("x. Exit program");
    }

    public void startConsole() {
        while (true) {
            this.showMenu();

            System.out.println("Choose one option:");
            String option = scanner.next();

            if (option.equals("1")) {
                this.handleCheckin();
            } else if (option.equals("2")) {
                this.handleCheckout();
            } else if (option.equals("3")){
                this.handleRoomNumberWithMeanRating();
            }
            else if (option.equals("a")) {
                this.handleShowAll();
            } else if (option.equals("x")) {
                break;
            } else {
                System.out.println("The command does not exist!");
            }
        }
    }
    private void handleRoomNumberWithMeanRating() {
        for (RoomNumberWithMeanRating roomNumberWithMeanRating : this.reservationService.getRoomsByMeanRating())
        {
            System.out.println(roomNumberWithMeanRating);
        }
    }

    private void handleCheckout() {
        try {
            System.out.println("Enter room number:");
           int roomNumber = scanner.nextInt();
           System.out.println("Enter feedback: ");
            String feedback = scanner.next();
            System.out.println("Enter rating between 1 and 5:");
            int rating = scanner.nextInt();

            this.reservationService.checkOutReservation(roomNumber,feedback,rating);

            System.out.println("Great. Your checkout is complete!");
        } catch (Exception exception) {
            System.out.println("Errors are displayed:");
            System.out.println(exception.getMessage());
        }
    }

    private void handleShowAll() {
        for (Reservation reservation : this.reservationService.getAll())
            System.out.println(reservation.toString());
    }


    private void handleCheckin() {
        try {
            System.out.println("Enter reservation ID: ");
            int idReservation = this.scanner.nextInt();
            System.out.println("Enter the number of persons: ");
            int numberOfPersons = this.scanner.nextInt();
            System.out.println("Enter room number: ");
            int roomNumber = this.scanner.nextInt();
            System.out.println("Enter the number of days: ");
            int numberOfDays = this.scanner.nextInt();

            this.reservationService.checkInReservation(idReservation, numberOfPersons, roomNumber, numberOfDays);

            System.out.println("We have successfully checked you in!");
        } catch (Exception exception) {
            System.out.println("Errors are displayed:");
            System.out.println(exception.getMessage());
        }
    }
}
