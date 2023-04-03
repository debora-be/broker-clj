-- -------------------------------------------------------------
-- TablePlus 3.1.2(296)
--
-- https://tableplus.com/
--
-- Database: postgres
-- Generation Time: 2021-04-03 15:38:20.3830
-- -------------------------------------------------------------


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."brokers" (
    "broker_id" uuid NOT NULL,
    "created_at" timestamp,
    "first_name" varchar NOT NULL,
    "last_name" varchar NOT NULL,
    PRIMARY KEY ("broker_id")
);

CREATE TABLE "public"."quotes" (
    "broker_id" uuid NOT NULL,
    "created_at" timestamp,
    "expire_at" date NOT NULL,
    "price" numeric NOT NULL,
    "quotation_id" uuid NOT NULL,
    PRIMARY KEY ("quotation_id")
);

CREATE TABLE "public"."policies" (
    "created_at" timestamp,
    "date_of_birth" date NOT NULL,
    "name" varchar NOT NULL,
    "policy_id" uuid NOT NULL,
    "sex" varchar NOT NULL,    
    PRIMARY KEY ("policy_id")
);

