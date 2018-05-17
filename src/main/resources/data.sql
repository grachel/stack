INSERT INTO users (id, creationdate, login, password, email) VALUES (1, SYSDATE, 'admin', '$2a$06$ylUpVFUaggdGZWdwR/sv/.5UaE08XEzNoTbUGtOZ7Smdbx5u9C6Nu', 'admin@admin.com');
INSERT INTO users (id, creationdate, login, password, email) VALUES (2, SYSDATE, 'user', '$2a$06$R/f6vIKPFVGoUeijDn.vtucj1xkNvENBVGYm/kFgSs7G6VYNh.zuC', 'user@user.com');
INSERT INTO users (id, creationdate, login, password, email) VALUES (3, SYSDATE, 'test', '$2a$06$ng6bW19hZtosod6GnDLAL.Ll67Fy4E21zmqm/GoXjwvlIllvRj2N.', 'test@test.com');

insert into posts(id, creationDate, score, body, title, user) VALUES (1, SYSDATE, 0, 'content', 'title', 2);
insert into answers(id, creationDate, score, body, user, post) VALUES (1, SYSDATE, 0, 'answer content', 2, 1);
insert into comments(id, creationDate, score, body, user, post, answer) VALUES (1, SYSDATE, 0, 'comment for post', 2, 1, null);
insert into comments(id, creationDate, score, body, user, post, answer) VALUES (2, SYSDATE, 0, 'comment for answer', 2, null, 1);

insert into tags(tag_id, tag) values (1, 'question');
insert into tags(tag_id, tag) values (2, 'some');

insert into posts_tags(id, tag_id) values (1, 1);
insert into posts_tags(id, tag_id) values (1, 2);