-- 가슴 운동
INSERT INTO category (exercise, exercise_area) VALUES
('딥스', '가슴'),
('푸시업', '가슴'),
('인클라인 벤치프레스', '가슴'),
('디클라인 벤치프레스', '가슴'),
('케이블 크로스오버', '가슴');

-- 등 운동
INSERT INTO category (exercise, exercise_area) VALUES
('바벨로우', '등'),
('시티드 로우', '등'),
('원암 덤벨로우', '등'),
('풀업', '등'),
('페이스풀', '등');

-- 어깨 운동
INSERT INTO category (exercise, exercise_area) VALUES
('사이드 레터럴 레이즈', '어깨'),
('프론트 레이즈', '어깨'),
('리버스 펙덱 플라이', '어깨'),
('업라이트 로우', '어깨');

-- 하체 운동
INSERT INTO category (exercise, exercise_area) VALUES
('런지', '하체'),
('레그 익스텐션', '하체'),
('레그 컬', '하체'),
('카프 레이즈', '하체'),
('힙 쓰러스트', '하체');

-- 이두 운동
INSERT INTO category (exercise, exercise_area) VALUES
('해머 컬', '이두'),
('인클라인 덤벨 컬', '이두'),
('프리처 컬', '이두'),
('케이블 컬', '이두'),
('바벨 컬', '이두');

-- 삼두 운동
INSERT INTO category (exercise, exercise_area) VALUES
('클로즈그립 벤치프레스', '삼두'),
('케이블 푸시다운', '삼두'),
('스컬크러셔', '삼두'),
('오버헤드 익스텐션', '삼두'),
('킥백', '삼두');

-- 코어 운동
INSERT INTO category (exercise, exercise_area) VALUES
('크런치', '코어'),
('레그레이즈', '코어'),
('러시안 트위스트', '코어'),
('데드버그', '코어'),
('사이드 플랭크', '코어');

-- 전신 운동
INSERT INTO category (exercise, exercise_area) VALUES
('버피', '전신'),
('클린 앤 저크', '전신'),
('터키시 겟업', '전신'),
('스내치', '전신'),
('로프 클라이밍', '전신');

INSERT INTO `healog`.`gym` (`name`, `address`, `phonenumber`) VALUES
('피트니스 프렌즈 강남점', '서울특별시 강남구 테헤란로 152', '02-555-1234'),
('스포애니 역삼점', '서울특별시 강남구 역삼로 123', '02-555-5678'),
('커브스 서초점', '서울특별시 서초구 서초대로 333', '02-587-1234'),
('24시 휘트니스 강남역점', '서울특별시 강남구 강남대로 432', '02-558-9876'),
('하이짐 청담점', '서울특별시 강남구 청담동 123-45', '02-515-7890'),

('크로스핏 홍대점', '서울특별시 마포구 와우산로 123', '02-332-4567'),
('골드짐 합정점', '서울특별시 마포구 양화로 234', '02-334-5678'),
('애플짐 상수점', '서울특별시 마포구 상수동 123-12', '02-337-8901'),
('파워하우스 연남점', '서울특별시 마포구 연남동 567-89', '02-336-2345'),
('주짓수 & 피트니스 홍대점', '서울특별시 마포구 어울마당로 456', '02-335-6789'),

('스포짐 판교점', '경기도 성남시 분당구 판교역로 234', '031-705-1234'),
('헬스존 정자점', '경기도 성남시 분당구 정자일로 123', '031-708-5678'),
('24/7 피트니스 분당점', '경기도 성남시 분당구 서현로 345', '031-702-9012'),
('파워짐 수내점', '경기도 성남시 분당구 수내로 678', '031-704-3456'),
('트레이닝뱅크 판교테크노밸리점', '경기도 성남시 분당구 대왕판교로 789', '031-706-7890'),

('비치짐 해운대점', '부산광역시 해운대구 해운대해변로 123', '051-742-1234'),
('파워피트니스 서면점', '부산광역시 부산진구 서면로 456', '051-802-5678'),
('멜킨스짐 광안리점', '부산광역시 수영구 광안해변로 789', '051-756-9012'),
('헬스타임 부산대점', '부산광역시 금정구 부산대학로 234', '051-581-3456'),
('익스트림짐 센텀시티점', '부산광역시 해운대구 센텀중앙로 567', '051-744-7890');


