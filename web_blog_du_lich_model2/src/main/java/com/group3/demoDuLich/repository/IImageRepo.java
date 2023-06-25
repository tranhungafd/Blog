package com.group3.demoDuLich.repository;

import com.group3.demoDuLich.models.Image;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IImageRepo extends PagingAndSortingRepository<Image, Integer> {
}
