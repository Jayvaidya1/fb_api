/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jay
 * Created: 11 Jun, 2018
 */

CREATE TABLE fb_api.configuration_mstr
(
  id bigserial NOT NULL,
  access_token character varying,
  is_active boolean,
  details character varying,
  CONSTRAINT configuration_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fb_api.configuration_mstr
  OWNER TO deck;
COMMENT ON TABLE fb_api.configuration_mstr
  IS 'all system configuration store here';

CREATE TABLE fb_api.fb_operation_mstr
(
  id bigserial NOT NULL,
  yt_channel character varying,
  fb_ref_id character varying,
  last_access bigint,
  CONSTRAINT fb_op_mstr_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fb_api.fb_operation_mstr
  OWNER TO deck;
COMMENT ON TABLE fb_api.fb_operation_mstr
  IS 'this is mstr table and all fb-id will store here';

-- Index: fb_api.fb_mstr_fb_id

-- DROP INDEX fb_api.fb_mstr_fb_id;

CREATE INDEX fb_mstr_fb_id
  ON fb_api.fb_operation_mstr
  USING btree
  (id);

CREATE TABLE fb_api.post_mstr
(
  id bigserial NOT NULL,
  fb_id bigint,
  message character varying,
  share_count integer,
  like_count integer,
  post_type character varying,
  created_date bigint,
  post_ref_id character varying,
  link character varying,
  CONSTRAINT post_mstr_pk PRIMARY KEY (id),
  CONSTRAINT post_mstr_fb_id_fk01 FOREIGN KEY (fb_id)
      REFERENCES fb_api.fb_operation_mstr (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fb_api.post_mstr
  OWNER TO deck;
COMMENT ON TABLE fb_api.post_mstr
  IS 'each post data store here against every page id';

-- Index: fb_api.fki_post_mstr_fb_id_fk01

-- DROP INDEX fb_api.fki_post_mstr_fb_id_fk01;

CREATE INDEX fki_post_mstr_fb_id_fk01
  ON fb_api.post_mstr
  USING btree
  (fb_id);


CREATE TABLE fb_api.comment_mstr
(
  id bigserial NOT NULL,
  post_id bigint,
  messages character varying,
  created_date bigint,
  comment_ref_id character varying,
  CONSTRAINT comment_mstr_pk PRIMARY KEY (id),
  CONSTRAINT comment_mstr_fk01 FOREIGN KEY (post_id)
      REFERENCES fb_api.post_mstr (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fb_api.comment_mstr
  OWNER TO deck;
COMMENT ON TABLE fb_api.comment_mstr
  IS 'all comment store here against each post';

-- Index: fb_api.fki_comment_fk01

-- DROP INDEX fb_api.fki_comment_fk01;

CREATE INDEX fki_comment_fk01
  ON fb_api.comment_mstr
  USING btree
  (post_id);

