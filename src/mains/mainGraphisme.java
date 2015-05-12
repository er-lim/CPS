package mains;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import services.IHotelVilleService;
import services.IMineService;
import services.IMoteurJeuService;
import services.IMurailleService;
import services.IRouteService;
import services.IVillageoisService;

import enums.ECommande;
import enums.ERace;

import implementations.HotelVilleImplem;
import implementations.MineImplem;
import implementations.MoteurJeuImplem;
import implementations.MurailleImplem;
import implementations.RouteImplem;
import implementations.VillageoisImplem;

public class mainGraphisme extends JPanel{
	
	/*public void paintHashMap(Graphics g,IMoteurJeuService MJ) {
		Graphics2D g2d = (Graphics2D) g;
		
	
		 //TOUT BINDER
		 
		 
		 
			HashMap<Object,Point> s = MJ.positions();
			
			//Collection<Point> pos = s.values();
			ArrayList<Point> pos = new ArrayList<Point>(s.values());

			int i =0;
			
			while(i<pos.size()){
				System.out.println("salut");
				g.drawRect(pos.get(i).x,pos.get(i).y, 10, 10);
			}
			
		
			g2d.setColor(Color.RED);
		g2d.fillOval(0, 0, 30, 30);
		g2d.drawOval(0, 50, 30, 30);		
		g2d.fillRect(50, 0, 30, 30);
		g2d.drawRect(50, 50, 30, 30);

		g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));
	}*/
	
	public static void main(String[] args) {
		
		
		IMoteurJeuService moteurJeu = new MoteurJeuImplem();
		
	
		IHotelVilleService hotelDeVille = new HotelVilleImplem();
		IHotelVilleService hotelDeVille2 = new HotelVilleImplem();

		hotelDeVille.init(50, 50, ERace.HUMAIN);
		hotelDeVille2.init(50, 50, ERace.ORC);
		
		ArrayList<IVillageoisService> villageois= new ArrayList<IVillageoisService>();
		ArrayList<Point> positionsVillageois = new ArrayList<Point>();
		IVillageoisService v = new VillageoisImplem();
		IVillageoisService v1 = new VillageoisImplem();
		IVillageoisService v2 = new VillageoisImplem();
		IVillageoisService v3 = new VillageoisImplem();

		v.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v1.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v2.init(ERace.ORC, 10, 10, 4, 3, 60);
		v3.init(ERace.ORC, 10, 10, 4, 3, 60);
//
//moteurJeu.positions
positionsVillageois.add( new Point(500-60,60));
positionsVillageois.add( new Point(500+60,60));
positionsVillageois.add( new Point(500-60, 940));
positionsVillageois.add(new Point(500+60, 940));
	
villageois.add(v);
	villageois.add(v1);
		villageois.add(v2);
		   villageois.add(v3);


ArrayList<IMineService> mines=new ArrayList<IMineService>();
ArrayList<Point> positionsMines = new ArrayList<Point>();
IMineService m = new MineImplem();
IMineService m1 = new MineImplem();
IMineService m2 = new MineImplem();
IMineService m3 = new MineImplem();
m.init(50, 50);
m1.init(50, 50);
m2.init(50, 50);
m3.init(50, 50);
//
positionsMines.add( new Point(10, 10));
positionsMines.add( new Point(1000-60, 10));
positionsMines.add( new Point(1000-60, 1000-60));
positionsMines.add( new Point(10,1000-60));

mines.add(m);
mines.add(m1);
mines.add(m2);
mines.add(m3);


ArrayList<IRouteService> routes=new ArrayList<IRouteService>();
ArrayList<Point> positionsRoutes = new ArrayList<Point>();
IRouteService r = new RouteImplem();
IRouteService r1 = new RouteImplem();

r.init(1000, 50);
r1.init(50 , 1000 - 150);
//
positionsRoutes.add( new Point(0, 1000/2));
positionsRoutes.add( new Point(1000/2, 80));
//
routes.add(r);
routes.add(r1);

ArrayList<IMurailleService> murailles=new ArrayList<IMurailleService>();
IMurailleService mu = new MurailleImplem();
//
mu.init(50, 50, 100);
//
ArrayList<Point> positionsMurailles = new ArrayList<Point>();
positionsMurailles.add( new Point(1000/2, 1000/2));
//
murailles.add(mu);

		
		
	
		moteurJeu.init(1664,1000,1000);
		
		moteurJeu.bindMine(mines, positionsMines);
		moteurJeu.bindMuraille(murailles, positionsMurailles);
		moteurJeu.bindRoute(routes, positionsRoutes);
		moteurJeu.bindVillageois(villageois, positionsVillageois);
		
		
		moteurJeu.bindHotelVille(hotelDeVille, hotelDeVille2, new Point(500,60), new Point(500,940));
		
	
		ThreadListener t = new ThreadListener(moteurJeu);
		t.start();
		
		while (!moteurJeu.estFini())
		{
			synchronized (moteurJeu) {
				System.out.println("Entrer une commande Villageois HUMAIN et argument");
				Scanner s = new Scanner(System.in);
				System.out.println("Entrer une commande Villageois ORC et argument");
				Scanner s1 = new Scanner(System.in);
				
				moteurJeu.pasJeu(ECommande.RIEN, ECommande.RIEN,0, 0, 0, 0);
				System.out.println(moteurJeu.pasJeuCourant());
				/*JFrame frame = new JFrame("Mini Tennis");
				frame.add( new mainGraphisme());
				frame.setSize(300, 300);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
			}
	
	//		moteurJeu.showMap();
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(moteurJeu.resultatFinal());
	}
	
	
	
	
	
}
