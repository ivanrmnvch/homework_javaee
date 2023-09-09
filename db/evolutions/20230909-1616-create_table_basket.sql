CREATE TABLE basket (
    id SERIAL NOT NULL PRIMARY KEY,
    "userId" INTEGER NOT NULL,
    "productId" INTEGER NOT NULL UNIQUE,
    updated timestamp with time zone NOT NULL,
    created timestamp with time zone NOT NULL,
    FOREIGN KEY ("userId") REFERENCES users (id),
    FOREIGN KEY ("productId") REFERENCES products (id)
);