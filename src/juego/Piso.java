package juego;
import java.awt.Color;
import entorno.Entorno;
public class Piso {
private double x;
private double y;
private double ancho;
private double alto;
public Piso(double x, double y) {
	this.x=x;
	this.y=y;
	ancho=690;
	alto=10;
}
public void setAncho(double ancho) {
	this.ancho = ancho;
}

public void dibujarse(Entorno entorno) {
	entorno.dibujarRectangulo(this.x, this.y, ancho, alto, 0, Color.white);
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
}
