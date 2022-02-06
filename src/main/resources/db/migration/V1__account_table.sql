create table account
(
    id                     uuid               not null,
    created_at             timestamp          not null,
    is_deleted             bool default false not null,
    updated_at             timestamp,
    password               varchar(255)       not null,
    account_number         varchar(255)       not null,
    client_id              varchar(255)       not null,
    status                 varchar(255)       not null,
    primary key (id)
)
