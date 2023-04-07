-- TRUNCATE addresses, shipping_details, products, customers, carts, orders;

CREATE TABLE IF NOT EXISTS public.addresses
(
    adress_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    door_no character varying(254),
    street_name character varying(254),
    area_name character varying(254),
    state character varying(254),
    country character varying(254),
    zip_code character varying(254),
    CONSTRAINT addresses_pkey PRIMARY KEY (adress_id)
);

CREATE TABLE IF NOT EXISTS public.products
(
    product_id character varying(254) NOT NULL,
    name character varying(254) NOT NULL,
    unit_price numeric(20,2) NOT NULL,
    description character varying(254),
    manufacturer character varying(254),
    category character varying(254),
    units_in_stock numeric(10,0),
    units_in_order numeric(10,0),
    discounted boolean,
    condition character varying(254),
    CONSTRAINT products_pkey PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS public.customers
(
    customer_id character varying(254) NOT NULL,
    name character varying(254) NOT NULL,
    phone_number character varying(254),
    billing_address_id bigint,
    CONSTRAINT customers_pkey PRIMARY KEY (customer_id),
    CONSTRAINT customers_fk_billing_address_id_adresses_adress_id FOREIGN KEY (billing_address_id)
        REFERENCES public.addresses (adress_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.shipping_details
(
    shipping_detail_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(254),
    shipping_date date,
    shipping_address_id bigint,
    CONSTRAINT shipping_details_pkey PRIMARY KEY (shipping_detail_id),
    CONSTRAINT fk_shipping_address_addresses_adress_id FOREIGN KEY (shipping_address_id)
        REFERENCES public.addresses (adress_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.carts
(
    cart_id character varying(254) NOT NULL,
    product_id character varying(254) NOT NULL,
    quantity bigint NOT NULL,
    CONSTRAINT carts_pkey PRIMARY KEY (cart_id),
    CONSTRAINT fk_product_id_products_product_id FOREIGN KEY (product_id)
        REFERENCES public.products (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.orders
(
    order_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    cart_id character varying(254) NOT NULL,
    customer_id character varying(254) NOT NULL,
    shipping_detail_id bigint NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
    CONSTRAINT fk_cart_id_cats_cart_id FOREIGN KEY (cart_id)
        REFERENCES public.carts (cart_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_customer_id_customers_customer_id FOREIGN KEY (customer_id)
        REFERENCES public.customers (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_shipping_detail_id_shipping_details_shipping_detail_id FOREIGN KEY (shipping_detail_id)
        REFERENCES public.shipping_details (shipping_detail_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

