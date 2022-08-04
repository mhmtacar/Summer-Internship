package deneme;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

@Measurement(name = "h2o_pH",database="test",timeUnit = TimeUnit.SECONDS)
public class H2OPhMeasurement {

	@TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "location")
    private String location;

    @Column(name = "pH")
    private int pH;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getpH() {
        return pH;
    }

    public void setpH(int pH) {
        this.pH = pH;
    }
}
