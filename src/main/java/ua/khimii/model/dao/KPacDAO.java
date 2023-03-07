package ua.khimii.model.dao;

import ua.khimii.model.ManagerDB;
import ua.khimii.model.entity.KPac;
import org.springframework.stereotype.Repository;
import ua.khimii.model.entity.filterEntity.SelectAndFilterKPac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KPacDAO {
    private Connection getConnection() {
        Connection connection;
        try {
            connection = ManagerDB.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public List<KPac> getAllKPacs() {
        List<KPac> kPacs = new ArrayList<>();
        KPac kPac;
        String QUERY = "SELECT * FROM `k-pac`;";
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                kPac = new KPac(
                        resultSet.getInt("k_pac_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("creation_date")
                );
                kPac.setDelete("delete");
                kPacs.add(kPac);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return kPacs;
    }

    public void delete(int id) {
        String QUERY = "DELETE FROM knowledge_package.`k-pac` WHERE k_pac_id=?;";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void create(KPac kPac) {
        String QUERY = "INSERT INTO knowledge_package.`k-pac`(title, description, creation_date) VALUES (?, ?, ?)";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, String.valueOf(kPac.getTitle()));
            preparedStatement.setString(2, String.valueOf(kPac.getDescription()));
            preparedStatement.setString(3, String.valueOf(kPac.getDate_of_creation()));
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public List<KPac> filterAndSorting(String filterBy, String sortBy) {
        List<KPac> kPacs = new ArrayList<>();
        KPac kPac;
        StringBuilder preparedQuery = new StringBuilder("SELECT * FROM `k-pac`");
        if (sortBy == null || sortBy.equals("")) {
            if (filterBy == null || filterBy.equals("")) {
                return getAllKPacs();
            } else {
                switch (filterBy) {
                    case "ascending":
                        preparedQuery.append(" ORDER BY k_pac_id");
                        break;
                    case "descending":
                        preparedQuery.append(" ORDER BY k_pac_id DESC");
                        break;
                    default:
                        return getAllKPacs();
                }
            }
        } else {
            switch (sortBy) {
                case "id" -> preparedQuery.append(" ORDER BY k_pac_id");
                case "title" -> preparedQuery.append(" ORDER BY title");
                case "description" -> preparedQuery.append(" ORDER BY description");
                case "creation date" -> preparedQuery.append(" ORDER BY creation_date");
            }
        }
        switch (filterBy) {
            case "descending":
                preparedQuery.append(" DESC");
                break;
            case "ascending":
                break;
            default:
                return getAllKPacs();
        }
        try (Connection con = getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(String.valueOf(preparedQuery));
            System.out.println(preparedQuery);
            while (resultSet.next()) {
                kPac = new KPac(
                        resultSet.getInt("k_pac_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("creation_date")
                );
                kPacs.add(kPac);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException();
        }
        return kPacs;
    }

    public List<KPac> getKPacSetById(int id) {
        List<KPac> kPacs = new ArrayList<>();
        KPac kPac;
        String query = "SELECT * FROM `k-pac` WHERE k_pac_id " +
                "IN (SELECT k_pac_id FROM `k-link` WHERE k_pac_set_id=?);";
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kPac = new KPac(
                        resultSet.getInt("k_pac_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("creation_date")
                );
                kPacs.add(kPac);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return kPacs;
    }
}
