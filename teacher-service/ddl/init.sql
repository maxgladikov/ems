CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.task
(
    id bigint NOT NULL DEFAULT nextval('app.task_id_seq'::regclass),
    student_id bigint NOT NULL,
    teacher_id bigint NOT NULL,
    task text COLLATE pg_catalog."default" NOT NULL,
    checked boolean NOT NULL DEFAULT false,
    mark bigint NOT NULL,
    CONSTRAINT task_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.task
    OWNER to postgres;