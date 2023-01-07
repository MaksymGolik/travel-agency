
INSERT INTO users (  first_name, last_name, email, password, phone_number, role) VALUES ( 'Nick', 'Green', 'nick@mail.com', '$2a$04$/5OH4XrS8m2JOgRCIaFMwOoy4eFW6cgCNSRyLOvDD/6hLYHHDNJiG', +380559488336,'USER');
INSERT INTO users ( first_name, last_name, email, password, phone_number, role) VALUES ( 'Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', +380589488336, 'MANAGER');
INSERT INTO users (  first_name, last_name, email, password, phone_number, role) VALUES ( 'Mike', 'Brown', 'mike@mail.com', '$2a$04$fuyCLq/wrTKA2olt4Eugy.LxWvXsMc7mrd6F5YvCeGApcdp3XQeOC',+380599488336, 'USER');


INSERT INTO countries ( name) VALUES ( 'Great Britain');
INSERT INTO countries ( name) VALUES ('Ukraine');
INSERT INTO countries ( name) VALUES ( 'Italy');


INSERT INTO hotels ( name, star_rating, country_id) VALUES ( 'Gorgany', 4, 2  );
INSERT INTO hotels ( name, star_rating, country_id) VALUES ( 'Tavel', 5, 2  );
INSERT INTO hotels ( name, star_rating, country_id) VALUES ( 'Lancaster', 5, 1  );
INSERT INTO hotels ( name, star_rating, country_id) VALUES ( 'Pinewood', 4, 3  );


INSERT INTO rooms ( number_of_room, people_capacity, price, room_type, hotel_id) VALUES (235, 2, 50.0, 'STD', 1);
INSERT INTO rooms (  number_of_room, people_capacity, price, room_type, hotel_id) VALUES ( 132, 3, 80.0, 'SUITE_MINI', 3);
INSERT INTO rooms (  number_of_room, people_capacity, price, room_type, hotel_id) VALUES ( 237, 1, 70.0, 'DUPLEX', 1);


INSERT INTO booking ( date_in, date_out, total_price, user_id) VALUES ('2023-01-05 14:00','2023-01-07 12:00', 100.0, 1  );
INSERT INTO booking ( date_in, date_out, total_price, user_id) VALUES ('2023-01-02 14:00','2023-01-04 12:00', 160.0, 3  );
INSERT INTO booking ( date_in, date_out, total_price, user_id) VALUES ('2023-01-01 14:00','2023-01-10 12:00', 140.0, 2  );


INSERT INTO booking_rooms (booking_id, room_id) VALUES (1,1);
INSERT INTO booking_rooms (booking_id, room_id) VALUES (2,2);
INSERT INTO booking_rooms (booking_id, room_id) VALUES (3,3);





