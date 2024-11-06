USE `healog`;

-- 기존에 추가된 카테고리 데이터 삭제 (옵션)
DELETE FROM category WHERE exercise IN (
    'Push-Up', 'Pull-Up', 'Squat', 'Deadlift', 'Bench Press',
    'Overhead Press', 'Bicep Curl', 'Tricep Extension', 'Lunges', 
    'Leg Press', 'Calf Raise', 'Crunch', 'Russian Twist', 'Plank', 
    'Bicycle Crunch', 'Lat Pulldown', 'Seated Row', 'Face Pull', 
    'Lateral Raise', 'Hip Thrust'
);

-- Category 테이블에 20개의 더미 데이터 삽입
INSERT INTO category (exercise, exercise_area)
VALUES 
('Push-Up', 'Upper Body'),
('Pull-Up', 'Upper Body'),
('Squat', 'Lower Body'),
('Deadlift', 'Lower Body'),
('Bench Press', 'Upper Body'),
('Overhead Press', 'Upper Body'),
('Bicep Curl', 'Arms'),
('Tricep Extension', 'Arms'),
('Lunges', 'Lower Body'),
('Leg Press', 'Lower Body'),
('Calf Raise', 'Lower Body'),
('Crunch', 'Core'),
('Russian Twist', 'Core'),
('Plank', 'Core'),
('Bicycle Crunch', 'Core'),
('Lat Pulldown', 'Back'),
('Seated Row', 'Back'),
('Face Pull', 'Shoulders'),
('Lateral Raise', 'Shoulders'),
('Hip Thrust', 'Lower Body');


-- Diary 테이블에 특정 User (Alice, ID: 0)에 대한 한 달치 더미 데이터 삽입
INSERT INTO diary (year, month, day, user_id, `condition`)
VALUES 
(2023, 10, 1, 0, 'Good condition'),
(2023, 10, 2, 0, 'Tired'),
(2023, 10, 3, 0, 'Energetic'),
(2023, 10, 4, 0, 'Moderate'),
(2023, 10, 5, 0, 'Good condition'),
(2023, 10, 6, 0, 'Tired'),
(2023, 10, 7, 0, 'Energetic'),
(2023, 10, 8, 0, 'Moderate'),
(2023, 10, 9, 0, 'Good condition'),
(2023, 10, 10, 0, 'Tired'),
(2023, 10, 11, 0, 'Energetic'),
(2023, 10, 12, 0, 'Moderate'),
(2023, 10, 13, 0, 'Good condition'),
(2023, 10, 14, 0, 'Tired'),
(2023, 10, 15, 0, 'Energetic'),
(2023, 10, 16, 0, 'Moderate'),
(2023, 10, 17, 0, 'Good condition'),
(2023, 10, 18, 0, 'Tired'),
(2023, 10, 19, 0, 'Energetic'),
(2023, 10, 20, 0, 'Moderate'),
(2023, 10, 21, 0, 'Good condition'),
(2023, 10, 22, 0, 'Tired'),
(2023, 10, 23, 0, 'Energetic'),
(2023, 10, 24, 0, 'Moderate'),
(2023, 10, 25, 0, 'Good condition'),
(2023, 10, 26, 0, 'Tired'),
(2023, 10, 27, 0, 'Energetic'),
(2023, 10, 28, 0, 'Moderate'),
(2023, 10, 29, 0, 'Good condition'),
(2023, 10, 30, 0, 'Tired'),
(2023, 10, 31, 0, 'Energetic');

SELECT * FROM diary;
