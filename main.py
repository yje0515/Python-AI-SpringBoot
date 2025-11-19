from idlelib.rpc import response_queue

from fastapi import FastAPI

# 데이터 유효성검사와 설정관리에 사용되는 라이브러리 ( 모델링이 쉽고 강력함)
from pydantic import BaseModel

# 요청과 응답 사이의 특정 작업 수행
# 미들웨어는 모든 요청에 대해 실행되며, 요청을 처리하기 전에 응답을 반환하기 전에 특정 작업을 수행할 수 있음
# 예를들어 로깅, 인증, cors처리, 압축 등
from starlette.middleware.base import  BaseHTTPMiddleware

import logging

# FastAPI app = new FastAPI()
app = FastAPI( # 웹의 시그니처와 환경설정을 담당
    title = "MBC AI Study",         # 앱 제목
    description="Python with web",  # 앱 주석
    version = "0.0.1",              # 앱 버전
    # 보안에 취약한 docs, redoc 접근 안되게 비활성화
    docs_url=None, 
    redoc_url=None
)

# Item 객체 생성 BaseModel 상속받음
class Item(BaseModel): # Item 객체 검증용
    name : str                  # 상품명 : 문자열
    description : str = None    # 상품설명 : 문자열(null)
    price : float               # 가격 : 실수형
    tax : float = None          # 세금 : 실수형(null)

# 로그 남기기
class LoggingMiddleware(BaseHTTPMiddleware):
    logging.basicConfig(level=logging.INFO)
    async  def dispatch(self,request,call_next):
        logging.info(f"Req : {request.method}{request.url}")
        response = await  call_next(request)
        logging.info(f"Status Code : {response.status_code}")
        return response
# 모든 요청에 대해 로그를 남기는 미들웨어 클래스를 사용
app.add_middleware(LoggingMiddleware)

@app.post("/items/{item_id}") # post 메서드 요청 (create)
async def create_item(item:Item):
    # BaseModel은 데이터 모델링을 쉽게 도와주고 유효성 검사도 수행
    # 잘못된 데이터가 들어오면 422 오류코드를 반환
    return item

@app.get("/") # 웹 브라우저에 http://localhost:8001/ -> get 요청
async def read_root(): # async : 비동기
    return {"Hello":"World"}

@app.get("/items/{item_id}") # http://localhost:8001/items/1
async def read_item(item_id:int,q:str = None):
    # q : 쿼리매개변수 (기본값 None)
    # item_id : 상품 번호 -> 경로 매개변수
    return {"item_id":item_id,q:q}

# 서버 실행
# uvicorn main:app --reload --port 8001
# uvicorn : 파이썬 백엔드 가동서버로 main.py의 app이라는 메서드를 사용
# reload : 갱신
# port : 8001번 포트를 사용

# 127.0.0.1:8001/docs 서버에서 도는 메서드들의 테스트를 볼 수 있다 해킹에 취약
# -> postman 사용하자
# 프론트가 없을 때 백엔드 테스트용 프로그램으로 활용하면 된다
