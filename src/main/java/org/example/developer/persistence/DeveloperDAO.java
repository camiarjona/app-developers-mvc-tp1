package org.example.developer.persistence;

import org.example.connection.DatabaseConnection;
import org.example.developer.model.Developer;
import org.example.project.model.Project;
import org.example.project.persistence.ProjectDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeveloperDAO {

    private Connection connection;

    public DeveloperDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void add(Developer developer, Project project) throws SQLException {
        String query = "insert into developers (dev_dni, dev_name, dev_surname, dev_age, main_language, project_id) values (?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, developer.getDni());
            ps.setString(2, developer.getName());
            ps.setString(3, developer.getSurname());
            ps.setInt(4, developer.getAge());
            ps.setString(5, developer.getMainLanguage());

            if (developer.getProject() != null) {
                ps.setInt(6, developer.getProject().getId());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.executeUpdate();
        }
    }

    public List<Developer> getDevelopers() throws SQLException {
        List<Developer> developers = new ArrayList<>();
        ProjectDAO projectDAO = new ProjectDAO();
        Map<Integer, Project> projects = projectDAO.getProjectsMap();

        String query = "select dev_dni, dev_name, dev_surname, dev_age, main_language, project_id from developers";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Developer developer = new Developer();
                developer.setDni(rs.getInt("dev_dni"));
                developer.setName(rs.getString("dev_name"));
                developer.setSurname(rs.getString("dev_surname"));
                developer.setAge(rs.getInt("dev_age"));
                developer.setMainLanguage(rs.getString("main_language"));

                int projectId = rs.getInt("project_id");
                developer.setProject(projects.get(projectId));

                developers.add(developer);

            }

        }

        return developers;
    }

    public void updateName(Developer developer) throws SQLException {
        String query = "update developers set dev_name=? where dev_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, developer.getName());
            ps.setInt(2, developer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateSurname(Developer developer) throws SQLException {
        String query = "update developers set dev_surname=? where dev_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, developer.getSurname());
            ps.setInt(2, developer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateAge(Developer developer) throws SQLException {
        String query = "update developers set dev_age=? where dev_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, developer.getAge());
            ps.setInt(2, developer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateMainLanguage(Developer developer) throws SQLException {
        String query = "update developers set main_language=? where dev_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, developer.getMainLanguage());
            ps.setInt(2, developer.getDni());
            ps.executeUpdate();
        }
    }

    public void updateProject(Developer developer, Project project) throws SQLException {
        String query = "update developers set project_id=? where dev_dni=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, project.getId());
            ps.setInt(2, developer.getDni());
            ps.executeUpdate();
        }
    }

    public void deleteDeveloper(Developer developer) throws SQLException {
        String query = "delete from developers where dev_dni=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, developer.getDni());
            ps.executeUpdate();
        }
    }
}
