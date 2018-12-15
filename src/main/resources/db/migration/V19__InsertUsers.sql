INSERT INTO user(user_id, first_name, last_name, gender, user_name, email_address, password_hash, active, created_at_utc, updated_at_utc)
VALUES
(1, 'Juan', 'Pérez', 'M', 'jperez', 'jperez@domain.com', '$2a$11$1VgBvKU5Wc53ysa.iZvITuiMfdxeHK7BfxUfCx5xDQZYpBMRsd9Ue', 1, UTC_TIMESTAMP(),UTC_TIMESTAMP()),
(2, 'Carlos', 'Pérez', 'M', 'cperez', 'cperez@domain.com', '$2a$11$1VgBvKU5Wc53ysa.iZvITuiMfdxeHK7BfxUfCx5xDQZYpBMRsd9Ue', 1, UTC_TIMESTAMP(),UTC_TIMESTAMP()),
(3, 'Alberto', 'Otero', 'M', 'aotero', 'aotero@domain.com', '$2a$11$1VgBvKU5Wc53ysa.iZvITuiMfdxeHK7BfxUfCx5xDQZYpBMRsd9Ue', 1, UTC_TIMESTAMP(),UTC_TIMESTAMP()),
(4, 'Martin', 'Segura', 'M', 'msegura', 'msegura@domain.com', '$2a$11$1VgBvKU5Wc53ysa.iZvITuiMfdxeHK7BfxUfCx5xDQZYpBMRsd9Ue', 1, UTC_TIMESTAMP(),UTC_TIMESTAMP());