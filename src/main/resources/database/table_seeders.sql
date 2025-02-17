USE dog_lister_db;

INSERT INTO breeds (name)
VALUES  ('Airedale Terrier'),
        ('Akita'),
        ('Alaskan Malamute'),
        ('American Bulldog'),
        ('American Eskimo Dog'),
        ('American Pit Bull Terrier'),
        ('Australian Cattle Dog'),
        ('Australian Shepherd'),
        ('Basset Hound'),
        ('Beagle'),
        ('Belgian Malinois'),
        ('Bernese Mountain Dog'),
        ('Bichon Frise'),
        ('Border Collie'),
        ('Boston Terrier'),
        ('Boxer'),
        ('Brittany'),
        ('Bulldog'),
        ('Cane Corso'),
        ('Cavalier King Charles Spaniel'),
        ('Chihuahua'),
        ('Chinese Crested'),
        ('Chow Chow'),
        ('Cocker Spaniel'),
        ('Dachshund'),
        ('Dalmatian'),
        ('Doberman Pinscher'),
        ('English Cocker Spaniel'),
        ('French Bulldog'),
        ('German Shepherd Dog'),
        ('German Shorthaired Pointer'),
        ('Golden Doodle'),
        ('Golden Retriever'),
        ('Great Dane'),
        ('Great Pyreness'),
        ('Greyhound'),
        ('Irish Setter'),
        ('Jack Russell Terrier'),
        ('Komondor'),
        ('Labrador Retriever'),
        ('Lhasa Apso'),
        ('Maltese'),
        ('Miniature Pinscher'),
        ('Miniature Schnauzer'),
        ('Newfoundland'),
        ('Pekingese'),
        ('Plott Hound'),
        ('Pomeranian'),
        ('Poodle'),
        ('Pug'),
        ('Rottweiler'),
        ('Saint Bernard'),
        ('Shar Pei'),
        ('Shetland Sheepdog'),
        ('Shih Tzu'),
        ('Siberian Husky'),
        ('Weimaraner'),
        ('Whippet'),
        ('Yorkshire Terrier');

INSERT INTO traits (name)
VALUES ('Affectionate'),
       ('Calm'),
       ('Curious'),
       ('Friendly'),
       ('Gentle'),
       ('Happy'),
       ('Housebroken'),
       ('Good with children'),
       ('Independent'),
       ('Lively'),
       ('Loyal'),
       ('Mischievous'),
       ('Obedient'),
       ('Protective'),
       ('Reserved'),
       ('Sensitive'),
       ('Smart'),
       ('Sociable'),
       ('Stubborn'),
       ('Submissive'),
       ('Vocal'),
       ('Well-behaved');

INSERT INTO dogs(name, age, playfulness, socialization, affection, training)
values ('Chupa', '6', '4', '1', '3', '2'),
       ('Snowflake', '2', '3', '3', '3', '4'),
       ('Speed', '1', '4', '4', '3', '4'),
       ('Frenchi', '3', '4', '5', '5', '2'),
       ('Sniffles', '2', '4', '4', '4', '4'),
       ('Bella', '9', '3', '3', '3', '5'),
       ('Hot Dog', '1', '4', '4', '4', '1'),
       ('Alpha', '4', '4', '3', '4', '5'),
       ('Remington', '2', '4', '4', '5', '4'),
       ('Sherlock', '5', '2', '4', '4', '2'),
       ('Banks', '2', '4', '4', '4', '4'),
       ('Bully', '1', '4', '3', '4', '4'),
       ('Shaggy', '4', '3', '4', '4', '5'),
       ('Naomi', '2', '4', '3', '5', '5'),
       ('Maverick', '2', '3', '2', '5', '5'),
       ('Princess', '1', '4', '3', '5', '4'),
       ('Sasha', '2', '5', '5', '5', '3'),
       ('Pouty', '3', '3', '3', '3', '2'),
       ('Jaguar', '6', '4', '4', '4', '4'),
       ('Sprinkle', '1', '4', '4', '5', '3');

