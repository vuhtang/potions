-- Когда хоти узнать статус заказ, чтобы понимать можем ли отдать
CREATE INDEX ind_order_status ON orders USING HASH (order_status);

-- Для поиска человека, которому нужно отдать заказ
CREATE INDEX ind_people_id ON people USING HASH (id);

-- При поиске рецепта нужного нам зелья
CREATE INDEX ind_recept ON ingredients_potions USING HASH (potion_id);

-- Для поиска EP нужного типа
CREATE INDEX ind_for_deliver ON enterprise_point USING HASH (type);

-- для поиска нужного ингредиента
CREATE INDEX ind_availability_potion ON enterprise_point_coldwarehouse USING HASH (potion_id);
CREATE INDEX ind_availability_ingredient ON enterprise_point_warehouse USING HASH (ingredient_id);

-- При поиске нужного количества ингредиентов на складе
CREATE INDEX ind_availability_count_potion ON enterprise_point_coldwarehouse USING BTREE (amount_of_ingredient);
CREATE INDEX ind_availability_count_ingredient ON enterprise_point_warehouse USING BTREE (amount_of_ingredient);

-- Узнать в каком типе EP искать ингридиент
CREATE INDEX ind_where_ingredient ON enterprise_point_ingredients USING HASH (ingredient_id);
