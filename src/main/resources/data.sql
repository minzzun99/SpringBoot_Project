--기존 데이터
INSERT INTO article (title,content) VALUES ('강민준','aaaa');
INSERT INTO article (title,content) VALUES ('김상학','bbbb');
INSERT INTO article (title,content) VALUES ('박지수','cccc');

-- article 테이블에 데이터 추가
INSERT INTO article (title,content) VALUES ('당신의 인생 영화는?','댓글 고');
INSERT INTO article (title,content) VALUES ('당신의 소울 푸드는?','댓글 고고');
INSERT INTO article (title,content) VALUES ('당신의 취미는?','댓글 고고고');

-- 4번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname, body) VALUES (4, 'Park', '굿 윌 헌팅');
INSERT INTO comment (article_id, nickname, body) VALUES (4, 'Kim', '헝거게임');
INSERT INTO comment (article_id, nickname, body) VALUES (4, 'Kang', '아이언맨');

-- 5번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname, body) VALUES (5, 'Park', '치킨');
INSERT INTO comment (article_id, nickname, body) VALUES (5, 'Kim', '샤브샤브');
INSERT INTO comment (article_id, nickname, body) VALUES (5, 'Kang', '초밥');

-- 6번 게시글의 댓글 추가
INSERT INTO comment (article_id, nickname, body) VALUES (6, 'Park', '조깅');
INSERT INTO comment (article_id, nickname, body) VALUES (6, 'Kim', '유튜브 시청');
INSERT INTO comment (article_id, nickname, body) VALUES (6, 'Kang', '야구');