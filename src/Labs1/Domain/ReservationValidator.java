package Labs1.Domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationValidator {
    /**
     * Validation on number of days, that has to be strictly positive;
     * @param reservation ...
     * @throws Exception ...
     */
    public void validate(Reservation reservation) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        if (reservation.getNumberOfDays() <= 0) {
            stringBuilder.append("The number of days must be > 0\n");
        }


        if (stringBuilder.length() > 0) {
            throw new Exception(stringBuilder.toString());
        }
    }
}
