INSERT INTO client_app
(client_id, access_token_validity_seconds, additional_information, authorities, authorized_grant_types, client_name, client_secret, client_type, redirect_uris, refresh_token_validity_seconds, resource_ids, `scope`, auto_approve_scope)
VALUES('auth-server-admin', 3600, '{}', '[]', '["implicit", "refresh_token", "password", "client_credentials", "authorization_code"]', '认证服务器管理后台', 'ae4e72ed-93ef-4053-96ff-d3c5efa266b5', 0, '["http://127.0.0.1:8000"]', 2592000, '[]', '["auth-server-admin"]', NULL);

INSERT INTO `role`
(id, app_id, code, name)
VALUES(1, 'auth-server-admin', 'ROLE_ADMIN', '管理员');

INSERT INTO `user`
(id, password, status, username)
VALUES(1, 'E10ADC3949BA59ABBE56E057F20F883E', 0, 'admin');

INSERT INTO clientdb.user_role(id, role_id, user_id)VALUES(1, 1, 1);
