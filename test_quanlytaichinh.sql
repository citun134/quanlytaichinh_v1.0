-- use quanlytaichinh_final;
CREATE TABLE if not exists accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists giaoDichThu (
    thuId INT AUTO_INCREMENT PRIMARY KEY ,
    ngayThu date NOT NULL,
    matHangThu VARCHAR(255) NOT NULL,
    thanhTienThu BIGINT NOT NULL,
    ghiChuThu VARCHAR(255) NOT NULL,
    account_id INT NOT NULL
    -- CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

ALTER TABLE giaoDichThu
ADD CONSTRAINT fk_account_id
FOREIGN KEY (account_id)
REFERENCES accounts(account_id);
