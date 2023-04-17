package juego;
import java.awt.Image;
import entorno.Entorno;
public class Fondo {
private int x;
private int y;
private Image imagen;
public Fondo (int x,int y, Image imagen) {
	this.x=x;
	this.y=y;
	this.imagen=imagen;
}
public void dibujarse(Entorno entorno) {
	entorno.dibujarImagen(imagen, this.x, this.y, 0, 1.28);
}
}
