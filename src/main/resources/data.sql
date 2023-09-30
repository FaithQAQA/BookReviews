-- Insert manga titles and authors into the 'books' table
INSERT INTO books (title, author)
VALUES 
    ('Naruto', 'Masashi Kishimoto'),
    ('One Piece', 'Eiichiro Oda'),
    ('Attack on Titan', 'Hajime Isayama'),
    ('Death Note', 'Tsugumi Ohba'),
    ('My Hero Academia', 'Kohei Horikoshi'),
    ('Demon Slayer', 'Koyoharu Gotouge'),
    ('Dragon Ball', 'Akira Toriyama');

-- Insert reviews for the manga into the 'reviews' table
INSERT INTO reviews (bookId, text)
VALUES 
    (1, 'Naruto is a classic in the world of manga. The story of Naruto Uzumaki is filled with action, friendship, and growth.'),
    (2, 'One Piece is an epic adventure that never ceases to amaze. The world-building and character development are outstanding.'),
    (3, 'Attack on Titan is a dark and gripping series with intense battles and a thought-provoking storyline.'),
    (4, 'Death Note is a psychological thriller that keeps you on the edge of your seat. The battle of wits between Light and L is brilliant.'),
    (5, 'My Hero Academia offers a fresh take on the superhero genre with its diverse cast of characters and unique Quirks.'),
    (6, 'Demon Slayer has stunning artwork and a heartwarming story of a brother-sister duo fighting demons.'),
    (7, 'Dragon Ball is a classic that introduced many to the world of manga and anime. Gokus adventures are timeless.');


       INSERT INTO users (email, encryptedpassword, enabled)
VALUES ('john@abc.com', '$2a$10$vzpUjabrpKi6A6ehsqj6EO7GUlXfasns4T2eQCtiyQZDV15Y5ug7S', 1);
INSERT INTO users (email, encryptedpassword, enabled)
VALUES ('hello@aaa.com', '$2a$10$ldiIEaS37V3C8YLc3lHi2O6L0pvzwciQNw6txELmo0KL4M19Pcs8O', 1);

INSERT INTO roles (rolename)
VALUES ('ROLE_USER');
INSERT INTO roles (rolename)
VALUES ('ROLE_GUEST');

INSERT INTO user_role (userid, roleid)
VALUES (1, 1); 
INSERT INTO user_role (userid, roleid)
VALUES (1, 2);
INSERT INTO user_role (userid, roleid)
VALUES (2, 2);
