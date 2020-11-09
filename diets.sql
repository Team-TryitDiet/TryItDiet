USE tryit_diet_db;
TRUNCATE TABLE ingredients;
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