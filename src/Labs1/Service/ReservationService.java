package Labs1.Service;

import Labs1.Domain.Reservation;
import Labs1.Domain.ReservationValidator;
import Labs1.Domain.dtos.RoomNumberWithMeanRating;
import Labs1.Repository.ReservationRepository;

import java.util.*;

public class ReservationService {
    private ReservationRepository serviceReservation;
    private ReservationValidator reservationValidator;
    /**
     * Instantiates a service entry service.
     * @param serviceReservation the repository for service entries.

     */

    public ReservationService(ReservationRepository serviceReservation, ReservationValidator reservationValidator) {
        this.serviceReservation = serviceReservation;
        this.reservationValidator = reservationValidator;
    }

    /**
     * Adds a service entry.
     * @param idReservation the id of the service entry, must be unique.
     * @param roomNumber the room number, must be free.
     * @param numberOfDays the number of days, must be > 0.
        *
     * @throws Exception if there are any validation errors.
     */
    public void checkInReservation(int idReservation, int numberOfPersons, int roomNumber, int numberOfDays) throws Exception {
        Reservation reservation = new Reservation(idReservation,numberOfPersons,roomNumber,numberOfDays);
       this.reservationValidator.validate(reservation);
       for(Reservation res : this.getAll()){
           if(res.getRoomNumber() == roomNumber && res.getFeedback()==null){
               throw new Exception("Camera nu e libera");
           }
       }
        this.serviceReservation.create(reservation);

    }
    /**
     * Marks an entry as finished by setting its billed price.
     * @param  roomNumber, must be occupied.
     * @param rating the rating, must be > 0.
     * @param feedback the feedback must be any string.
     * @throws Exception if there are validation errors.
     */
    public void checkOutReservation(int roomNumber, String feedback, int rating) throws Exception {

        if (rating <= 0) {
            throw new Exception("The rating must be > 0!");
        }
        if (rating > 5) {
            throw new Exception("The rating must be <= 5!");
        }
        List<Reservation> entries = this.getAll();
        boolean foundRoomWithGuests = false;
        for (Reservation reservation : entries) {
            if (reservation.getFeedback() == null && reservation.getRating() == 0 && reservation.getRoomNumber() == roomNumber) {
                reservation.setFeedback(feedback);
                reservation.setRating(rating);
                foundRoomWithGuests = true;
                break;
            }
        }

        if (!foundRoomWithGuests) {
            throw new Exception("The room is free or it doesn't exist!");
        }
    }

    /**
     *
     * @return
     */
    public List<RoomNumberWithMeanRating> getRoomsByMeanRating() {
        Map<Integer, List<Float>> standsWithPrices = new HashMap<>();
//        Map<Integer, Float> standsWithPrices = new HashMap<>();
//        Map<Integer, Integer> standsWithCounts = new HashMap<>();

        for (Reservation entry : this.getAll()) {
            if (entry.getFeedback() != null) {
                int roomNumber = entry.getRoomNumber();
                float rating = entry.getRating();

                if (!standsWithPrices.containsKey(roomNumber)) {
                    List<Float> ratings = new ArrayList<>();
                    ratings.add(rating);
                    standsWithPrices.put(roomNumber, ratings);
                } else {
                    standsWithPrices.get(roomNumber).add(rating);
                }
            }
        }

        List<RoomNumberWithMeanRating> results = new ArrayList<>();
        for (Map.Entry<Integer, List<Float>> entry : standsWithPrices.entrySet()) {
            int standNumber = entry.getKey();
            float average = 0;
            for (float price : entry.getValue()) {
                average += price;
            }
            average /= entry.getValue().size();

            results.add(new RoomNumberWithMeanRating(standNumber, average));
        }

        results.sort(Comparator.comparing(RoomNumberWithMeanRating::getMeanRating).reversed());
        return results; // read more: https://stackoverflow.com/questions/16252269/how-to-sort-an-arraylist
    }

    /**
     * Gets all the checkIns
     * @return
     */
    public List<Reservation> getAll() { return this.serviceReservation.read();}
}
