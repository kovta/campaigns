CREATE SCHEMA campaigns;
CREATE TABLE campaigns.campaigns (
id bigserial primary key,
date timestamp,
datasource character varying(256),
campaign character varying(256),
clicks numeric,
impressions numeric
);

insert into campaigns.campaigns VALUES (0, current_timestamp - INTERVAL '0 DAY', 'test1', 'a', 10, 53);
insert into campaigns.campaigns VALUES (1, current_timestamp - INTERVAL '1 DAY', 'test1', 'a', 12, 207);
insert into campaigns.campaigns VALUES (2, current_timestamp - INTERVAL '2 DAY', 'test1', 'b', 4, 144);

insert into campaigns.campaigns VALUES (3, current_timestamp - INTERVAL '1 DAY', 'test2', 'c', 5, 319);
insert into campaigns.campaigns VALUES (4, current_timestamp - INTERVAL '2 DAY', 'test2', 'd', 9, 426);
insert into campaigns.campaigns VALUES (5, current_timestamp - INTERVAL '3 DAY', 'test2', 'd', 31, 299);
insert into campaigns.campaigns VALUES (6, current_timestamp - INTERVAL '4 DAY', 'test2', 'd', 24, 751);

insert into campaigns.campaigns VALUES (7, current_timestamp - INTERVAL '3 DAY', 'test3', 'e', 2, 352);
insert into campaigns.campaigns VALUES (8, current_timestamp - INTERVAL '4 DAY', 'test3', 'e', 7, 1366);
insert into campaigns.campaigns VALUES (9, current_timestamp - INTERVAL '5 DAY', 'test3', 'e', 11, 53);
insert into campaigns.campaigns VALUES (10, current_timestamp - INTERVAL '6 DAY', 'test3', 'f', 3, 885);
insert into campaigns.campaigns VALUES (11, current_timestamp - INTERVAL '7 DAY', 'test3', 'f', 20, 423);
insert into campaigns.campaigns VALUES (12, current_timestamp - INTERVAL '8 DAY', 'test3', 'f', 3, 723);
insert into campaigns.campaigns VALUES (13, current_timestamp - INTERVAL '9 DAY', 'test3', 'f', 10, 812);
insert into campaigns.campaigns VALUES (14, current_timestamp - INTERVAL '1 DAY', 'test3', 'g', 18, 1421);
insert into campaigns.campaigns VALUES (15, current_timestamp - INTERVAL '2 DAY', 'test3', 'g', 14, 632);
