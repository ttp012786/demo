CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE guests (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    room_number VARCHAR(50) NOT NULL,
    checked_out_at TIMESTAMP,
    checked_out BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE parcels (
    id UUID PRIMARY KEY,
    guest_id UUID REFERENCES guests(id),
    tracking_number VARCHAR(50) UNIQUE NOT NULL,
    received_at TIMESTAMP NOT NULL,
    picked_up_at TIMESTAMP
);
