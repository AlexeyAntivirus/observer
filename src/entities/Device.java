package entities;

import java.util.Date;

public interface Device extends NetworkElement<Device> {
	int getIn();

	void setIn(int in);

	String getType();

	void setType(String type);

	String getManufacturer();

	void setManufacturer(String manufacturer);

	String getModel();

	void setModel(String model);

	Date getProductionDate();

	void setProductionDate(Date productionDate);

}