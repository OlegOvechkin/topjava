DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (user_id, description, calories) VALUES
  (100001, 'Админ ланч', 510),
  (100001, 'Админ ужин', 2000),
  (100000, 'User ланч', 200),
  (100000, 'User ужин', 1050);


