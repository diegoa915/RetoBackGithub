package com.init.products.entitys;

public class Cliente {

	private String cedula;
	
	private String direccion;
		

	public Cliente(String cedula, String direccion, int horaActual) {
		
		this.cedula = cedula;
		this.direccion = direccion;		
	}

	public Cliente() {
		
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
