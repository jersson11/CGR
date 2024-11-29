package com.test.testactivedirectory.infrastructure.persistence.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.testactivedirectory.infrastructure.persistence.entity.Menu.Menu;

public interface MenuRepositoryJpa extends JpaRepository<Menu,Long> {


}
