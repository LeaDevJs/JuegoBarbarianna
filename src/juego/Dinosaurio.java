package juego;
import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
public class Dinosaurio {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private double angulo;
	private int piso;
	private Image imagen;	
	private int velocidad;	
	private boolean laserlanzado;
	
	public Dinosaurio(int x, int y, Image imagen) {
		Random  rnd = new Random();
		this.x=x;
		this.y=y;
		ancho=105;
		alto=61;
		angulo=0;
		piso=4;		
		laserlanzado=false;
		this.imagen=imagen;
		this.velocidad= rnd.nextInt(3)+1; //valor aleatorio real [1 a 3]
	}
	public boolean isLaserLanzado() {
		return laserlanzado;
	}
	public void setLaserLanzado(boolean laserExiste) {
		this.laserlanzado = laserExiste;
	}
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen, this.x, this.y, angulo);
	}
	public Image setImage(Image imagen) {
		return this.imagen=imagen;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void moverIzq() {
		this.x-=velocidad;
	}
	public void moverDer() {
		this.x+=velocidad;
	}
	public int getPiso() {
		return piso;
	}
	public int bajapiso() {
		return piso-=1;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	
}