INSERT INTO ads(title, short_description, description, price, image, dog_id)
values ('Not for sale, Just like posting his picture everywhere', 'Don''t let this adorable image fool you. Chupa is a ferocious dog, ready to nibble fingers at a moment''s notice', 'Chupa may be small in size, but he''s big on attitude! This feisty pup is always on high alert, keeping an eye out for any potential threats. But don''t let his tough exterior fool you - deep down, Chupa is a total softie. He loves nothing more than curling up in a warm lap and snuggling with his favorite human. And when it comes to playtime, he''s always up for a game of fetch or tug-of-war. Just be prepared for some serious cuteness overload when you see this little guy in action!', 0,'chupa.jpeg', 1),
       ('Meet Snowflake: Loyal and Intelligent Akita', 'Discover the unique traits of this regal breed - a loyal companion and excellent watchdog', 'Snowflake, the majestic Akita, is the epitome of loyalty and intelligence. This regal pup will stick by your side through thick and thin, always ready to defend and protect those she loves. Her keen senses and sharp mind make her an excellent watchdog, but she''s also a great companion for quiet nights in. Snowflake is a dog of many talents - she excels in obedience training, and can even learn complex tricks with ease. And while she may have a serious side, she''s also a playful pup at heart. Her fluffy white coat and happy grin are sure to put a smile on your face. If you''re looking for a dog that''s equal parts brains and beauty, Snowflake is the one for you.', 1500, 'Akita.jpeg', 2),
       ('Meet Speed: The Lightning-Fast Whippet', 'Discover why this family-friendly breed is often mistaken for the Italian Greyhound', 'If you''re looking for a furry friend who can keep up with your active lifestyle, Speed is the pup for you! This Whippet is built for the fast lane, with a sleek physique and lightning-fast reflexes that make him an expert at chasing down prey. But more than just a racing champion, Speed is also a lovable companion who thrives on human companionship. He loves nothing more than snuggling up in your lap and showering you with affection. And when it''s time to burn off some energy, he''s always up for a run around the park or a game of fetch. With his long legs and graceful gait, Speed is a true athlete who will impress everyone who sees him in action. Plus, his family-friendly nature makes him a great fit for any home. So don''t miss your chance to add this lightning-fast Whippet to your family!', 1100,'Whippet.jpeg', 3),
       ('Meet Frenchi: The Charming French Bulldog', 'Discover the lovable personality and unique wrinkles of this adorable breed', 'Frenchi is the charming French Bulldog with a personality as unique as his wrinkles! With his adorable wrinkled face, perky ears, and wiggly butt, Frenchi is sure to steal your heart. He loves nothing more than cuddling up next to his favorite humans and soaking up all the love and attention he can get. And with his natural charm, he''s sure to make everyone around him smile. But Frenchi also has a playful side - he loves to play fetch, tug of war, and run around the house like a little tornado. With his compact size and easygoing nature, Frenchi is the perfect apartment dog. He doesn''t need a lot of space to be happy, as long as he has his favorite humans by his side. So if you''re looking for a furry friend who will always brighten up your day, Frenchi is the French Bulldog for you!', 2500,'french bulldog.jpeg', 4),
       ('Meet Sniffles: The Adventure-Ready Plott Hound', 'North Carolina''s State Dog with an excellent sense of smell', 'If you''re looking for a furry companion who''s always ready for an adventure, Sniffles is the Plott Hound for you! This sweet and adventurous pup has an excellent sense of smell, which makes him a perfect companion for hikes, walks, and outdoor activities. He loves nothing more than exploring the great outdoors and taking in all the new sights and smells. But don''t let his adventurous side fool you - Sniffles is also a loyal and loving friend. He has a gentle disposition and a heart of gold, which makes him a great family dog. He''s always up for a cuddle session on the couch or a good belly rub. With his floppy ears and soulful eyes, Sniffles is sure to melt your heart. So if you''re looking for a dog that''s equal parts adventurous and loving, Sniffles is the perfect fit!', 900,'Plott Hound.jpeg', 5),
       ('Meet Bella: Your Loyal Partner-in-Crime', 'Discover the dynamic and intelligent German Shepherd - the perfect furry friend for adventure seekers', 'Are you an adventure seeker looking for a loyal companion? Look no further than Bella - the dynamic and intelligent German Shepherd who is ready to be your partner in crime! Known for her loyalty and bravery, Bella is a natural protector who will always have your back. But she''s also a lot of fun! With her love for play and exercise, she''s the perfect companion for anyone with an active lifestyle. Bella is highly intelligent and excels at obedience training and agility courses, making her a quick learner who is always eager to please. But more than that, she''s a loving friend who will stick by your side through thick and thin. Whether you''re looking for a jogging partner or a loyal companion, Bella is the German Shepherd for you!', 2750,'German Shepherd.jpeg', 6),
       ('Meet Hot Dog: The Curious and Lovable Dachshund', 'Discover why this breed is often called the hot dog of the dog world', 'If you''re looking for a pup with a big personality, then Hot Dog is the Dachshund for you! With his short legs, long body, and playful nature, he''s a real standout among the canine crowd. He loves to explore his surroundings and investigate every nook and cranny, and he''s always up for an adventure. But when it''s time to relax, Hot Dog is a total lap dog who loves nothing more than snuggling up next to his favorite humans and showering them with affection. And don''t let his small size fool you - he''s a brave and fearless pup who will defend his family against any threat. With his charming personality and lovable nature, Hot Dog is sure to steal your heart. So don''t miss your chance to add this curious and affectionate Dachshund to your family!', 1000,'Dachshund.jpeg', 7),
       ('Meet Alpha: The Versatile Belgian Malinois', 'Discover the exceptional loyalty and dynamic nature of this working breed', 'Looking for a loyal and versatile companion? Look no further than Alpha - the dynamic Belgian Malinois! Known for their keen intelligence, boundless energy, and unwavering loyalty, Malinois are exceptional working dogs who thrive on challenges. And Alpha is no exception. This pup is a natural athlete who excels at obedience training, agility courses, and more. But beyond their impressive skills, Alpha is a loving and loyal friend who will do whatever it takes to keep their family safe. With their striking appearance and impressive skill set, Alpha is sure to turn heads wherever they go. Whether you''re looking for a faithful companion or a working dog, Alpha is the Malinois for you!', 2000,'Belgian Malinois.jpeg', 8),
       ('Meet Remington: The Energetic Australian Shepherd', 'Discover why this charming breed is the perfect partner for herding cows', 'Looking for a furry partner to help you herd some cows? Look no further than Remington, the charming and energetic Australian Shepherd! This pup is full of energy and always ready to play, whether it''s a game of fetch or a romp around the yard. With their intelligence and agility, Australian Shepherds are natural-born athletes who excel at obedience training and agility courses. And Remington is no exception - they''re a skilled athlete and a natural herder who will make any cow-chasing task a breeze. But more than just a working dog, Remington is a loving and loyal friend who will always be by your side. They have a playful nature and a charming personality that makes them a hit with everyone they meet. With their striking appearance and outgoing nature, Remington is sure to turn heads wherever they go. So if you''re looking for a furry partner who can work hard and play hard, Remington is the Australian Shepherd for you!', 2250,'Australian Shepherd.jpeg', 9),
       ('Meet Sherlock: The Curious and Calm Basset Hound', 'Discover why this pup with ears so long, they double as a scarf, is the perfect story-book dog!', 'If you''re looking for a loyal companion who is also a natural-born detective, look no further than Sherlock the Bassett Hound! With their keen sense of smell and strong tracking instincts, Sherlock is always on the hunt for clues. But when they''re not sleuthing, they''re happy to relax on their own, lounging in the sun or snoozing in a cozy corner. Their independent nature makes them a great fit for busy families or people who work outside the home. Plus, their adorable droopy ears and soulful eyes make them the perfect story-book dog. So if you''re ready to add a loyal and loveable companion to your life, Sherlock is the Basset Hound for you!', 1000,'Basset Hound.jpeg', 10),
       ('Meet Banks: The Regal and Playful Poodle', 'Step up your dog game with the original fancy furball - the Poodle!', 'Looking for a pup with the perfect fluff of curls? Meet Banks - the fluffy, regal, and fashionable Poodle who is sure to turn heads wherever they go! With their elegant appearance and stylish haircut, Poodles like Banks are known for their regal bearing and refined demeanor. But don''t let their fancy exterior fool you - Poodles are also energetic and playful pups who love to have fun. Whether it''s a game of fetch or a stroll around the block, Banks is always up for some excitement. And with their fluffy coat and charming personality, Poodles like Banks are sure to steal your heart. They''re also known for being intelligent and easy to train, making them a popular choice for people who want a well-behaved companion. So whether you''re looking for a fashionable pup to show off at the dog park or a loyal companion to curl up with on the couch, Banks is the Poodle for you!', 3000,'Poodle.jpeg', 11),
       ('Meet Bully: The Loyal American Bulldog', 'Discover why this breed is known for their gentle disposition and protective nature', 'Get ready to unleash the ultimate bulldozer - the American Bulldog! With a jaw as strong as a vice and a heart as big as Texas, Bully is the perfect example of this lovable breed. American Bulldogs like Bully are muscular and affectionate, making them loyal companions who will always have your back. They love spending time with their humans, whether it''s cuddling up on the couch or going for a walk in the park. And with their charming personality and big heart, American Bulldogs like Bully are sure to win over everyone they meet. They''re also known for being protective of their family, making them great watchdogs who will alert you to any potential danger. But despite their imposing appearance, American Bulldogs like Bully are also incredibly gentle with their family members, especially children. They''re a great choice for families who want a loyal and caring companion to share their lives with. So whether you''re looking for a loyal friend to take on adventures or a cuddly companion to share your home with, Bully is the American Bulldog for you!', 1500,'American Bulldog.jpeg', 12),
       ('Meet Shaggy: The Quirky and Friendly Komondor', 'Discover why this four-legged ball of fluff is sure to turn heads wherever you go', 'Shaggy the Komondor is not your average dog - with a coat that rivals a floor duster and a personality as quirky as its appearance, this lovable pooch is sure to make a lasting impression. But don''t be fooled by their shaggy exterior - Komondors like Shaggy are also known for their friendly, loyal, and protective nature. Despite their large size and imposing appearance, they make great family pets and are particularly gentle with children and other pets. Their quirky personality and love for belly rubs make them great companions for people of all ages. And when it comes to guarding their families, Komondors like Shaggy take their job seriously - they will go to great lengths to protect their loved ones from harm. So if you''re looking for a dog that''s both unique and lovable, look no further than Shaggy the Komondor!', 3000, 'Komondor.jpeg', 13),
       ('Meet Naomi: The Loyal Teddy Bear Rottweiler', 'Discover why this powerful breed is actually a big softie at heart', 'Unleash the beast! Meet Naomi - the loyal teddy bear of a dog, otherwise known as the Rottweiler. With their powerful build and imposing presence, Rottweilers like Naomi are often misunderstood as being aggressive or dangerous. But in reality, they''re big softies at heart who are incredibly loyal and protective of their families. They make excellent guard dogs and are fiercely devoted to their owners. Rottweilers like Naomi are also great with children and other pets, making them the perfect family dog. Plus, with their teddy bear-like appearance and affectionate nature, they''re sure to win over even the most skeptical of dog lovers. So if you''re looking for a loyal and devoted companion who will always have your back, look no further than Naomi the Rottweiler!', 2050,'Rottweiler.jpeg', 14),
       ('Meet Maverick: The Gentle Giant Cane Corso', 'Discover why this Italian masterpiece is known for their affectionate and loyal personality', 'Don''t be intimidated by Maverick''s massive build and deep bark - this Cane Corso is a gentle giant at heart. Known for their patient and loving demeanor, Cane Corsos like Maverick make excellent family dogs. They''re great with children and other pets, and are always eager to be close to their humans. But don''t let their gentle nature fool you - Cane Corsos are also known for being excellent guard dogs who will protect their families at all costs. With his imposing appearance and loving personality, Maverick is sure to steal your heart and become a loyal companion for life. So if you''re looking for a devoted furry friend who will always be by your side, consider adding Maverick the Cane Corso to your family!', 4000,'Cane Corso.jpeg', 15),
       ('Meet Princess: The Spunky Chihuahua', 'Discover why this pocket-sized pup has a big personality', 'Say hello to Princess - the #pocket-sized pup with a big personality! This spunky Chihuahua may be small in size, but she makes up for it with her feisty attitude and lively energy. She''s always on the go, ready to explore the world around her and make new friends. Princess loves nothing more than a good game of fetch or tug-of-war, and she''s always up for a challenge. But despite her active nature, she''s also a cuddly companion who loves nothing more than snuggling up in a warm lap. With her bright eyes and wagging tail, Princess is sure to steal your heart and keep you on your toes. So why not add a little bit of royalty to your life with this lovable little princess?', 1600, 'chihuahua.jpeg', 16),
       ('Meet Sasha: The Energetic Golden Doodle', 'Experience the love and affection of this bubbly pup who loves cuddles and outdoor activities', 'Looking for a furry friend who will always be up for an adventure? Look no further than Sasha - the Golden Doodle who will steal your heart! This energetic pup is a true bundle of joy, with a wagging tail and an infectious smile that will brighten up your day. She loves exploring the world around her, whether it''s going for a run in the park or splashing around in a nearby river. And when it''s time to wind down, Sasha is the ultimate cuddle buddy. She''ll snuggle up in your lap and shower you with affection, making you feel loved and cherished. With her warm heart and outgoing personality, Sasha is the perfect addition to any family. Whether you''re looking for a playmate, a companion, or just a friend to share your adventures with, Sasha is the pup for you.', 3000,'golden doodle.jpeg', 17),
       ('Meet Pouty: The Lovable Basset Hound', 'Discover why this cute and goofy pup is the perfect companion', 'Looking for a furry friend who''s equal parts cute and goofy? Meet Pouty, the lovable Basset Hound with a face that will melt your heart. With her droopy ears and soulful eyes, Pouty is a master of the sad puppy face. But don''t let her pouty expression fool you - this pup is full of life and energy. She loves nothing more than bounding around the yard and exploring her surroundings. And when it''s time to relax, she''s always up for a good belly rub or a snuggle on the couch. Pouty is also known for her vocal talents - her deep, baying howls are sure to make you smile. But more than that, she''s a loyal companion who will always keep you entertained with her playful antics. So if you''re looking for a dog that''s equal parts cute and fun, Pouty is the one for you!', 500,'Basset Hound.jpeg', 18),
       ('Meet Jaguar: The Fun-Loving Labrador', 'Discover why this friendly pup is always up for an adventure!', 'Fast like a jaguar and fun-loving like a Labrador, Jaguar is the perfect companion for anyone looking for a furry friend with a wild streak. This lovable pup is always ready for action, whether it''s running around and exploring the great outdoors or splashing in a nearby stream. And when it''s time to take a break, Jaguar is always up for cuddles and kisses. With his friendly and social personality, he''s a true people pleaser who loves making new friends. But don''t let his goofy demeanor fool you - Jaguar is also a smart and trainable pup who excels in obedience training. If you''re looking for a furry friend who''s equal parts fun and friendly, Jaguar is the perfect fit for you and your family!', 950, 'labrador.jpeg', 19),
       ('Meet Sprinkle: The Pocket-Sized Pomeranian', 'Discover the tiny dog with a big personality and fierce loyalty', 'If you''re looking for a furry friend that you can carry around with you everywhere, then Sprinkle is the pup for you! This pint-sized Pomeranian is a bundle of energy and fun, always ready to play and explore. With her fluffy coat and adorable face, she''s sure to steal your heart from the moment you meet her. But don''t be fooled by her small size - Sprinkle is a mighty dog with a big personality. She''s fiercely loyal and protective of her loved ones, making her an excellent watchdog. And when it''s time to relax, she''s a big fan of snuggles and naps in your lap. With her bright eyes and perky ears, Sprinkle is sure to turn heads wherever she goes. If you''re ready for a big adventure with a tiny dog, Sprinkle is the one for you!', 2500,'Pomeranian.jpeg', 20);

