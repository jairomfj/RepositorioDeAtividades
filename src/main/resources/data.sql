-- users
INSERT INTO users
SELECT 1, now(), true, 'admin@433@teste', 'admin'
WHERE
    NOT EXISTS (
        SELECT id FROM users WHERE id = 1
    );

-- class year
INSERT INTO class_year (id, name, external_id, created_at)
SELECT 1, '1o Ano', '1o-ano', now()
WHERE
    NOT EXISTS (
        SELECT id FROM class_year WHERE id = 1
    );


-- subjects
INSERT INTO subjects (id, name, external_id, created_at)
SELECT 1, 'Português', 'portugues', now()
WHERE
    NOT EXISTS (
        SELECT id FROM subjects WHERE id = 1
    );


-- categories
INSERT INTO categories (id, created_at, name, external_id, class_year, subjects)
SELECT 1, NOW(), 'Teste', 'teste', 1, 1
WHERE
    NOT EXISTS (
        SELECT id FROM categories WHERE id = 1
    );

-- exercises
INSERT INTO exercises (id, active, external_id, text, level, type, created_at)
SELECT 1, true, 'd1bf885c-b9e6-4b4d-a033-18b188052e51', 'Qual as cores da bandeira do Brasil?', 0, 0, now()
WHERE
    NOT EXISTS (
        SELECT id FROM exercises WHERE id = 1
    );

-- exercise_categories
INSERT INTO exercise_categories
SELECT 1, 1
WHERE
    NOT EXISTS (
        SELECT exercise_id, category_id FROM exercise_categories WHERE exercise_id = 1 AND category_id = 1
    );
