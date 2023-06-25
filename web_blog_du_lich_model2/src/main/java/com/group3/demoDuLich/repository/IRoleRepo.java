package com.group3.demoDuLich.repository;

import com.group3.demoDuLich.models.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoleRepo extends PagingAndSortingRepository<Role,Integer> {
}
