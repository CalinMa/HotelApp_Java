
//This is an application for managing a hotel. It has the following functionalities:

//Check in: id_checkin, number of persons, room number, number of days.
// The room number must be from a room where nobody is checked in.
// The number of days has to be strictly positive. The ID must be unique.

//Check out: give the room number, a feedback and a rating.
//The room number must exist, the feedback must be a non null string, the rating has to be between 1 and 5;

//Display rooms: display the descending sorted after rating (mean rating for each room).
// The program will display the room number and the rating;


package Labs1;

import Labs1.Domain.Reservation;
import Labs1.Domain.ReservationValidator;
import Labs1.Repository.ReservationRepository;
import Labs1.Service.ReservationService;
import Labs1.UserInterface.Console;


public class Main {

    public static void main(String[] args) throws Exception {
        ReservationRepository serviceReservation = new ReservationRepository();
        ReservationValidator reservationValidator = new ReservationValidator();
        ReservationService reservationService = new ReservationService(serviceReservation,reservationValidator);
        reservationService.checkInReservation(1, 2, 100, 1);
        reservationService.checkInReservation(2, 2, 101, 2);
        reservationService.checkInReservation(3, 2, 102, 3);
        reservationService.checkInReservation(4, 1, 103, 7);



        Console console = new Console(reservationService);

       console.startConsole();
    }
}

