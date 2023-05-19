package com.spaghettierol.demo2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String title;
    private int level;
    private boolean optional;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name = "module_convenor",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "convenor_id"))
    private Set<Convener> conveners;
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Session> sessions;

}
