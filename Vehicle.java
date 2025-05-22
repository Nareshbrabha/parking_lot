public class Vehicle {
    String reg;
    String color;
    String type;

    public Vehicle() {
    }

    public Vehicle(String type, String color, String reg) {
        this.reg = reg;
        this.color = color;
        this.type = type;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "license='" + reg + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
