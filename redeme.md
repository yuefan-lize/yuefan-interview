# 数据库脚本

create table account
(
account_id int primary key auto_increment comment '主键ID',
unfilled_vacancies int default 0 comment '余额',
create_time datetime default current_timestamp,
update_time datetime default current_timestamp on update current_timestamp
);

# 数据库连接信息

    url: jdbc:mysql://localhost:3306/yuefan-lize?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456

# 环境信息

JDK版本使用的是17，springboot版本使用的是3.4.0

# 接口说明

所有的接口都保存在[yuefan-lize.http](yuefan-lize.http)，使用IDEA直接打开，运行左侧运行按钮即可。
