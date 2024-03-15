package com.mpt.MadhyaPradeshTourism.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private String description ;

    @Column(nullable = false)
    private Date startDate ;

    @Column(nullable = false)
    private Date endDate ;

    @Column(nullable = false)
    private int totalDay ;

    @Column(nullable = false)
    private int totalNight ;

    @Column(nullable = false)
    private int totalSeat ;

    @Column(nullable = false)
    private int availableSeat ;

    @Column(nullable = false)
    private int bookedSeats ;

    @Column(columnDefinition = "LONGBLOB")
    @Basic(fetch = FetchType.LAZY)
    private byte[] image ;

    @OneToMany
    private List<Location> pickupLocation ;

    @OneToMany
    private List<Location> visitingLocations ;
}
