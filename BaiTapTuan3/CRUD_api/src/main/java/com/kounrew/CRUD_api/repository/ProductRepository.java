package com.kounrew.CRUD_api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kounrew.CRUD_api.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	//Tìm Kiếm theo nội dung tên
	List<ProductEntity> findByProductNameContaining(String name);

	//Tìm kiếm và Phân trang
	Page<ProductEntity> findByProductNameContaining(String name, Pageable pageable);

	Optional<ProductEntity> findByProductName(String name);

	Optional<ProductEntity> findByCreateDate(Date createAt);
}
