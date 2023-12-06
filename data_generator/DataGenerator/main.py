from faker import Faker
import random
from datetime import datetime, timedelta

fake = Faker()

# Генерация случайных дат в заданном диапазоне
def generate_random_date(start_date, end_date):
    return fake.date_time_between_dates(datetime.strptime(start_date, "%Y-%m-%d"), datetime.strptime(end_date, "%Y-%m-%d"))

# Генерация случайных данных для таблицы People
def generate_people_data(num_rows):
    data = []
    for id in range(num_rows):
        temp = fake.name().split(' ')
        name = temp[0]
        surname = temp[1]
        date_of_birth = (fake.date_of_birth(minimum_age=14, maximum_age=60)).strftime("%Y-%m-%d")
        data.append((id, name, surname, date_of_birth))
    return data


def generate_customers(peoples):
    data = []
    temp = random.sample(peoples, len(peoples) // 2)
    for id in range(len(peoples)//2):
        data.append((id, temp[id][0]))
    return data


def generate_workers(peoples):
    data = []
    temp = random.sample(peoples, len(peoples) // 5)
    for id in range(len(peoples)//5):
        data.append((id, temp[id][0]))
    return data


# # Генерация случайных данных для таблицы Enterprise_Point_Type
# def generate_enterprise_point_type_data(num_rows):
#     data = ['shop', 'extradition', 'order']
#     return data

# Генерация случайных данных для таблицы Enterprise_Point
def generate_enterprise_point_data(num_rows, types):
    data = []
    for id in range(num_rows):
        name = fake.company()
        location = fake.city()
        type_id = random.choice(types)[0]
        data.append((id, name, location, type_id))
    return data

# # Генерация случайных данных для таблицы Order_Status
# def generate_order_status_data(num_rows):
#     data = ['Accepted', 'Processing', 'Review', 'Passed']
#     return data

# Генерация случайных данных для таблицы Orders
def generate_orders_data(num_rows, customers, statuses):
    data = []
    for id in range(num_rows):
        customer_id = random.choice(customers)[0]
        order_status = random.choice(statuses)[0]
        creation_time = generate_random_date("2023-01-01", "2023-11-01").strftime("%Y-%m-%d %H:%M:%S")
        if (order_status == 'Passed'):
            completion_time = creation_time + timedelta(days=random.randint(1, 30))
        else:
            completion_time = generate_random_date("2099-01-01", "2099-01-01").strftime("%Y-%m-%d %H:%M:%S")
        data.append((id, customer_id, order_status, creation_time, completion_time))
    return data


def generate_orders_potions_data(num_rows, potions, orders):
    data = []
    for id in range(num_rows):
        potions_id = random.choice(potions)[0]
        order_id = random.choice(orders)[0]
        amount_of_potions = random.randint(1, 10)
        data.append((id, potions_id, order_id, amount_of_potions))
    return data


def generate_enterprise_deliviers_data(num_rows, points, workers):
    data = []
    for id in range(num_rows):
        EP_from_id = random.choice(points)[0]
        EP_to_id = random.choice(points)[0]
        while EP_from_id == EP_to_id:
            EP_to_id = random.choice(points)[0]
        courier_id = random.choice(workers)[0]
        creation_time = generate_random_date("2023-01-01", "2023-11-01").strftime("%Y-%m-%d %H:%M:%S")
        completion_time = generate_random_date("2023-01-01", "2023-11-01").strftime("%Y-%m-%d %H:%M:%S")
        while (creation_time > completion_time):
            completion_time = generate_random_date("2023-01-01", "2023-11-01").strftime("%Y-%m-%d %H:%M:%S")
        data.append((id, EP_from_id, EP_to_id, courier_id, creation_time, completion_time))
    return data


def generate_deliviers_ingridients_data(num_rows, delivier, ingridients):
    data = []
    for id in range(num_rows):
        delivier_id = random.choice(delivier)[0]
        ingridient_id = random.choice(ingridients)[0]
        amount_of_ingridient = random.randint(1, 5)
        data.append((id, delivier_id, ingridient_id, amount_of_ingridient))
    return data


def generate_workers_posts_data(num_rows, workers, posts, EPs):
    data = []
    for id in range(num_rows):
        worker_id = random.choice(workers)[0]
        EP = random.choice(EPs)
        post = random.choice(posts)
        EP_id = EP[0]
        while EP[3] != post[2]:
            post = random.choice(posts)
        post_id = post[0]
        taking_date = generate_random_date("2021-01-01", "2023-11-01")
        dismiss_date = generate_random_date("2021-01-01", "2023-12-01")
        while (taking_date > dismiss_date):
            dismiss_date = generate_random_date("2023-01-01", "2023-11-01")
        taking_date = taking_date.strftime("%Y-%m-%d")
        dismiss_date = dismiss_date.strftime("%Y-%m-%d")
        data.append((id, worker_id, EP_id, post_id, taking_date, dismiss_date))
    return data


def generate_deliviers_tools_data(num_rows, deliviers, tools):
    data = []
    for id in range(num_rows):
        deliviers_id = random.choice(deliviers)[0]
        tool_id = random.choice(tools)[0]
        amount_of_potions = random.randint(1, 5)
        data.append((id, deliviers_id, tool_id, amount_of_potions))
    return data


# # Генерация случайных данных для таблицы Ingredients
# def generate_ingredients_data(num_rows, living_things):
#     data = ['Глаз паука', 'Сахар', 'Грибы', 'Магмакремень',
#             'Красный порошок', 'Блестящий порошок', 'Костная мука',
#             'Медовая бутылка', 'Паучье око', 'Золотая морковь',
#             'Водная бутылка', 'Красная пыльца', 'Перо', 'Слизь',
#             'Золотой слиток']
#     return data

# # Генерация случайных данных для таблицы Effects
# def generate_effects_data():
#     data = [('регенерации', 1), ('инстант-регенерации', 2),
#             ('силы', 1), ('силы', 2), ('огнестойкости', 1),
#             ('огнестойкости', 2), ('Зелье замедления', 1),
#             ('восполнения', 1), ('восполнения', 2), ('урона', 1),
#             ('урона', 2), ('бесполезности', 1), ('бесполезности', 2),
#             ('беспризорности', 1), ('беспризорности', 2),
#             ('невидимости', 1), ('невидимости', 2), ('ночное видения', 1),
#             ('ночного видения', 2), ('замедленного падения', 1),
#             ('обновления вдохновения', 1), ('обновлениfя вдохновения', 2)]
#     return data

# # Генерация случайных данных для таблицы Potions
# def generate_potions_data(num_rows, effects):
#     data = [('Зелье регенерации', 0), ('Зелье инстант-регенерации', 1),
#             ('Зелье силы', 2), ('Зелье силы II', 3), ('Зелье огнестойкости', 4),
#             ('Зелье огнестойкости II', 5), ('Зелье замедления', 6),
#             ('Зелье восполнения', 7), ('Зелье восполнения II', 8), ('Зелье урона', 9),
#             ('Зелье урона II', 10), ('Зелье бесполезности', 11), ('Зелье бесполезности II', 12),
#             ('Зелье беспризорности', 13), ('Зелье беспризорности II', 14),
#             ('Зелье невидимости', 15), ('Зелье невидимости II', 16), ('Зелье ночного видения', 17),
#             ('Зелье ночного видения II', 18), ('Зелье замедленного падения', 19),
#             ('Зелье обновления вдохновения', 20), ('Зелье обновления вдохновения II', 21)]
#     return data



# Генерация случайных данных для таблицы Tests
# def generate_tests_data(num_rows, potions, statuses, workers, living_things):
#     data = []
#     for id in range(num_rows):
#         potion_id = random.choice(potions)[0]
#         test_date = (generate_random_date("2023-01-01", "2023-11-01")).strftime("%Y-%m-%d")
#         test_status = random.choice(statuses)[0]
#         worker_id = random.choice(workers)[0]
#         living_thing_id = random.choice(living_things)[0]
#         duration = random.randint(1, 10)
#         data.append((id, potion_id, test_date, test_status, worker_id, living_thing_id, duration))
#     return data


# Генерация случайных данных для таблицы Enterprise_Point_Warehouse (Надо подумь так чтобы в зависимости от типа вносили продукты и количество логичное)
def generate_enterprise_point_warehouse_data(num_rows, enterprise_points, potions):
    data = []

    for id in range(num_rows):
        ep = enterprise_points[id]
        if(ep[3] != 1):
            continue

        ep_id = ep[0]
        random_potions = random.sample(potions, random.randint(0, 15))


        for i in random_potions:
            potion_id = i[0]
            amount_of_ingredient = random.randint(0, 50)
            if(amount_of_ingredient != 0):
                data.append((id, ep_id, potion_id, amount_of_ingredient))
    return data

# Генерация случайных данных для таблицы Enterprise_Point_Ingredients (Ручками надо заполнить)
def generate_enterprise_point_ingredients_data(num_rows, enterprise_points, ingredients):
    data = []

    for id in range(num_rows):
        ep = enterprise_points[id]
        if(ep[3] != 0):
            continue

        ep_id = ep[0]
        random_ingridients = random.sample(ingredients, random.randint(0, 15))

        for i in random_ingridients:
            ingridient_id = i[0]
            amount_of_ingredient = random.randint(0, 50)
            if(amount_of_ingredient != 0):
                data.append((id, ep_id, ingridient_id, amount_of_ingredient))
    return data


def generate_tests_data(num_rows, enterprise_points, potions, test_statuses, workers, living_things):
    data = []

    for id in range(num_rows):
        ep = enterprise_points[id]
        if(ep[3] != 2):
            continue

        creation_time = (generate_random_date("2023-01-01", "2023-11-01")).strftime("%Y-%m-%d")
        potion_id = random.choice(potions)[0]
        worker_id = random.choice(workers)[0]
        living_thing_id = random.choice(living_things)[0]
        duration_sec = random.randint(40, 150)
        if(duration_sec > 60 and duration_sec < 110):
            test_statuses_id = 0
        else:
            test_statuses_id = 1

        data.append((id, creation_time, potion_id, test_statuses_id, worker_id, living_thing_id, duration_sec))

    return data





ingridients = [
    ('Глаз паука', 3),
    ('Сахар', 'NULL'),
    ('Грибы', 'NULL'),
    ('Магмакремень', 'NULL'),
    ('Красный порошок', 'NULL'),
    ('Блестящий порошок', 'NULL'),
    ('Костная мука', 4),
    ('Медовая бутылка', 'NULL'),
    ('Паучье око', 3),
    ('Золотая морковь', 'NULL'),
    ('Водная бутылка', 'NULL'),
    ('Красная пыльца', 'NULL'),
    ('Перо', 5),
    ('Слизь', 2),
    ('Золотой слиток', 'NULL')
]

living_things = [
        (1, 'Other'),
        (2, 'Slime'),
        (3, 'Spider'),
        (4, 'Skeleton'),
        (5, 'Chicken')
]


potions = [
        (0, 'Зелье регенерации', 0),
        (1, 'Зелье инстант-регенерации', 1),
        (2, 'Зелье силы', 2), (3, 'Зелье силы II', 3),
        (4, 'Зелье огнестойкости', 4), (5, 'Зелье огнестойкости II', 5),
        (6, 'Зелье замедления', 6),
        (7, 'Зелье восполнения', 7), (8, 'Зелье восполнения II', 8),
        (9, 'Зелье урона', 9), (10, 'Зелье урона II', 10),
        (11, 'Зелье бесполезности', 11), (12, 'Зелье бесполезности II', 12),
        (13, 'Зелье беспризорности', 13), (14, 'Зелье беспризорности II', 14),
        (15, 'Зелье невидимости', 15), (16, 'Зелье невидимости II', 16),
        (17, 'Зелье ночного видения', 17), (18, 'Зелье ночного видения II', 18),
        (19, 'Зелье замедленного падения', 19),
        (20, 'Зелье обновления вдохновения', 20), (21, 'Зелье обновления вдохновения II', 21)
]


effects = [
        (0, 'регенерации', 1, 60),
        (1, 'инстант-регенерации', 2, 90),
        (2, 'силы', 1, 60), (3, 'силы', 2, 90),
        (4, 'огнестойкости', 1, 60),
        (5, 'огнестойкости', 2, 90),
        (6, 'Зелье замедления', 1, 60),
        (7, 'восполнения', 1, 60), (8, 'восполнения', 2, 90),
        (9, 'урона', 1, 60), (10, 'урона', 2, 90),
        (11, 'бесполезности', 1, 60), (12, 'бесполезности', 2, 90),
        (13, 'беспризорности', 1, 60), (14, 'беспризорности', 2, 90),
        (15, 'невидимости', 1, 60), (16, 'невидимости', 2, 90),
        (17, 'ночное видения', 1, 60), (18, 'ночного видения', 2, 90),
        (19, 'замедленного падения', 1, 60),
        (20, 'обновления вдохновения', 1, 60), (21, 'обновлениfя вдохновения', 2, 90)
]


enterprise_point_types = [
        (0, 'shop'),
        (1, 'issuing orders'),
        (2, 'test')
]


posts = [
    (0, 'seller', 0),
    (1, 'deliver', 0),
    (2, 'manager', 1),
    (3, 'tester', 2)
]


tools_armors = [
        (0, 'sword'),
        (1, 'pickaxe')
]


statuses = [
    (0, 'Accepted'),
    (1, 'Processing'),
    (2, 'Review'),
    (3, 'Passed')
]


test_statuses = [
    (0, 'Good ready'),
    (1, 'Bad ready')
]


n = 10

peoples = generate_people_data(n)
customers = generate_customers(peoples)
workers = generate_workers(peoples)
enterprise_pointes = generate_enterprise_point_data(n, enterprise_point_types)
workers_posts = generate_workers_posts_data(n, workers, posts, enterprise_pointes)
orders = generate_orders_data(n, customers, statuses)
orders_potions = generate_orders_potions_data(n, potions, orders)
enterprise_deliviers = generate_enterprise_deliviers_data(n, enterprise_pointes, workers)
deliviers_tools = generate_deliviers_tools_data(n, enterprise_deliviers, tools_armors)
deliviers_ingridients = generate_deliviers_ingridients_data(n, enterprise_deliviers, ingridients)
enterprise_point_coldwarehouse = generate_enterprise_point_warehouse_data(n, enterprise_pointes, potions)
enterprise_point_warehouse = generate_enterprise_point_ingredients_data(n, enterprise_pointes, ingridients)
tests = generate_tests_data(n, enterprise_pointes, potions, test_statuses, workers, living_things)


with open('example.txt', 'w') as file:

    file.write('INSERT INTO' + ' ingredients ' + '(id, name, living_thing_id)\n')
    file.write('VALUES')
    for i in ingridients:
        file.write(str(i))
        if i != ingridients[len(ingridients) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' living_things ' + '(id, name)\n')
    file.write('VALUES')
    for i in living_things:
        file.write(str(i))
        if i != living_things[len(living_things) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' effects ' + '(id, name, power, duration)\n')
    file.write('VALUES')
    for i in effects:
        file.write(str(i))
        if i != effects[len(effects) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' enterprise_point_type ' + '(id, type)\n')
    file.write('VALUES')
    for i in enterprise_point_types:
        file.write(str(i))
        if i != enterprise_point_types[len(enterprise_point_types) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' posts ' + '(id, name, EPT_id)\n')
    file.write('VALUES')
    for i in posts:
        file.write(str(i))
        if i != posts[len(posts) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' potions ' + '(id, name, effect_id)\n')
    file.write('VALUES')
    for i in potions:
        file.write(str(i))
        if i != potions[len(potions) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' tools_armor ' + '(id, name)\n')
    file.write('VALUES')
    for i in tools_armors:
        file.write(str(i))
        if i != tools_armors[len(tools_armors) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' order_status ' + '(id, status)\n')
    file.write('VALUES')
    for i in statuses:
        file.write(str(i))
        if i != statuses[len(statuses) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' test_status ' + '(id, status)\n')
    file.write('VALUES')
    for i in test_statuses:
        file.write(str(i))
        if i != test_statuses[len(test_statuses) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')


    file.write('INSERT INTO' + ' people ' + '(id, name, surname, date_of_birth)\n')
    file.write('VALUES')
    for i in peoples:
        file.write(str(i))
        if i != peoples[len(peoples) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')

    file.write('INSERT INTO' + ' customers ' + '(id, human_id)\n')
    file.write('VALUES')
    for i in customers:
        file.write(str(i))
        if i != customers[len(customers) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')

    file.write('INSERT INTO' + ' workers ' + '(id, human_id)\n')
    file.write('VALUES')
    for i in workers:
        file.write(str(i))
        if i != workers[len(workers) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')

    file.write('INSERT INTO' + ' enterprise_point ' + '(id, name, location, type)\n')
    file.write('VALUES')
    for i in enterprise_pointes:
        file.write(str(i))
        if i != enterprise_pointes[len(enterprise_pointes) - 1]:
            file.write(', \n')
        else:file.write(';\n\n')

    file.write('INSERT INTO' + ' workers_posts ' + '(id, worker_id, post_id, ep_id, taking_date, dismiss_date)\n')
    file.write('VALUES')
    for i in workers_posts:
        file.write(str(i))
        if i != workers_posts[len(workers_posts) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' orders ' + '(id, customer_id, order_status, creating_time, completion_time)\n')
    file.write('VALUES')
    for i in orders:
        file.write(str(i))
        if i != orders[len(orders) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' orders_potions ' + '(id, order_id, Potion_id, amount_of_potions)\n')
    file.write('VALUES')
    for i in orders_potions:
        file.write(str(i))
        if i != orders_potions[len(orders_potions) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' enterprise_deliveries ' + '(id, ep_from_id, ep_to_id, courier_id, creation_time, completion_time)\n')
    file.write('VALUES')
    for i in enterprise_deliviers:
        file.write(str(i))
        if i != enterprise_deliviers[len(enterprise_deliviers) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' deliveries_tools ' + '(id, delivery_id, tool_id, amount_of_tools)\n')
    file.write('VALUES')
    for i in deliviers_tools:
        file.write(str(i))
        if i != deliviers_tools[len(deliviers_tools) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' deliveries_ingredients ' + '(id, delivery_id, ingredient_id, amount_od_ingredient)\n')
    file.write('VALUES')
    for i in deliviers_ingridients:
        file.write(str(i))
        if i != deliviers_ingridients[len(deliviers_ingridients) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' enterprise_point_coldwarehouse ' + '(id, ep_id, potion_id, amount_of_ingredient)\n')
    file.write('VALUES')
    for i in enterprise_point_coldwarehouse:
        file.write(str(i))
        if i != enterprise_point_coldwarehouse[len(enterprise_point_coldwarehouse) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' enterprise_point_warehouse ' + '(id, ep_id, ingredient_id, amount_of_ingredients)\n')
    file.write('VALUES')
    for i in enterprise_point_warehouse:
        file.write(str(i))
        if i != enterprise_point_warehouse[len(enterprise_point_warehouse) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

    file.write('INSERT INTO' + ' tests ' + '(id, potion_id, test_date, test_status, worker_id, living_thing_id, duration)\n')
    file.write('VALUES')
    for i in tests:
        file.write(str(i))
        if i != tests[len(tests) - 1]:
            file.write(', \n')
        else:
            file.write(';\n\n')

#
# # Вывод сгенерированных данных (можете закомментировать при необходимости)
print("People:", peoples)
print("Enterprise_Point_Type:", enterprise_point_types)
print("Enterprise_Point:", enterprise_pointes)
print("Order_Status:", statuses)
print("Customers:", customers)
print("Workers:", workers)
print("Workers_Posts:", workers_posts)
print("Posts:", posts)
print("Enterprise_Deliveries:", enterprise_deliviers)
print("Ingredients:", ingridients)
print("Potions:", potions)
print("Orders:", orders)
print("Enterprise_Point_Warehouse:", enterprise_point_coldwarehouse)
print("Enterprise_Point_Ingredients:", enterprise_point_warehouse)
print("Deliveries_Ingredients:", deliviers_ingridients)
print("Tools_Armor:", tools_armors)
print("Deliveries_Tools:", deliviers_tools)
