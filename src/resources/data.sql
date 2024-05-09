INSERT INTO PRODUCT (NAME, DESCRIPTION, AUTHOR, PRICE,IMAGE_PATH) VALUES ('Don Quixote', 'The final and greatest utterance of the human mind." -- Fyodor Dostoyevsky. A founding work of modern Western literature, Cervantes masterpiece has been translated into more than 60 languages and the novels elderly knight, Don Quixote, and his loyal squire, Sancho Panza, rank among fictions most recognized characters. This monumental parody of chivalric romances and epic of heroic idealism presents a strikingly contemporary narrative that also reflects the historical realities of 17th century Spain','Miguel de Cervantes Saavedra', 100, 'https://coverart.oclc.org/ImageWebSvc/oclc/+-+3584215756_140.jpg');
INSERT INTO PRODUCT (NAME, DESCRIPTION, AUTHOR, PRICE,IMAGE_PATH) VALUES ('Alices adventures in wonderland', 'This edition brings together the complete and unabridged text with more than 70 stunning illustrations by Robert Ingpen, each reflecting his unique style and extraordinary imagination in visualising this enchanting story.','Lewis Carroll', 50, 'https://coverart.oclc.org/ImageWebSvc/oclc/+-+9826578756_140.jpg');
INSERT INTO PRODUCT (NAME, DESCRIPTION, AUTHOR, PRICE,IMAGE_PATH) VALUES ('The adventures of Tom Sawyer', 'The adventures of a mischievous young boy and his friends growing up in a Mississippi River town in the nineteenth century. Who could forget the pranks, the adventures, the sheer fun of Tom Sawyer? Its something every child should experience and every child will love. From Toms sly trickery with the whitewashed fence, when he cleverly manipulates everyone so they happily do his work for him, to his and Becky Thatchers calamities in Bat Cave, the enjoyment just never ends','Mark Twain', 25, 'https://coverart.oclc.org/ImageWebSvc/oclc/+-+04429400_140.jpg');

INSERT INTO BOOKINGSTATUS (NAME) VALUES ('SUBMITTED');
INSERT INTO BOOKINGSTATUS (NAME) VALUES ('REJECTED');
INSERT INTO BOOKINGSTATUS (NAME) VALUES ('APPROVED');
INSERT INTO BOOKINGSTATUS (NAME) VALUES ('CANCELLED');
INSERT INTO BOOKINGSTATUS (NAME) VALUES ('IN_DELIERY');

INSERT INTO ROLE (NAME) VALUES ('ADMIN');
INSERT INTO ROLE (NAME) VALUES ('MANAGER');
INSERT INTO ROLE (NAME) VALUES ('CUSTOMER');

INSERT INTO USERS (NAME, ADDRESS, EMAIL, PHONE, ROLE_ID, LOGIN, PASSWORD) VALUES ('Sebastian','street 48','sarbe@gmail.com', '3108888001',(SELECT ROLE_ID FROM ROLE WHERE NAME = 'CUSTOMER'), 'sarbelaez','Test123');
INSERT INTO USERS (NAME, ADDRESS, EMAIL, PHONE, ROLE_ID, LOGIN, PASSWORD) VALUES ('Andrea','street 50','andre@gmail.com', '3108888002',(SELECT ROLE_ID FROM ROLE WHERE NAME = 'MANAGER'), 'andriucar','Test321');
INSERT INTO USERS (NAME, ADDRESS, EMAIL, PHONE, ROLE_ID, LOGIN, PASSWORD) VALUES ('David','street 51','david@gmail.com', '3108888003',(SELECT ROLE_ID FROM ROLE WHERE NAME = 'ADMIN'), 'DAVID','Test555');

INSERT INTO BOOKING (USER_ID,PRODUCT_ID,DELIVERY_ADDRESS,DELIVERY_DATE,DELIVERY_TIME,BOOKINGSTATUS_ID,QUANTITY) VALUES ((SELECT USER_ID FROM USERS WHERE NAME = 'Sebastian'),(SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Don Quixote'),(SELECT ADDRESS FROM USERS WHERE NAME = 'Sebastian'),'2024-04-29', '13:30',(SELECT BOOKINGSTATUS_ID FROM BOOKINGSTATUS WHERE NAME = 'SUBMITTED'),1);
INSERT INTO BOOKING (USER_ID,PRODUCT_ID,DELIVERY_ADDRESS,DELIVERY_DATE,DELIVERY_TIME,BOOKINGSTATUS_ID,QUANTITY) VALUES ((SELECT USER_ID FROM USERS WHERE NAME = 'Sebastian'),(SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Don Quixote'),(SELECT ADDRESS FROM USERS WHERE NAME = 'Sebastian'),'2024-04-29', '13:30',(SELECT BOOKINGSTATUS_ID FROM BOOKINGSTATUS WHERE NAME = 'CANCELLED'),1);

INSERT INTO BOOKSTORE (PRODUCT_ID, AVAILABLE_QTY, BOOKED_QTY, SOLD_QTY) VALUES ((SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Don Quixote'),50, 10,20);
INSERT INTO BOOKSTORE (PRODUCT_ID, AVAILABLE_QTY, BOOKED_QTY, SOLD_QTY) VALUES ((SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Alices adventures in wonderland'),5, 4,3);
--INSERT INTO BOOKSTORE (PRODUCT_ID, AVAILABLE_QTY, BOOKED_QTY, SOLD_QTY) VALUES ((SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Don Quixote'),50, (SELECT COUNT (PRODUCT_ID) FROM BOOKING WHERE PRODUCT_ID = (SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Don Quixote') AND BOOKINGSTATUS_ID = (SELECT BOOKINGSTATUS_ID FROM BOOKINGSTATUS WHERE NAME = 'APPROVED')),(SELECT COUNT (PRODUCT_ID) FROM BOOKING WHERE PRODUCT_ID = (SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Don Quixote') AND BOOKINGSTATUS_ID = (SELECT BOOKINGSTATUS_ID FROM BOOKINGSTATUS WHERE NAME = 'IN_DELIERY')));
--INSERT INTO BOOKSTORE (PRODUCT_ID, AVAILABLE_QTY, BOOKED_QTY, SOLD_QTY) VALUES ((SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Alices adventures in wonderland'),100, (SELECT COUNT (PRODUCT_ID) FROM BOOKING WHERE PRODUCT_ID = (SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Alices adventures in wonderland') AND BOOKINGSTATUS_ID = (SELECT BOOKINGSTATUS_ID FROM BOOKINGSTATUS WHERE NAME = 'APPROVED')),(SELECT COUNT (PRODUCT_ID) FROM BOOKING WHERE PRODUCT_ID = (SELECT PRODUCT_ID FROM PRODUCT WHERE NAME = 'Alices adventures in wonderland') AND BOOKINGSTATUS_ID = (SELECT BOOKINGSTATUS_ID FROM BOOKINGSTATUS WHERE NAME = 'IN_DELIERY')));


