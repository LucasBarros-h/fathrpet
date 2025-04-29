CREATE TABLE marketplace (
    id BIGSERIAL PRIMARY KEY,
    pokemon_id BIGINT NOT NULL,
    seller_id BIGINT NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    created_listing_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_sold BOOLEAN DEFAULT FALSE,
    sold_at TIMESTAMP,
    CONSTRAINT fk_marketplace_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE,
    CONSTRAINT fk_marketplace_user FOREIGN KEY (seller_id) REFERENCES "user"(id) ON DELETE CASCADE
);