CREATE SCHEMA campaigns;
CREATE TABLE campaigns.campaigns (
id bigserial primary key,
date timestamp,
datasource character varying(256),
campaign character varying(256),
clicks numeric,
impressions numeric
);

insert into campaigns.campaigns VALUES (0, current_timestamp, 'test', 'test', 1, 1);