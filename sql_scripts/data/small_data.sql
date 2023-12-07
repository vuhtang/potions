INSERT INTO living_things (id, name)
VALUES(1, 'Other'),
      (2, 'Slime'),
      (3, 'Spider'),
      (4, 'Skeleton'),
      (5, 'Chicken');

ALTER SEQUENCE living_things_id_seq RESTART WITH 6;

INSERT INTO ingredients (name, living_thing_id)
VALUES('Глаз паука', 3), 
('Сахар', NULL),
('Грибы', NULL),
('Магмакремень', NULL),
('Красный порошок', NULL),
('Блестящий порошок', NULL),
('Костная мука', 4), 
('Медовая бутылка', NULL),
('Паучье око', 3), 
('Золотая морковь', NULL),
('Водная бутылка', NULL),
('Красная пыльца', NULL),
('Перо', 5), 
('Слизь', 2), 
('Золотой слиток', NULL);


INSERT INTO effects (id, name, power, duration)
VALUES(0, 'регенерации', 1, 60), 
(1, 'инстант-регенерации', 2, 90), 
(2, 'силы', 1, 60), 
(3, 'силы', 2, 90), 
(4, 'огнестойкости', 1, 60), 
(5, 'огнестойкости', 2, 90), 
(6, 'Зелье замедления', 1, 60), 
(7, 'восполнения', 1, 60), 
(8, 'восполнения', 2, 90), 
(9, 'урона', 1, 60), 
(10, 'урона', 2, 90), 
(11, 'бесполезности', 1, 60), 
(12, 'бесполезности', 2, 90), 
(13, 'беспризорности', 1, 60), 
(14, 'беспризорности', 2, 90), 
(15, 'невидимости', 1, 60), 
(16, 'невидимости', 2, 90), 
(17, 'ночное видения', 1, 60), 
(18, 'ночного видения', 2, 90), 
(19, 'замедленного падения', 1, 60), 
(20, 'обновления вдохновения', 1, 60), 
(21, 'обновлениfя вдохновения', 2, 90);

ALTER SEQUENCE effects_id_seq RESTART WITH 22;

INSERT INTO enterprise_point_type (id, type)
VALUES(0, 'shop'), 
(1, 'issuing orders'), 
(2, 'test');
ALTER SEQUENCE enterprise_point_type_id_seq RESTART WITH 3;

INSERT INTO posts (id, name, EPT_id)
VALUES(0, 'seller', 0), 
(1, 'deliver', 0), 
(2, 'manager', 1), 
(3, 'tester', 2);
ALTER SEQUENCE posts_id_seq RESTART WITH 4;

INSERT INTO potions (id, name, effect_id)
VALUES(0, 'Зелье регенерации', 0), 
(1, 'Зелье инстант-регенерации', 1), 
(2, 'Зелье силы', 2), 
(3, 'Зелье силы II', 3), 
(4, 'Зелье огнестойкости', 4), 
(5, 'Зелье огнестойкости II', 5),
(6, 'Зелье замедления', 6), 
(7, 'Зелье восполнения', 7), 
(8, 'Зелье восполнения II', 8), 
(9, 'Зелье урона', 9), 
(10, 'Зелье урона II', 10), 
(11, 'Зелье бесполезности', 11), 
(12, 'Зелье бесполезности II', 12), 
(13, 'Зелье беспризорности', 13), 
(14, 'Зелье беспризорности II', 14), 
(15, 'Зелье невидимости', 15), 
(16, 'Зелье невидимости II', 16), 
(17, 'Зелье ночного видения', 17), 
(18, 'Зелье ночного видения II', 18), 
(19, 'Зелье замедленного падения', 19), 
(20, 'Зелье обновления вдохновения', 20), 
(21, 'Зелье обновления вдохновения II', 21);
ALTER SEQUENCE potions_id_seq RESTART WITH 22;

INSERT INTO tools_armor (id, name)
VALUES(0, 'sword'), 
(1, 'pickaxe');
ALTER SEQUENCE tools_armor_id_seq RESTART WITH 2;

INSERT INTO order_status (id, status)
VALUES(0, 'Accepted'), 
(1, 'Processing'), 
(2, 'Review'), 
(3, 'Passed');
ALTER SEQUENCE order_status_id_seq RESTART WITH 4;

INSERT INTO test_status (id, status)
VALUES(0, 'Good ready'), 
(1, 'Bad ready');
ALTER SEQUENCE test_status_id_seq RESTART WITH 2;

INSERT INTO people (id, name, surname, date_of_birth)
VALUES(0, 'Karen', 'Zimmerman', make_date(1999, 10, 21)), 
(1, 'Katelyn', 'Scott', make_date(1966, 10, 27)), 
(2, 'Victoria', 'Preston', make_date(1963, 1, 26)), 
(3, 'Christina', 'Kim', make_date(1966, 5, 31)), 
(4, 'Jessica', 'Ray', make_date(2007, 1, 27));
ALTER SEQUENCE people_id_seq RESTART WITH 5;

INSERT INTO customers (id, human_id)
VALUES(0, 3), 
(1, 1);
ALTER SEQUENCE customers_id_seq RESTART WITH 2;

INSERT INTO workers (id, human_id)
VALUES(0, 0);
ALTER SEQUENCE workers_id_seq RESTART WITH 1;

