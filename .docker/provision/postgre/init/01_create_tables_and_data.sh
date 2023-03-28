#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 -U "$DB_CHOUF_USER" -d "$DB_CHOUF_NAME" <<-EOSQL
  CREATE TABLE IF NOT EXISTS seller
  (
      id                integer      NOT NULL,
      seller_id         uuid         NOT NULL,
      firstname      	  varchar(120) NOT NULL,
      lastname      	  varchar(120) NOT NULL,
      email          	  varchar(120) NOT NULL,
      company_name      varchar(120) NOT NULL,
      active    		    boolean      NOT NULL DEFAULT TRUE,
      creation_time     timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      update_time       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (id),
      CONSTRAINT seller_id_uniq UNIQUE (seller_id)
  );

  CREATE TABLE IF NOT EXISTS customer
  (
      id                integer      NOT NULL,
      customer_id       uuid         NOT NULL,
      firstname      	  varchar(120) NOT NULL,
      lastname      	  varchar(120) NOT NULL,
      email          	  varchar(120) NOT NULL,
      active    		    boolean      NOT NULL DEFAULT TRUE,
      creation_time     timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      update_time       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (id),
      CONSTRAINT customer_id_uniq UNIQUE (customer_id)
  );

  CREATE TABLE IF NOT EXISTS alert
  (
      id                integer      NOT NULL,
      alert_id          uuid         NOT NULL,
      customer_id       integer      NOT NULL,
      estate_type      	char(1) 	   NOT NULL,
      country_code      char(2)      NOT NULL,
      region_code       varchar(6)   NOT NULL,
      city_code      	  varchar(30)  NULL,
      criteria      	  json 		     NOT NULL,
      active    		    boolean      NOT NULL DEFAULT TRUE,
      creation_time     timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      update_time       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (id),
      CONSTRAINT alert_id_uniq UNIQUE (alert_id)
  );

  CREATE TABLE IF NOT EXISTS offer
  (
      id                integer      NOT NULL,
      offer_id          uuid         NOT NULL,
      seller_id         integer      NOT NULL,
      estate_type      	char(1) 	   NOT NULL,
      country_code      char(2)      NOT NULL,
      region_code       varchar(6)   NOT NULL,
      city_code      	  varchar(30)  NOT NULL,
      price             integer      NOT NULL,
      floor_area 		    integer      NOT NULL,
  	  land_area 		    integer      NULL,
  	  rooms 			      integer      NULL,
  	  bedrooms 		      integer      NULL,
  	  bathrooms 		    integer      NULL,
  	  stairs 			      boolean      NOT NULL DEFAULT FALSE,
  	  parking 		      boolean      NOT NULL DEFAULT FALSE,
  	  cellar 			      boolean      NOT NULL DEFAULT FALSE,
  	  version 		      integer      NOT NULL DEFAULT 1,
      active    		    boolean      NOT NULL DEFAULT TRUE,
      creation_time     timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      update_time       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (id),
      CONSTRAINT offer_id_uniq UNIQUE (offer_id)
  );

  INSERT INTO public.seller (id, seller_id, firstname, lastname, email, company_name, active, creation_time, update_time)
  VALUES(1, uuid_generate_v4 (), 'alice', 'wonderland','alice@wonderland.com', 'Pills Corp', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.seller (id, seller_id, firstname, lastname, email, company_name, active, creation_time, update_time)
  VALUES(2, uuid_generate_v4 (), 'bob', 'c.martin','unclebob@cleancode.com', 'Dust Wiper', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.customer (id, customer_id, firstname, lastname, email, active, creation_time, update_time)
  VALUES(1, uuid_generate_v4 (), 'john', 'doe','john.doe@whatever.com', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.customer (id, customer_id, firstname, lastname, email, active, creation_time, update_time)
  VALUES(2, uuid_generate_v4 (), 'lemmy', 'kilmister','lemmy@motorhead.com', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.offer (id, offer_id, seller_id, estate_type, country_code, region_code, city_code, price, floor_area, land_area, rooms, bedrooms, bathrooms, stairs, parking, cellar, "version", active, creation_time, update_time)
  VALUES(1, uuid_generate_v4 (), 2, 'F', 'FR', 'FR-33', '33063', 600000, 113, NULL, 4, 3, 2, false, false, false, 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.offer (id, offer_id, seller_id, estate_type, country_code, region_code, city_code, price, floor_area, land_area, rooms, bedrooms, bathrooms, stairs, parking, cellar, "version", active, creation_time, update_time)
  VALUES(2, uuid_generate_v4 (), 2, 'F', 'FR', 'FR-75', '75120', 490000, 51, NULL, 2, NULL, NULL, false, false, true, 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.offer (id, offer_id, seller_id, estate_type, country_code, region_code, city_code, price, floor_area, land_area, rooms, bedrooms, bathrooms, stairs, parking, cellar, "version", active, creation_time, update_time)
  VALUES(3, uuid_generate_v4 (), 1, 'H', 'IT', 'IT-78', '66666', 299666, 666, 2000, 10, 7, NULL, true, true, false, 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.alert (id, alert_id, customer_id, estate_type, country_code, region_code, city_code, criteria, active, creation_time, update_time)
  VALUES(1, uuid_generate_v4 (), 1, 'F', 'FR', 'FR-33', NULL, json_object('{price_min, 100000, price_max, 500000, floor_area_min, 25, floor_area_max, 100, rooms_min, 2}'), true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.alert (id, alert_id, customer_id, estate_type, country_code, region_code, city_code, criteria, active, creation_time, update_time)
  VALUES(2, uuid_generate_v4 (), 1, 'H', 'FR', 'FR-33', NULL, json_object('{price_min, 200000, price_max, 300000, floor_area_min, 100, rooms_min, 4}'), true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.alert (id, alert_id, customer_id, estate_type, country_code, region_code, city_code, criteria, active, creation_time, update_time)
  VALUES(3, uuid_generate_v4 (), 1, 'H', 'IT', 'IT-78', NULL, json_object('{price_min, 200000, price_max, 300000, floor_area_min, 100, rooms_min, 4}'), true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

  INSERT INTO public.alert (id, alert_id, customer_id, estate_type, country_code, region_code, city_code, criteria, active, creation_time, update_time)
  VALUES(4, uuid_generate_v4 (), 2, 'F', 'FR', 'FR-33', NULL, json_object('{}'), true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

EOSQL