package repository.book.DAO;

import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOGen<T> {
    private final Connection connection;
    protected static final Logger LOGGER = Logger.getLogger(DAOGen.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public DAOGen() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();
    }


    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }


    private String createInsertQuery(T t) {

        StringBuilder sb = new StringBuilder();
        String query = "INSERT INTO " + type.getSimpleName() + "(";
        Object object=t;
        for (Field field : object.getClass().getDeclaredFields()) {
            if(field.getName().equals(type.getSimpleName().toLowerCase()+"Id")) {
                continue;
            }
            else
                query+=field.getName()+",";
        }

        query=query.substring(0, query.length() - 1);
        query+=")";
        query+=" VALUES (";
        for (Field field : object.getClass().getDeclaredFields()) {
            if(field.getName().equals(type.getSimpleName().toLowerCase()+"Id")) {
                continue;
            }
            else
                query+="?,";
        }
        query=query.substring(0, query.length() - 1);
        query+=")";
        sb.append(query);
        return sb.toString();
    }


    public List<T> findAll() {

        String query = createSelectQuery("1");
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        }
        return null;
    }


    public T findById(int id) {

        String query = createSelectQuery(type.getSimpleName().toLowerCase()+"Id");// in loc de id
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        return null;

    }


    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }


    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int k=1;
        ResultSet resultSet = null;
        String query=createInsertQuery(t);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for(Field field : t.getClass().getDeclaredFields()) {
                if(field.getName().equals(type.getSimpleName().toLowerCase()+"Id")) {
                    continue;
                }
                else {
                    field.setAccessible(true);
                    statement.setObject(k, field.get(t).toString());
                    k++;
                }
            }

            statement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public T update(T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        int k=1;
        String query = createUpdateQuery();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            int id=0;
            for(Field field : t.getClass().getDeclaredFields()) {
                if(field.getName().equals(type.getSimpleName().toLowerCase()+"Id")) {
                    field.setAccessible(true);
                    id=Integer.parseInt(field.get(t).toString());
                }
                field.setAccessible(true);
                statement.setObject(k,field.get(t).toString());
                k++;
            }
            statement.setObject(k,id);
            System.out.println(statement);
            statement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public boolean delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int k=1;
        String query = createDeleteQuery(t);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for(Field field : t.getClass().getDeclaredFields()) {
                if(field.getName().equals(type.getSimpleName().toLowerCase()+"Id")) {
                    field.setAccessible(true);
                    statement.setObject(1,field.get(t).toString());
                    k++;
                }
                else {
                    continue;

                }
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                sb.append(field.getName()).append("=?,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE " + type.getSimpleName().toLowerCase() + "Id=?");
        return sb.toString();
    }

    private String createDeleteQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append(type.getSimpleName().toLowerCase()).append("Id=?");
        return sb.toString();
    }

}

