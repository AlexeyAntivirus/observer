package entities.impl;

import entities.Device;

import java.util.Date;
import java.util.Queue;

public class Battery implements Device {

	private int in;
	private String type;
	private String manufacturer;
	private String model;
	private Date productionDate;
	private int chargeVolume = 42;

	@Override
	public int getIn() {
		return in;
	}

	@Override
	public void setIn(int in) {
		this.in = in;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getManufacturer() {
		return manufacturer;
	}

	@Override
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public Date getProductionDate() {
		return productionDate;
	}

	@Override
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public int getChargeVolume() {
		return chargeVolume;
	}

	public void setChargeVolume(int chargeVolume) {
		this.chargeVolume = chargeVolume;
	}

	@Override
	public void fillAllFields(Queue<Field> fields) {

	}

	@Override
	public Queue<Field> getAllFields() {
		return null;
	}

	@Override
	public int compareTo(Device o) {
		return 0;
	}
}
