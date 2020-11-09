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
       ('chicken & rice', CURDATE(), 1, 3),
       ('Lemon Chicken with Orzo', CURDATE(), 2, 4),
       ('Baked Pot Stickers with Dipping Sauce', CURDATE(), 2, 5),
       ('Blushing Grapefruit Sorbet', CURDATE(), 2, 6);

INSERT INTO recipes (preparation, notes)
VALUES
('salt, pepper, heavy whipping cream, chicken, pasta, cumin', 'some notes on chicken alfredo'),
('How to: beef, onion, garlic, butter, pasta, carrots, celery', 'some salt, pepper, beef bouillon'),
('salt, pepper, rice, butter, chicken bouillon', 'some more notes on chicken and rice'),
('1/3 cup all-purpose flour
1 teaspoon garlic powder
1 pound boneless skinless chicken breasts
3/4 teaspoon salt, divided
1/2 teaspoon pepper
2 tablespoons olive oil
1 can (14-1/2 ounces)
reduced-sodium chicken broth
1-1/4 cups uncooked whole wheat orzo pasta
2 cups chopped fresh spinach
1 cup grape tomatoes
halved
3 tablespoons lemon juice
2 tablespoons minced fresh basil
Lemon wedges, optional'),
('2 cups finely chopped cooked chicken breast
1 can (8 ounces) water chestnuts, drained and chopped
4 green onions, thinly sliced
1/4 cup shredded carrots
1/4 cup reduced-fat mayonnaise
1 large egg white
1 tablespoon reduced-sodium soy sauce
1 garlic clove, minced
1 teaspoon grated fresh gingerroot
48 wonton wrappers
Cooking spray
SAUCE:
1/2 cup jalapeno pepper jelly
1/4 cup rice vinegar
2 tablespoons reduced-sodium soy sauce'),
('3 cups water
1 cup sugar
1/2 cup honey
1 tablespoon grated grapefruit zest
1 tablespoon minced fresh gingerroot
2 whole star anise
2 whole cloves
1 bay leaf
2 cups ruby red grapefruit juice, chilled
3 tablespoons lemon juice');

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
       (3, 16),
       (4, 23),
       (5, 8),
       (6, 15);

INSERT INTO recipes_diets(recipe_id, diet_id)
    VALUES (1, 2),
    (2, 2),
    (2, 3),
    (3, 1);

# INSERT INTO ingredients (name)
# VALUES
# ('erythritol'),
# ('xylitol'),
# ('splenda'),
# ('honey'),
# ('molasses'),
# ('white sugar'),
# ('brown sugar'),
# ('white vinegar'),
# ('balsamic vinegar'),
# ('apple cider vinegar'),
# ('baking soda'),
# ('baking powder'),
# ('cocoa powder'),
# ('olive oil'),
# ('peanut oil'),
# ('coconut oil'),
# ('spray oil'),
# ('sea salt'),
# ('milled pepper'),
# ('basil'),
# ('oregano'),
# ('parsley'),
# ('garlic powder'),
# ('onion powder'),
# ('cinnamon'),
# ('nutmeg'),
# ('chili'),
# ('cumin'),
# ('thyme'),
# ('fennel'),
# ('vanilla extract'),
# ('almond extract'),
# ('maple extract'),
# ('mint extract'),
# ('orange extract'),
# ('rum extract'),
# ('almond flour'),
# ('coconut flour'),
# ('flour'),
# ('almond meal'),
# ('coconut'),
# ('protein powder'),
# ('flax seed meal'),
# ('thickener'),
# ('chia seeds'),
# ('soy sauce'),
# ('worcestershire sauce'),
# ('kitchen bouquet'),
# ('onion'),
# ('green onions'),
# ('garlic'),
# ('jalapeno slices'),
# ('black olives'),
# ('ro-tel'),
# ('iced tea bags'),
# ('mint tea bags'),
# ('pizza sauce'),
# ('organic beef broth'),
# ('organic chicken broth'),
# ('pumpkin seeds'),
# ('sunflower seeds'),
# ('macadamia nuts'),
# ('almonds'),
# ('walnuts'),
# ('pine nuts'),
# ('cashews'),
# ('pecans'),
# ('sun-dried tomatoes'),
# ('roasted red peppers'),
# ('artichoke hearts'),
# ('pesto'),
# ('alfredo sauce'),
# ('pumpkin'),
# ('feta'),
# ('medium cheddar'),
# ('sharp cheddar'),
# ('parmesan'),
# ('cream cheese'),
# ('shredded mozzarella'),
# ('heavy whipping cream'),
# ('butter'),
# ('sour cream'),
# ('beef'),
# ('eggs'),
# ('pasta'),
# ('chicken'),
# ('bacon'),
# ('salami'),
# ('pepperoni'),
# ('green beans'),
# ('grape tomatoes'),
# ('pickles'),
# ('cucumber'),
# ('zucchini'),
# ('cauliflower'),
# ('spinach'),
# ('mushrooms'),
# ('lettuce'),
# ('lemon juice'),
# ('green olives'),
# ('mayonnaise'),
# ('ketchup'),
# ('yellow mustard'),
# ('relish'),
# ('brussels sprouts'),
# ('carrots'),
# ('ginger root'),
# ('green'),
# ('red bell peppers'),
# ('lime juice'),
# ('blueberries'),
# ('strawberries'),
# ('barbecue sauce'),
# ('mayonnaise'),
# ('snap peas');