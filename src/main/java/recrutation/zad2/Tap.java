package recrutation.zad2;

public class Tap {
    private double flowSpeed;
    private double temperature;

    public Tap(double flowSpeed, double temperature) {
        this.flowSpeed = flowSpeed;
        this.temperature = temperature;
    }

    public double getFlowSpeed() {
        return flowSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return String.format("Tap: flow speed: %.5f, temperature: %.5fC", flowSpeed, temperature);
    }
}
