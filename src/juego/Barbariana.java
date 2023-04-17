package juego;
import java.awt.Image;
import java.awt.Color;
import entorno.Entorno;
public class Barbariana {
	private double x;
	private double y;	
	private int ancho;
	private double escala;
	private double angulo;
	private int piso;
	private Image imagenb;
	private int vidas;	
	
	public Barbariana (double d, double e,Image imagen) {
		this.x=d;
		this.y=e;		
		ancho= 50;
		angulo=0;
		piso=1;
		this.imagenb = imagen;
		vidas=3;
	}
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(this.imagenb, this.x, this.y,this.angulo,this.escala);
	}
	public void dibujarvidabarbariana(Entorno entorno,int vidas) {
		entorno.cambiarFont("Copperplate Gothic Bold",20, Color.white);
		entorno.escribirTexto("Nivel de Vida",322,20);	
		if(vidas==3) {
			entorno.dibujarRectangulo(350,30, 50,5, angulo, Color.red);
			entorno.dibujarRectangulo(400,30, 50,5, angulo, Color.red);
			entorno.dibujarRectangulo(450,30, 50,5, angulo, Color.red);
			}
		else if(vidas==2) {
			entorno.dibujarRectangulo(350,30, 50,5, angulo, Color.red);
			entorno.dibujarRectangulo(400,30, 50,5, angulo, Color.red);			
			}
		else if(vidas==1)
			entorno.dibujarRectangulo(350,30, 50,5, angulo, Color.red);	
		else if(vidas==0)
			entorno.dibujarRectangulo(350,30,2,5, angulo, Color.red);
	}
	public Image setImage(Image imagen) {
		return this.imagenb=imagen;
	}
	public void setEscala(double escala) {
		this.escala = escala;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getAncho() {		
		return ancho;
	}	
	
	public void moverIzq() {
		this.x-=3;
	}
	public void moverDer() {
		this.x+=3;
	}
	public int bajapiso() {
		return piso-=1;
	}
	public int getPiso() {
		return piso;
	}
	public int subepiso() {
		return piso+=1;
	}
	public int getVidas() {
		return vidas;
	}
	public void pierdeVida() {
		vidas-=1;
	}
}