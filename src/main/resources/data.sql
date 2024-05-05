INSERT INTO users
SELECT 1, now(), true, 'admin@433@teste', 'admin'
WHERE
    NOT EXISTS (
        SELECT id FROM users WHERE id = 1
    );

INSERT INTO categories (id, created_at, name)
SELECT 1, NOW(), 'Teste'
WHERE
    NOT EXISTS (
        SELECT id FROM categories WHERE id = 1
    );


INSERT INTO exercises (id, active, external_id, text, level, type, created_at)
SELECT 1, true, 'd1bf885c-b9e6-4b4d-a033-18b188052e51', 'Qual as cores da bandeira do Brasil?', 0, 0, now()
WHERE
    NOT EXISTS (
        SELECT id FROM exercises WHERE id = 1
    );

INSERT INTO exercise_categories
SELECT 1, 1
WHERE
    NOT EXISTS (
        SELECT exercise_id, category_id FROM exercise_categories WHERE exercise_id = 1 AND category_id = 1
    );