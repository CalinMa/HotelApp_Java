package Labs1.Domain;
// this is an entity class
public class Reservation {
   private int idReservation;
    int numberOfPersons;
    int roomNumber;
    int numberOfDays;
    String feedback;
    int rating;

    public Reservation(int idReservation,int numberOfPersons,int roomNumber, int numberOfDays) {
        this.idReservation = idReservation;
        this.numberOfPersons = numberOfPersons;
        this.roomNumber = roomNumber;
        this.numberOfDays = numberOfDays;

    }

    public int getIdReservation() {
        return idReservation;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", numberOfPersons=" + numberOfPersons +
                ", roomNumber=" + roomNumber +
                ", numberOfDays=" + numberOfDays +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                '}';
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
