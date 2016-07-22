CREATE TABLE payments (
  order_id BIGINT NOT NULL,
  amount DOUBLE NOT NULL,
  type VARCHAR(255) DEFAULT "CREDIT_CARD",
  FOREIGN KEY (order_id) REFERENCES orders(id)
);

