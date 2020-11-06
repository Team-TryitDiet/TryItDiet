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
('salt, pepper, heavy whipping cream, chicken, pasta, cumin', 'some notes on chicken alfredo'),
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

# USE tryit_diet_db;
# TRUNCATE TABLE ingredients;
INSERT INTO ingredients (name)
VALUES
    ('erythritol'),
    ('xylitol'),
    ('splenda'),
    ('honey'),
    ('molasses'),
    ('white sugar'),
    ('brown sugar'),
    ('white vinegar'),
    ('balsamic vinegar'),
    ('apple cider vinegar'),
    ('baking soda'),
    ('baking powder'),
    ('cocoa powder'),
    ('olive oil'),
    ('peanut oil'),
    ('coconut oil'),
    ('spray oil'),
    ('sea salt'),
    ('milled pepper'),
    ('basil'),
    ('oregano'),
    ('parsley'),
    ('garlic powder'),
    ('onion powder'),
    ('cinnamon'),
    ('nutmeg'),
    ('chili'),
    ('cumin'),
    ('thyme'),
    ('fennel'),
    ('vanilla extract'),
    ('almond extract'),
    ('maple extract'),
    ('mint extract'),
    ('orange extract'),
    ('rum extract'),
    ('almond flour'),
    ('coconut flour'),
    ('almond meal'),
    ('coconut'),
    ('protein powder'),
    ('flax seed meal'),
    ('thickener'),
    ('chia seeds'),
    ('soy sauce'),
    ('worcestershire sauce'),
    ('kitchen bouquet'),
    ('onion'),
    ('garlic'),
    ('jalapeno slices'),
    ('black olives'),
    ('ro-tel'),
    ('iced tea bags'),
    ('mint tea bags'),
    ('pizza sauce'),
    ('organic beef broth'),
    ('organic chicken broth'),
    ('pumpkin seeds'),
    ('sunflower seeds'),
    ('macadamia nuts'),
    ('almonds'),
    ('walnuts'),
    ('pine nuts'),
    ('cashews'),
    ('pecans'),
    ('sun-dried tomatoes'),
    ('roasted red peppers'),
    ('artichoke hearts'),
    ('pesto'),
    ('alfredo sauce'),
    ('pumpkin'),
    ('feta'),
    ('medium cheddar'),
    ('sharp cheddar'),
    ('parmesan'),
    ('cream cheese'),
    ('shredded mozzarella'),
    ('heavy whipping cream'),
    ('butter'),
    ('sour cream'),
    ('beef'),
    ('eggs'),
    ('chicken'),
    ('bacon'),
    ('salami'),
    ('pepperoni'),
    ('green beans'),
    ('grape tomatoes'),
    ('pickles'),
    ('cucumber'),
    ('zucchini'),
    ('cauliflower'),
    ('spinach'),
    ('mushrooms'),
    ('lettuce'),
    ('lemon juice'),
    ('green olives'),
    ('mayonnaise'),
    ('ketchup'),
    ('yellow mustard'),
    ('relish'),
    ('brussels sprouts'),
    ('carrots'),
    ('ginger root'),
    ('green'),
    ('red bell peppers'),
    ('lime juice'),
    ('blueberries'),
    ('strawberries'),
    ('barbecue sauce'),
    ('snap peas');




# PANTRY RAID
# Sweeteners (6):

#
#

# Vinegar (3):
#


# Baking Needs (4):


# Oils (4):


# Spices (3):


#
# Mrs. Dash’s Garlic Italian Blend,

#
# Flours (7):

#
# Thickeners (2):


# Liquid flavor (3):


# Other (10):

#
# Nuts and seeds (9):

#
# Occasionally:

# *instant coffee
#
#
#
#
#
# 9:09
# REFRIGERATOR RIGHTEOUSNESS
# Cheeses (7):

# Other dairy(6): Greek yogurt, cottage cheese, low-carb yogurt,


# Meat (8):


# Vegetables (10):

# Fruit (1):

# Fats(2):

# Non-dairy(2): Unsweetened almond milk, unsweetened coconut milk
# Other (3):

# Occasionally:


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