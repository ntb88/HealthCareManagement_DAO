package HealthCareDAO101.dao;

import HealthCareDAO101.Model.Doctor;
import HealthCareDAO101.jdbc.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    //create doctor
    public void createDoctor(Doctor doctor) throws SQLException  {
        String sql = "insert into Doctors (doctorId, firstName, lastName, specialty, email) values(?,?,?,?,?)";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctor.getDoctorId());
            ps.setString(2, doctor.getFirstName());
            ps.setString(3, doctor.getLastName());
            ps.setString(4, doctor.getSpecialty());
            ps.setString(5, doctor.getEmail());
            ps.executeUpdate();
        }
    }


    //create doc by id
    public Doctor getDoctorById(int doctorID) throws SQLException {
        String sql = "select * from Doctors where doctorId = ?";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, doctorID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setFirstName(rs.getString("firstName"));
                doctor.setLastName(rs.getString("lastName"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setEmail(rs.getString("email"));
                return doctor;
            }
        }
        return null;
    }


    public void updateDoctor(Doctor doctor) throws SQLException{
        String sql = "Update Doctors set firstName = ?,lastName = ?,specialty = ?,email = ? where doctorId = ?";
        try (Connection conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getSpecialty());
            ps.setString(4, doctor.getEmail());
            ps.setInt(5, doctor.getDoctorId());
            ps.executeUpdate();
        }
    }

    public void deleteDoctor(int doctorID) throws SQLException{
        String sql = "delete from Doctors where doctorId = ?";
        try(Connection conn = DataBaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorID);
            ps.executeUpdate();
        }
    }



    List<Doctor> getAllDoctors() throws SQLException{
        String sql = "select * from Doctors";
        List<Doctor> doctors = new ArrayList<>();
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setFirstName(rs.getString("firstName"));
                doctor.setLastName(rs.getString("lastName"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setEmail(rs.getString("email"));
                doctors.add(doctor);
            }
        }
        return doctors;
    }


}