-- 각 트레이너의 salt값은 임의로 생성된 20자 문자열입니다
-- password는 실제 환경에서는 암호화되어야 하지만, 여기서는 예시로 해시된 값을 사용했습니다
INSERT INTO `healog`.`trainer` 
(`id`, `email`, `password`, `salt`, `name`, `phonenumber`, `birthday`, `bio`, `img`, `location`, `user_count`, `gender`) 
VALUES
('trainer_1', 'john.kim@healog.com', 'hashed_password_1', 'salt123456789abcdef1', '김준호', '010-1234-5678', '1990-03-15', '前 국가대표 보디빌더 선수\n케어 전문 퍼스널 트레이너\n체형교정 자격증 보유', 'trainer1.jpg', 1, 12, 1),
('trainer_2', 'sarah.lee@healog.com', 'hashed_password_2', 'salt123456789abcdef2', '이서연', '010-2345-6789', '1992-07-22', '요가 지도자 자격증 보유\n식단관리 전문가\n다이어트 프로그램 전문', 'trainer2.jpg', 2, 8, 2),
('trainer_3', 'mike.park@healog.com', 'hashed_password_3', 'salt123456789abcdef3', '박민수', '010-3456-7890', '1988-11-30', '생활스포츠지도사 2급\n재활트레이닝 전문가\n기능해부학 강사', 'trainer3.jpg', 3, 15, 1),
('trainer_4', 'jenny.choi@healog.com', 'hashed_password_4', 'salt123456789abcdef4', '최지은', '010-4567-8901', '1993-05-18', '미국 NASM-CPT 자격증 보유\n체지방 감량 전문\n온라인 PT 진행', 'trainer4.jpg', 4, 10, 2),
('trainer_5', 'david.hong@healog.com', 'hashed_password_5', 'salt123456789abcdef5', '홍대현', '010-5678-9012', '1991-09-25', '크로스핏 레벨 2 트레이너\n전문선수 트레이닝 담당\n체력 향상 프로그램 전문가', 'trainer5.jpg', 5, 18, 1),

('trainer_6', 'emma.kang@healog.com', 'hashed_password_6', 'salt123456789abcdef6', '강은미', '010-6789-0123', '1994-02-14', '필라테스 강사 자격증 보유\n코어 강화 전문\n산전/산후 체형관리', 'trainer6.jpg', 6, 9, 2),
('trainer_7', 'tom.shin@healog.com', 'hashed_password_7', 'salt123456789abcdef7', '신태호', '010-7890-1234', '1989-06-20', '보디빌딩 대회 수상 경력\n근력향상 프로그램 전문\n영양학 자격증 보유', 'trainer7.jpg', 7, 14, 1),
('trainer_8', 'lucy.yoon@healog.com', 'hashed_password_8', 'salt123456789abcdef8', '윤루시', '010-8901-2345', '1995-10-08', 'NSCA-CPT 자격증 보유\n여성 전문 트레이너\n스트레칭 마스터', 'trainer8.jpg', 8, 11, 2),
('trainer_9', 'peter.lim@healog.com', 'hashed_password_9', 'salt123456789abcdef9', '임피터', '010-9012-3456', '1987-12-03', '물리치료사 자격증 보유\n재활운동 전문\n자세교정 전문가', 'trainer9.jpg', 9, 13, 1),
('trainer_10', 'sophia.han@healog.com', 'hashed_password_10', 'salt123456789abcde10', '한소피아', '010-0123-4567', '1992-04-27', '요가 & 필라테스 강사\n명상 프로그램 진행\n체형교정 전문가', 'trainer10.jpg', 10, 7, 2),

('trainer_11', 'jake.jung@healog.com', 'hashed_password_11', 'salt123456789abcde11', '정재국', '010-2345-6789', '1990-08-12', '스포츠마사지 자격증 보유\n근육이완 전문가\n체형교정 프로그램 운영', 'trainer11.jpg', 11, 16, 1),
('trainer_12', 'lisa.baek@healog.com', 'hashed_password_12', 'salt123456789abcde12', '백리사', '010-3456-7890', '1993-01-29', '댄스 전공\n체지방 감량 전문가\n유산소 운동 프로그램 전문', 'trainer12.jpg', 12, 9, 2),
('trainer_13', 'alex.song@healog.com', 'hashed_password_13', 'salt123456789abcde13', '송알렉스', '010-4567-8901', '1988-05-16', '크로스핏 레벨 3 트레이너\n기능성 운동 전문가\n체력 향상 프로그램 운영', 'trainer13.jpg', 13, 20, 1),
('trainer_14', 'amy.go@healog.com', 'hashed_password_14', 'salt123456789abcde14', '고에이미', '010-5678-9012', '1991-11-23', '영양사 자격증 보유\n식단관리 전문가\n다이어트 프로그램 진행', 'trainer14.jpg', 14, 12, 2),
('trainer_15', 'kevin.jung@healog.com', 'hashed_password_15', 'salt123456789abcde15', '정케빈', '010-6789-0123', '1989-07-09', '스포츠의학 전공\n선수 트레이닝 담당\n부상 재활 전문가', 'trainer15.jpg', 15, 15, 1),

