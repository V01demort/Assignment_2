package entity;

import service.Manageable;  // Implements the new interface

public class Manager extends Employee implements Manageable {
    private int teamSize;

    public Manager() {
        super();
        this.teamSize = 0;
    }

    public Manager(int id, String name, double salary, int teamSize) {
        super(id, name, salary);
        if (teamSize < 0) throw new IllegalArgumentException("Team size cannot be negative.");
        this.teamSize = teamSize;
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
    public String getRole() {  // Implements abstract method
        return "Manager";
    }

    @Override
    public void manage() {  // Implements Manageable interface
        System.out.println(getName() + " is managing operations.");
    }

    public void assignTask() {
        System.out.println(getName() + " assigned a task to the team.");
    }

    @Override
    public String toString() {
        return "Manager{" + super.toString() + ", teamSize=" + teamSize + "}";
    }
}