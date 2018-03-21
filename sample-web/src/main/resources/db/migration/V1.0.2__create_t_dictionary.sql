create table t_dictionary
(
   id                   bigint not null auto_increment comment '主键',
   en_name              varchar(30) comment '英文名',
   cn_name              varchar(30) comment '中文名',
   code                 varchar(15) comment '代码',
   entry                varchar(30) comment '词条',
   pin_yin_first_letter    varchar(30) comment '拼音首字母',
   five_pen_first_letter   varchar(30) comment '五笔首字母',
   parent_code           varchar(15) comment '上级代码',
   type                 varchar(1) default '1' comment '类型，1：列表，2：树，9：其他',
   remark               varchar(255) comment '备注',
   mark                 INT(1) DEFAULT 0 COMMENT '标记删除位 默认0 1为删除',
   opt_lock             INT(11) DEFAULT 0 COMMENT '乐观锁，防止并行修改覆盖操作',
   create_time          DATETIME DEFAULT current_timestamp() COMMENT '创建时间',
   update_time          DATETIME DEFAULT NULL COMMENT '更新时间',
   primary key (id)
);



create index Index_t_dictionary_enName on t_dictionary
(
   en_name
);

create index Index_t_dictionary_code on t_dictionary
(
   code
);