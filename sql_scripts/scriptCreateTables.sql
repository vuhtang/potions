CREATE TABLE People (
    Id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    date_of_birth DATE
);

CREATE TABLE Customers (
    Id INT PRIMARY KEY,
    Human_id INT UNIQUE REFERENCES People (id) ON DELETE CASCADE
);

CREATE TABLE Workers (
    Id INT PRIMARY KEY,
    Human_id INT UNIQUE REFERENCES People (id) ON DELETE CASCADE
);

CREATE TABLE Enterprise_Point_Type (
    Id SERIAL PRIMARY KEY,
    Type VARCHAR(60) NOT NULL
);

CREATE TABLE Enterprise_Point (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(40) NOT NULL,
    Location VARCHAR(60) NOT NULL,
    Type INT REFERENCES Enterprise_Point_Type (id) ON DELETE CASCADE
);

CREATE TABLE Posts (
    id SERIAL PRIMARY KEY,
    Name VARCHAR(60) NOT NULL,
    EPT_id INT REFERENCES Enterprise_Point_Type (id) ON DELETE CASCADE
);

CREATE TABLE Workers_Posts (
    Id SERIAL PRIMARY KEY,
    Worker_id INT REFERENCES Workers (id) ON DELETE CASCADE,
    Post_id INT REFERENCES Posts (id) ON DELETE SET NULL,
    EP_Id INT REFERENCES Enterprise_Point (id) ON DELETE CASCADE,
    Taking_date DATE,
    Dismiss_date DATE
);

CREATE TABLE Living_Things (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(40) NOT NULL,
);

CREATE TABLE Ingredients (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(40) NOT NULL,
    Living_Thing_id INT REFERENCES Living_Things (id) ON DELETE SET NULL
);

CREATE TABLE Enterprise_Point_Warehouse (
    Id SERIAL PRIMARY KEY,
    EP_id INT REFERENCES Enterprise_Point (id) ON DELETE CASCADE,
    Ingredient_id INT REFERENCES Ingredients (id) ON DELETE CASCADE,
    Amount_of_Ingredient INT
);


CREATE TABLE Enterprise_Point_Coldwarehouse (
    Id SERIAL PRIMARY KEY,
    EP_id INT REFERENCES Enterprise_Point (id) ON DELETE CASCADE,
    Potion_id INT REFERENCES Potions (id) ON DELETE CASCADE,
    Amount_of_Ingredient INT
);


CREATE TABLE Enterprise_Point_Ingredients (
    Id SERIAL PRIMARY KEY,
    EPT_id INT REFERENCES Enterprise_Point_Type (id) ON DELETE CASCADE,
    Ingredient_id INT REFERENCES Ingredients (id) ON DELETE CASCADE
);

CREATE TABLE Order_Status (
    Id SERIAL PRIMARY KEY,
    Status VARCHAR(255) NOT NULL
);

CREATE TABLE Orders (
    Id SERIAL PRIMARY KEY,
    Customer_id INT REFERENCES Customers (id) ON DELETE CASCADE,
    Order_status INT REFERENCES Order_Status (id) ON DELETE CASCADE,
    Creation_time TIMESTAMP WITHOUT TIME ZONE,
    Completion_time TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE Enterprise_Deliveries (
    Id SERIAL PRIMARY KEY,
    EP_from_id INT REFERENCES Enterprise_Point (id) ON DELETE CASCADE,
    EP_to_id INT REFERENCES Enterprise_Point (id) ON DELETE CASCADE,
    Courier_id INT REFERENCES Workers (id) ON DELETE SET NULL,
    Creation_time TIMESTAMP WITHOUT TIME ZONE,
    Completion_time TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE Effects (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(60) NOT NULL,
    Power INT,
    Duration INT
);

CREATE TABLE Potions (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(60) NOT NULL,
    Effect_id INT REFERENCES Effects (id) ON DELETE SET NULL
);

CREATE TABLE Orders_Potions (
    Id SERIAL PRIMARY KEY,
    Order_id INT REFERENCES Orders (id) ON DELETE CASCADE,
    Potion_id INT REFERENCES Potions (id) ON DELETE CASCADE,
    Amount_of_potions INT NOT NULL
);

CREATE TABLE Ingredients_Potions (
    Id SERIAL PRIMARY KEY,
    Potion_id INT REFERENCES Potions (id) ON DELETE CASCADE,
    Ingredient_id INT REFERENCES Ingredients (id) ON DELETE CASCADE,
    Amount_of_Ingredient INT NOT NULL
);

CREATE TABLE Deliveries_Ingredients (
    Id SERIAL PRIMARY KEY,
    Delivery_id INT REFERENCES Enterprise_Deliveries (id) ON DELETE CASCADE,
    Ingredient_id INT REFERENCES Ingredients (id) ON DELETE CASCADE,
    Amount_of_Ingredient INT NOT NULL
);

CREATE TABLE Tools_Armor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE Deliveries_Tools (
    Id SERIAL PRIMARY KEY,
    Delivery_id INT REFERENCES Enterprise_Deliveries (id) ON DELETE CASCADE,
    Tool_id INT REFERENCES Tools_Armor (id) ON DELETE CASCADE,
    Amount_of_tools INT NOT NULL
);

CREATE TABLE Test_Status (
    Id SERIAL PRIMARY KEY,
    Status VARCHAR(40) NOT NULL
);

CREATE TABLE Tests (
    Id SERIAL PRIMARY KEY,
    Potion_id INT REFERENCES Potions (id) ON DELETE CASCADE,
    Test_date DATE,
    Test_status INT REFERENCES Test_Status (id) ON DELETE SET NULL,
    Worker_id INT REFERENCES Workers (id) ON DELETE SET NULL,
    Living_Thing_id INT REFERENCES Living_Things (id) ON DELETE SET NULL,
    Duration INT NOT NULL
);