INSERT INTO client (name)
VALUES  ('Ivan'),
        ('Oleg'),
        ('Igor'),
        ('John'),
        ('Peter'),
        ('Mike'),
        ('Dorian'),
        ('Steve'),
        ('James'),
        ('Kevin');

INSERT INTO planet (id, name)
VALUES  ('MRK', 'Mercury'),
        ('VNR', 'Venera'),
        ('ETH', 'EARTH'),
        ('MRS', 'Mars'),
        ('STR', 'Saturn');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id)
VALUES  ('2024-01-05 10:00:00', 1, 'MRS', 'VNR'),
        ('2023-08-25 15:35:00', 2, 'STR', 'MRK'),
        ('2023-12-16 21:15:05', 3, 'STR', 'VNR'),
        ('2022-10-08 08:15:35', 4, 'ETH', 'MRS'),
        ('2008-04-17 03:38:27', 5, 'MRS', 'MRK'),
        ('2001-05-13 23:17:45', 6, 'ETH', 'VNR'),
        ('1998-06-07 18:11:21', 7, 'MRK', 'MRS'),
        ('1992-02-04 05:36:11', 8, 'VNR', 'MRK'),
        ('1990-11-07 09:15:05', 9, 'MRK', 'STR'),
        ('1988-05-09 07:01:03', 10, 'VNR', 'ETH');