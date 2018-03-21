-- -----------------------
-- --用户表 t_user -------
-- -----------------------
CREATE TABLE t_user (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL COMMENT '用户姓名',
    age INT(3) DEFAULT 0 COMMENT '用户年龄',
    status INT(1) DEFAULT 0 COMMENT '用户状态',
    mark INT(1) DEFAULT 0 COMMENT '标记删除位 默认0 1为删除',
    opt_lock INT(11) DEFAULT 0 COMMENT '乐观锁，防止并行修改覆盖操作',
    description VARCHAR(50) DEFAULT NULL COMMENT '用户描述/备注',
    addition VARCHAR(100) DEFAULT NULL COMMENT '用户扩展字段，通常用来对指定行做扩展，适用于存储一些配置信息，不能通过该字段高效查询。请注意。',
    create_time DATETIME DEFAULT current_timestamp() COMMENT '创建时间',
    update_time DATETIME DEFAULT NULL COMMENT '更新时间'
);