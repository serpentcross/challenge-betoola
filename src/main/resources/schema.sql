DROP TABLE IF EXISTS rate;

CREATE TABLE rate (
    id INT PRIMARY KEY,
    code VARCHAR(3) NOT NULL,
    price DOUBLE PRECISION NOT NULL
);

INSERT INTO rate (id, code, price) VALUES (978, 'GBP', 1.2375);
INSERT INTO rate (id, code, price) VALUES (826, 'EUR', CAST(1 / (SELECT price FROM rate WHERE code = 'GBP') AS DECIMAL(10,4)));