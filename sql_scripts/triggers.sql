CREATE OR REPLACE FUNCTION decreaseAmounts() RETURNS TRIGGER AS
$$
DECLARE
    ep_fr_id int;
    ep_t_id int;
    cnt int;
BEGIN

    select ep_from_id, ep_to_id into ep_fr_id, ep_t_id from enterprise_deliveries where id = new.delivery_id;

    update enterprise_point_warehouse
    set amount_of_ingredient = amount_of_ingredient - new.amount_of_ingredient
    where ep_id = ep_fr_id
      and ingredient_id = new.ingredient_id;

    select count(id)
    into cnt
    from enterprise_point_warehouse
    where ep_id = ep_t_id
      and ingredient_id = new.ingredient_id;

    if cnt = 0 then
        insert into enterprise_point_warehouse(ep_id, ingredient_id, amount_of_ingredient)
        values (ep_t_id, new.ingredient_id, 0);
    end if;

    return new;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER decreaseAmountsDuringDelivery
    AFTER INSERT
    ON deliveries_ingredients
    FOR EACH ROW
EXECUTE PROCEDURE decreaseAmounts();

CREATE OR REPLACE FUNCTION checkAmount()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    if new.amount_of_ingredient < 0 then
        update enterprise_point_warehouse set amount_of_ingredient = 0 where id = new.id;
    end if;
    return new;
END;
$$;

CREATE TRIGGER checkAmountTrigger
    AFTER INSERT OR UPDATE
    ON enterprise_point_warehouse
    FOR EACH ROW
EXECUTE PROCEDURE checkAmount();


CREATE OR REPLACE FUNCTION increaseAmounts() RETURNS TRIGGER AS
$$
DECLARE
    rec record;
BEGIN

    raise notice 'increasing amount';
    for rec in select ingredient_id, amount_of_ingredient
               from deliveries_ingredients
               where delivery_id = new.id
        loop
            update enterprise_point_warehouse
            set amount_of_ingredient = amount_of_ingredient + rec.amount_of_ingredient
            where ep_id = new.ep_to_id
              and ingredient_id = rec.ingredient_id;
        end loop;
    return new;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER increaseAmountsAfterDelivery
    AFTER UPDATE OF completion_time
    ON enterprise_deliveries
    FOR EACH ROW
EXECUTE PROCEDURE increaseAmounts();

CREATE OR REPLACE FUNCTION changeStatus() RETURNS TRIGGER AS
$$
DECLARE
    status_id int;
BEGIN

    select id into status_id from order_status where status = 'Passed';
    update orders set order_status = status_id where id = new.id;
    return new;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER orderStatusChange
    AFTER UPDATE OF completion_time
    ON orders
    FOR EACH ROW
EXECUTE PROCEDURE changeStatus();

CREATE OR REPLACE FUNCTION checkIngredients() RETURNS TRIGGER AS
$$
DECLARE
    cnt int;
BEGIN

    raise notice 'JOPA';
    select count(id)
    into cnt
    from enterprise_point_warehouse
    where ep_id = new.ep_id
      and ingredient_id = new.ingredient_id;

    if cnt = 0 then
        insert into enterprise_point_warehouse(ep_id, ingredient_id, amount_of_ingredient)
        values (new.ep_id, new.ingredient_id, new.amount_of_ingredient);
    end if;

    return new;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER checkIngredientsInWarehouse
    BEFORE UPDATE
    ON enterprise_point_warehouse
    FOR EACH ROW
EXECUTE PROCEDURE checkIngredients();