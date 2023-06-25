package com.group3.demoDuLich.repository;

import com.group3.demoDuLich.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAccountRepo extends PagingAndSortingRepository<User, Integer> {
    User findAccountByUsername(String username);
}
