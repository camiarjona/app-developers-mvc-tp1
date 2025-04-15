package org.example.project.persistence;

import connection.DatabaseConnection;
import org.example.project.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private Connection connection;

    public ProjectDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void add(Project project) throws SQLException {
        String sql = "INSERT INTO projects (pr_name, pr_description) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, project.getProyectName());
            ps.setString(2, project.getDescription());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    project.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Project> getProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT project_id, pr_name, pr_description FROM projects";

        try (PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("project_id"),
                        rs.getString("pr_name"),
                        rs.getString("pr_description")
                ));
            }
        }
        return projects;
    }

    public void updateName(Project project) throws SQLException {
        String sql = "UPDATE projects SET pr_name = ? WHERE project_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, project.getProyectName());
            ps.setInt(2, project.getId());
            ps.executeUpdate();
        }
    }

    public void updateDescription(Project project) throws SQLException {
        String sql = "UPDATE projects SET pr_description = ? WHERE project_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, project.getDescription());
            ps.setInt(2, project.getId());
            ps.executeUpdate();
        }
    }

    public void deleteProject(Project project) throws SQLException {
        String sql = "DELETE FROM projects WHERE project_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, project.getId());
            ps.executeUpdate();
        }
    }
}
