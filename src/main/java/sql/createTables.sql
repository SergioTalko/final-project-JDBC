CREATE TABLE HOTELS (
  ID            NUMBER,
  CONSTRAINT HOTEL_ID_PK PRIMARY KEY (ID),
  HOTEL_NAME    NVARCHAR2(50)  NOT NULL,
  HOTEL_COUNRTY NVARCHAR2(50)  NOT NULL,
  HOTEL_CITY    NVARCHAR2(50)  NOT NULL,
  HOTEL_STREET  NVARCHAR2(100) NOT NULL
);

CREATE TABLE USERS (
  ID            NUMBER,
  CONSTRAINT USER_ID_PK PRIMARY KEY (ID),
  USER_NAME     NVARCHAR2(50) NOT NULL,
  USER_PASSWORD NVARCHAR2(50) NOT NULL,
  USER_COUNRTY  NVARCHAR2(50) NOT NULL,
  USER_TYPE     NVARCHAR2(10) NOT NULL CHECK (USER_TYPE IN ('ADMIN', 'USER'))
);

CREATE TABLE ROOMS (
  ID            NUMBER,
  CONSTRAINT ROOM_ID_PK PRIMARY KEY (ID),
  NUMBER_GUESTS NUMBER  NOT NULL,
  PRICE         NUMBER(*, 2),
  BREAKFAST     NVARCHAR2(10) NOT NULL CHECK (BREAKFAST IN ('FALSE', 'TRUE')),
  PETS_ALLOWDED NVARCHAR2(10) NOT NULL CHECK (PETS_ALLOWDED IN ('FALSE', 'TRUE')),
  DATE_FROM     DATE,
  HOTEL_ID      NUMBER,
  CONSTRAINT HOTEL_ID_FK FOREIGN KEY (HOTEL_ID) REFERENCES HOTELS (ID)
);

CREATE TABLE ORDERS (
  ID         NUMBER,
  CONSTRAINT ORDER_ID_PK PRIMARY KEY (ID),
  USER_ID       NUMBER ,
  CONSTRAINT USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
  ROOM_ID       NUMBER ,
  CONSTRAINT ROOM_ID_FK FOREIGN KEY (ROOM_ID) REFERENCES ROOMS(ID),
  DATE_FROM  DATE   NOT NULL,
  DATE_TO    DATE   NOT NULL,
  MONEY_PAID NUMBER(*, 2)

);