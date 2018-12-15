CREATE TABLE IF NOT EXISTS operation (
  operation_id SMALLINT(6)  UNSIGNED NOT NULL AUTO_INCREMENT,
  description VARCHAR(45) NULL,
  created_at_utc DATETIME NULL,
  updated_at_utc DATETIME NULL,
  PRIMARY KEY (operation_id)
  )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci; 