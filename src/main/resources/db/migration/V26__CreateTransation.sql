CREATE TABLE  transaction (
  transaction_id BIGINT(20)  UNSIGNED NOT NULL AUTO_INCREMENT,
  transaction_type_id SMALLINT(6) UNSIGNED NOT NULL,
  created_at_utc DATETIME NULL,
  updated_at_utc DATETIME NULL,
  PRIMARY KEY (transaction_id),
  INDEX IX_transaction_type_id (transaction_type_id ASC),
  CONSTRAINT FK_transaction_transactional_type
    FOREIGN KEY (transaction_type_id)
    REFERENCES transaction_type (type_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci; 