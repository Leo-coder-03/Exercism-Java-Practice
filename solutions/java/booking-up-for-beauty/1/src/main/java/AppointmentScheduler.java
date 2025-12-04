import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class AppointmentScheduler {
    public LocalDateTime schedule(String appointmentDateDescription) {
       DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
       LocalDateTime dateTime = LocalDateTime.parse(appointmentDateDescription,parser);
       return dateTime;
    }

    public boolean hasPassed(LocalDateTime appointmentDate) {
        LocalDateTime date1 = LocalDateTime.now();
        return appointmentDate.isBefore(date1);
    }

    public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
       int hour = appointmentDate.getHour();
        return hour>=12 && hour<18;
    }

    public String getDescription(LocalDateTime appointmentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, YYYY, 'at' h:mm a");
        return "You have an appointment on "+ appointmentDate.format(formatter)+".";
    }

    public LocalDate getAnniversaryDate() {
       int year = LocalDate.now().getYear();
        return LocalDate.of(year,9,15);
    }
}
