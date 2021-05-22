insert into user (address,email,phone) values (
'giza',
'heba.elgreatly@gmail.com',
'01000000000'
);

insert into item (item_Name,price,available) values (
'pizza',
50.75,
true
);

insert into item (item_Name,price,available) values (
'pasta',
60.0,
true
);

insert into item (item_Name,price,available) values (
'shrimp',
260.0,
true
);

insert into item (item_Name,price,available) values (
'fries',
30.50,
true
);

insert into orders (user_id,ordered,created_on,total) values(
(select user_id from user where email='heba.elgreatly@gmail.com'),
0,
null,
0.0
);

insert into orders (user_id,ordered,created_on,total) values(
(select user_id from user where email='heba.elgreatly@gmail.com'),
1,
NOW(),
300.0
);

insert into cart_item (item_quantity,item_id,order_id)
values (
2,
(select item_id from item where item_name = 'pizza'),
(select order_id from orders where ordered=0 and user_id = (select user_id from user where email='heba.elgreatly@gmail.com'))
);

insert into cart_item (item_quantity,item_id,order_id)
values (
1,
(select item_id from item where item_name = 'shrimp'),
(select order_id from orders where ordered=0 and user_id = (select user_id from user where email='heba.elgreatly@gmail.com'))
);

insert into cart_item (item_quantity,item_id,order_id)
values (
3,
(select item_id from item where item_name = 'pasta'),
(select order_id from orders where ordered=0 and user_id = (select user_id from user where email='heba.elgreatly@gmail.com'))
);


insert into cart_item (item_quantity,item_id,order_id)
values (
5,
(select item_id from item where item_name = 'fries'),
(select order_id from orders where ordered=1 and user_id = (select user_id from user where email='heba.elgreatly@gmail.com'))
);