package HealthCareDAO101.dao;

import HealthCareDAO101.Model.Appointment;
import HealthCareDAO101.jdbc.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public void createAppointment(Appointment appointment) throws SQLException{
        String sql = "INSERT INTO Appointments(appointmentId, patientId, doctorId, appointmentDate, notes) VALUES(?,?,?,?,?)";
        try(Connection  conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointment.getAppointmentId());
            ps.setInt(2, appointment.getPatientId());
            ps.setInt(3, appointment.getDoctorId());
            ps.setString(4, appointment.getAppointmentDate());
            ps.setString(5, appointment.getNotes());
            ps.executeUpdate();
        }
    }

    public Appointment getAppointmentById(int appointmentId) throws SQLException{
        String sql = "SELECT * FROM Appointments WHERE appointmentId = ?";
        Appointment appointment = new Appointment();
        try(Connection  conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointmentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                appointment.setAppointmentId(rs.getInt("appointmentId"));
                appointment.setPatientId(rs.getInt("patientId"));
                appointment.setDoctorId(rs.getInt("doctorId"));
                appointment.setAppointmentDate(rs.getString("appointmentDate"));
                appointment.setNotes(rs.getString("notes"));
                return appointment;
            }
        }
        return null;
    }

    public void updateApointment(Appointment appointment)throws SQLException{
        String sql = "UPDATE Appointments SET patientId = ?, doctorId=?, appointmentDate=?, notes = ? WHERE appointmentId = ?";
        try(Connection  conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setString(3, appointment.getAppointmentDate());
            ps.setString(4, appointment.getNotes());
            ps.setInt(5, appointment.getAppointmentId());
            ps.executeUpdate();
        }
    }

    public void deleteAppointment(int appointmentId) throws SQLException{
        String sql = "DELETE FROM Appointments WHERE appointmentId = ?";
        try(Connection  conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        }
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList();
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM appointments");
            ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentId(rs.getInt(1));
                    appointment.setPatientId(rs.getInt(2));
                    appointment.setDoctorId(rs.getInt(3));
                    appointment.setAppointmentDate(rs.getString(4));
                    appointment.setNotes(rs.getString(5));
                    appointments.add(appointment);
        }
        }
        return appointments;
    }

}
