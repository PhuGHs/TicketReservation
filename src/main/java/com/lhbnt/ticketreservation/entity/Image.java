package com.lhbnt.ticketreservation.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "images", indexes = {
        @Index(name = "idx_resource_id", columnList = "resourceId")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, name = "file_name")
    private String fileName;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Lob
    @Column(nullable = false, name = "image_data")
    private byte[] imageData;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "image_order")
    private Integer imageOrder = 0;

    @Column(name = "resource_id")
    private UUID resourceId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
