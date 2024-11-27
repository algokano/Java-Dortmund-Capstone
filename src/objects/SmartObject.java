package objects;

public abstract class SmartObject {
    private String name;
    private int consumptionRate;
    private boolean isOn;
    private boolean isPowered;

    public SmartObject(String name, int consumptionRate) {
        this.name = name;
        this.consumptionRate = consumptionRate;
        this.isOn = false;
        this.isPowered = false;
    }

    public String getName() {
        return name;
    }

    public int getConsumptionRate() {
        return isOn ? consumptionRate : 0;
    }

    public void turnOn() {
        isOn = true;
        isPowered = false;
        System.out.println(name + " is now ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(name + " is now OFF.");
    }

    public boolean isOn() {
        return isOn;
    }

    public abstract void simulate();
    
    public void setConsumptionRate(int consumptionRate) {
        if (consumptionRate >= 0) {
            this.consumptionRate = consumptionRate;
            System.out.println(name + " consumption rate adjusted to: " + consumptionRate);
        } else {
            System.out.println("Consumption rate cannot be negative.");
        }
    }
    
    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
        System.out.println(name + (isPowered ? " is powered." : " is not powered."));
    }
}