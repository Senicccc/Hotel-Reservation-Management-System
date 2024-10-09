package hotelmanagement.rooms;

import hotelmanagement.reservations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room implements Reserveable {
    protected int roomNumber;
    protected String roomType;
    protected int pricePerNight;
    private boolean isAvailable;

    private static List<Room> rooms = new ArrayList<>();

    public Room(int roomNumber, String roomType, int pricePerNight, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        rooms.add(this);
    }
    
    public void bookRoom() {
        System.out.println("Selamat kamar anda telah berhasil dipesan!");
    }

    public static void checkAvailability() {
        int standardCount = 0;
        int deluxeCount = 0;
        int suiteCount = 0;
        System.out.println("Ketersediaan Kamar:");
        for (Room room : rooms) {
            if (room != null && room.isAvailable()) {
                System.out.println("Kamar " + room.getRoomNumber() + " - " + room.getRoomType() + " - kosong");
            }
        }

        for (Room room : rooms) {
            if (room != null) {
                if (room.isAvailable()) {
                    if (room instanceof StandardRoom) {
                        standardCount++;
                    } else if (room instanceof DeluxeRoom) {
                        deluxeCount++;
                    } else if (room instanceof SuiteRoom) {
                        suiteCount++;
                    }
                }
            }
        }

        System.out.println("Total Kamar Tersedia:");
        System.out.println("Standard: " + standardCount + " kamar tersedia");
        System.out.println("Deluxe: " + deluxeCount + " kamar tersedia");
        System.out.println("Suite: " + suiteCount + " kamar tersedia");
    }

    public static void cancelReservation(String customerName) {
        boolean found = false;

        for (Reservation reservation : Reservation.getReservations()) {
            if (reservation.getCustomerName().equalsIgnoreCase(customerName)) {
                for (Room room : rooms) {
                    if (room.getRoomNumber() == reservation.getRoomNumber()) {
                        room.setAvailable(true); 
                        found = true;
                        System.out.println("Reservasi untuk " + customerName + " berhasil dibatalkan.");
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Tidak ada reservasi atas nama " + customerName + ".");
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }
    
    public void getRoomDetails() {
        System.out.println("\nInformasi Kamar " + "\nTipe " + roomType 
                + " - Harga per malam: Rp " + pricePerNight);
    }

    @Override
    public void reserve() {
        if (isAvailable) {
            setAvailable(false); 
            System.out.println("Kamar " + roomNumber + " telah berhasil dipesan.");
        } else {
            System.out.println("Kamar " + roomNumber + " tidak tersedia untuk dipesan.");
        }
    }

    @Override
    public void cancel() {
        if (!isAvailable) {
            setAvailable(true);
            System.out.println("Reservasi untuk kamar " + roomNumber + " telah dibatalkan.");
        } else {
            System.out.println("Kamar " + roomNumber + " tidak memiliki reservasi yang aktif.");
        }
    }  
}

