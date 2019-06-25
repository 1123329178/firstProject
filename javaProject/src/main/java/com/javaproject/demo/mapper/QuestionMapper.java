package com.javaproject.demo.mapper;

import com.javaproject.demo.dto.QuestionDTO;
import com.javaproject.demo.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by codedrinker on 2019/5/2.
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator," +
            "tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId")Integer userId);

    @Select("select * from question where id =#{id} ")
    Question getById(@Param("id") Integer id);

    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    void update(Question question);

    @Select("select * from question where id =#{id} ")
    Question selectByQuestion(Integer id);
    @Update("update question set view_Count=view_Count+1 where id=#{id}")
    void updateViwe(Question updateQuestion);
}
