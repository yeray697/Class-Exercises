package yrj.dam;

public interface IBufer {
	public void escribir(int valor);
	public int leer();
	public void mostrarEstado(String cadena);
	public String mostrarSalida();
}
