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
