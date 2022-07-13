package com.example.security.springsecurity.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
public class CommonVO implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 20)
    private LocalDateTime updatedAt;

    @Getter
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isEnable = true;
}
