CREATE TABLE IF NOT EXISTS bank_accounts
(
    id             uuid PRIMARY KEY,
    type           VARCHAR(20) NOT NULL,
    bank_client_id uuid        NOT NULL REFERENCES bank_clients,
    balance        DECIMAL     NOT NULL
);
