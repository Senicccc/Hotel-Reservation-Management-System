package hotelmanagement;

import java.util.Scanner;
import hotelmanagement.rooms.*;
import hotelmanagement.reservations.Reservation;
import hotelmanagement.users.*;

public class Hotel {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean looping = true;

        while (looping) {
            System.out.println("La Nichole Hotel");
            System.out.println("\nPilih user: ");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Keluar");

            System.out.print("Masukkan pilihan anda: ");
            int login = input.nextInt();
            input.nextLine();

            switch (login) {
                case 1:
                    Customer customer = new Customer();
                    System.out.println("\nAnda login sebagai " + customer.getRole());
                    menuCustomer(input);
                    break;

                case 2:
                    boolean adminLoginSuccess = false;
                    while (!adminLoginSuccess) {
                        System.out.print("Masukkan password admin: ");
                        String adminPassword = input.nextLine();
                        Admin admin = new Admin();

                        if (admin.login(adminPassword)) {
                            System.out.println("\nAnda login sebagai " + admin.getRole());
                            menuAdmin(input);
                            adminLoginSuccess = true;
                        } else {
                            System.out.println("Password yang anda masukkan salah!");
                            System.out.print("Tekan ENTER untuk coba lagi ATAU ketik 'n' untuk keluar: ");
                            String retry = input.nextLine();
                            if (retry.equalsIgnoreCase("n")) {
                                break;
                            }
                        }
                    }
                    break;

                case 3:
                    looping = false;
                    System.out.println("Terima kasih telah menggunakan layanan kami!");
                    break;

                default:
                    System.out.println("Maaf, pilihan anda tidak valid. Silakan coba lagi.");
            }
        }
        input.close();
    }

    public static void menuAdmin(Scanner input) {
        boolean running = true;
        Reservation reservation = new Reservation();

        while (running) {
            System.out.println("\nMENU UTAMA");
            System.out.println("1. Cek Kamar Secara Lengkap");
            System.out.println("2. Lihat Transaksi Yang Ada");
            System.out.println("3. Kembali Ke Menu Login");
            System.out.println("4. Keluar Program");

            System.out.print("Masukkan Pilihan Anda: ");
            int pilihanAdmin = input.nextInt();
            input.nextLine();

            switch (pilihanAdmin) {
                case 1:
                    Room.checkAvailability(); 
                    break;
                case 2:
                    reservation.showAllReservations();
                    break;
                case 3:
                    running = false;
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan layanan kami!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    public static void menuCustomer(Scanner input) {
        boolean running = true;
        Reservation reservation = new Reservation();

        while (running) {
            System.out.println("\nMENU UTAMA");
            System.out.println("1. Pesan Kamar");
            System.out.println("2. Cek Ketersediaan Kamar");
            System.out.println("3. Lihat Detail Pesanan");
            System.out.println("4. Batalkan Pesanan");
            System.out.println("5. Cek Harga");
            System.out.println("6. Hubungi Kami");
            System.out.println("7. Tentang Kami");
            System.out.println("8. Kembali Ke Menu Login");
            System.out.println("9. Keluar Program");

            System.out.print("Masukkan Pilihan Anda: ");
            int pilihanCustomer = input.nextInt();
            input.nextLine();

            switch (pilihanCustomer) {
                case 1:
                    reservation.makeReservation();
                    break;
                case 2:
                    Room.checkAvailability(); 
                    break;
                case 3:
                    System.out.print("Masukkan nama pengguna untuk melihat detail reservasi: ");
                    String name = input.nextLine();
                    reservation.reservationDetails(name);
                    break;
                case 4:
                    System.out.print("Masukkan nama pelanggan untuk membatalkan pesanan: ");
                    String customerNameToCancel = input.nextLine();
                    Room.cancelReservation(customerNameToCancel); 
                    break;
                case 5:
                    countTotal(input);
                    break;
                case 6:
                    System.out.println("Hubungi kami di +62 1234 5678 90");
                    break; // Tambahkan break di sini
                case 7:
                    System.out.println("La Nichole Hotel menyediakan layanan terbaik untuk anda.");
                    break;
                case 8:
                    running = false;
                    break;
                case 9:
                    System.out.println("Terima kasih telah menggunakan layanan kami!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
    
    public static void countTotal(Scanner input) {
        System.out.println("\nTipe Kamar: ");
        System.out.println("1. Standard (Rp 1.000.000/malam)");
        System.out.println("2. Deluxe (Rp 2.250.000/malam)");
        System.out.println("3. Suite (Rp 3.500.000/malam)");

        System.out.print("Masukkan pilihan kamar anda (1-3): ");
        int type = input.nextInt();
        System.out.print("Masukkan durasi menginap (malam): ");
        int duration = input.nextInt();

        int totalPrice = 0;
        switch (type) {
            case 1:
                totalPrice = 1000000 * duration;
                break;
            case 2:
                totalPrice = 2250000 * duration;
                break;
            case 3:
                totalPrice = 3500000 * duration;
                break;
            default:
                System.out.println("Tipe kamar tidak valid.");
                return;
        }
        System.out.println("Total harga untuk menginap: Rp. " + totalPrice);
    }
}
