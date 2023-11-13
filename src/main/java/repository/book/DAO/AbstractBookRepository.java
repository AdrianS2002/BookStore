package repository.book.DAO;
import repository.book.BookRepository;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AbstractBookRepository<T> implements BookRepository<T> {

    private Connection connection;
    private  Class<T> type=null;


    @SuppressWarnings("unchecked")
    public AbstractBookRepository(Connection connection) {

        this.connection = connection;
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
                query +=" ?,";
        }
        query=query.substring(0, query.length() - 1);
        query+=")";
        sb.append(query);
        return sb.toString();
    }

    @Override
    public List<T> findAll() {
        List<T> books = new ArrayList<T>();
        String sql = "SELECT * FROM "+type.getSimpleName();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            return createObjects(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }


    @Override
    public Optional<T> findById(Long id) {
        String sql = createSelectQuery("Id");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Optional<T>) createObjects(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                    System.out.println(value.getClass());
                    if(field.getType() == LocalDate.class)
                        method.invoke(instance, ((LocalDateTime)value).toLocalDate());
                    else
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

    @Override
    public boolean save(T t) {
        int k=1;
        String query=createInsertQuery(t);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for(Field field : t.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    statement.setObject(k, field.get(t));
                    k++;
                }
            int rowsInserted = statement.executeUpdate();

            return (rowsInserted != 1) ? false : true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAll() {
        String sql =createDeleteQuery();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

}