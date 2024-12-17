package com.cgr.base.infrastructure.persistence.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.base.infrastructure.persistence.entity.Menu.Menu;

public interface IMenuRepositoryJpa extends JpaRepository<Menu,Long> {


}