INSERT INTO enterprise_point (id, name, location, type)
VALUES(0, 'Mccall-Cannon', 'West Travis', 2), 
(1, 'Wallace, Ramirez and Kemp', 'Lynchberg', 2), 
(2, 'Cole, Wiley and Manning', 'New Christopher', 2), 
(3, 'Flores-Gonzalez', 'Jayfurt', 1), 
(4, 'Owen, Johnson and Cruz', 'Bushland', 2);
ALTER SEQUENCE enterprise_point_id_seq RESTART WITH 5;

INSERT INTO workers_posts (id, worker_id, post_id, ep_id)
VALUES(0, 0, 3, 2),
(1, 0, 2, 3),
(2, 0, 3, 2),
(3, 0, 3, 2),
(4, 0, 2, 3);
ALTER SEQUENCE workers_posts_id_seq RESTART WITH 5;

INSERT INTO orders (id, customer_id, order_status, creation_time, completion_time)
VALUES(0, 0, 0, make_timestamp(2023, 6, 12, 15, 4, 45), make_timestamp(2099, 1, 1, 0, 0, 0)),
(1, 1, 2, make_timestamp(2023, 10, 21, 23, 36, 4), make_timestamp(2099, 1, 1, 0, 0, 0)),
(2, 1, 3, make_timestamp(2023, 3, 24, 3, 30, 8), make_timestamp(2099, 1, 1, 0, 0, 0)),
(3, 0, 3, make_timestamp(2023, 1, 21, 9, 14, 42), make_timestamp(2099, 1, 1, 0, 0, 0)),
(4, 0, 1, make_timestamp(2023, 7, 16, 3, 23, 15), make_timestamp(2099, 1, 1, 0, 0, 0));
ALTER SEQUENCE orders_id_seq RESTART WITH 5;

INSERT INTO orders_potions (id, order_id, Potion_id, amount_of_potions)
VALUES(0, 0, 3, 4),
(1, 1, 1, 8),
(2, 2, 1, 1),
(3, 3, 1, 9),
(4, 4, 1, 6);
ALTER SEQUENCE orders_potions_id_seq RESTART WITH 5;

INSERT INTO enterprise_deliveries (id, ep_from_id, ep_to_id, courier_id, creation_time, completion_time)
VALUES(0, 3, 0, 0, make_timestamp(2023, 4, 25, 9, 53, 24), make_timestamp(2023, 2, 20, 10, 25, 35)),
(1, 1, 4, 0, make_timestamp(2023, 8, 2, 11, 21, 10), make_timestamp(2023, 9, 9, 2, 40, 10)),
(2, 1, 3, 0, make_timestamp(2023, 5, 15, 16, 3, 1), make_timestamp(2023, 8, 30, 0, 36, 57)),
(3, 4, 2, 0, make_timestamp(2023, 1, 2, 20, 25, 49), make_timestamp(2023, 10, 19, 11, 3, 55)),
(4, 0, 0, 0, make_timestamp(2023, 1, 30, 16, 45, 45), make_timestamp(2023, 4, 30, 23, 47, 24));
ALTER SEQUENCE enterprise_deliveries_id_seq RESTART WITH 5;

INSERT INTO deliveries_tools (id, delivery_id, tool_id, amount_of_tools)
VALUES(0, 0, 0, 2),
(1, 2, 1, 4),
(2, 1, 1, 2),
(3, 3, 1, 3),
(4, 2, 0, 4);
ALTER SEQUENCE deliveries_tools_id_seq RESTART WITH 5;

INSERT INTO deliveries_ingredients (id, delivery_id, ingredient_id, amount_of_ingredient)
VALUES(0, 3, 2, 4),
(1, 3, 3, 5),
(2, 2, 5, 4),
(3, 3, 7, 1),
(4, 1, 6, 5);
ALTER SEQUENCE deliveries_ingredients_id_seq RESTART WITH 5;

INSERT INTO enterprise_point_coldwarehouse (ep_id, potion_id, amount_of_ingredient)
VALUES(3, 7, 32),
(3, 6, 48),
(3, 11, 40),
(3, 0, 4),
(3, 15, 24),
(3, 18, 7),
(3, 13, 43);


INSERT INTO tests (id, test_date, potion_id, test_status, worker_id, living_thing_id, duration)
VALUES(0, make_timestamp(2023, 8, 1, 19, 43, 22), 2, 0, 0, 2, 76),
(1, make_timestamp(2023, 3, 14, 15, 3, 10), 0, 1, 0, 2, 42),
(2, make_timestamp(2023, 9, 11, 2, 56, 1), 11, 0, 0, 4, 103),
(4, make_timestamp(2023, 8, 17, 13, 31, 17), 13, 0, 0, 2, 61);
ALTER SEQUENCE tests_id_seq RESTART WITH 5;

INSERT INTO enterprise_point_ingredients(ept_id, ingredient_id)
VALUES(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7);

INSERT INTO enterprise_point_warehouse(ep_id, ingredient_id, amount_of_ingredient)
VALUES(3, 3, 5),
(3, 4, 4),
(3, 5, 5),
(3, 6, 5);

INSERT INTO ingredients_potions(potion_id, ingredient_id, amount_of_ingredient)
VALUES (1, 2, 1),
       (2, 4, 1),
(3, 5, 1),
(4, 6, 1),
(5, 7, 1),
(6, 8, 1),
(7, 9, 1);

