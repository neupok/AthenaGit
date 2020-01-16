/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.domrf.athenegit.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

/**
 *
 * @author al.neupokoev
 */
@Entity
@Table(name = "RC_DDLLOG", schema = "OD")
public class DDLEvent {
    @Id
    @Column(name = "event_id")
    Long id;
    @Column(name = "ddl_timestamp")
    Date ddlTimestamp;
    @Column(name = "sysevent")
    String sysEvent;
    @Column(name = "login_user")
    String loginUser;
    @Column(name = "instance_num")
    Long instanceNum;
    @Column(name = "database_name")
    String databaseName;
    @Column(name = "dict_obj_name")
    String objName;
    @Column(name = "dict_obj_type")
    String objType;
    @Column(name = "dict_obj_owner")
    String owner;
    @Column(name = "host")
    String host;
    @Column(name = "ip")
    String ip;
    @Column(name = "os_user")
    String osUser;
    @Column(name = "obj_current_ddl")
    String currentDDL;
}
