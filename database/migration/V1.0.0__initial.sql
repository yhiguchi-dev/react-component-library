CREATE SCHEMA IF NOT EXISTS purchase;

CREATE TABLE purchase.user
(
    id            CHAR(36)                               NOT NULL,
    email_address VARCHAR(256)                           NOT NULL,
    password      VARCHAR(256)                           NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    UNIQUE (email_address)
);

CREATE TABLE purchase.item
(
    id         SERIAL                                 NOT NULL,
    name       VARCHAR(256)                           NOT NULL,
    price      INTEGER                                NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE INDEX idx_purchase_item_name ON purchase.item(name varchar_pattern_ops);

CREATE TABLE purchase.transaction
(
    id         CHAR(36)                               NOT NULL,
    user_id    CHAR(36)                               NOT NULL,
    item_id    INTEGER                                NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES purchase.user (id),
    FOREIGN KEY (item_id) REFERENCES purchase.item (id)
);

CREATE TABLE purchase.address
(
    transaction_id CHAR(36)                               NOT NULL,
    zip_code       VARCHAR(256)                           NOT NULL,
    prefecture     VARCHAR(256)                           NOT NULL,
    city           VARCHAR(256)                           NOT NULL,
    street         VARCHAR(256)                           NOT NULL,
    created_at     TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (transaction_id),
    FOREIGN KEY (transaction_id) REFERENCES purchase.transaction (id) ON DELETE CASCADE
);

CREATE TABLE purchase.identity
(
    transaction_id CHAR(36)                               NOT NULL,
    first_name     VARCHAR(256)                           NOT NULL,
    last_name      VARCHAR(256)                           NOT NULL,
    created_at     TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    CONSTRAINT pk_identity PRIMARY KEY (transaction_id),
    FOREIGN KEY (transaction_id) REFERENCES purchase.transaction (id) ON DELETE CASCADE
);
