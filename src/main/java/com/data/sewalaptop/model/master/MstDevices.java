package com.data.sewalaptop.model.master;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mst_device", schema = "public")
public class MstDevices {
    @Id
    @SequenceGenerator(name = "mst_device_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_device_seq")
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_name")
    private String deviceName;
}
