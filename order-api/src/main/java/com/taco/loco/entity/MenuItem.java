package com.taco.loco.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MENU_ITEM")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    private Timestamp updatedAt;

    public MenuItem(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
