CREATE TABLE tower_slot (
    id BIGSERIAL PRIMARY KEY,
    tower_id BIGINT NOT NULL,
    pokemon_id BIGINT,
    user_id BIGINT NOT NULL,
    entry_time TIMESTAMP,
    minimum_exit_time TIMESTAMP,
    withdrawn BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_slot_tower FOREIGN KEY (tower_id) REFERENCES tower(id) ON DELETE CASCADE,
    CONSTRAINT fk_slot_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemon(id) ON DELETE SET NULL,
    CONSTRAINT fk_slot_user FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);