CREATE OR REPLACE FUNCTION checkArrays(a1 int[], a2 int[])
    RETURNS boolean
    LANGUAGE plpgsql
AS
$$
DECLARE
    len1 int;
    len2 int;
BEGIN

    len1 := array_length(a1, 1);
    if len1 = 0 then
        return false;
    end if;
    len2 := array_length(a2, 1);
    if len2 != len1 then
        return false;
    end if;

    return true;
END;
$$;

CREATE OR REPLACE FUNCTION insertWorker(human int) RETURNS int
    LANGUAGE plpgsql AS
$$
DECLARE
    idd int;
BEGIN
    insert into workers (human_id) values (human) returning id into idd;
    return idd;
END;
$$;

CREATE OR REPLACE PROCEDURE makePersonAWorker(person_id int, post_id_t int, ep_id_t int)
    LANGUAGE plpgsql
AS
$$
DECLARE
    cnt_w    int;
    cnt_p    int;
    workr_id int;
BEGIN

    select count(people.id) into cnt_p from people where id = person_id;
    select count(workers.id) into cnt_w from workers where human_id = person_id;
    if cnt_w = 0 and cnt_p = 1 then
        workr_id := insertWorker(person_id);
        insert into workers_posts(worker_id, post_id, ep_id, taking_date)
        values (workr_id, post_id_t, ep_id_t, now());
    end if;

END;
$$;

-- CALL makePersonAWorker(999, 2, 1);

CREATE OR REPLACE PROCEDURE dismissWorker(worker_id_t int)
    LANGUAGE plpgsql
AS
$$
BEGIN
    update workers_posts set dismiss_date = now() where worker_id = worker_id_t;
END;
$$;

-- CALL dismissWorker(3000);

CREATE OR REPLACE PROCEDURE makePersonACustomer(person_id int)
    LANGUAGE plpgsql
AS
$$
DECLARE
    cnt_c int;
    cnt_p int;
BEGIN

    select count(people.id) into cnt_p from people where id = person_id;
    select count(customers.id) into cnt_c from customers where human_id = person_id;
    if cnt_c = 0 and cnt_p = 1 then
        insert into customers (human_id) values (person_id);
    end if;

END
$$;

CREATE OR REPLACE PROCEDURE createOrder(customer_t_id int, potions int[], amounts int[], out res int)
    LANGUAGE plpgsql
AS
$$
DECLARE
    len               int;
    status_accepted   int := 0;
    customer_order_id int;
BEGIN

    if not checkArrays(potions, amounts) then
        return;
    end if;

    len := array_length(potions, 1);
    raise notice '%', len;

    insert into orders(customer_id, order_status, creation_time, completion_time)
    values (customer_t_id, status_accepted, now(), NULL)
    returning id into customer_order_id;

    for counter in 1..len
        loop
            insert into orders_potions(order_id, potion_id, amount_of_potions)
            values (customer_order_id, potions[counter], amounts[counter]);
        end loop;

    res := customer_order_id;
END;
$$;

-- CALL createOrder(1, array[1,2], array[1,1]);

CREATE OR REPLACE PROCEDURE createToolsDelivery(ep_from_id_t int, ep_to_id_t int, courier_id_t int, tools int[],
                                                amounts int[])
    LANGUAGE plpgsql
AS
$$
DECLARE
    delivery_id_t int;
    len           int;
    counter       int;
BEGIN

    if not checkarrays(tools, amounts) then
        return;
    end if;

    insert into enterprise_deliveries(ep_from_id, ep_to_id, courier_id, creation_time)
    values (ep_from_id_t, ep_to_id_t, courier_id_t, now())
    returning id into delivery_id_t;

    len := array_length(tools, 1);

    for counter in 1..len
        loop
            insert into deliveries_tools(delivery_id, tool_id, amount_of_tools)
            values (delivery_id_t, tools[counter], amounts[counter]);
        end loop;
END;
$$;

-- CALL createToolsDelivery(2, 3, 1, array[0, 1], array[2,2]);

CREATE OR REPLACE PROCEDURE createIngredientsDelivery(ep_from_id_t int, ep_to_id_t int, courier_id_t int,
                                                      ingredients int[],
                                                      amounts int[])
    LANGUAGE plpgsql
AS
$$
DECLARE
    delivery_id_t int;
    len           int;
    counter       int;
BEGIN

    if not checkarrays(ingredients, amounts) then
        return;
    end if;

    insert into enterprise_deliveries(ep_from_id, ep_to_id, courier_id, creation_time)
    values (ep_from_id_t, ep_to_id_t, courier_id_t, now())
    returning id into delivery_id_t;

    len := array_length(ingredients, 1);

    for counter in 1..len
        loop
            insert into deliveries_ingredients(delivery_id, ingredient_id, amount_of_ingredient)
            values (delivery_id_t, ingredients[counter], amounts[counter]);
        end loop;
END;
$$;

-- CALL createIngredientsDelivery(5, 7, 0, array[1], array[5]);

select * from orders join order_status on orders.order_status = order_status.id  where order_status.status != 'Passed'


