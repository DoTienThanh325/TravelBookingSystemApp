USE TRAVELBOOKINGSYSTEMAPP;
SET SQL_SAFE_UPDATES = 0;


INSERT INTO TOUR (tourName, startFrom, destination, dayStart, numberOfDays, price, maxNumberOfPassengers, currentPassengers, tourState, maxNumberOfGuides, currentGuides, tourGuideState, languageGuideNeed) 
VALUES 
('Miền Tây sông nước', 'Cần Thơ', 'Châu Đốc - Rừng tràm Trà Sư', '2026-01-10', 3, 2800000, 25, 10, 'Not Full', 2, 1, 'NOT FULL', 'Tiếng Nhật'),
('Về với biển xanh Nha Trang', 'Cam Ranh', 'Nha Trang', '2026-01-20', 4, 4200000, 40, 40, 'Full', 3, 3, 'FULL', 'Tiếng Anh'),
('Khám phá Đà Lạt mộng mơ', 'TP. Hồ Chí Minh', 'Đà Lạt', '2026-02-05', 3, 3100000, 30, 15, 'Not Full', 2, 2, 'FULL', 'Tiếng Hàn'),
('Lên rừng xuống biển Quy Nhơn - Phú Yên', 'Quy Nhơn', 'Quy Nhơn - Phú Yên', '2026-02-15', 4, 4500000, 20, 20, 'Full', 2, 1, 'NOT FULL', 'Tiếng Trung');

INSERT INTO Customer (`Name`, Birthday, phoneNumber, Email, TourBooking, BookingState, BookingDate, numberOfCustomers, price) VALUES
('Nguyễn Văn An', '1990-05-15', '0912345678', 'an.nguyen@email.com', 'TOUR001', 'Confirmed', '2025-10-01', 2, 7000000),
('Trần Thị Bình', '1995-08-22', '0987654321', 'binh.tran@email.com', 'TOUR002', 'Confirmed', '2025-10-05', 1, 4200000),
('Lê Văn Cường', '1988-11-30', '0905112233', 'cuong.le@email.com', 'TOUR001', 'Pending', '2025-10-10', 4, 14000000),
('Phạm Thị Dung', '2000-01-20', '0934567890', 'dung.pham@email.com', 'TOUR003', 'Confirmed', '2025-10-12', 2, 15600000);

INSERT INTO Guide (`Name`, Birthday, phoneNumber, Email, guideExperience, TourBooking, BookingState, BookingDate) VALUES
('Hoàng Minh Tuấn', '1985-03-12', '0911111111', 'tuan.hm@guide.com', 5.5, 'TOUR001', 'Confirmed', '2025-09-15'),
('Vũ Thị Lan Anh', '1992-07-19', '0922222222', 'vtla@guide.com', 3, 'TOUR002', 'Confirmed', '2025-09-20'),
('Đặng Quốc Bảo', '1990-09-01', '0933333333', 'bao.dq@guide.com', 4, 'TOUR003', 'Confirmed', '2025-09-25'),
('Trịnh Thu Hà', '1989-06-25', '0944444444', 'ha.trinh@guide.com', 6, 'TOUR001', 'cancelled', '2025-10-02');

SELECT * FROM TOUR;
SELECT * FROM Customer;
SELECT * FROM Guide;