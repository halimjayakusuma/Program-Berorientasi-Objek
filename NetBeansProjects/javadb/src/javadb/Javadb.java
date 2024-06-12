package javadb;

import java.sql.*;
import java.util.Scanner;

public class Javadb {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/penjualan";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Insert Buku");
            System.out.println("2. Edit Buku");
            System.out.println("3. Show Buku");
            System.out.println("4. Delete Buku");
            System.out.println("5. Insert Penulis");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertBuku();
                    break;
                case 2:
                    editBuku();
                    break;
                case 3:
                    showBuku();
                    break;
                case 4:
                    deleteBuku();
                    break;
                case 5:
                    insertPenulis();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    public static void insertBuku() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID Buku: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Judul Buku: ");
        String judul = scanner.nextLine();
        System.out.print("Enter Tahun Terbit: ");
        int tahun = scanner.nextInt();
        System.out.print("Enter Stok: ");
        int stok = scanner.nextInt();
        System.out.print("Enter ID Penulis: ");
        int penulis = scanner.nextInt();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO buku (id, `judul buku`, `tahun terbit`, stok, penulis) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, judul);
            ps.setInt(3, tahun);
            ps.setInt(4, stok);
            ps.setInt(5, penulis);

            ps.execute();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editBuku() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID Buku to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Judul Buku: ");
        String judul = scanner.nextLine();
        System.out.print("Enter new Tahun Terbit: ");
        int tahun = scanner.nextInt();
        System.out.print("Enter new Stok: ");
        int stok = scanner.nextInt();
        System.out.print("Enter new ID Penulis: ");
        int penulis = scanner.nextInt();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "UPDATE buku SET `judul buku` = ?, `tahun terbit` = ?, stok = ?, penulis = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, judul);
            ps.setInt(2, tahun);
            ps.setInt(3, stok);
            ps.setInt(4, penulis);
            ps.setInt(5, id);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBuku() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM buku");
            int i = 1;
            while (rs.next()) {
                System.out.println("Data ke-" + i);
                System.out.println("ID Buku: " + rs.getInt("id"));
                System.out.println("Judul Buku: " + rs.getString("judul buku"));
                System.out.println("Tahun Terbit: " + rs.getInt("tahun terbit"));
                System.out.println("Stok: " + rs.getInt("stok"));
                System.out.println("ID Penulis: " + rs.getInt("penulis"));
                i++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteBuku() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID Buku to delete: ");
        int id = scanner.nextInt();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "DELETE FROM buku WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertPenulis() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID Penulis: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Nama Penulis: ");
        String nama = scanner.nextLine();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO penulis (id, `nama penulis`) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, nama);

            ps.execute();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
