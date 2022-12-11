CREATE TABLE customer_details (
    id uuid NOT NULL,
    cibil_score bytea NOT NULL,
    name character varying(255) ,
    is_active boolean default true,
    CONSTRAINT customer_details_pkey PRIMARY KEY (id)
);
