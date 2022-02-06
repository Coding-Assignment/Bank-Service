create table bank_user
(
    id                     uuid               not null,
    created_at             timestamp          not null,
    is_deleted             bool default false not null,
    updated_at             timestamp,
    password               varchar(1000)       not null,
    username               varchar(255)       not null,
    full_name              varchar(255)       not null,
    access_token           varchar(1000)       not null,
    primary key (id)
)
