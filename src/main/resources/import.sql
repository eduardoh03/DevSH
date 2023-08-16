
INSERT INTO tb_user (login, password) VALUES ('admin@admin.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');

INSERT INTO profiles (CREATED_AT, ID, UPDATED_AT, USER_ID, IMG_URL, NAME) VALUES (CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1, 'https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50', 'Admin');
INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);