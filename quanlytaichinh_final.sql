use quanlytaichinh_final;
CREATE TABLE if not exists accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists giaoDich (
    gdId INT AUTO_INCREMENT PRIMARY KEY,
    ngay DATE NOT NULL,
    matHang VARCHAR(255) NOT NULL,
    thanhTien BIGINT NOT NULL,
    ghiChu VARCHAR(255) NOT NULL,
    account_id INT NOT NULL,
    CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

-- ALTER TABLE `quanlytaichinh`.`giaodich`
-- ADD CONSTRAINT `giaodich_ibfk_1` FOREIGN KEY (`user_id`)
-- REFERENCES `accounts` (`account_id`) ON DELETE CASCADE;