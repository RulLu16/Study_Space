package com.clone.insta.repository;

import com.clone.insta.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface imageRepository extends JpaRepository<Image, Integer> {

}