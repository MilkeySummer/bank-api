
CREATE TABLE IF NOT EXISTS accounts (
                                        id BIGSERIAL PRIMARY KEY,
                                        balance DECIMAL(19,2) NOT NULL DEFAULT 0
    );


CREATE TABLE IF NOT EXISTS operations (
                                          id BIGSERIAL PRIMARY KEY,
                                          account_id BIGINT NOT NULL,
                                          amount DECIMAL(19,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    type VARCHAR(20) NOT NULL CHECK (type IN ('PUT', 'TAKE', 'TRANSFER_OUT', 'TRANSFER_IN')),
    FOREIGN KEY (account_id) REFERENCES accounts(id)
    );


INSERT INTO accounts (balance) VALUES (1000.00), (500.00);