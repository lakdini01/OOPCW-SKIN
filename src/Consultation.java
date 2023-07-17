import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Consultation {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;


    public Consultation(LocalDate date, LocalTime startTime, LocalTime endTime, double cost, String notes, Doctor doctor, Patient patient) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.notes = notes;
        this.doctor = doctor;
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
