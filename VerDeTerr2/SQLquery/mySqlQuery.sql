CREATE TABLE `Member` (
	ID VARCHAR(20) NOT NULL,
	PW VARCHAR(20) NOT NULL,
	PWHint VARCHAR(20) NOT NULL,
	Email VARCHAR(255),
	UserType VARCHAR(20),
	Nickname VARCHAR(30) UNIQUE,
	Age Integer,
	Gender VARCHAR(20),
	ManagerYn BOOLEAN NOT NULL,
	RegDate DATETIME NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE `Mbti_ML` (
	Idx INTEGER AUTO_INCREMENT NOT NULL,
	ID VARCHAR(20) NOT NULL,
	Answer1 Text(100000) NOT NULL,
	Answer2 Text(100000) NOT NULL,
	Answer3 Text(100000) NOT NULL,
	Answer4 Text(100000) NOT NULL,
	Answer5 Text(100000) NOT NULL,
	Answer6 Text(100000) NOT NULL,
	Answer7 Text(100000) NOT NULL,
	Answer8 Text(100000) NOT NULL,
	Answer9 Text(100000) NOT NULL,
	Answer10 Text(100000) NOT NULL,
	Answer11 Text(100000) NOT NULL,
	Answer12 Text(100000) NOT NULL,
	Answer13 Text(100000) NOT NULL,
	Answer14 Text(100000) NOT NULL,
	Answer15 Text(100000) NOT NULL,
	Answer16 Text(100000) NOT NULL,
	PRIMARY KEY (Idx),
	foreign key (id)
	references `Member`(id)
	on update cascade
);

CREATE TABLE `Mbti_Type` (
	UserType VARCHAR(20) NOT NULL,
	UserCharacter VARCHAR(1000) NOT NULL,
	Job VARCHAR(1000) NOT NULL,
	Feature VARCHAR(5000) NOT NULL,
	PRIMARY KEY (UserType)
);

CREATE TABLE `Mbti_ML_Output` (
	Idx INTEGER AUTO_INCREMENT NOT NULL,
	ID VARCHAR(20) NOT NULL,
	TestDate DATETIME not null,
	UserAnswer Text(100000) NOT NULL,
	UserType VARCHAR(20),
	PRIMARY KEY (Idx),
	FOREIGN KEY (Idx)
	REFERENCES Mbti_ML(Idx)
	ON UPDATE cascade,
	FOREIGN KEY (UserType)
	REFERENCES Mbti_Type(UserType)
	ON UPDATE cascade
);

alter table `member`
	add foreign key(UserType)
	references Mbti_ML_Output(UserType)
	ON UPDATE cascade;

select * from mbti_type;

insert into Mbti_Type
	values ('ENFP', '재기 발랄한 활동가',
		'크리에이티브 디렉터, 디자이너, 시나리오 작가, 방송 프로듀서, 홍보 컨설턴트, 상담사, 상품 기획자','직업');
insert into Mbti_Type
	values ('ENFJ', '정의로운 사회운동가',
		' 아나운서, 리포터, 방송 MC, 언어교사, 아동 복지사, CEO, 취업 컨설턴트, 동시 통역가','직업');
insert into Mbti_Type
	values ('INFP',
		'열정적인 중재자',' 예술가, 소설가, 시인, 음악가, 미술 치료사, 사회복지사, 작곡가, 사서','직업');
insert into Mbti_Type
	values ('INFJ',
		'선의의 옹호자',' 직업상담사, 특수 교사, 노인 복지사, 아트 디렉터, 프리랜서 기획, 저널리스트, 상품기획 MD','직업');
insert into Mbti_Type
	values ('ENTP',
		'논쟁을 즐기는 변론가',' 발명가, 벤처 사업가, 에이전트, 배우, 가수, 영화감독, 칼럼니스트, 정치인','직업');
insert into Mbti_Type
	values ('ENTJ',
		'대담한 통솔자',' 경영 컨설턴트, 공인 중개사, 관리사, 변호사, 재무 상담사, 경제 분석가, 벤처 투자가, 판사','직업');
insert into Mbti_Type
	values ('INTP',
		'논리적인 사색가',' 경제학자, 심리학자, 경찰, 프로그래머, 천문학자, 비평가, 아트디렉터, 연구원','직업');
insert into Mbti_Type
	values ('INTJ',
		'용의주도한 전략가',' 분석가, 회계사, 인류학자, 파일럿, 경영 컨설턴트, 제약회사 연구원, 웹 개발자, 최고 재무 책임자','직업');
insert into Mbti_Type
	values ('ESTJ',
		'엄격한 관리자',' 감독관, 예산 분석가, 은행장, 정책 책임자, 보안 요원, 기관사, 교육 전','직업');
insert into Mbti_Type
	values ('ESFJ',
		'사교적인 외교관',' 홍보 책임자, 호텔 지배인, 마케팅 책임자, 초등학교 교사, 특수 교사, 비서, 유치원 교사','직업');
insert into Mbti_Type
	values ('ISTJ',
		'청렴 결백한 논리주의자',' 통계학자, 바이어, 기상학자, 법률 연구원, 보험 심사관, 형사, 감정 평가사, 세관 조사관','직업');
insert into Mbti_Type
	values ('ISFJ',
		'용감한 수호자',' 행정 보조원, 인사 관리자, 신용 상담가, 보호 감찰관, 물리치료사, 정신과 의사, 방사선 기사','직업');
insert into Mbti_Type
	values ('ESTP',
		'모험을 즐기는 사업가',' 경찰관, 소방관, 군 장교, 펀드 매니저, 은행원, 기자, 여행 가이드, 건축 엔지니어','직업');
insert into Mbti_Type
	values ('ESFP',
		'자유로운 영혼의 연예인',' 코미디언, 의상 디자이너, 일러스트레이터, 애니메이터, 여행 상품 기획자, 놀이 치료사','직업');
insert into Mbti_Type
	values ('ISTP',
		'만능 재주끼',' 파일럿, 카레이서, 범죄학자, 사진 작가, 판매원, 운동선수, 항공기 정비사, 네트워크 관리자','직업');
insert into Mbti_Type
	values ('ISFP',
		'호기심 많은 예술가',' 보석 세공사, 음향 디자이너, 만화가, 지질학자, 사육사, 수의사, 법률 비서, 약사','직업');

CREATE TABLE `Post` (
	Idx INTEGER AUTO_INCREMENT NOT NULL,
	Title VARCHAR(200) NOT NULL,
	Content VARCHAR(5000) NOT NULL,
	Writer VARCHAR(100) NOT NULL,
	ViewCnt INTEGER NOT NULL,
	NoticeYn BOOLEAN NOT NULL,
	DeleteYn BOOLEAN not null,
	InsertTime DATETIME NOT NULL,
	UpdateTime DATETIME,
	DeleteTime DATETIME,
	PostType VARCHAR(20),
	PRIMARY KEY (Idx),
	FOREIGN KEY (Writer)
	REFERENCES `Member`(Nickname)
	ON UPDATE cascade
);

CREATE TABLE `Comment` (
	Idx INTEGER auto_increment NOT NULL,
	BoardIdx INTEGER NOT NULL,
	Content VARCHAR(3000) NOT NULL,
	Writer VARCHAR(100) NOT NULL,
	DeleteYn BOOLEAN NOT NULL,
	InsertTime DATETIME NOT NULL,
	UpdateTime DATETIME,
	DeleteTime DATETIME,
	PostType VARCHAR(20),
	PRIMARY KEY (Idx),
	FOREIGN KEY (Writer)
	REFERENCES `Member`(Nickname)
	ON UPDATE cascade,
	FOREIGN KEY (BoardIdx)
	REFERENCES `Post`(Idx)
	ON UPDATE cascade
);