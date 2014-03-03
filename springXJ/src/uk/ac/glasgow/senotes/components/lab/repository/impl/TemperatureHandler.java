package uk.ac.glasgow.senotes.components.lab.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.glasgow.senotes.components.lab.repository.Reading;
import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureMetric;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureReport;

/**
 * A wrapper around the raw database management system for
 * reporting and querying temperature readings.
 * 
 * @author tws
 * 
 */
public class TemperatureHandler 
	implements TemperatureReport, TemperatureQuery {
	
	private String tableName = "temperature_reading";
		
	private DBMS dbms;
	
	public TemperatureHandler (DBMS dbms)
		throws SQLException{
		
		this.dbms = dbms;
		if (!temperatureTableExists())
			createTemperatureTable();
	}
	
	@Override
	public List<Reading<Temperature>> 
		getMostRecentTemperatureReadings(
			String sensorID,
			Integer n){
		
		List<Reading<Temperature>> result = 
			new ArrayList<Reading<Temperature>>();
		
		try {
			
			String condition =
				"sensor_id='"+sensorID+"' ORDER BY recorded DESC";
			
			ResultSet resultSet =
				dbms.getRows(tableName, condition);
			
			Integer count = 0;
			
			while (resultSet.next() && count < n){
				
				Reading<Temperature> reading = 
					parseRow(resultSet);
				
				result.add(reading);
				
				count ++;
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	private Reading<Temperature> 
		parseRow(ResultSet resultSet) throws SQLException{
		
		Timestamp timeStamp =
			resultSet.getTimestamp("recorded");
		
		Date recorded = 
			new Date(timeStamp.getTime());
		
		String sensorID = 
			resultSet.getString("sensor_id");
		
		Double value =
			resultSet.getDouble("temperature");
		
		TemperatureMetric metric = 
			TemperatureMetric.CELSIUS;
		
		Temperature temperature = 
			new Temperature(metric, value);
		
		Reading<Temperature> reading =
			new Reading<Temperature>(
				recorded, sensorID, temperature);
		
		return reading;
	}
	

	@Override
	public void
		recordTemperatureReading(Reading<Temperature> reading){
		
		Date recorded = reading.getRecorded();
		String sensorID = reading.getSensorID();
		Double value = reading.getSensorValue().getValue();
		
		try {
			Timestamp timeStamp = 
				new Timestamp(recorded.getTime());
			
			Map<String, Object> values = 
				new HashMap<String,Object>();
			
			values.put("recorded", timeStamp);
			values.put("sensor_id", sensorID);
			values.put("temperature",value);
			
			dbms.insertRow(tableName,values);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Boolean temperatureTableExists() 
		throws SQLException {
		return dbms.tableExists(tableName);
	}
	
	private void createTemperatureTable()
		throws SQLException {
		String fields =
			"id INT NOT NULL " +
			"  GENERATED ALWAYS AS " +
			"    IDENTITY (START WITH 1, INCREMENT BY 1) ," +
			"recorded TIMESTAMP," +
			"sensor_id VARCHAR(32)," +
			"temperature REAL," +
			"PRIMARY KEY(ID)";
		
		dbms.createTable(tableName, fields);
	}
	
}
