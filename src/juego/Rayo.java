package juego;
import java.awt.Image;
import entorno.Entorno;
public class Rayo{
private double x;
private double y;
private double ancho;
private double alto;
private Image imagen;
public Rayo(double x, double y, Image imagen) {
	
	this.x=x;
	this.y=y;
	ancho=45.0;
	alto=15.0;
	this.imagen=imagen;
}


public double getX() {
	return x;
}

public double getY() {
	return y;
}

public double getAncho() {
	return ancho;
}

public double getAlto() {
	return alto;
}

public void dibujarse(Entorno entorno) {
	entorno.dibujarImagen(imagen, this.x, this.y, 0);
}
public void moverDerecha() {
	this.x+=3;
}
public void moverIzquierda() {
	this.x-=3;
}
}