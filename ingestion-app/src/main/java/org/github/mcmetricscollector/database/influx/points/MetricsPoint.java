package org.github.mcmetricscollector.database.influx.points;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;

@Measurement(name = "metrics")
@Data
public class MetricsPoint {

    @Column(timestamp = true)
    private Instant timestamp;

    @Column(tag = true, name = "server_name")
    private String serverName;

    @Column(tag = true, name = "server_type")
    private String serverType;

    @Column(name = "online_players")
    private int onlinePlayers;

    @Column(name = "cpu_usage")
    private double cpuUsage;

    @Column(name = "memory_usage")
    private double memoryUsage;

    @Column
    private double tps1;

    @Column
    private double tps5;

    @Column
    private double tps15;

}
