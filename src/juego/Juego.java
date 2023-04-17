package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;
import entorno.InterfaceJuego;
import javax.sound.sampled.Clip;
public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private int kills=0;
	// Variables y métodos propios de cada grupo
	// imagenes
	private Image imagenb;
	private Image imagenbizq;
	private Image rayo;
	private Image rayoizq;
	private Image lazerder;
	private Image lazerizq;
	private Image velociraptor;
	private Image velociraptorder;
	private Image computadora;
	private Fondo fondo;
	private Image castlevania;
	private Image barbaescder;
	private Image barbaescizq;
	//audio
	private Clip sRayoDino;
	private Clip musica;
	private Clip ganaste;
	private Clip perdiste;
	private Clip sSalto;
	private Clip rayolaser;
	
	
	private Barbariana barbariana;
	private boolean barbarianaderecha=true;
	private boolean rayoderecha;
	private Rayo rayito;	
	private boolean rayitoExiste=false;		
	private Piso plataforma1;
	private Piso plataforma2;
	private Piso plataforma3;
	private Piso plataforma4;
	private Computadora pc;	
	private Dinosaurio[] dinosaurios;	
	private Dinosaurio dino;
	private Laser[] lasers;
	private Laser laser;	
	private boolean creoDino=false;
	private int cantDinos=1;
	private int contadorTiempo=0;
	private int segundos=0;
	private int limite=30;
	private boolean salto;
	private boolean Jugando=true;
	private int contadorTiempoDinos=0;
	private int contadorTiempoLasers=0;
	private boolean escudoBarbariana=false;
	private int escudoDisponible=3;
	private boolean gano;
	
	//colision barbariana laser
	public static boolean colisionBarbarianaLaser(Barbariana barbariana, Laser laser) {
		if(barbariana.getX()>=(laser.getX()-laser.getAncho()/2)&&barbariana.getX()<=(laser.getX()+(laser.getAncho()/2)))
			if(barbariana.getY()>=(laser.getY()-laser.getAlto()/2)&&barbariana.getY()<=(laser.getY()+laser.getAlto()/2)) 
				return true;
		return false;
		
	}
	//colision rayo laser
	public static boolean colisionRayoLaser(Rayo rayito, Laser laser) {
		if(rayito.getX()>=(laser.getX()-laser.getAncho()/2)&&rayito.getX()<=(laser.getX()+laser.getAncho()/2)
			&&rayito.getY()>=(laser.getY()-laser.getAlto()/2)&&rayito.getY()<=(laser.getY()+laser.getAlto()/2)) 
				return true;
		return false;
		
	}
	//colision barbariana dino
	public static boolean colisionBarbarianaDino(Barbariana barbariana, Dinosaurio dino) {
		if(barbariana.getX()>=(dino.getX()-dino.getAncho()/2)&&barbariana.getX()<=(dino.getX()+dino.getAncho()/2)
		   &&(barbariana.getY()>(dino.getY()-dino.getAlto()/2)&&barbariana.getY()<(dino.getY()+dino.getAlto()/2)))
			return true;
		return false;
	}
	//colision rayo dino
	public static boolean colisionRayoDino(Rayo rayito, Dinosaurio dino) {
		if(rayito.getX()>=(dino.getX()-dino.getAncho()/2)&&rayito.getX()<=(dino.getX()+dino.getAncho()/2)&&
			rayito.getY()>=(dino.getY()-dino.getAlto()/2)&&rayito.getY()<=(dino.getY()+dino.getAlto()/2)) 
			return true;
	return false;
	}	
	//colision barbariana pc
	public boolean colisionBarbarianaPC(Barbariana barbariana, Computadora pc) {
		if(this.barbariana.getPiso()==4 && (this.barbariana.getX()<this.pc.extremoD()) && (this.barbariana.getX()>this.pc.extremoI())) {
			return true;
		}
		return false;
	}
	
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Castlevania Barbariana Viking Edition - Grupo 2", 800, 600);
		

		// Inicializar lo que haga falta para el juego
		this.castlevania= Herramientas.cargarImagen("./castlevania.png");
		this.fondo= new Fondo(400,300,this.castlevania);
		this.imagenb = Herramientas.cargarImagen("./barbariana.png");
		this.imagenbizq = Herramientas.cargarImagen("./barbarianaizq.png");
		this.rayo = Herramientas.cargarImagen("./rayoder.png");
		this.rayoizq = Herramientas.cargarImagen("./rayoizq.png");
		this.lazerder = Herramientas.cargarImagen("./laserder.png");
		this.lazerizq = Herramientas.cargarImagen("./laserizq.png");
		this.velociraptor= Herramientas.cargarImagen("./velociraptorizq.png");
		this.velociraptorder = Herramientas.cargarImagen("./velociraptor.png");
		this.barbaescder= Herramientas.cargarImagen("./barbarianaescudo.png");
		this.barbaescizq= Herramientas.cargarImagen("./barbarianaescudoizq.png");
		this.computadora = Herramientas.cargarImagen("./pc.png");
		this.musica= Herramientas.cargarSonido("./musica.wav");
		this.ganaste= Herramientas.cargarSonido("./ganaste.wav");
		this.perdiste= Herramientas.cargarSonido("./perdiste.wav");
		this.rayito =new Rayo(75,550,rayo);
		this.barbariana= new Barbariana(75,550,this.imagenb);
		this.plataforma1= new Piso(350,this.barbariana.getY()+35);
		this.plataforma2= new Piso(340,this.barbariana.getY()-100);
		this.plataforma3= new Piso(450,this.barbariana.getY()-250);
		this.plataforma4= new Piso(340,this.barbariana.getY()-400);
		this.plataforma1.setAncho(900);
		this.pc = new Computadora(400,this.barbariana.getY()-450,this.computadora);	
		this.dino= new Dinosaurio(0,120,velociraptorder);
		this.dinosaurios = new Dinosaurio[8];
		this.dinosaurios[0]=this.dino;
		this.lasers=new Laser[8];

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y
	 * por lo tanto es el método más importante de esta clase. Aquí se debe
	 * actualizar el estado interno del juego para simular el paso del tiempo
	 * (ver el enunciado del TP para mayor detalle).
	 */
	@Override
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		if(this.Jugando==true) {
			this.musica.loop(musica.LOOP_CONTINUOUSLY);
		}
		else {
			this.musica.stop();
		}
		if(this.contadorTiempoLasers>110) {
			this.contadorTiempoLasers=0;
		}		
		this.contadorTiempoLasers+=1;
		this.contadorTiempoDinos+=1;
		this.fondo.dibujarse(entorno);
		if(this.barbariana.getVidas()!=0)
			this.barbariana.dibujarvidabarbariana(entorno, barbariana.getVidas());
		this.plataforma2.dibujarse(entorno);
		this.plataforma1.dibujarse(entorno);
		this.plataforma3.dibujarse(entorno);
		this.plataforma4.dibujarse(entorno);		
		this.pc.dibujarse(entorno);
		this.barbariana.dibujarse(entorno);		
		if(this.Jugando) {
		//movimiento barbariana
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)&&barbariana.getX()>0+barbariana.getAncho()/2 &&!entorno.estaPresionada(entorno.TECLA_ABAJO)&&this.escudoBarbariana==false&&!entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			this.barbarianaderecha = false;
			this.barbariana.setImage(this.imagenbizq);
			this.barbariana.dibujarse(entorno);
			barbariana.moverIzq();
		}
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)&&barbariana.getX()<entorno.ancho()-barbariana.getAncho()/2&&!entorno.estaPresionada(entorno.TECLA_ABAJO)&&this.escudoBarbariana==false&&!entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			this.barbarianaderecha = true;
			this.barbariana.setImage(this.imagenb);
			barbariana.moverDer();
		}
		//metodo agacharse primer piso
				if(this.barbariana.getPiso()==1 && this.barbarianaderecha==false) {
					
					if (entorno.estaPresionada(entorno.TECLA_ABAJO) && this.escudoBarbariana==false) {
						this.barbariana.setY(555);
						this.barbariana.setImage(imagenbizq);						
						this.barbariana.setEscala(0.7);						
					}
					else { if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(550);
						this.barbariana.setImage(imagenbizq);						
						this.barbariana.setEscala(1);
						}
					}
				}
				if(this.barbariana.getPiso()==1 && this.barbarianaderecha==true && this.escudoBarbariana==false) {
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						this.barbariana.setY(555);
						this.barbariana.setImage(imagenb);						
						this.barbariana.setEscala(0.7);						

					}
					else { if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(550);
						this.barbariana.setImage(imagenb);						
						this.barbariana.setEscala(1);}		
					}
				}
				//metodo agacharse segundo piso
				if(this.barbariana.getPiso()==2 && this.barbarianaderecha==false && this.escudoBarbariana==false) {					
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						this.barbariana.setY(419);
						this.barbariana.setImage(imagenbizq);						
						this.barbariana.setEscala(0.7);						
					}
					else { if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(414);
						this.barbariana.setImage(imagenbizq);						
						this.barbariana.setEscala(1);
						}
					}
				}
				if(this.barbariana.getPiso()==2 && this.barbarianaderecha==true && this.escudoBarbariana==false) {					
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						this.barbariana.setY(419);
						this.barbariana.setImage(imagenb);						
						this.barbariana.setEscala(0.7);						

					}
					else { if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(414);
						this.barbariana.setImage(imagenb);						
						this.barbariana.setEscala(1);}
					}
				}
				//metodo agacharse tercer piso
				if(this.barbariana.getPiso()==3 && this.barbarianaderecha==false && this.escudoBarbariana==false) {					
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						this.barbariana.setY(276);
						this.barbariana.setImage(imagenbizq);						
						this.barbariana.setEscala(0.7);						
					}
					else { if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(271);
						this.barbariana.setImage(imagenbizq);						
						this.barbariana.setEscala(1);
						}

					}
				}
				if(this.barbariana.getPiso()==3 && this.barbarianaderecha==true && this.escudoBarbariana==false) {
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						this.barbariana.setY(276);
						this.barbariana.setImage(imagenb);
						this.barbariana.setEscala(0.7);						

					}
					else { if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(271);
						this.barbariana.setImage(imagenb);
						this.barbariana.setEscala(1);
						}
					}
				}
				//metodo agacharse cuarto piso
				if(this.barbariana.getPiso()==4 && this.barbarianaderecha==false && this.escudoBarbariana==false) {					
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						this.barbariana.setY(128);
						this.barbariana.setImage(imagenbizq);
						this.barbariana.setEscala(0.7);						
					}
					else { if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(123);
						this.barbariana.setImage(imagenbizq);
						this.barbariana.setEscala(1);
						}

					}
				}
				if(this.barbariana.getPiso()==4 && this.barbarianaderecha==true && this.escudoBarbariana==false) {
					if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						this.barbariana.setY(128);
						this.barbariana.setImage(imagenb);
						this.barbariana.setEscala(0.7);						

					}
					else {if( !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){
						this.barbariana.setY(123);
						this.barbariana.setImage(imagenb);
						this.barbariana.setEscala(1);
						}
					}
				}
		//salto de barbariana con sonido
				if (this.salto==true && this.escudoBarbariana==false) {
				this.contadorTiempo=this.contadorTiempo+1;
				if (this.contadorTiempo==this.limite) {
					this.segundos++;
					this.limite=this.limite+10;
					this.sSalto= Herramientas.cargarSonido("./salto.wav");	
				}
					if (this.segundos==1) {	
						this.sSalto.start();
						this.barbariana.setY(this.barbariana.getY()-10);
					}
					if (this.segundos==2) {
						this.barbariana.setY(this.barbariana.getY()+10);
					}
					if (this.segundos==3) {
						this.salto=false;
						this.segundos=0;	
					}
				}
				if (entorno.sePresiono(entorno.TECLA_ARRIBA)) 
					this.salto=true;
			//barbariana sube al siguiente piso
			if (entorno.sePresiono('u')) {
				if(barbariana.getX()> 720 && barbariana.getY() > plataforma2.getY()) {
					this.barbariana.setX(barbariana.getX()-100);
					this.barbariana.setY(barbariana.getY()-136);
					this.barbariana.setImage(this.imagenbizq);
					this.barbariana.subepiso();
					}
				if(barbariana.getX()<80 && barbariana.getY() > plataforma3.getY() && barbariana.getY() < plataforma2.getY() ) {	
					this.barbariana.setX(barbariana.getX()+100);
					this.barbariana.setY(barbariana.getY()-148);
					this.barbariana.setImage(this.imagenb);
					this.barbariana.subepiso();
				}
				if(barbariana.getX()>720 && barbariana.getY() > plataforma4.getY() && barbariana.getY() < plataforma3.getY() ) {
					this.barbariana.setX(barbariana.getX()-100);
					this.barbariana.setY(barbariana.getY()-148);
					this.barbariana.setImage(this.imagenbizq);	
					this.barbariana.subepiso();
				}
				}
			// barbariana cae a la plataforma inferior
			if(barbariana.getX()> 700 && barbariana.getY() < plataforma2.getY() && barbariana.getY() > plataforma3.getY()) {
				this.barbariana.setX(barbariana.getX()+20);
				this.barbariana.setY(550);
				this.barbariana.setImage(this.imagenb);
				this.barbariana.bajapiso();
			}
			if(barbariana.getX()<80 && barbariana.getY() < plataforma3.getY() && barbariana.getY() > plataforma4.getY() ) {
				this.barbariana.setX(barbariana.getX()-10);
				this.barbariana.setY(barbariana.getY()+148);
				this.barbariana.setImage(this.imagenbizq);
				this.barbariana.bajapiso();
			}
			if(barbariana.getX()>700 && barbariana.getY() < plataforma4.getY() ) {
				this.barbariana.setX(barbariana.getX()+10);
				this.barbariana.setY(barbariana.getY()+148);
				this.barbariana.setImage(this.imagenb);
				this.barbariana.bajapiso();
			}	
		// disparo rayo
		if (entorno.sePresiono(entorno.TECLA_ESPACIO) && this.rayitoExiste==false && this.escudoBarbariana==false && !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false){			
			this.rayitoExiste=true;
			if(this.barbarianaderecha) {
				this.rayito = new Rayo(barbariana.getX()  ,barbariana.getY()-10,this.rayo);
				this.rayoderecha=true;				
			}
			else {
				this.rayito = new Rayo(barbariana.getX()  ,barbariana.getY()-10,this.rayoizq);
				this.rayoderecha=false;				
			}
		}
		if (this.rayitoExiste) {
			rayito.dibujarse(entorno);
			if(this.rayoderecha)
				rayito.moverDerecha();
			else
				rayito.moverIzquierda();
		}
		//escudo de barbariana
		if(entorno.estaPresionada('d')&& this.barbarianaderecha==true && this.salto==false && !entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			this.barbariana.setImage(barbaescder);
			this.escudoBarbariana=true;
		}
		else {
			if(entorno.estaPresionada('d')&& this.barbarianaderecha==false && !entorno.estaPresionada(entorno.TECLA_ABAJO) && this.salto==false) {
				this.barbariana.setImage(barbaescizq);
				this.escudoBarbariana=true;
			}
			else {
				this.escudoBarbariana=false;
			}
		}
		
		//dinosaurios		
		for(int i=0; i<this.dinosaurios.length && this.cantDinos<5;i++) {					
			if (dinosaurios[i]==null && this.creoDino== true &&this.contadorTiempoDinos==120) {												
				this.dino= new Dinosaurio(0,120,velociraptorder);
				creoDino=false;
				this.dinosaurios[i]=this.dino;
				this.cantDinos+=1;
				this.contadorTiempoDinos=0;			
			}		
			creoDino=true;
		}
		//movimiento dino
		for(int i=0; i<this.dinosaurios.length;i++) {
			if(this.dinosaurios[i]!=null) {
				if(dinosaurios[i].getX()<entorno.ancho()-dino.getAncho()/2 && dinosaurios[i].getPiso()==4) {
						dinosaurios[i].moverDer();
				if(dinosaurios[i].getX()-(dinosaurios[i].getAncho()/2)> this.plataforma4.getAncho() && dinosaurios[i].getX() < entorno.ancho())  									
					dinosaurios[i].bajapiso();					
				}
		
				if( dinosaurios[i].getX()>0+dino.getAncho()/2 &&dinosaurios[i].getPiso()==3){
					dinosaurios[i].setY(270);
					dinosaurios[i].setImage(this.velociraptor);					
					dinosaurios[i].moverIzq();
		
				if(dinosaurios[i].getX()+(dinosaurios[i].getAncho()/2)-5<(entorno.ancho()-this.plataforma3.getAncho())) 				
					dinosaurios[i].bajapiso();		
										
				}			
				
				if(dinosaurios[i].getX()<entorno.ancho()-dino.getAncho()/2 && dinosaurios[i].getPiso()==2) {
					dinosaurios[i].setY(420);
					dinosaurios[i].setImage(this.velociraptorder);					
					dinosaurios[i].moverDer();
				}
				if(dinosaurios[i].getX()-(dinosaurios[i].getAncho()/2)+5> this.plataforma2.getAncho() && dinosaurios[i].getX()< entorno.ancho()) 				
					dinosaurios[i].bajapiso();		
				
				if(dinosaurios[i].getPiso()==1) {
					dinosaurios[i].setY(555);
					dinosaurios[i].setImage(this.velociraptor);					
					dinosaurios[i].moverIzq();
					if(dinosaurios[i].getX()<0) {
						dinosaurios[i].setImage(this.velociraptorder);
						dinosaurios[i].setPiso(4);
						dinosaurios[i].setY(120);							
					}
						
				}
				// laser dino
				if ( dinosaurios[i].isLaserLanzado()==false&&contadorTiempoLasers>100&&contadorTiempoLasers<110)
					if(dinosaurios[i].getPiso()==4) {
						this.laser= new Laser(dinosaurios[i].getX()+(dinosaurios[i].getAncho()/2),dinosaurios[i].getY()-10,true,this.lazerder);
						this.lasers[i]=this.laser;
						dinosaurios[i].setLaserLanzado(true);
						contadorTiempoLasers=0;
					}
					else if (dinosaurios[i].getPiso()==3){
						this.laser= new Laser(dinosaurios[i].getX()-(dinosaurios[i].getAncho()/2),dinosaurios[i].getY()-10,false,this.lazerizq);
						this.lasers[i]=this.laser;
						dinosaurios[i].setLaserLanzado(true);
						contadorTiempoLasers=0;
					}else if(dinosaurios[i].getPiso()==2) {
						this.laser= new Laser(dinosaurios[i].getX()+(dinosaurios[i].getAncho()/2),dinosaurios[i].getY()-10,true,this.lazerder);
						this.lasers[i]=this.laser;
						dinosaurios[i].setLaserLanzado(true);
						contadorTiempoLasers=0;
					}else if(dinosaurios[i].getPiso()==1){
						this.laser= new Laser(dinosaurios[i].getX()-(dinosaurios[i].getAncho()/2),dinosaurios[i].getY()-10,false,this.lazerizq);
						this.lasers[i]=this.laser;
						dinosaurios[i].setLaserLanzado(true);
						contadorTiempoLasers=0;
					}
				
					
				}
			  }
		//dibujar dino
				for(int i=0; i<this.dinosaurios.length;i++) {
					if(dinosaurios[i]!=null) {
						this.dinosaurios[i].dibujarse(entorno);			
						}			
				}
		//dibujar laser
				for(int i=0; i<this.lasers.length;i++) {
					if(lasers[i]!=null) {						
						lasers[i].dibujarse(entorno);
						if(lasers[i].isDerecha())
							lasers[i].moverDerecha();
						else
							lasers[i].moverIzquierda();
					}
				}			
		
		//colision rayo con pared 
		if( this.rayito.getX()>(entorno.ancho()-this.rayito.getAncho()/2) || this.rayito.getX()<(0+this.rayito.getAncho()/2)) {
			this.rayitoExiste=false;			
		}
		// colision laser con pared
		for(int i=0; i<this.lasers.length;i++) {			
			if( lasers[i]!=null&& (lasers[i].getX()>(entorno.ancho()-lasers[i].getAncho()/2) || lasers[i].getX()<(0+lasers[i].getAncho()/2))) { 
				lasers[i]=null;
				contadorTiempoLasers=0;
			if(dinosaurios[i]!=null)
				this.dinosaurios[i].setLaserLanzado(false);	
			}			
		}	
		//colision barbariana con laser
		for(int i=0; i<this.lasers.length;i++) {
			if(lasers[i]!=null) {
				if(colisionBarbarianaLaser(this.barbariana, this.lasers[i])&&this.escudoBarbariana==false && !entorno.estaPresionada(entorno.TECLA_ABAJO)) { 
					if(this.barbariana.getVidas()!=1) {
						this.barbariana.pierdeVida();
						this.lasers[i]=null;
					}	
					else  {						
						this.Jugando=false;	
						this.barbariana.pierdeVida();						
						lasers[i].dibujarse(entorno);
						this.perdiste.start();						
					}	
								
					
					}
				else {
					if(this.colisionBarbarianaLaser(this.barbariana, this.lasers[i])&&this.escudoBarbariana==true) {
						this.lasers[i]=null;
						contadorTiempoLasers=0;
						this.escudoDisponible-=1;
						if(this.escudoDisponible<0) {
							this.Jugando=false;					
							this.perdiste.start();
						}
					}
				}
			}
		}
		//colision rayo con laser
		for(int i=0; i<this.lasers.length && this.rayitoExiste;i++) {
			if(lasers[i]!=null) {
				if(colisionRayoLaser(this.rayito, this.lasers[i])) {
					this.rayolaser= Herramientas.cargarSonido("./rayolaser.wav");
					this.rayolaser.start();
					if(dinosaurios[i]!=null)
						dinosaurios[i].setLaserLanzado(false);	
				rayitoExiste=false;
				this.lasers[i]=null;
				contadorTiempoLasers=0;
				}
			}
		}
		
		
		//colision rayo con dino		
		for(int i=0; i<this.dinosaurios.length && this.rayitoExiste;i++) {
			if(dinosaurios[i]!=null) {
				if(colisionRayoDino(this.rayito, this.dinosaurios[i])) {
					this.sRayoDino=Herramientas.cargarSonido("./sonido.wav");
					this.sRayoDino.start();
					this.dinosaurios[i]=null;
					this.rayitoExiste=false;
					this.kills+=1;
					this.cantDinos-=1;
					this.contadorTiempoDinos=0; 
				}
			}
		}		
		// colision barbariana dino
		for(int i=0; i<this.dinosaurios.length;i++) {
			if(dinosaurios[i]!=null)
				if(colisionBarbarianaDino(this.barbariana, this.dinosaurios[i])) {
					if(this.barbariana.getVidas()!=1) {
						this.barbariana.pierdeVida();
						this.dinosaurios[i]=null;
						}
					else {						
						this.Jugando=false;
						this.barbariana.pierdeVida();					
						this.dinosaurios[i].dibujarse(entorno);
						this.perdiste.start();}
				}
		}		
		
		
		// colision barbariana pc
		if(colisionBarbarianaPC(this.barbariana, this.pc)) {			
			this.Jugando=false;
			this.gano=true;
			this.ganaste.start();
		}					
	}	
		
		//escribe la cantidad de escudos y los dinosaurios eliminados
		entorno.cambiarFont("Copperplate Gothic Bold", 20, Color.white);
		if(this.escudoDisponible>-1) {
		entorno.escribirTexto("Cant. escudos = "+this.escudoDisponible, 10, 50);
		}
		entorno.cambiarFont("Copperplate Gothic Bold", 20, Color.white);
		entorno.escribirTexto("Eliminados = "+this.kills, 600, 50);	
		
		
		
	//escribe si el jugador ganó o perdió	
	if(this.Jugando==false && this.gano==true) {
		entorno.cambiarFont(null,50, Color.GREEN);			
		entorno.escribirTexto("¡Has Ganado!", 100, 300);
	}else if(this.Jugando==false){
		barbariana.dibujarvidabarbariana(entorno,barbariana.getVidas());
		entorno.cambiarFont(null,80, Color.red);			
		entorno.escribirTexto("¡GAME OVER!", 100, 300);
	}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
