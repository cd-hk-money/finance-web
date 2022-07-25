# 🔁 돈다 - 웹 모듈

> 재무제표 기반 주식가격 평가 서비스 프로젝트 **'돈다'**

```
1. 돈다 단다. (동사) 저울로 무게를 헤아린다. “돈”은 아래아 발음.
2. 돈다. (형용사) 꿀이나 설탕의 맛과 같다.
3. 돈다. (동사) 물체가 일정한 축을 중심으로 원을 그리면서 움직이다.
4. 돈다. (동사) 기능이나 체제가 제대로 작용하다.
5. 돈(money) 다(all)
```

<br><br>

## 통신 개요도

![image](https://user-images.githubusercontent.com/68547545/175465406-22f034a7-96c7-4c36-b420-e3a56cb516b6.png)

```
1. 각 instance를 실행하여 Eureka Server에 등록
2. Client에서 API Gateway로 request
(3,4) API gateway에서 수신한 request를 어떤 instance에서 처리할 수 있을지  Service Discovery에서 찾음
5. API gateway가 client로부터 받은 request에 적합한 service instance에 request 전달
6. Service instance로부터 받은 response result 전달
```

## 시스템 구조도

![image](https://user-images.githubusercontent.com/68547545/180676735-4a1de05b-9af0-444d-8842-d3e97e122756.png)

## 클래스 다이어그램

### InterestGroup 관심종목그룹

![image](https://user-images.githubusercontent.com/68547545/180676628-bfbf9d3d-b76c-44b3-ba89-d6a099000f89.png)


### Member 회원

![image](https://user-images.githubusercontent.com/68547545/180676664-edd39be3-7196-4cb3-8b4c-4f527a929bb3.png)

### Dto, VO

![image](https://user-images.githubusercontent.com/68547545/180676679-a2aff476-98c9-4357-824c-83b31d6e4d1c.png)


## API

### InterestGroup 관심종목그룹

![image](https://user-images.githubusercontent.com/68547545/180676460-f5667fbf-8a09-492a-8a1e-376a38d9e3e1.png)
![image](https://user-images.githubusercontent.com/68547545/180676492-0791704a-8d52-4889-a96e-a71b1905dc2d.png)
![image](https://user-images.githubusercontent.com/68547545/180676500-bfebf8cc-1524-4947-bd14-44dd752dacf9.png)
![image](https://user-images.githubusercontent.com/68547545/180676512-789db0f8-a081-4dbf-b8f6-df5e9245628c.png)
![image](https://user-images.githubusercontent.com/68547545/180676523-6d432c3c-09cc-43fa-99e9-c1d6e42231d0.png)
![image](https://user-images.githubusercontent.com/68547545/180676546-89063085-8f69-43b8-bace-15e869cb2d91.png)
![image](https://user-images.githubusercontent.com/68547545/180676561-04dcbcbc-d704-4050-97c9-ab6c4408cf34.png)

### Member 회원, 알림 설정

![image](https://user-images.githubusercontent.com/68547545/180676584-bee06ab4-8f3c-4ecd-899b-3cbc4e068c07.png)
![image](https://user-images.githubusercontent.com/68547545/180676587-94fd505c-bc63-4e53-9646-77f0627b51ff.png)
![image](https://user-images.githubusercontent.com/68547545/180676590-884327e5-6fe6-4b44-9078-1bc657a871c8.png)





`all updated by 장재영 2022/07/25`
