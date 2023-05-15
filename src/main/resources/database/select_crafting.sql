USE dog_lister_db;
SELECT ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, dogs.id, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id,
       GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds,
       GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits
FROM ads
         JOIN dogs ON ads.dog_id = dogs.id
         JOIN dog_breeds ON dogs.id = dog_breeds.dog_id
         JOIN breeds ON dog_breeds.breed_id = breeds.id
         JOIN dog_traits ON dogs.id = dog_traits.dog_id
         JOIN traits ON dog_traits.trait_id = traits.id
         JOIN user_ads ON ads.id = user_ads.ad_id
WHERE user_id = 6
GROUP BY ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, dogs.id, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id;

SELECT ads.id AS ads_id, ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, dogs.id AS dogs_id, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id AS breed_id,
       GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds,
       GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits
FROM ads
         JOIN dogs ON ads.dog_id = dogs.id
         JOIN dog_breeds ON dogs.id = dog_breeds.dog_id
         JOIN breeds ON dog_breeds.breed_id = breeds.id
         JOIN dog_traits ON dogs.id = dog_traits.dog_id
         JOIN traits ON dog_traits.trait_id = traits.id
         JOIN user_ads ON ads.id = user_ads.ad_id
WHERE ads.id = 21
GROUP BY ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, dogs.id, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id;

USE dog_lister_db;
SELECT ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id,
       GROUP_CONCAT(DISTINCT breeds.name SEPARATOR ', ') AS breeds,
       GROUP_CONCAT(DISTINCT traits.name SEPARATOR ', ') AS traits
FROM ads
         JOIN dogs ON ads.dog_id = dogs.id
         JOIN dog_breeds ON dogs.id = dog_breeds.dog_id
         JOIN breeds ON dog_breeds.breed_id = breeds.id
         JOIN dog_traits ON dogs.id = dog_traits.dog_id
         JOIN traits ON dog_traits.trait_id = traits.id
         JOIN user_ads ON ads.id = user_ads.ad_id
WHERE traits.name IN ('Independent','Sociable')
GROUP BY ads.id, ads.title, ads.description, ads.short_description, ads.price, ads.image, dogs.name, dogs.age, dogs.playfulness, dogs.socialization, dogs.affection, dogs.training, breeds.id
HAVING COUNT(DISTINCT traits.name) = 2;

# USE dog_lister_db;
# UPDATE ads
# SET title = '?', short_description = '?',
#     description = '?', price = '?'
# WHERE id = 4;
#
# UPDATE dogs
# SET name = '?', age = '?',
#     playfulness = '?', socialization = '?',
#     affection = '?', training = '?'
# WHERE id = 4;
# insert into dog_traits(dog_id, trait_id) VALUES (21,1);

UPDATE dog_breeds
SET breed_id = 2
WHERE dog_id = 1;

SELECT * FROM ads JOIN dogs d on d.id = ads.dog_id JOIN dog_traits dt on d.id = dt.dog_id JOIN traits t on t.id = dt.trait_id
         WHERE t.name IN ('Sociable')  GROUP BY t.name HAVING COUNT(DISTINCT t.name) = 1;

SELECT ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.id, t.name
FROM ads
         JOIN dogs d ON d.id = ads.dog_id
         JOIN dog_traits dt ON d.id = dt.dog_id
         JOIN traits t ON t.id = dt.trait_id
WHERE t.name IN ('Sociable')
GROUP BY ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, t.id, t.name
HAVING COUNT(DISTINCT t.name) = 1;

SELECT ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, b.id, b.name
FROM ads
         JOIN dogs d ON d.id = ads.dog_id
         JOIN dog_breeds db on d.id = db.dog_id
         JOIN breeds b on b.id = db.breed_id
WHERE b.name = 'Akita'
GROUP BY ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training, b.id, b.name
HAVING COUNT(DISTINCT b.name) = 1;

SELECT ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training,
        GROUP_CONCAT(DISTINCT b.name SEPARATOR ', ') AS breeds,
        GROUP_CONCAT(DISTINCT t.name SEPARATOR ', ') AS traits
FROM ads
         JOIN dogs d ON d.id = ads.dog_id
         JOIN dog_breeds db on d.id = db.dog_id
         JOIN breeds b on b.id = db.breed_id
         JOIN dog_traits dt ON d.id = dt.dog_id
         JOIN traits t ON t.id = dt.trait_id
GROUP BY ads.title, ads.description, ads.short_description, ads.price, ads.image, ads.dog_id, d.name, d.age, d.playfulness, d.socialization, d.affection, d.training
ORDER BY dog_id ASC;

SELECT * FROM ads JOIN user_ads ua ON ads.id = ua.ad_id JOIN dogs d ON d.id = ads.dog_id WHERE user_id = 2;