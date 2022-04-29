insert into wad(id, name, genre, iwad) values ('a15d5780-b37d-11ec-b909-0242ac120002', 'Valiant', 'Modern', 'Doom 2');
insert into wad(id, name, genre, iwad) values ('c9b5a5d4-b37d-11ec-b909-0242ac120002', 'Plutonia 2', 'Arcade', 'Plutonia');
insert into wad(id, name, genre, iwad, download_link) values ('f2fe8302-b37d-11ec-b909-0242ac120002', 'Moonblood', 'Mixed Classic Modern', 'Doom 2', 'www.doomworld.com');
insert into wad(id, name, genre, iwad, released) values ('24f0241c-38a2-4777-aafe-b7f9df60d2b4', 'No End In Sight', 'Classic', 'Doom', '2022-01-01');

insert into map(id, name, author, enemies, wad_id, partime) values ('f4c55928-97e5-4e03-b6de-d8b720033f80', 'Entrance', 'Skillsaw', 512, 'a15d5780-b37d-11ec-b909-0242ac120002', '00:00:30.0000');
insert into map(id, name, author, enemies, wad_id) values ('b95f5661-2cd9-4508-a2ce-0d765c7fb38d', 'Entryway', 'Casali Brothers', 32, 'c9b5a5d4-b37d-11ec-b909-0242ac120002');
insert into map(id, name, author, enemies, wad_id) values ('ea03b49d-9505-41ea-8702-3ee4ab3b9a2c', 'Starport', 'Deadwing', 19, 'f2fe8302-b37d-11ec-b909-0242ac120002');
insert into map(id, name, author, enemies, wad_id) values ('8275fbdc-d3db-4d44-9a93-774a0c72a791', 'Ritual Site', 'Deadwing', 212, 'f2fe8302-b37d-11ec-b909-0242ac120002');

insert into review(id, author, description, rating, wad_id) values ('bb8f079f-182a-42b5-942d-818a5eb0f1fc', 'rd', 'very good', 5, 'a15d5780-b37d-11ec-b909-0242ac120002');
insert into review(id, author, description, rating, wad_id) values ('5260f931-3712-4f90-b43c-72c1ba44b157', 'Deadwing', 'good', 4, 'a15d5780-b37d-11ec-b909-0242ac120002');
insert into review(id, author, description, rating, wad_id) values ('84eeba16-0e40-4835-9de7-5aa4ff02c63e', 'FrancisT15', 'decent', 3, 'a15d5780-b37d-11ec-b909-0242ac120002');
insert into review(id, author, description, rating, wad_id) values ('ff044f08-9235-410f-8ef7-a272bb48d90e', 'rd', 'I didnt like much', 5, 'c9b5a5d4-b37d-11ec-b909-0242ac120002');
insert into review(id, author, description, rating, wad_id) values ('ce05e98f-e7ee-4ae1-8194-1b8e57608d67', 'FrancisT15', 'fun wad', 4, 'f2fe8302-b37d-11ec-b909-0242ac120002');
insert into review(id, author, description, rating, wad_id) values ('948bcd45-3b95-4336-8a21-f1731d6bdd58', 'Antares031', 'greatest wad', 5, 'f2fe8302-b37d-11ec-b909-0242ac120002');

insert into review(id, author, description, rating, map_id) values ('3720b503-4431-4d21-98f5-64528f3fc80c', 'Antares031', 'greatest map', 5, '8275fbdc-d3db-4d44-9a93-774a0c72a791');
insert into review(id, author, description, rating, map_id) values ('df420d16-dc46-4851-b532-6223ee12df8b', 'rd', 'good map, very fun', 5, 'ea03b49d-9505-41ea-8702-3ee4ab3b9a2c');