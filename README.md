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

## 5. 주요 기능

### 🎥 영화 관리
- **등록 / 수정 / 삭제 (관리자 전용)**  
  → 관리자는 새로운 영화를 등록하고, 기존 영화 정보를 업데이트하거나 삭제할 수 있음  
- **검색 / 목록 / 상세 조회 (사용자 전용)**  
  → 사용자는 영화 제목, 감독, 장르 등을 기준으로 검색 가능  
  → 개별 영화 상세 정보(상영 시간, 장르, 감독, 리뷰 등) 확인 가능  

---

### 🗓️ 상영 일정 & 좌석 관리
- **상영 스케줄 등록**  
  → 영화별 상영관, 상영 시간 지정 가능  
- **좌석 자동 생성**  
  → 스크린마다 A~J행, 1~7열 구조의 좌석 자동 생성  
- **잔여 좌석 관리**  
  → 실시간으로 예약 가능한 좌석 조회 가능  

---

### 🎟️ 예매 & 결제
- **좌석 선택 → 임시 홀드 → 결제 확정**  
  → 사용자가 좌석을 선택하면 일정 시간 동안 좌석이 홀드됨  
  → 결제 완료 시 좌석 예약이 최종 확정  
- **결제 실패 시 좌석 자동 반환**  

---

### ✍️ 리뷰 시스템
- **리뷰 작성 / 수정 / 삭제**  
  → 사용자는 본인이 예매한 영화에 대한 리뷰 작성 가능  
- **평점 집계**  
  → 영화별 평균 평점 계산 및 노출  

---

### 🔐 인증 & 권한 관리
- **JWT 기반 인증**  
  → 로그인 시 JWT 토큰 발급 및 유효성 검증  
- **권한(Role) 분리**  
  - `ROLE_USER`: 영화 조회, 예매, 리뷰 작성 가능  
  - `ROLE_ADMIN`: 영화 등록/수정/삭제, 스케줄 관리 가능  

---

### ⚡ 성능 최적화
- **Redis 캐싱**  
  → 자주 조회되는 영화 검색 결과를 캐싱하여 응답 속도 개선  
- **캐시 무효화 정책**  
  → 영화 등록/수정/삭제 시 관련 캐시 자동 제거  
- **검색 고도화**: Elasticsearch 기반 실시간 검색  

---

### 📊 추가 기능(확장 계획)
- 결제 모듈: 실제 PG(Payment Gateway) 연동  
- 추천 시스템: 사용자 맞춤 영화 추천    

---

## 6. 사용한 기술 목록

### 언어 및 프레임워크
- [Java 17](https://www.notion.so/Java-17-2532dc3ef51480abb970ff2c30da6e55?pvs=21)  
- [Spring Boot](https://www.notion.so/Spring-Boot-2532dc3ef51480608337e9315be4741c?pvs=21)  
- [Spring Data JPA](https://www.notion.so/Spring-Data-JPA-2532dc3ef5148009944fe2ec4bec0c79?pvs=21)  

### 인증 · 인가
- [Spring Security](https://www.notion.so/Spring-Security-2532dc3ef51480c493ccc4ae9d2dcba7?pvs=21)  
- [JWT](https://www.notion.so/JWT-2532dc3ef5148044b673da71f00b5d6e?pvs=21)  
- [slf4j](https://www.notion.so/slf4j-2532dc3ef51480a195e6ee3d0c4f9d34?pvs=21) (JWT 로그)  

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

## 7. 트러블슈팅 (Troubleshooting)

👉 추후 상세 내용 삽입  

📌 **작성 템플릿 예시**  
- **문제 상황**: (어떤 문제가 발생했는지 간단히)  
- **원인 분석**: (로그, 코드, 설정 등으로 원인 추적)  
- **해결 방법**: (어떤 방식으로 해결했는지 단계별 기술)  
- **배운 점**: (이 문제를 통해 배운 것, 적용할 교훈)  

---
