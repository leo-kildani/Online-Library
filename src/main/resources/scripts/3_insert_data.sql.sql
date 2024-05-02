USE cs157a_final;

INSERT INTO users (username, password, email, first_name, last_name, active, dob)
VALUES
('alice123', 'pass1234', 'alice123@example.com', 'Alice', 'Johnson', 1, '1995-03-12'),
('bobmarley', 'bobsecure', 'bob.marley@example.com', 'Bob', 'Marley', 0, '1988-08-15'),
('charlie_m', 'deltaecho', 'charlie.m@example.net', 'Charlie', 'Morris', 1, '1983-11-22'),
('david4mvp', 'davidrocks', 'david4mvp@example.org', 'David', 'Smith', 0, '1975-06-04'),
('eve_bravo', 'eve2023', 'eve.bravo@example.com', 'Eve', 'Bravo', 1, '1999-12-31'),
('frankie8', 'goodluck', 'frankie8@example.net', 'Frankie', 'Lee', 0, '1993-05-08'),
('grace_h', 'grace12345', 'grace.h@example.org', 'Grace', 'Hall', 1, '1981-04-14'),
('hannah_sun', 'sunshine', 'hannah.sun@example.com', 'Hannah', 'Sun', 1, '1986-07-23'),
('ian_neo', 'neomatrix', 'ian.neo@example.net', 'Ian', 'Neo', 0, '1991-09-17'),
('julia_blue', 'juliablue', 'julia.blue@example.org', 'Julia', 'Blue', 1, '1990-10-12');

INSERT INTO user_roles (username, role)
VALUES
('alice123', 'Member'),
('bobmarley', 'Member'),
('charlie_m', 'Member'),
('david4mvp', 'Member'),
('eve_bravo', 'Member'),
('frankie8', 'Member'),
('grace_h', 'Member'),
('hannah_sun', 'Member'),
('ian_neo', 'Admin'),
('julia_blue', 'Admin');


INSERT INTO authors (first_name, last_name, biography)
VALUES
('John', 'Smith', 'John Smith is an American novelist and essayist known for his explorations of American culture and personal identity.'),
('Emily', 'Johnson', 'Emily Johnson is a Canadian poet whose works focus on nature and the human spirit.'),
('Michael', 'Brown', 'Michael Brown is an English historian specializing in medieval Europe and has written several award-winning books.'),
('Jessica', 'Davis', 'Jessica Davis is an Australian author known for her thrilling mysteries and complex characters.'),
('William', 'Wilson', 'William Wilson is a British biographer with an interest in political figures of the 20th century.'),
('Sophia', 'Martinez', 'Sophia Martinez is an American short story writer whose collections reflect on interpersonal relationships and social dynamics.'),
('James', 'Lee', 'James Lee is an Irish novelist whose narratives often delve into the struggles during the Irish revolutionary period.'),
('Lisa', 'White', 'Lisa White is a New Zealand author famous for her fantasy novels set in imaginative worlds filled with mythical creatures.'),
('David', 'Harris', 'David Harris is a South African playwright known for his plays that address issues of apartheid and reconciliation.'),
('Maria', 'Garcia', 'Maria Garcia is a Spanish author and cultural critic, noted for her insightful essays on contemporary European society.');

INSERT INTO genres (genre_name, overview)
VALUES
('Fantasy', 'Fantasy involves magical elements, often set in imaginary worlds where magic and mythological creatures are common.'),
('Science Fiction', 'Science Fiction explores futuristic concepts, scientific advancements, and often the potential consequences of such innovations.'),
('Mystery', 'Mystery genre involves unsolved puzzles or crimes that are gradually solved through the narrative.'),
('Romance', 'Romance focuses on relationships and love, often detailing the emotional journey between individuals.'),
('Thriller', 'Thrillers are characterized by fast pacing, tension, excitement, and the anticipation of what will happen next.'),
('Historical Fiction', 'Historical Fiction tells stories set in the past, often using historical events or figures as a backdrop to the narrative.'),
('Horror', 'Horror aims to evoke fear through suspense, atypical situations, or supernatural elements.'),
('Biography', 'Biography is a detailed description or account of someone''s life, involving more than just basic facts.'),
('Poetry', 'Poetry emphasizes expression through the structure of verse and rhythmic qualities of the language used.'),
('Self-Help', 'Self-Help genres focus on personal improvement and often provide guidance and strategies to enhance oneself.');

