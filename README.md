# Quiz BackEnd

http://3.37.139.180:9002/api/q

| method | api       | desc     |
|--------|:----------|:---------|
| GET    | /category | 카테고리 조회  |
| POST   | /start    | 문제 시작    |
| GET    | /{id}     | 문제 단건 조회 |


## SAMPLE

- Request :  
```http request
GET http://3.37.139.180:9002/api/q/category
```

- Response :  
```json
[
  {
    "id": 3,
    "name": "DVA-C01",
    "p_id": 1,
    "question_cnt": 3,
    "success_cnt": 2
  },
  {
    "id": 4,
    "name": "데이터분석",
    "p_id": 2,
    "question_cnt": 60,
    "success_cnt": 45
  }
]
```

---

- Request  
```http request
POST http://localhost:9002/api/q/start
{
    "user_id": 1,
    "category_id": 3,
    "question_cnt": 3
}
```

- Response  
```json
[
    "1",
    "5",
    "7"
]
```

---

- Request
```http request
GET http://3.37.139.180:9002/api/q/5
```

- Response
```json
{
  "id": 5,
  "text": "An application extracts metadata from files uploaded to an S3 bucket using Lambda functions; the information is then saved in Amazon DynamoDB. The program begins to behave strangely, and the developer want to investigate the Lambda function code's logs for faults.\n\nWhere would the Developer look for logs based on this system configuration?",
  "image": null,
  "seq": 1,
  "use_yn": "Y",
  "category_id": 3
}
```