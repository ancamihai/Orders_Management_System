package org.example.business_layer.table_generators;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * JTable generator
 */
public class AbstractTableGenerator<T> {

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractTableGenerator() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * @param resultSet the ResultSet obtained from the findAll Query
     * @return the header of the JTable
     */
    public Object[] generateColumnNames(List<T> resultSet) {
        try {
            Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            int size=type.getDeclaredFields().length;
            Object[] columnNames =new Object[size];
            for (int i=0; i<size; i++) {
                Field field=type.getDeclaredFields()[i];
                Object object=field.getName();
                columnNames[i]=object;
            }
            return columnNames;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param resultSet the ResultSet obtained from the findAll Query
     * @return the data which will populate the JTable
     */
    public Object[][] generateRowData(List<T> resultSet) {
        try {
            Object[][] rowData = new Object[resultSet.size()][];
            for (int i = 0; i < resultSet.size(); i++) {
                Object object = resultSet.get(i);
                rowData[i]=new Object[object.getClass().getDeclaredFields().length];
                for (int j = 0; j < object.getClass().getDeclaredFields().length; j++) {
                    Field field = object.getClass().getDeclaredFields()[j];
                    field.setAccessible(true);
                    Object value = field.get(object);
                    rowData[i][j]=value;
                }
            }
            return rowData;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
