<h1>MQ를 이용한 대량의 데이터 처리</h1>

<h3>1. 도입 목적</h3>

- 비동기, 확장성, 보증성
- 부하 분산 처리
- 데이터 손실 방지

<br/>

<h3>2. 기존 처리 방식</h3>

![image](https://github.com/user-attachments/assets/e01c9def-7943-4684-b7a2-4242f0e065b7)
> 요청이 들어오면 중간 과정 없이 그대로 서버가 받아서 처리하는 구조
- 처리해야 할 데이터가 많아지니 과부하 발생
- 그에 따라 정상적으로 처리되지않고 유실되는 데이터 발생

<br/>

<h3>3. 개선 방안</h3>

![111123](https://github.com/user-attachments/assets/6ecc9f67-4951-4ba6-9bc3-3935d626cec1)

> AMQP 도입, 중간에서 분산 처리하는 구조로 개선
- 처리해야 할 데이터를 분산해줌으로써, 프로세스 안정화
- 결론적으로, 전송된 메세지의 보장성 제공
  
<br/>

<h3>4. 테스트 결과</h3>

<h4>1. Apache JMeter을 사용하여 부하 테스트 진행</h4>
- 초당 5000건, 60초간 총 300,000건의 Request 

<br/>
<br/>

<h4>2. AMQP 도입 전</h4>

![image](https://github.com/user-attachments/assets/ce7301a9-1e7a-47f4-ae16-1fe103d518e6)
> 프로세스 과부하로 인하여 요청 전체를 처리하지 못 하고 Timeout이 발생한 데이터가 생김
  
<br/>

<h4>3. AMQP 도입 후</h4>

![image](https://github.com/user-attachments/assets/cd4f2f4c-2210-4828-ad8c-a11de964364c)
> 리스닝중인 프로세스가 과부하가 발생하지 않게 관리하여 유실된 데이터 없이 전체 처리 완료