('trainer_16', 'bella.nam@healog.com', 'hashed_password_16', 'salt123456789abcde16', '남벨라', '010-7890-1234', '1994-09-05', '요가 지도자 자격증 보유\n임산부 요가 전문\n스트레스 해소 프로그램 진행', 'trainer16.jpg', 16, 8, 2),
('trainer_17', 'chris.ahn@healog.com', 'hashed_password_17', 'salt123456789abcde17', '안크리스', '010-8901-2345', '1987-03-21', '미국 ACE-CPT 자격증 보유\n웨이트 트레이닝 전문가\n체력 증진 프로그램 운영', 'trainer17.jpg', 17, 17, 1),
('trainer_18', 'diana.oh@healog.com', 'hashed_password_18', 'salt123456789abcde18', '오다이애나', '010-9012-3456', '1992-12-15', '필라테스 마스터 트레이너\n재활 필라테스 전문\n코어 강화 프로그램 진행', 'trainer18.jpg', 18, 11, 2),
('trainer_19', 'eric.seo@healog.com', 'hashed_password_19', 'salt123456789abcde19', '서에릭', '010-0123-4567', '1990-06-30', '보디빌딩 선수 출신\n근비대 프로그램 전문가\n식단관리 컨설팅', 'trainer19.jpg', 19, 14, 1),
('trainer_20', 'grace.kim@healog.com', 'hashed_password_20', 'salt123456789abcde20', '김그레이스', '010-1234-5678', '1993-08-17', '스포츠 영양학 전공\n체중감량 전문가\n온라인 PT 프로그램 운영', 'trainer20.jpg', 20, 10, 2);


INSERT INTO `healog`.`user` 
(`id`, `name`, `email`, `password`, `salt`, `gender`, `birthday`, `phonenumber`, `trainer_exist`) 
VALUES
-- 20대 회원
('user_1', '김미래', 'mirae.kim@gmail.com', 'hashed_password_1', 'salt123456789abcdef1', 2, '1997-03-15', '010-1111-2222', 1),
('user_2', '박준영', 'junyoung.park@naver.com', 'hashed_password_2', 'salt123456789abcdef2', 1, '1996-07-22', '010-2222-3333', 0),
('user_3', '이하은', 'haeun.lee@gmail.com', 'hashed_password_3', 'salt123456789abcdef3', 2, '1999-11-30', '010-3333-4444', 1),
('user_4', '정우진', 'woojin.jung@daum.net', 'hashed_password_4', 'salt123456789abcdef4', 1, '1995-05-18', '010-4444-5555', 1),
('user_5', '최유진', 'yujin.choi@gmail.com', 'hashed_password_5', 'salt123456789abcdef5', 2, '1998-09-25', '010-5555-6666', 0),
('user_6', '강민준', 'minjun.kang@naver.com', 'hashed_password_6', 'salt123456789abcdef6', 1, '1997-02-14', '010-6666-7777', 1),
('user_7', '윤서아', 'seoa.yoon@gmail.com', 'hashed_password_7', 'salt123456789abcdef7', 2, '1996-06-20', '010-7777-8888', 1),
('user_8', '임도현', 'dohyun.lim@kakao.com', 'hashed_password_8', 'salt123456789abcdef8', 1, '1995-10-08', '010-8888-9999', 0),

-- 30대 회원
('user_9', '한지민', 'jimin.han@gmail.com', 'hashed_password_9', 'salt123456789abcdef9', 2, '1992-12-03', '010-9999-0000', 1),
('user_10', '송민호', 'minho.song@naver.com', 'hashed_password_10', 'salt123456789abcd10', 1, '1991-04-27', '010-0000-1111', 1),
('user_11', '백서연', 'seoyeon.baek@gmail.com', 'hashed_password_11', 'salt123456789abcd11', 2, '1990-08-12', '010-1234-5678', 0),
('user_12', '조현우', 'hyeonwoo.jo@daum.net', 'hashed_password_12', 'salt123456789abcd12', 1, '1993-01-29', '010-2345-6789', 1),
('user_28', '차진호', 'jinho.cha@gmail.com', 'hashed_password_28', 'salt123456789abcd28', 1, '1961-10-08', '010-8899-0011', 1),
('user_29', '표미선', 'misun.pyo@naver.com', 'hashed_password_29', 'salt123456789abcd29', 2, '1964-12-03', '010-9900-1122', 0),
('user_30', '추광호', 'kwangho.chu@gmail.com', 'hashed_password_30', 'salt123456789abcd30', 1, '1960-04-27', '010-0011-2233', 1);