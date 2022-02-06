CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO bank_user (id, created_at, password, username, full_name,
                       access_token)
VALUES (uuid_generate_v4(), CURRENT_DATE, '$2a$12$0e3RuqI2orV.jGs6ntWCRO5ywIS0gOOFyjkGQY0jFVjKPSbim8Zxy',
        'admin123', 'Admin Admin', '$2a$12$0e3RuqI2orV.jGs6ntWCRO5ywIS0gOOFyjkGQY0jFVjKPSbim8Zxy');