INSERT INTO dog_breeds(dog_id, breed_id)
VALUES (1,29),
       (2,2),
       (3,58),
       (4,29),
       (5, 47),
       (6, 30),
       (7, 25),
       (8, 11),
       (9, 8),
       (10, 9),
       (11, 49),
       (12, 4),
       (13, 39),
       (14, 51),
       (15, 19),
       (16, 21),
       (17, 32),
       (18, 50),
       (19, 40),
       (20, 48);

INSERT INTO dog_traits (dog_id, trait_id)
VALUES
    (1, 1), (1, 3), (1, 9), (1, 12), (1, 18),
    (2, 2), (2, 5), (2, 8), (2, 14), (2, 18), (2, 20),
    (3, 1), (3, 4), (3, 7), (3, 10), (3, 11), (3, 19),
    (4, 2), (4, 5), (4, 6), (4, 8), (4, 16), (4, 21),
    (5, 4), (5, 10), (5, 11), (5, 14), (5, 15), (5, 18),
    (6, 1), (6, 3), (6, 5), (6, 9), (6, 10), (6, 12),
    (7, 2), (7, 7), (7, 8), (7, 13), (7, 16), (7, 22),
    (8, 1), (8, 6), (8, 11), (8, 14), (8, 17), (8, 19),
    (9, 2), (9, 3), (9, 4), (9, 8), (9, 12), (9, 21),
    (10, 4), (10, 7), (10, 9), (10, 10), (10, 13), (10, 18),
    (11, 1), (11, 5), (11, 11), (11, 14), (11, 16), (11, 20),
    (12, 2), (12, 3), (12, 6), (12, 12), (12, 15), (12, 22),
    (13, 1), (13, 4), (13, 7), (13, 9), (13, 11), (13, 17),
    (14, 2), (14, 5), (14, 8), (14, 13), (14, 14), (14, 21),
    (15, 3), (15, 6), (15, 10), (15, 15), (15, 16), (15, 19),
    (16, 1), (16, 2), (16, 4), (16, 7), (16, 12), (16, 18),
    (17, 3), (17, 5), (17, 8), (17, 9), (17, 13), (17, 20),
    (18, 2), (18, 6), (18, 11), (18, 14), (18, 15), (18, 22),
    (19, 1), (19, 4), (19, 10), (19, 16), (19,21),
    (20, 5), (20, 18), (20, 19), (20, 21);

