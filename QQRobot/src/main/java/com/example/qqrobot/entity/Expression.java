package com.example.qqrobot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName expression
 */
@TableName(value ="expression")
@Data
public class Expression implements Serializable {
    /**
     * 
     */
    private String file;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private Integer id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}