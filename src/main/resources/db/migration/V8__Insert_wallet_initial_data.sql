INSERT INTO wallet (balance, user_id) VALUES
(1000.00, (SELECT id FROM "user" WHERE email = 'admin@fathrpet.com'));