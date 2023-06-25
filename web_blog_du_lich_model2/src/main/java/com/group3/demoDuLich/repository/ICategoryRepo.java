package com.group3.demoDuLich.repository;

import com.group3.demoDuLich.models.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepo extends PagingAndSortingRepository<Category, Integer> {
}
