package ua.khimii.model.dao;

import org.springframework.stereotype.Repository;
import ua.khimii.model.ManagerDB;
import ua.khimii.model.entity.KPacSet;
import ua.khimii.model.entity.filterEntity.SelectAndFilterKPac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KPacSetDAO {
    private Connection getConnection() {
        Connection connection;
        try {
            connection = ManagerDB.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public List<KPacSet> getALl() {
        List<KPacSet> kPacSets = new ArrayList<>();
        KPacSet kPacSet;
        String query = "SELECT * FROM `k-pac_set`;";
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                kPacSet = new KPacSet(
                        resultSet.getInt("k_pac_set_id"),
                        resultSet.getString("set_title")
                );
                kPacSet.setDelete("delete");
                kPacSets.add(kPacSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kPacSets;
    }

    public void delete(int id) {
        String QUERY = "DELETE FROM knowledge_package.`k-pac_set` WHERE k_pac_set_id=?;";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<KPacSet> filterAndSort(SelectAndFilterKPac filterKPacSet) {
        List<KPacSet> filtered = new ArrayList<>();
        KPacSet kPacSet;
        String[] filterBy = filterKPacSet.getSortingTitleArray();
        String sortBy = filterKPacSet.getFilter();

        StringBuilder preparedQuery = new StringBuilder("SELECT * FROM `k-pac_set`");
        if (filterBy.length == 0) {
            switch (sortBy) {
                case "ascending":
                    preparedQuery.append(" ORDER BY k_pac_set_id");
                    break;
                case "descending":
                    preparedQuery.append(" ORDER BY k_pac_set_id DESC");
                    break;
                default:
                    return getALl();
            }
        } else {
            for (String s : filterBy) {
                if (s.equals("id")) {
                    preparedQuery.append(" ORDER BY k_pac_set_id");
                }
                if (s.equals("title")) {
                    preparedQuery.append(" ORDER BY set_title");
                }
            }
            if (sortBy.equals("descending")) {
                preparedQuery.append(" DESC");
            }
        }
        try (Connection con = getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(String.valueOf(preparedQuery));
            while (resultSet.next()) {
                kPacSet = new KPacSet(
                        resultSet.getInt("k_pac_set_id"),
                        resultSet.getString("set_title")
                );
                filtered.add(kPacSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filtered;
    }

    public void createNewKPacSet(String title) {
        String query = "insert into `k-pac_set`(set_title) VALUES (?);";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public int getLastKPacSetId() {
        int lastId = 1;
        String query = "SELECT k_pac_set_id FROM `k-pac_set` ORDER BY k_pac_set_id DESC LIMIT 1;";
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lastId = resultSet.getInt("k_pac_set_id");
            }
            statement.close();
            return lastId;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void save(int id, String[] kPacs) {
        int i = 1, j = 1;
        StringBuilder query = new StringBuilder("INSERT INTO `k-link` (k_pac_id, k_pac_set_id) VALUES");
        for (String s : kPacs) {
            if (i == kPacs.length) {
                query.append(" (?, ").append(id).append(");");
            } else {
                query.append(" (?, ").append(id).append("),");
            }
            i++;
        }
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query));
            for (String s : kPacs) {
                preparedStatement.setString(j, s);
                j++;
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
