# PARED_GAMES_aijyakae_server
Aijyakae는 Stable Diffusion사의 이미지 생성형 AI API를 활용하여
사용자가 원하는 프롬프트를 입력받아 이미지를 생성해주는 애플리케이션 입니다.
사용자가 만든 프롬프트와 사진, 닉네임을 저장하여 여러 사용자와 공유할 수 있는 기능을 갖고 있으며,
AWS를 활용하여 이를 직접 배포해본 경험이 의미있는 레포지토리 입니다.

## 프로젝트 중점 사항

* nginx reverse proxy를 이용하여 https 443번 포트로 들어온 요청을, Springboot API process로 redirect
* RDS와 EC2를 다른 서브넷에 배치하여, 외부에서의 DB연결을 차단.
* 이미지 파일의 Create, Update는 Spring boot를 통해 진행하고, DB에는 S3의 URL을 적재하여,
사용자가 게시판에 들어갔을 때 S3에서 직접 이미지를 가져올 수 있도록 설계,
이를 통해 ec2의 대역폭을 절감할 수 있었음.
* Github Actions, Docker Hub, EC2 Crontab를 이용하여 서버의 CI/CD를 구축
* Route 53을 이용하여 ec2 엔드포인트와 도메인을 연결

## Architecture

![Aijyakae-Architecture](https://github.com/user-attachments/assets/6c9b1a87-7735-453e-9241-5795638c22ba)

## Client Repository

https://github.com/lectykim/PARED_GAMES_aijyakae
