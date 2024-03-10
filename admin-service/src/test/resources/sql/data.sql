INSERT INTO authorities (name)  VALUES  ('USER'),('ADMIN'),('STUDENT'),('TEACHER');

INSERT INTO users (id,username,parole) VALUES   (1,'max','d73077ce48cf8a7ef1c1d67690ee5cac40c303dbc5bacbc885c66adc00adc910'),
                                                (2,'daria','acc1db45553dcba9bd5da74fbbb883a9743cdbe10ae1dae65cd8a5d0977da460'),
                                                (3,'alina','6061d6d3e1331c1a4452379b59867ff4eb318bea684b30368442657197b877e6'),
                                                (4,'vasiliy','7a2ef658e57383e368e15fc0e821182140d10d8a4f2f132c4a67131e9c84c01e');

INSERT INTO users_authorities (user_id,authority_id)  SELECT 1,a.id FROM authorities a where name='ADMIN';
INSERT INTO users_authorities (user_id,authority_id)  SELECT 2,a.id FROM authorities a where name='ADMIN';
INSERT INTO users_authorities (user_id,authority_id)  SELECT 3,a.id FROM authorities a where name='ADMIN';
INSERT INTO users_authorities (user_id,authority_id)  SELECT 4,a.id FROM authorities a where name='USER';
