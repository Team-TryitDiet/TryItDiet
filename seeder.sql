DROP DATABASE tryit_diet_db;
CREATE DATABASE tryit_diet_db;

USE tryit_diet_db;
INSERT INTO diets (title)
VALUES ('dairy-free'),
       ('gluten-free'),
       ('high-protein'),
       ('keto'),
       ('paleo'),
       ('pescatarian'),
       ('mediterranean'),
       ('vegan'),
       ('vegetarian'),
       ('other');

INSERT INTO posts (title, date, user_id, recipe_id)
VALUES ('chicken alfredo', CURDATE(), 1, 1),
       ('beef stroganoff', CURDATE(), 1, 2),
       ('chicken & rice', CURDATE(), 1, 3);

INSERT INTO recipes (preparation, notes)
VALUES
('salt, pepper, heavy whipping cream, chicken, pasta, cumin', 'some more notes on chicken alfredo'),
('How to: beef, onion, garlic, butter, pasta, carrots, celery', 'some salt, pepper, beef bouillon'),
('salt, pepper, rice, butter, chicken bouillon', 'some more notes on chicken and rice');

INSERT INTO recipes_ingredients (recipe_id, ingredient_id)
VALUES (1, 3),
       (1, 5),
       (1, 6),
       (1, 13),
       (2, 3),
       (2, 5),
       (2, 2),
       (2, 13),
       (2, 1),
       (2, 14),
       (2, 15),
       (3, 3),
       (3, 5),
       (3, 8),
       (3, 14),
       (3, 15),
       (3, 16);
INSERT INTO recipes_diets(recipe_id, diet_id)
    VALUES (1, 2),
    (2, 2),
    (2, 3),
    (3, 1);


INSERT INTO ingredients (name)
VALUES
       ('celery'),
       ('beef'),
       ('chicken'),
       ('beans'),
       ('butter'),
       ('milk'),
       ('cheese'),
       ('broccoli'),
       ('carrots'),
       ('salmon'),
       ('lamb'),
       ('pork'),
       ('pasta'),
       ('garlic'),
       ('onion'),
       ('rice');

# Build the relationship between ingredients and foodgroups manually
# INSERT INTO ingredients_foodgroups (ingredient_id, foodgroup_id)
# VALUES (1, 1),
#        (2, 5),
#        (3, 5),
#        (4, 1),
#        (4, 5),
#        (5, ),
#        (6, ),
#        (7, ),
#        (8, ),
#        (9, ),
#        (10, ),
#        (11, ),
#        (12, ),
#        (13, ),
#        (14, ),
#        (15, ),
#        (16, 1);
#
# INSERT INTO foodgroups (name)
# VALUES
# ('vegetable'),
# ('grain'),
# ('diary'),
# ('fruit'),
# ('protein'),
# ('oils');