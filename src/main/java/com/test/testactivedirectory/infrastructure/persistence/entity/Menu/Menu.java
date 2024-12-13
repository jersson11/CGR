package com.test.testactivedirectory.infrastructure.persistence.entity.Menu;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String icon;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SubMenuEntity> children = new ArrayList<>();

    public Menu() {
    }

    public Menu(Long id, String title, String subtitle, String type, String icon, List<SubMenuEntity> children) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.type = type;
        this.icon = icon;
        this.children = children;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Menu other = (Menu) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (children == null) {
            if (other.children != null)
                return false;
        } else if (!children.equals(other.children))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Menu [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", type=" + type + ", icon=" + icon
                + ", children=" + children + "]";
    }

}
