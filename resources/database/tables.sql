-- Brokers table
CREATE TABLE brokers (
    broker_id VARCHAR NOT NULL,
    created_at TIMESTAMP,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    PRIMARY KEY (broker_id)
);

-- Quotes table
CREATE TABLE quotes (
    quotation_id VARCHAR NOT NULL,
    broker_id VARCHAR NOT NULL,
    created_at TIMESTAMP,
    expire_at VARCHAR NOT NULL,
    price NUMERIC NOT NULL,
    PRIMARY KEY (quotation_id),
    FOREIGN KEY (broker_id) REFERENCES brokers (broker_id)
);

-- Policies table
CREATE TABLE policies (
    policy_id VARCHAR NOT NULL,
    broker_id VARCHAR NOT NULL,
    created_at TIMESTAMP,
    date_of_birth VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    sex VARCHAR NOT NULL,
    PRIMARY KEY (policy_id),
    FOREIGN KEY (broker_id) REFERENCES brokers (broker_id)
);