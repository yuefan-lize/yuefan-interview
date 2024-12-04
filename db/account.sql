create table account
(
    account_id         int primary key auto_increment comment '主键ID',
    unfilled_vacancies int      default 0 comment '余额',
    create_time        datetime default current_timestamp,
    update_time        datetime default current_timestamp on update current_timestamp
);