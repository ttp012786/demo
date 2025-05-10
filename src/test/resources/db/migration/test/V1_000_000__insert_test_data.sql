INSERT INTO guests (id, name, email, checked_in_at, checked_out_at) VALUES
    ('guest-1', 'John Doe', 'john.doe@example.com', NOW(), NULL),
    ('guest-2', 'Jane Smith', 'jane.smith@example.com', NOW(), NULL);

INSERT INTO parcels (id, guest_id, tracking_number, received_at, picked_up_at) VALUES
    ('parcel-1', 'guest-1', 'TRACK123456', NOW(), NULL);
