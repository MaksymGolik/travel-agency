
INSERT INTO users (id,  first_name, last_name, email, password, phone_number, role) VALUES (7, 'Nick', 'Green', 'nick@mail.com', '$2a$04$/5OH4XrS8m2JOgRCIaFMwOoy4eFW6cgCNSRyLOvDD/6hLYHHDNJiG', +380559488336,'USER');
INSERT INTO users (id,  first_name, last_name, email, password, phone_number, role) VALUES (8, 'Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', +380589488336, 'MANAGER');
INSERT INTO users (id,  first_name, last_name, email, password, phone_number, role) VALUES (9, 'Mike', 'Brown', 'mike@mail.com', '$2a$04$fuyCLq/wrTKA2olt4Eugy.LxWvXsMc7mrd6F5YvCeGApcdp3XQeOC',+380599488336, 'USER');


INSERT INTO countries (id, name) VALUES (1, 'Great Britain');
INSERT INTO countries (id, name) VALUES (2,'Ukraine');
INSERT INTO countries (id, name) VALUES (3, 'Italy');


INSERT INTO hotels (id, name, star_rating, country_id) VALUES (1, 'Gorgany', 4, 2  );
INSERT INTO hotels (id, name, star_rating, country_id) VALUES (2, 'Tavel', 5, 2  );
INSERT INTO hotels (id, name, star_rating, country_id) VALUES (3, 'Lancaster', 5, 1  );
INSERT INTO hotels (id, name, star_rating, country_id) VALUES (4, 'Pinewood', 4, 3  );


INSERT INTO rooms (id,  number_of_room, people_capacity, price, room_type, hotel_id) VALUES (1, 235, 2, 5000.0, 'STD', 1);
INSERT INTO rooms (id,  number_of_room, people_capacity, price, room_type, hotel_id) VALUES (2, 132, 3, 8000.0, 'SUITE_MINI', 3);
INSERT INTO rooms (id,  number_of_room, people_capacity, price, room_type, hotel_id) VALUES (3, 237, 1, 7000.0, 'DUPLEX', 1);









