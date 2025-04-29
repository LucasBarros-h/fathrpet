INSERT INTO pokemon (name, type, ability, attack, defense, health, is_base, level, exp, listed, owner_id) VALUES
('Cainã', 'DOMADOR_DE_GATOS', 'CHAMADO_DA_NATUREZA', 55, 50, 45, true, '1', '0', false, (SELECT id FROM "user" WHERE email = 'admin@fathrpet.com')),
('Pedro Lixo', 'EXPLORADOR_DO_LIXO', 'DETERMINACAO_DO_CATADOR', 45, 50, 55, true, '1', '0', false, (SELECT id FROM "user" WHERE email = 'admin@fathrpet.com')),
('Vito Aurélio', 'FOLGADO', 'ROUBAR', 50, 55, 45, true, '1', '0', false, (SELECT id FROM "user" WHERE email = 'admin@fathrpet.com')),
('Chande', 'MENTIROSO', 'ALZHEIMER', 50, 45, 55, true, '1', '0', false, (SELECT id FROM "user" WHERE email = 'admin@fathrpet.com')),
('Aedis amigo do Maxal', 'AGOUREIRO', 'PALAVRA_DA_PRAGA', 45, 55, 50, true, '1', '0', false, (SELECT id FROM "user" WHERE email = 'admin@fathrpet.com')),
('Pilton amigo do Maxal também', 'LUTADOR', 'LUVAS_DE_ACADEMIA', 55, 45, 50, true, '1', '0', false, (SELECT id FROM "user" WHERE email = 'admin@fathrpet.com'));
