package br.pucrio.inf.lac.invisiblebattler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import ContextNetGeo.CtxCoordinate;
import ContextNetGeo.Polygon;

public class Area implements Serializable {

	Vector<Integer> xs;
	Vector<Integer> ys;
	Polygon polygonA = GerarPoligonoA();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Area() {

	}

	public void addPoint() {

	}

	private static Polygon GerarPoligonoA() {
		// Poligono 1 - Externa
		ArrayList<CtxCoordinate> listaCoordenadasMaster = new ArrayList<CtxCoordinate>();

		listaCoordenadasMaster.add(new CtxCoordinate(30, 260));
		listaCoordenadasMaster.add(new CtxCoordinate(130, 350));
		listaCoordenadasMaster.add(new CtxCoordinate(250, 330));
		listaCoordenadasMaster.add(new CtxCoordinate(320, 180));
		listaCoordenadasMaster.add(new CtxCoordinate(250, 50));
		listaCoordenadasMaster.add(new CtxCoordinate(70, 60));
		listaCoordenadasMaster.add(new CtxCoordinate(30, 260));

		// Poligono 1 - Interna
		ArrayList<CtxCoordinate> listaCoordenadasEscravo = new ArrayList<CtxCoordinate>();
		listaCoordenadasEscravo.add(new CtxCoordinate(140, 260));
		listaCoordenadasEscravo.add(new CtxCoordinate(110, 150));
		listaCoordenadasEscravo.add(new CtxCoordinate(230, 210));
		listaCoordenadasEscravo.add(new CtxCoordinate(140, 210));
		listaCoordenadasEscravo.add(new CtxCoordinate(140, 260));

		Polygon polygon = new Polygon(listaCoordenadasMaster, listaCoordenadasEscravo);
		return polygon;
	}
}