INSERT INTO users (name, email, username, password)
VALUES ('Gage','gage@gmail.com','Gage','$2a$12$uebzoghT/K2LsilFE1Mc9OEfFCOZDbp.7FFVGYDjQhuErBMKMA0JO'),
       ('Guy', 'Funguy@gmail.com', 'Guy', '$2a$12$6cfMjKZF7LJ53RsveBme/evE2v.5ZjN/zGSCRuRsK7JdOoXbKxNLy'),
       ('Sam', 'Samking@gmail.com', 'Sam', '$2a$12$trqJ6nvZ31hPnkCivGk6gu9ExxLDvxP8FvaYNAUa08MVNPP.pPFAO'),
       ('Tim', 'Timothy2@yahoo.com', 'Tim', '$2a$12$myJ8ts1W.0bpSMgnI89u.uKCho4uZlmeO4mi04Ozq3ucLimg2c49q'),
       ('Zy', 'Zyzy@gmail.com', 'Zy', '$2a$12$JdOkO1pT.zvWd8/ynY.f5eVTkbyvc0sH2g2b5SqkJ3ceb8U9NBagq'),
       ('Marc', 'mark10@hotmail.com', 'Marc', '$2a$12$CXPzdzLtDLX/aMelEQ1GqudFWplyR3B1cNvHuY.5jfcAjmS9nGE32'),
       ('Anthony' ,'Anthony7@gmail.com', 'Anthony', '$2a$12$Wm4Xl2SYbfFHlPrtLWObwuwOyjRJBbVSoSA2nkv8kv.jzVGfiKARi');

INSERT INTO user_ads (user_id, ad_id)
VALUES  (1,1),
        (2,2),(2,3),(2,4),
        (3,5),(3,6),(3,7),
        (4,8),(4,9),
        (5,10),(5,11),(5,12),(5,13),
        (6,14),(6,15),(6,16),
        (7,17),(7,18),(7,19),(7,20)
;

