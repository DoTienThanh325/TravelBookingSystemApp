package TourDAO;

import Model.Tour;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TourDAO {

    // Thêm Tour vào Database
    public void addTour(Tour tour) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tours (tourName, startFrom, destination, dayStart, numberOfDays, price, maxNumberOfPassengers, currentPassengers, tourState,  maxNumberOfGuides, currentGuides, tourGuideState, languageGuideNeed) VALUES (?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = TourDBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tour.getTourName());
            pstmt.setString(2, tour.getStartFrom());
            pstmt.setString(3, tour.getDestination());

            try {
                java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(tour.getDayStart());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pstmt.setDate(4, sqlDate);
            } catch (ParseException e) {
                pstmt.setNull(4, Types.DATE);
            }

            pstmt.setDouble(5, tour.getNumberOfDays());
            pstmt.setDouble(6, tour.getPrice());
            pstmt.setInt(7, tour.getMaxNumberOfPassengers());
            pstmt.setInt(8, tour.getCurrentPassengers());
            pstmt.setString(9, tour.getTourState());
            pstmt.setInt(10, tour.getMaxNumberOfGuides());
            pstmt.setString(11, tour.getTourGuideState());
            pstmt.setString(12, tour.getLanguageGuideNeed());

            pstmt.executeUpdate();

        }
    }

    public Tour getTourById(String tourId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tours WHERE tourId = ?";
        Tour tour = null;
        try (Connection conn = TourDBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tourId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tour = createTourFromResultSet(rs);
            }
        }
        return tour;
    }

    // Cập nhật lại số lượng khách hàng, hướng dẫn viên và trạng thái tour
    public void updateTour(Tour tour) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE TOUR SET currentPassangers = ?, tourState = ?, currentGuides = ?, tourGuideState = ?";

        try (Connection conn = TourDBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, tour.getCurrentPassengers());
            pstmt.setString(2, tour.getTourState());
            pstmt.setInt(3, tour.getCurrentGuides());
            pstmt.setString(4, tour.getTourGuideState());

            pstmt.executeUpdate();
        }
    }

    private Tour createTourFromResultSet(ResultSet rs) throws SQLException {
        // BƯỚC 1: Lấy về dưới dạng java.sql.Date từ ResultSet
        Date dbDate = rs.getDate("dayStart");
        String formattedDate = null; // Khởi tạo chuỗi kết quả
        // BƯỚC 2: Kiểm tra xem ngày có null không để tránh lỗi
        if (dbDate != null) {
            // Chuyển java.sql.Date sang java.time.LocalDate
            LocalDate localDate = dbDate.toLocalDate();

            // BƯỚC 3: Tạo một formatter với định dạng bạn muốn ("dd/MM/yyyy")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // BƯỚC 4: Dùng formatter để định dạng LocalDate thành String
            formattedDate = localDate.format(formatter);
        }
        Tour tour = new Tour(
                rs.getString("tourName"),
                rs.getString("startFrom"),
                rs.getString("destination"),
                formattedDate,
                rs.getDouble("numberOfDays"),
                rs.getDouble("price"),
                rs.getInt("maxNumberOfPassengers"),
                rs.getInt("currentPassengers"),
                rs.getString("tourState"),
                rs.getInt("maxNumberOfGuides"),
                rs.getInt("currentGuides"),
                rs.getString("tourGuideState"),
                rs.getString("languageGuideNeed")
        );
        return tour;
    }

    // Hàm lấy tên tour
    public String[] collectTourInfo(String DB_URL, String DB_USER, String DB_PASSWORD) {
        ArrayList<String> arr = new ArrayList<>();
        String sql = "select distinct tourName from TOUR";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String tenTour = rs.getString("tourName");
                arr.add(tenTour);
            }
        } catch (SQLException e) {
            System.err.println("Error1");
            e.printStackTrace();
        }
        String[] TourNameArr = arr.toArray(new String[0]);
        return TourNameArr;
    }

    // Hàm lấy dayStart
    public String[] collectTourStartDate(String tourName, String DB_URL, String DB_USER, String DB_PASSWORD) {
        ArrayList<String> arrStart = new ArrayList<>();
        String sql = "select dayStart from TOUR where tourName = ?";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Tìm startDay
                    Date date = rs.getDate("dayStart");
                    String dayStart = formatter.format(date);
                    arrStart.add(dayStart);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error2");
            e.printStackTrace();
        }
        return arrStart.toArray(new String[0]);
    }

    // Hàm lấy numberOfDays
    public Double[] collectTourDays(String tourName, String date, String DB_URL, String DB_USER, String DB_PASSWORD) {
        ArrayList<Double> arr = new ArrayList<>();
        String sql = "select numberOfDays from TOUR where tourName = ? and dayStart = STR_TO_DATE(?, '%d/%m/%Y')";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourName);
            ps.setString(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Double numDays = rs.getDouble("numberOfDays");
                    arr.add(numDays);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error2");
            e.printStackTrace();
        }
        return arr.toArray(new Double[0]);
    }
}