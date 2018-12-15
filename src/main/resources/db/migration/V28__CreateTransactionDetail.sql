CREATE TABLE IF NOT EXISTS transaction_detail (
  transaction_detail_id BIGINT(20)  UNSIGNED NOT NULL AUTO_INCREMENT,
  transaction_id BIGINT(20) UNSIGNED NOT NULL,
  operation_id SMALLINT(6) UNSIGNED NOT NULL,
  account_id BIGINT(20) UNSIGNED NOT NULL,
  amount DECIMAL(10,2) NULL,
  created_at_utc DATETIME NULL,
  updated_at_utc DATETIME NULL,
  PRIMARY KEY (transaction_detail_id),
  INDEX fk_transaccion_detail_operation1_idx (operation_id ASC),
  INDEX fk_transaccion_detail_account1_idx (account_id ASC),
  INDEX fk_transaccion_detail_transaction1_idx (transaction_id ASC),
  CONSTRAINT fk_transaccion_detail_operation1
    FOREIGN KEY (operation_id)
    REFERENCES operation (operation_id) ,
  CONSTRAINT fk_transaccion_detail_account1
    FOREIGN KEY (account_id)
    REFERENCES account (account_id) ,
  CONSTRAINT fk_transaccion_detail_transaction1
    FOREIGN KEY (transaction_id)
    REFERENCES transaction (transaction_id) 
  )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci; 