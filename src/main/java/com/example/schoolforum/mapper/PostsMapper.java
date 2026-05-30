package com.example.schoolforum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.mybatisflex.core.BaseMapper;
import com.example.schoolforum.pojo.Posts;

/**
 * 帖子表 映射层。
 *
 * @author sugu
 * @since 2026-02-17
 */
@Mapper
public interface PostsMapper extends BaseMapper<Posts> {

    @Update("UPDATE posts SET like_count = like_count - 1 WHERE id = #{id} AND like_count > 0")
    int decrementLikeCountIfPositive(@Param("id") Long id);
}
