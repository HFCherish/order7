CREATE TABLE orderItems (
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  quantity INTEGER DEFAULT 0,
  amount DOUBLE NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products(id),
  FOREIGN KEY (order_id) REFERENCES orders(id)
);

