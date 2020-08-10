package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.annotation.DataHide;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yangxj
 * @date 2020-07-06 14:51
 */
@TableName("person")
@Data
public class PersonDO {
    @TableId
    private String id;
    private String firstName;
    @DataHide
    private String lastName;

    public PersonDO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }
}
