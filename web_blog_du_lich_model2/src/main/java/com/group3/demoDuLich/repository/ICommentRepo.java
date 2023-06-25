package com.group3.demoDuLich.repository;

import com.group3.demoDuLich.models.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentRepo extends PagingAndSortingRepository<Comment, Integer> {
}
