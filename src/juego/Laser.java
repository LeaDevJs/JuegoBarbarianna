package juego;
import java.awt.Image;
import entorno.Entorno;
public class Laser {	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image imagen;
	private boolean derecha;
	public Laser(double x, double y,boolean direccion, Image imagen) {		
		this.x=x;
		this.y=y;
		ancho=55.0;
		alto=35.0;
		this.derecha=direccion;
		this.imagen=imagen;
	}
	
	public boolean isDerecha() {
		return derecha;
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
		this.x+=4;
	}
	public void moverIzquierda() {
		this.x-=4;
	}
}

