package HealthCareDAO101.dao;

import HealthCareDAO101.Model.Patient;
import HealthCareDAO101.jdbc.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    //create patient
    public void createPatient(Patient patient) throws SQLException {
        String sql = "insert into Patients (FirstName, LastName, DateOfBirth, Email, PhoneNumber) values(?,?,?,?,?)";
        try (Connection conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setString(3, patient.getDateOfBirth());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getPhoneNumber());
            ps.executeUpdate();
        }
    }

    public Patient getPatientById(int patientId) throws SQLException {
        String sql = "select * from Patients where PatientId = ?";
        try (Connection conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Patient patient = new Patient();
                    patient.setPatientId(rs.getInt("PatientId"));
                    patient.setFirstName(rs.getString("FirstName"));
                    patient.setLastName(rs.getString("LastName"));
                    patient.setDateOfBirth(rs.getString("DateOfBirth"));
                    patient.setEmail(rs.getString("Email"));
                    patient.setPhoneNumber(rs.getString("PhoneNumber"));
                    return patient;
                }
            }
        }
        return null;
    }

    public void updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE Patients SET FirstName = ?, LastName = ?, DateOfBirth = ?, Email = ?, PhoneNumber = ? WHERE PatientID = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getDateOfBirth());
            stmt.setString(4, patient.getEmail());
            stmt.setString(5, patient.getPhoneNumber());
            stmt.setInt(6, patient.getPatientId());
            stmt.executeUpdate();
        }
    }

    public void deletePatient(int patientId) throws SQLException {
        String query = "DELETE FROM Patients WHERE PatientID = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        String query = "SELECT * FROM Patients";
        List<Patient> patients = new ArrayList<>();
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("PatientID"));
                patient.setFirstName(rs.getString("FirstName"));
                patient.setLastName(rs.getString("LastName"));
                patient.setDateOfBirth(rs.getString("DateOfBirth"));
                patient.setEmail(rs.getString("Email"));
                patient.setPhoneNumber(rs.getString("PhoneNumber"));
                patients.add(patient);
            }
        }
        return patients;
    }

}
