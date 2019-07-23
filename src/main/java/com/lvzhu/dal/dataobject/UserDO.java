package com.lvzhu.dal.dataobject;

import java.io.Serializable;
import lombok.Data;

/**
* 
*/
@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    private Integer id;

    /**
    * 
    */
    private String name;

    /**
    * 
    */
    private Integer age;

    /**
    * 
    */
    private Integer type;

}

