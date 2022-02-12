insert into roles (id, name) values (1, 'TEST_ROLE');
insert into persons (id, login, password, email, role_id) values (1, 'Admin', 'Admin', 'Admin@gmail.com', 1);
insert into rooms (id, name, creator_id) values (1, 'TEST_ROOM', 1);
insert into messages (id, description, author_id, room_id) values (1, 'TEST DESC MESSAGE', 1, 1);
insert into persons_rooms (person_id, rooms_id) values (1, 1);