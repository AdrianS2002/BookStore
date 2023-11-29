package repository.user;
import model.Book;
import model.Role;
import model.User;
import model.builder.BookBuilder;
import model.builder.UserBuilder;
import model.validator.Notification;
import repository.security.RightsRolesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;
import static java.util.Collections.singletonList;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }
//**

    //adminul poate vedea toti userii  45
    @Override
    public List<User> findAll() {
        String sql = "SELECT * from user";

        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return users;
    }

    private Long findRoleIdForUser(Long userId) throws SQLException {
        String sql = "SELECT role_id FROM user_role WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getLong(1);
    }




  //**  //trebuuie schimbat  concatenarea de stringuri   Nu ar trebui s afolosim Optional<User>
  @Override
  public Notification<User> findByUsernameAndPassword(String username, String password) {
      Notification<User> findByUsernameAndPasswordNotification = new Notification<>();

      try {
          String fetchUserSql = "SELECT * FROM `" + USER + "` WHERE `username`=? AND `password`=?";

          try (PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSql)) {
              preparedStatement.setString(1, username);
              preparedStatement.setString(2, password);

              try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                  if (userResultSet.next()) {
                      User user = new UserBuilder()
                              .setUsername(userResultSet.getString("username"))
                              .setPassword(userResultSet.getString("password"))
                              .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                              .build();

                      findByUsernameAndPasswordNotification.setResult(user);
                  } else {
                      findByUsernameAndPasswordNotification.addError("Invalid username or password!");
                      return findByUsernameAndPasswordNotification;
                  }
              }
          }

      } catch (SQLException e) {
          System.out.println(e.toString());
          findByUsernameAndPasswordNotification.addError("Something is wrong with the Database!");
      }

      return findByUsernameAndPasswordNotification;
  }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }



    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Long id, Long userRole) {
        try {
            String sql = "UPDATE user_role SET role_id = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userRole);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeById(Long id) {
        try{
            String sql = "DELETE from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //** concatenare de stringuri
 ///
    @Override
    public boolean existsByUsername(String username) {
        try {
            String fetchUserSql = "SELECT * FROM `" + USER + "` WHERE `username`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSql);
            preparedStatement.setString(1, username);

            ResultSet userResultSet = preparedStatement.executeQuery();
            return userResultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    private User getUserFromResultSet(ResultSet resultSet) throws SQLException{
        return new UserBuilder()
                .setId(resultSet.getLong("id"))
                .setUsername(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .setRoles(rightsRolesRepository.findRolesForUser(resultSet.getLong("id")))
                .build();
    }

}