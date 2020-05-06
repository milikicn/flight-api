INSERT INTO city (id, name, country, description) VALUES
    (10001, 'Mecheria', 'Algeria', 'Mecheria is a small city situated in Nasma Province, Algeria in the Atlas mountains, capital of Mecheria District.'),
    (10002, 'Goroka', 'Papua New Guinea', 'Goroka is the capital of the Eastern Highlands Province of Papua New Guinea. It is a town of approximately 19,000 people, 1600m above sea level.');

INSERT INTO comment (id, description, created, modified, city_id) VALUES
    (1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.', '2020-05-06T21:23:00.605206', '2020-05-06T21:23:00.605206', 10001),
    (2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.', '2020-04-03T01:54:00.605206', '2020-04-03T01:54:00.605206', 10001),
    (3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.', '2020-04-29T14:14:00.605206', '2020-04-29T14:14:00.605206', 10001),
    (4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.', '2020-05-05T16:11:00.605206', '2020-05-05T16:11:00.605206', 10001),
    (5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.', '2020-05-06T22:24:00.605206', '2020-05-06T22:24:00.605206', 10002),
    (6, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus venenatis dolor, vel molestie risus.', '2020-04-04T04:31:00.605206', '2020-04-04T04:31:00.605206', 10002);