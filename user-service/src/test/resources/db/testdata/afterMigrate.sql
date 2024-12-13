insert into users (id, version, first_name, last_name, email, username, pool_id)
values
    ('1a9ac01b-1fe5-4dcc-a5be-0338b11184e9', 0, 'John', 'Doe', 'john@example.com', 'johndoe',
     '1a9ac01b-1fe5-4dcc-a5be-0338b11184e9'),
    ('637468f2-e071-70e0-ea63-f8f9a763d944', 0, 'Backend', 'Java', 'java@example.com', 'java',
     '637468f2-e071-70e0-ea63-f8f9a763d944');

insert into users_roles (role_id, user_id)
values
    ('3e104fc3-e627-41e8-a749-aef0d6a47b41', '1a9ac01b-1fe5-4dcc-a5be-0338b11184e9'),
    ('3e104fc3-e627-41e8-a749-aef0d6a47b41', '637468f2-e071-70e0-ea63-f8f9a763d944');