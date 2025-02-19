package com.kounrew.CRUD_api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kounrew.CRUD_api.entity.CategoryEntity;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	//Tìm Kiếm theo nội dung tên
	List<CategoryEntity> findByCategoryNameContaining(String name);

	//Tìm kiếm và Phân trang
	Page<CategoryEntity> findByCategoryNameContaining(String name, Pageable pageable);

	Optional<CategoryEntity> findByCategoryName(String name);
}
