package com.group3.demoDuLich.repository;

import com.group3.demoDuLich.models.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepo extends PagingAndSortingRepository<Blog, Integer> {
    List<Blog> findAllByCategoryId(int id);

    @Query(nativeQuery = true, value = "SELECT blog.*, count(comment.id) as sl FROM blogDuLich.comment join blog_comments on blog_comments.comments_id = comment.id \n" +
            "right join blog on blog.id = blog_comments.blog_id \n" +
            "where category_id=:idC \n" +
            "group by blog.id \n" +
            "order by sl desc limit :index , :count")
    List<Blog> findTopByComment(@Param("idC") int idC, @Param("index") int index, @Param("count") int count);
 @Query(nativeQuery = true, value = "SELECT * FROM blogDuLich.blog where title like concat('%',:title,'%')")
    List<Blog> search(@Param("title") String title);

 @Query(nativeQuery = true, value = "SELECT avg(count) FROM blogDuLich.comment join blog_comments on blog_comments.comments_id= comment.id where blog_id=:blog_id ")
 Integer rating(@Param("blog_id") int blog_id);
}
