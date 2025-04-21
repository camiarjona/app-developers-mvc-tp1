package org.example.designer.persistence;

import org.example.connection.DatabaseConnection;
import org.example.designer.model.Designer;
import org.example.developer.model.Developer;
import org.example.project.model.Project;
import org.example.project.persistence.ProjectDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DesignerDAO {

    private final Connection connection;

    public DesignerDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void add(Designer designer, Project project) throws SQLException {
        String query = "insert into designers (des_dni, des_name, des_surname, des_age, specialty, project_id) values (?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, designer.getDni());
            ps.setString(2, designer.getName());
            ps.setString(3, designer.getSurname());
            ps.setInt(4, designer.getAge());
            ps.setString(5, designer.getSpecialty());

            if (designer.getProject() != null) {
                ps.setInt(6, designer.getProject().getId());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.executeUpdate();
        }
    }

    public List<Designer> getDesigners() throws SQLException {
        List<Designer> designers = new ArrayList<>();
        ProjectDAO projectDAO = new ProjectDAO();
        Map<Integer, Project> projects = projectDAO.getProjectsMap();

        String query = "select des_dni, des_name, des_surname, des_age, specialty, project_id from designers";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Designer designer = new Designer();

                designer.setDni(rs.getInt("des_dni"));
                designer.setName(rs.getString("des_name"));
                designer.setSurname(rs.getString("des_surname"));
                designer.setAge(rs.getInt("des_age"));
                designer.setSpecialty(rs.getString("specialty"));

                int projectId = rs.getInt("project_id");
                designer.setProject(projects.get(projectId));

                designers.add(designer);

            }

        }

        return designers;
    }

    public void updateName(Designer designer) throws SQLException {
        String query = "update designers set des_name=? where des_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, designer.getName());
            ps.setInt(2, designer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateSurname(Designer designer) throws SQLException {
        String query = "update designers set des_surname=? where des_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, designer.getSurname());
            ps.setInt(2, designer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateAge(Designer designer) throws SQLException {
        String query = "update designers set des_age=? where des_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, designer.getAge());
            ps.setInt(2, designer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateSpecialty(Designer designer) throws SQLException {
        String query = "update designers set specialty=? where des_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, designer.getSpecialty());
            ps.setInt(2, designer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateProject(Designer designer, Project project) throws SQLException {
        String query = "update designers set project_id=? where des_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, project.getId());
            ps.setInt(2, designer.getDni());
            ps.executeUpdate();
        }
    }

    public void deleteDesigner(Designer designer) throws SQLException {
        String query = "delete from designers where des_dni=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, designer.getDni());
            ps.executeUpdate();
        }
    }
}
