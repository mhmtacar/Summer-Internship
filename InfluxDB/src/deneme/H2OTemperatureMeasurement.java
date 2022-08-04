package deneme;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

@Measurement(name = "h2o_temperature",database="test",timeUnit = TimeUnit.SECONDS)
public class H2OTemperatureMeasurement {

	@TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "location")
    private String location;

    @Column(name = "degrees")
    private int degrees;

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

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}
