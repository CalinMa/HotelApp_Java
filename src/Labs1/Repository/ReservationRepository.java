package Labs1.Repository;
import Labs1.Domain.Reservation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// This is a database for my reservations
public class ReservationRepository {
    private Map<Integer, Reservation> storage = new HashMap<>();
    //CRUD Methods = Create, Read, Update, Delete

    /**
     * Adds a service entry to the repository.
     * @param reservation the service entry to add
     * @throws Exception if the id already exists
     */
    public void create(Reservation reservation) throws Exception {
        if (this.storage.containsKey(reservation.getIdReservation())) {
            throw new Exception("The id already exists");
        }
        this.storage.put(reservation.getIdReservation(),reservation);
    }
    /**
     * Returns all service entries.
     * @return all service entries.
     */
    public List<Reservation> read() {
        return new ArrayList<>(this.storage.values());
    }
}
