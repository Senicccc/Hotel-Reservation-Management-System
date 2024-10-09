package hotelmanagement.reservations;

import hotelmanagement.rooms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reservation {
    private String customerName;
    private String phoneNumber;
    private int roomNumber;
    private String roomType;
    private int duration;
    private int totalPrice;
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public Reservation(String customerName, String phoneNumber, int roomNumber, String roomType, int duration, int totalPrice) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.duration = duration;
        this.totalPrice = totalPrice;
        reservations.add(this);
    }

    public Reservation() {
        initializeRooms(); 
    }

    private void initializeRooms() {
        for (int i = 0; i < 10; i++) {
            rooms.add(new StandardRoom(i + 201, "Standard", 1000000, true));
            rooms.add(new DeluxeRoom(i + 401, "Deluxe", 2250000, true));
            rooms.add(new SuiteRoom(i + 601, "Suite", 3500000, true));
        }
    }

    public void makeReservation() {
        Scanner input = new Scanner(System.in);
        System.out.println("Tipe Kamar \n1. Standard \n2. Deluxe \n3. Suite");
        System.out.print("Masukkan pilihan anda (1-3): ");
        int type = input.nextInt();
        input.nextLine();

        Room[] selectedRooms = getSelectedRoomType(type);
        if (selectedRooms == null) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Room reservedRoom = reserveRoom(selectedRooms);
        if (reservedRoom != null) {
            reservedRoom.getRoomDetails();
            System.out.print("Masukkan nama pengguna: ");
            String name = input.nextLine();
            System.out.print("Masukkan nomor telepon: ");
            String phone = input.nextLine();
            System.out.print("Masukkan durasi menginap: ");
            int duration = input.nextInt();
            input.nextLine(); 
            int totalPrice = reservedRoom.getPricePerNight() * duration;
            System.out.println("\nReservasi berhasil!");
            System.out.println("Detail Pemesan:");
            System.out.println("Nama: " + name);
            System.out.println("Nomor Telepon: " + phone);
            System.out.println("Kamar no: " + reservedRoom.getRoomNumber() + " (" + reservedRoom.getRoomType() + ")");
            System.out.println("Durasi: " + duration + " malam");
            System.out.println("Total Harga: Rp. " + totalPrice);
            reservedRoom.bookRoom();
            new Reservation(name, phone, reservedRoom.getRoomNumber(), reservedRoom.getRoomType(), duration, totalPrice);
        } else {
            System.out.println("Maaf, semua kamar sudah penuh.");
        }
    }

    private Room[] getSelectedRoomType(int type) {
        if (type == 1) {
            return getRoomsByType(StandardRoom.class);
        } else if (type == 2) {
            return getRoomsByType(DeluxeRoom.class);
        } else if (type == 3) {
            return getRoomsByType(SuiteRoom.class);
        }
        return null; 
    }

    private Room[] getRoomsByType(Class<? extends Room> roomClass) {
        List<Room> selectedRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (roomClass.isInstance(room)) {
                selectedRooms.add(room);
            }
        }
        return selectedRooms.toArray(new Room[0]);
    }

    private Room reserveRoom(Room[] rooms) {
        for (Room room : rooms) {
            if (room != null && room.isAvailable()) {
                room.setAvailable(false);
                return room;
            }
        }
        return null; 
    }

    public static void showAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Belum ada reservasi yang dibuat.");
        } else {
            System.out.println("Daftar Reservasi:");
            for (Reservation reservation : reservations) {
                System.out.println("Nama: " + reservation.customerName +
                                   ", Telepon: " + reservation.phoneNumber +
                                   ", Kamar No: " + reservation.roomNumber +
                                   ", Tipe Kamar: " + reservation.roomType +
                                   ", Durasi: " + reservation.duration +
                                   " malam, Total Harga: Rp. " + reservation.totalPrice);
            }
        }
    }

    public static void reservationDetails(String name) {
        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.customerName.equalsIgnoreCase(name)) {
                System.out.println("Detail Reservasi:");
                System.out.println("Nama: " + reservation.customerName +
                                   ", Telepon: " + reservation.phoneNumber +
                                   ", Kamar No: " + reservation.roomNumber +
                                   ", Tipe Kamar: " + reservation.roomType +
                                   ", Durasi: " + reservation.duration +
                                   " malam, Total Harga: Rp. " + reservation.totalPrice);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada reservasi atas nama " + name + ".");
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }
}
