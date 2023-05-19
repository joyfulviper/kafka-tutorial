# spring-boot + docker + kafka tutorial

1. 다음과 같이 docker-compose 파일 작성 후 docker-compose up -d 명령어로 kafka 서버 키기(각 요소가 무엇인지 알고 싶고 또 다른 설정을 하고 싶다면 다음의 링크 참고 https://www.baeldung.com/ops/kafka-docker-setup)
```aidl
version: '2'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
```
2. consumer와 producer에 관한 설정 하기
```aidl
spring:
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer      
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      properties:
        retries: 3
        retry.backoff.ms: 1000
    # properties 부분은 메시지 큐에 전송이 실패할 경우 3회 시도하고 시도 마다 1000ms씩 늦춰서 시도한다는 뜻
    consumer:
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer      
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer      
      auto-offset-reset: earliest
      properties:
        spring.json.trusted.packages: com.example.kafaktutorial
      # consumer의 properties 부분은 메시지 큐가 객체를 받을 때 신뢰할 수 있는 객체라는 설정 (설정 안하면 역직렬화 안됨)
```
3. 간단한 메시지를 주고 받는 코드는 test 패키지에 있음
4. KafkaConfig 클래스는 컨슈머가 처리할 예외 로직이 담겨있음(예외 발생시 3회 재시도 후 실패시 DLT 큐에 넣기)
5. 나머지 구현 내용은 코드 참조