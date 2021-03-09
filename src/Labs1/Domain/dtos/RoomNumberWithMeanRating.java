package Labs1.Domain.dtos;
//this is a class for the mean rating
public class RoomNumberWithMeanRating {
    private int roomNumber;
    private float meanRating;

    public RoomNumberWithMeanRating(int roomNumber, float meanRating) {
        this.roomNumber = roomNumber;
        this.meanRating = meanRating;
    }

    public int getRoomNumber() { return roomNumber; }

    public float getMeanRating() { return meanRating; }

    @Override
    public String toString() {
        return "RoomNumberWithMeanRating{" +
                "roomNumber=" + roomNumber +
                ", meanRating=" + meanRating +
                '}';
    }
}
