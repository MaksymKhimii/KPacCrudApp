INSERT INTO knowledge_package.`k-pac`(title, description, creation_date)
VALUES ('algebra', 'equations, variables, functions', '1966-02-11'),
       ('geometry', 'lines, shapes, angles, area', '1966-03-11'),
       ('physics', 'Newton''s laws, radiation, speed', '1967-04-11'),
       ('biology', 'plants, animals, anatomy', '1917-11-10'),
       ('chemistry', 'reagents, elements', '2007-11-05'),
       ('history', 'wars, laws, revolutions', '2004-01-10'),
       ('literature', 'books, writers, genres', '1999-08-07'),
       ('english', 'letters, words, grammar', '2013-09-09');

INSERT INTO knowledge_package.`k-pac_set`(k_pac_set_id, set_title)
VALUES (1, 'mathematical sciences'),
       (2, 'natural sciences'),
       (3, 'humanitarian sciences');

INSERT INTO knowledge_package.`k-link`(k_link_id, k_pac_id, k_pac_set_id)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 2),
       (4, 4, 2),
       (5, 5, 2),
       (6, 6, 3),
       (7, 7, 3),
       (8, 8, 3);
