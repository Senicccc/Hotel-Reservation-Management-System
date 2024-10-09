package hotelmanagement.rooms;

public class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber, String roomType, int pricePerNight, 
            boolean isAvailable) {
        super(roomNumber, roomType, pricePerNight, isAvailable);
    } 
    
     @Override
    public void getRoomDetails() {
        System.out.println("\nInformasi Kamar " + "\nTipe " + roomType 
                + " - Harga per malam: Rp " + pricePerNight);
    }
}


