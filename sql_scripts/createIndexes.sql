-- Когда хотим узнать статус заказ, чтобы понимать можем ли отдать
CREATE INDEX ind_order_status ON orders USING HASH (order_status);
CREATE INDEX ind_order_status_id ON order_status USING HASH (id);

DROP INDEX ind_order_status_id;

-- Для поиска человека, которому нужно отдать заказ
CREATE INDEX ind_people_id ON people USING HASH (id);

-- При поиске рецепта нужного нам зелья
CREATE INDEX ind_recept ON ingredients_potions USING HASH (potion_id);

-- Для поиска EP нужного типа
CREATE INDEX ind_for_deliver ON enterprise_point USING HASH (type);

DROP INDEX ind_for_deliver;

-- для поиска нужного ингредиента (Только эти два индекса оказались полезными)
CREATE INDEX ind_availability_potion ON enterprise_point_coldwarehouse USING HASH (potion_id);
CREATE INDEX ind_availability_ingredient ON enterprise_point_warehouse USING HASH (ingredient_id);

DROP INDEX ind_availability_potion;
DROP INDEX ind_availability_ingredient;

-- При поиске нужного количества ингредиентов на складе
CREATE INDEX ind_availability_count_potion ON enterprise_point_coldwarehouse USING BTREE (amount_of_ingredient);
CREATE INDEX ind_availability_count_ingredient ON enterprise_point_warehouse USING BTREE (amount_of_ingredient);

DROP INDEX ind_availability_count_ingredient;

-- Узнать в каком типе EP искать ингридиент
CREATE INDEX ind_where_ingredient ON enterprise_point_ingredients USING HASH (ingredient_id);


-- Запросы для теста

EXPLAIN ANALYZE SELECT * FROM orders
WHERE order_status = (select id from order_status where status = 'Passed');

EXPLAIN ANALYZE SELECT * FROM people
                WHERE id = 9432;

EXPLAIN ANALYZE SELECT count(*) FROM enterprise_point
JOIN enterprise_point_warehouse epw on enterprise_point.id = epw.ep_id
WHERE type = (SELECT id FROM enterprise_point_type WHERE type = 'shop')
AND epw.ingredient_id = (SELECT id FROM ingredients WHERE name = 'Сахар')
AND epw.amount_of_ingredient >= 5;

EXPLAIN ANALYZE SELECT count(*) FROM enterprise_point
JOIN enterprise_point_coldwarehouse epw on enterprise_point.id = epw.ep_id
WHERE type = (SELECT id FROM enterprise_point_type WHERE type = 'issuing orders')
AND epw.potion_id = (SELECT id FROM potions WHERE name = 'Зелье силы')
AND epw.amount_of_ingredient >= 2;