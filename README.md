# 🎬 FilmPass

## 1. 프로젝트 소개
영화 예매 시스템 - **FilmPass**  
FilmPass는 고객들에게 빠르고 안정적인 영화 티켓 예매 경험을 제공하고,  
고객들이 원하는 영화를 즐길 수 있도록 도와주는 영화 관람 티켓팅 전문 어플리케이션입니다.

---

## 2. 팀원 소개
👉 (추후 삽입 예정)

---

## 3. 개발 기간
📅 **2025/07/17 ~ 2025/08/22**

---

## 4. 개발 환경
- **OS**: Windows 10 / macOS (팀원별 환경)  
- **IDE**: IntelliJ IDEA Ultimate  
- **Version Control**: Git, GitHub  
- **Build Tool**: Gradle 8.x  
- **Database(Local)**: H2 Database (개발용)  
- **Database(Prod)**: MySQL 8.x  
- **Cache**: Redis 7.x  
- **Infra**: Docker, AWS EC2  
- **협업 툴**: Notion, Slack, ERD Cloud  

---

## 5. 시스템 설계 자료

### 🗂️ 아키텍처
👉 추후 아키텍처 다이어그램 삽입 (예: AWS EC2 + Docker + Redis + MySQL 흐름도)

### 🗄️ ERD (Entity Relationship Diagram)
👉 추후 ERD 다이어그램 삽입 (예: 영화–스케줄–좌석–리뷰 관계)

### 🖼️ 와이어프레임
👉 추후 와이어프레임 이미지 삽입 (예: 메인 화면, 예매 화면, 리뷰 화면 등)

---

## 6. 주요 기능

### 🎥 영화 관리
- **등록 / 수정 / 삭제 (관리자 전용)**  
- **검색 / 목록 / 상세 조회 (사용자 전용)**  

### 🗓️ 상영 일정 & 좌석 관리
- 상영 스케줄 등록  
- 좌석 자동 생성 (A~J행, 1~7열)  
- 잔여 좌석 실시간 관리  

### 🎟️ 예매 & 결제
- 좌석 선택 → 임시 홀드 → 결제 확정  
- 결제 실패 시 좌석 자동 반환  

### ✍️ 리뷰 시스템
- 리뷰 작성 / 수정 / 삭제  
- 영화별 평점 집계  

### 🔐 인증 & 권한 관리
- JWT 기반 인증  
- `ROLE_USER` / `ROLE_ADMIN` 권한 분리  

### ⚡ 성능 최적화
- Redis 캐싱  
- 캐시 무효화 정책  
- Elasticsearch 기반 검색 (추후)  

### 📊 추가 기능(확장 계획)
- 결제 모듈: 실제 PG(Payment Gateway) 연동  
- 추천 시스템: 사용자 맞춤 영화 추천  

---

## 7. 사용한 기술 목록

### 언어 및 프레임워크
- [Java 17](https://www.notion.so/Java-17-2532dc3ef51480abb970ff2c30da6e55?pvs=21)  
- [Spring Boot](https://www.notion.so/Spring-Boot-2532dc3ef51480608337e9315be4741c?pvs=21)  
- [Spring Data JPA](https://www.notion.so/Spring-Data-JPA-2532dc3ef5148009944fe2ec4bec0c79?pvs=21)  

### 인증 · 인가
- [Spring Security](https://www.notion.so/Spring-Security-2532dc3ef51480c493ccc4ae9d2dcba7?pvs=21)  
- [JWT](https://www.notion.so/JWT-2532dc3ef5148044b673da71f00b5d6e?pvs=21)  
- [slf4j](https://www.notion.so/slf4j-2532dc3ef51480a195e6ee3d0c4f9d34?pvs=21)  

### Database
- [MySQL](https://www.notion.so/MySQL-2532dc3ef514806c9f4cc1640845b938?pvs=21)  
- [Redis](https://www.notion.so/Redis-2532dc3ef51480c0a2e7fea7ec82b3fb?pvs=21)  

### Infra & CI/CD
- [Docker](https://www.notion.so/Docker-2532dc3ef514804bb589cea20c6ce0a7?pvs=21)  
- [Amazon EC2](https://www.notion.so/Amazon-EC2-2532dc3ef5148073b9a5e0a259353db7?pvs=21)  
- [Amazon RES](https://www.notion.so/Amazon-RES-2532dc3ef5148061834ae705f9f54aa0?pvs=21)  
- [GitHub Actions](https://www.notion.so/GItHub-Actions-2532dc3ef5148066aa62cf389544829e?pvs=21)  
- [Nginx](https://www.notion.so/Nginx-2532dc3ef514807da073d65237044491?pvs=21)  
- [Elasticsearch](https://www.notion.so/Elasticsearch-2532dc3ef51480a596a6f0ce5c7db6b7?pvs=21)  
- [Kibana](https://www.notion.so/Kibana-2532dc3ef5148012b68fc1eec1b032cb?pvs=21)  

### Test
- [Postman](https://www.notion.so/Postman-2532dc3ef51480f6a06aefe6be154f60?pvs=21)  
- [Junit5](https://www.notion.so/Junit5-2542dc3ef514808bba63d26bcc427c58?pvs=21)  
- [K6](https://www.notion.so/K6-2542dc3ef514803aa7bdd88582b1dcef?pvs=21)  

### Tools
- [IntelliJ IDEA](https://www.notion.so/Intellij-IDEA-2532dc3ef51480548555fb9ef02f2c13?pvs=21)  

### Collaboration
- [Notion](https://www.notion.so/Notion-2532dc3ef514808098b4ea22f9c93d74?pvs=21)  
- [GitHub](https://www.notion.so/GitHub-2532dc3ef5148033aaf2facc6a9b94e3?pvs=21)  
- [Slack](https://www.notion.so/Slack-2532dc3ef5148098aca4dfd7ffa588c6?pvs=21)  
- [ERD cloud](https://www.notion.so/ERD-cloud-2532dc3ef51480eb8e9afe8163f6a536?pvs=21)  
- [RESTful API](https://www.notion.so/RESTful-API-2532dc3ef51480a5b84af6eefdea05b2?pvs=21)  
- [draw.io](https://www.notion.so/draw-io-2532dc3ef51480af9860fb9393ec39c6?pvs=21)  

---

## 8. API 명세서
👉 추후 Swagger UI 캡쳐 / 표 / Notion API 문서 링크 삽입  

예시:  
- `POST /api/movies` (ADMIN) – 영화 등록  
- `GET /api/movies` – 영화 검색/목록 조회  
- `GET /api/movies/{id}` – 영화 상세 조회  
- `PATCH /api/movies/{id}` (ADMIN) – 영화 수정  
- `DELETE /api/movies/{id}` (ADMIN) – 영화 삭제  

---

## 9. 트러블슈팅 (Troubleshooting)

👉 추후 상세 내용 삽입  

📌 **작성 템플릿 예시**  
- **문제 상황**: (어떤 문제가 발생했는지 간단히)  
- **원인 분석**: (로그, 코드, 설정 등으로 원인 추적)  
- **해결 방법**: (어떤 방식으로 해결했는지 단계별 기술)  
- **배운 점**: (이 문제를 통해 배운 것, 적용할 교훈)  

---