INSERT INTO subgenres (genre_name, subgenre_name)
VALUES
('Fantasy', 'High Fantasy'),
('Fantasy', 'Urban Fantasy'),
('Science Fiction', 'Cyberpunk'),
('Science Fiction', 'Space Opera'),
('Mystery', 'Cozy Mystery'),
('Mystery', 'Detective Fiction'),
('Romance', 'Contemporary Romance'),
('Romance', 'Historical Romance'),
('Thriller', 'Psychological Thriller'),
('Thriller', 'Crime Thriller');

INSERT INTO books (isbn, title, author_id, publish_date, genre_name)
VALUES
('978-3-16-1-0', 'The Enchanted Forest', 1, '2021-05-15', 'Fantasy'),
('978-0-262-2-9', 'Futures Unseen', 3, '2022-08-23', 'Science Fiction'),
('978-1-860-3-6', 'Mystery of the Old House', 2, '2019-03-10', 'Mystery'),
('978-3-16-4-1', 'Love in the Mountains', 5, '2018-07-21', 'Romance'),
('978-0-262-5-0', 'The Final Hour', 4, '2020-11-11', 'Thriller'),
('978-1-860-6-7', 'A Tale of Two Wars', 6, '2017-02-14', 'Historical Fiction'),
('978-3-16-7-2', 'Nightmare Alley', 7, '2023-01-01', 'Horror'),
('978-0-262-8-1', 'Life of Pi', 8, '2015-05-20', 'Biography'),
('978-1-860-9-8', 'Echoes of the Heart', 9, '2021-09-12', 'Poetry'),
('978-3-16-10-3', 'Becoming Your Best', 10, '2022-06-30', 'Self-Help');

INSERT INTO reviews (content, poster_username, publish_date, isbn)
VALUES
('A captivating tale of adventure and magic.', 'alice123', '2023-04-01', '978-3-16-1-0'),
('A thought-provoking book that explores deep themes of technology and humanity.', 'bobmarley', '2023-04-02', '978-0-262-2-9'),
('Classic mystery with a modern twist. Highly recommended!', 'charlie_m', '2023-04-03', '978-1-860-3-6'),
('A romantic story that will leave you yearning for more.', 'david4mvp', '2023-04-04', '978-3-16-4-1'),
('Edge of your seat thriller that keeps you guessing until the last page.', 'eve_bravo', '2023-04-05', '978-0-262-5-0'),
('Historical fiction at its best, immersing the reader in an epic narrative.', 'frankie8', '2023-04-06', '978-1-860-6-7'),
('Truly horrifying. Not for the faint of heart but well worth the read.', 'grace_h', '2023-04-07', '978-3-16-7-2'),
('An insightful look into the life of an inspiring figure. Beautifully written.', 'hannah_sun', '2023-04-08', '978-0-262-8-1');


INSERT INTO user_favorites_author (username, author_id)
VALUES
('alice123', 1),
('bobmarley', 3),
('charlie_m', 5),
('david4mvp', 7),
('eve_bravo', 9),
('frankie8', 2),
('grace_h', 4),
('hannah_sun', 6);


INSERT INTO user_likes_genre (username, genre_name)
VALUES
('alice123', 'Fantasy'),
('bobmarley', 'Science Fiction'),
('charlie_m', 'Mystery'),
('david4mvp', 'Romance'),
('eve_bravo', 'Thriller'),
('frankie8', 'Historical Fiction'),
('grace_h', 'Horror'),
('hannah_sun', 'Biography');

INSERT INTO book_is_subgenre (isbn, subgenre_name)
VALUES
('978-3-16-1-0', 'High Fantasy'),
('978-0-262-2-9', 'Cyberpunk'),
('978-1-860-3-6', 'Detective Fiction'),
('978-3-16-4-1', 'Contemporary Romance'),
('978-0-262-5-0', 'Psychological Thriller'),
('978-1-860-6-7', 'War Fiction'),
('978-3-16-7-2', 'Supernatural Horror'),
('978-0-262-8-1', 'Literary Biography'),
('978-1-860-9-8', 'Narrative Poetry'),
('978-3-16-10-3', 'Motivational');