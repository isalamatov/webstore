INSERT INTO products (product_id, name, unit_price, description, manufacturer,category, units_in_stock, units_in_order, discounted, condition)
VALUES ('P1234', 'iPhone 14 Pro','999','Last generation apple phone with 3 cameras and front camera autofocus (very usefull)','Apple','Smartphone','1000','0','false','new')
ON CONFLICT DO NOTHING;

INSERT INTO products (product_id, name, unit_price, description, manufacturer,category, units_in_stock, units_in_order, discounted, condition)
VALUES ('P1235', 'Dell Inspirion','1599','Dell Inspiron 14-inch Laptop (Black) with 3 rd Generation Intel Core processors','Dell','Laptop','1000','0','false','new')
ON CONFLICT DO NOTHING ;

INSERT INTO products (product_id, name, unit_price, description, manufacturer,category, units_in_stock, units_in_order, discounted, condition)
VALUES ('P1236', 'Nexus 7','300','Google Nexus 7 is the lightest 7 inch tablet With a quad - core Qualcomm Snapdragon™ S4 Pro processor','Google','Tablet','1000','0','false','new')
ON CONFLICT DO NOTHING ;
