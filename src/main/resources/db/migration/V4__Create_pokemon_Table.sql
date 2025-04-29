CREATE TABLE pokemon (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    attack INT NOT NULL,
    defense INT NOT NULL,
    health INT NOT NULL,
    ability VARCHAR(255),
    is_base BOOLEAN DEFAULT FALSE,
    level INT NOT NULL,
    exp INT NOT NULL,
    listed BOOLEAN DEFAULT FALSE,
    owner_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pokemon_user FOREIGN KEY (owner_id) REFERENCES "user"(id) ON DELETE SET NULL
);