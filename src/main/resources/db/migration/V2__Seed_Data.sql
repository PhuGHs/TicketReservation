-- Insert sample theaters
INSERT INTO theaters (name, location) VALUES
                                          ('Grand Cinema', 'Downtown'),
                                          ('Regal Movies', 'Uptown');

-- Insert sample auditoriums
INSERT INTO auditoriums (theater_id, name, capacity) VALUES
                                                         (1, 'Auditorium A', 150),
                                                         (1, 'Auditorium B', 200),
                                                         (2, 'Auditorium C', 100);

-- Insert sample movies
INSERT INTO movies (title, description, poster_image, duration, genre) VALUES
                                                                           ('Inception', 'A mind-bending thriller', 'inception.jpg', 148, 'Sci-Fi'),
                                                                           ('Titanic', 'A tragic love story', 'titanic.jpg', 195, 'Romance'),
                                                                           ('Avengers: Endgame', 'Superheroes unite', 'endgame.jpg', 181, 'Action');

-- Insert sample screenings
INSERT INTO screenings (auditorium_id, movie_id, start_time, end_time) VALUES
                                                                           (1, 1, '2025-04-10 18:00:00', '2025-04-10 20:28:00'),
                                                                           (2, 2, '2025-04-11 19:30:00', '2025-04-11 22:45:00'),
                                                                           (3, 3, '2025-04-12 20:00:00', '2025-04-12 23:01:00');

-- Insert sample seats
INSERT INTO seats (screening_id, seat_number, is_available, version) VALUES
                                                                         (1, 1, TRUE, 'v1'),
                                                                         (1, 2, TRUE, 'v1'),
                                                                         (2, 1, TRUE, 'v1'),
                                                                         (2, 2, TRUE, 'v1'),
                                                                         (3, 1, TRUE, 'v1');

-- Insert sample users
INSERT INTO users (username, email, password, created_date) VALUES
                                                                ('john_doe', 'john@example.com', 'hashed_password', '2025-04-01'),
                                                                ('jane_doe', 'jane@example.com', 'hashed_password', '2025-04-02');

-- Insert sample system roles
INSERT INTO system_roles (role_name) VALUES
                                         ('ADMIN'),
                                         ('USER');

-- Insert sample user roles
INSERT INTO user_roles (role_id, user_id) VALUES
                                              (1, 1), -- John Doe as Admin
                                              (2, 2); -- Jane Doe as User

-- Insert sample bookings
INSERT INTO bookings (user_id, seat_id, status, created_at, expires_at) VALUES
                                                                            (1, 1, 'CONFIRMED', '2025-04-05 14:00:00', '2025-04-05 16:00:00'),
                                                                            (2, 2, 'PENDING', '2025-04-05 15:00:00', '2025-04-05 17:00:00');
