package juego;
import java.awt.Image;
import entorno.Entorno;
public class Computadora {
private double x;
private double y;
private Image imagen;
public Computadora(double x,double y, Image imagen){
	this.x=x;
	this.y=y;	
	this.imagen=imagen;
}
public void dibujarse(Entorno entorno) {
	entorno.dibujarImagen(this.imagen, this.x, this.y, 0);
}
public double extremoD() {
	return getX()+20;
}
public double extremoI() {
	return getX()-20;
}
public double getX() {
	return x;
}
public double getY() {
	return y;
}
}
