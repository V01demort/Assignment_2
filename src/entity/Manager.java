package entity;

public class Manager extends Employee {
    private int teamSize;

    public Manager() {
        super();
        this.teamSize = 0;
    }

    public Manager(int id, String name, double salary, int teamSize) {
        super(id, name, salary);
        setTeamSize(teamSize);
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        if (teamSize < 0) throw new IllegalArgumentException("Team size cannot be negative.");
        this.teamSize = teamSize;
    }

    @Override
    public void work() {
        System.out.println(getName() + " is managing the store team.");
    }

    @Override
    public double calculateBonus() {
        return super.calculateBonus() + teamSize * 100;
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    public void assignTask() {
        System.out.println(getName() + " assigned a task to the team.");
    }

    @Override
    public String toString() {
        return "Manager{" + super.toString() + ", teamSize=" + teamSize + "}";
    }
}